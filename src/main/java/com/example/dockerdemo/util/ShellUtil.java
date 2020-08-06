package com.example.dockerdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/5/13 16:43
 */
public final class ShellUtil {

    private ShellUtil(){}

    private static final Logger LOG = LoggerFactory.getLogger(ShellUtil.class);

    /**
     * 同步执行shell命令
     * @param shell
     */
    public static void execShell(String shell) throws Exception {
        shell = shell + " >/dev/null 2>&1";
        LOGGER.error("shell = {}",shell);
        Process process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shell});
        String inStr = consumeInputStream(process.getInputStream());
        // 标准错误流（必须写在 waitFor 之前）
        String errStr = consumeInputStream(process.getErrorStream());
        int retCode = process.waitFor();
        LOG.info("inStr = {}", inStr);
        LOG.info("errStr = {}", errStr);
        LOG.info("shell exec result = {}", retCode);
    }


    /**
     *   消费inputstream，并返回
     */
    private static String consumeInputStream(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"GBK"));
        String s ;
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * 解压zip类型的文件
     * @param descFile
     * @param targetFolder
     */
    public static void unCompress(String descFile,String targetFolder,String format) throws Exception {
        String shell = null;
        if(FileCons.COMPRESS_TAR.equals(format)){
            shell = "tar -xvf " + descFile + " --strip-components 1 " + " -C "+ targetFolder;
        }else if(FileCons.COMPRESS_ZIP.equals(format)){
            shell = "unzip -j " + descFile + " -d "+ targetFolder;
        }else if(FileCons.COMPRESS_TAR_GZ.equals(format)){
            shell = "tar -zxvf " + descFile + " --strip-components 1 " + " -C "+ targetFolder;
        }
        ShellUtil.execShell(shell);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellUtil.class);

    public static String compress(String source,String dest,String format) throws Exception {
        Path sourcePath = Paths.get(source);
        String folderName = sourcePath.toFile().getName();
        String shell = "cd " + sourcePath.getParent().toFile().getPath() + " && ";
        if(FileCons.COMPRESS_ZIP.equals(format)){
            shell += "zip -r " + dest + " " + folderName;
        }else if(FileCons.COMPRESS_TAR.equals(format)){
            shell += "tar -cvhf " + dest + " "+ folderName;
        }else if(FileCons.COMPRESS_TAR_GZ.equals(format)){
            shell += "tar -zcvhf " + dest + " "+ folderName;
        }

        ShellUtil.execShell(shell);
        return dest;
    }

    /**
     * 删除文件
     * @param deletePath
     */
    public static void deleteFile(String deletePath) throws Exception {
        Path path = Paths.get(deletePath);
        if(path.toFile().isFile()){
            Files.delete(path);
        }else{
            String shell;
            shell = "rm -rf "+ deletePath;
            ShellUtil.execShell(shell);
        }
    }

}
