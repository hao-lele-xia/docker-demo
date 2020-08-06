package com.example.dockerdemo.util;

import org.apache.commons.compress.archivers.*;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

/**
 * @author chenhao
 * @description <p>
 * created by chenhao 2020/6/5 9:50
 */
public final class CompressUtil {

    private CompressUtil(){}

    private static ArchiveInputStream getAis(InputStream fis,String format) throws IOException {
        ArchiveInputStream ais = null;
        if(FileCons.COMPRESS_TAR_GZ.equals(format)){
            CompressorInputStream cis = new GzipCompressorInputStream(fis, true);
            ais = new TarArchiveInputStream(cis);
        }else if (FileCons.COMPRESS_TAR.equals(format)){
            ais = new TarArchiveInputStream(fis);
        }else if(FileCons.COMPRESS_ZIP.equals(format)){
            ais = new ZipArchiveInputStream(fis);
        }
        return ais;
    }

    /**
     * 解压Zip文件
     *
     * @param sourcePath
     *            需要解压的zip文件位置
     * @param destPath
     *            解压的目标位置
     */
    private static void unCompressFile(String sourcePath, String destPath, String format) throws IOException {
        ArchiveInputStream ais = null;
        try{
            InputStream fis = Files.newInputStream(Paths.get(sourcePath));
            ais = getAis(fis,format);
            File f = null;
            ArchiveEntry entry = null;
            while (Objects.nonNull(entry = ais.getNextEntry())) {
                if (ais.canReadEntryData(entry)) {
                    //解压时去掉第一层目录
                    String entryName = entry.getName();
                    String childName = entryName.substring(entryName.indexOf('/') + 1);
                    if(StringUtils.isBlank(childName)){
                        continue;
                    }
                    f = new File(destPath, childName);
                    writeStream(ais, f, entry);
                }
            }
        }finally {
            if(Objects.nonNull(ais)){
                ais.close();
            }
        }
    }

    private static void writeStream(ArchiveInputStream ais, File f, ArchiveEntry entry) throws IOException {
        if (entry.isDirectory()) {
            if (!f.isDirectory() && !f.mkdirs()) {
                throw new IOException("failed to create directory " + f);
            }
        } else {
            File parent = f.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                throw new IOException("failed to create directory " + parent);
            }
            try (OutputStream o = Files.newOutputStream(f.toPath())) {
                IOUtils.copy(ais, o);
            }
        }
    }

    public static void unCompress(String sourcePath, String destPath,String format) throws IOException {
        unCompressFile(sourcePath,destPath,format);
    }


    public static void compress(String sourceFolder, String dest,String format) throws IOException {
        createArchiveStream(sourceFolder, dest,format);
    }
    private static void createArchiveStream(String sourceFolder, String tarGzPath, String format) throws IOException{
        ArchiveOutputStream tarOs = null;
        try{
            OutputStream fos = new BufferedOutputStream(new FileOutputStream(tarGzPath));
            if(FileCons.COMPRESS_TAR_GZ.equals(format)){
                GZIPOutputStream gos = new GZIPOutputStream(fos);
                tarOs = new TarArchiveOutputStream(gos);
            }else if(FileCons.COMPRESS_TAR.equals(format)){
                tarOs = new TarArchiveOutputStream(fos);
            }else if(FileCons.COMPRESS_ZIP.equals(format)){
                tarOs = new ZipArchiveOutputStream(fos);
            }
            addFiles(sourceFolder, "", tarOs);
        }finally {
            if(Objects.nonNull(tarOs)){
                tarOs.close();
            }
        }
    }

    public static void deleteFileOrFolder(String path) throws IOException {
        Path root = Paths.get(path);
        File file = root.toFile();
        if(file.exists()){
            if(file.isFile()){
                Files.delete(root);
            }else{
                File[] files = file.listFiles();
                if(Objects.nonNull(files)){
                    Arrays.asList(files).parallelStream().forEach(t -> {
                        try {
                            deleteFileOrFolder(t.getPath());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
                Files.delete(root);
            }
        }
    }

    private static void addFiles(String filePath, String parent, ArchiveOutputStream tarArchive) throws IOException {
        File file = new File(filePath);
        String entryName = parent + file.getName();
        ArchiveEntry entry = tarArchive.createArchiveEntry(file, entryName);
        tarArchive.putArchiveEntry(entry);
        if (file.isFile()) {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            // 写入文件
            IOUtils.copy(bis, tarArchive);
            tarArchive.closeArchiveEntry();
            bis.close();
        } else if (file.isDirectory()) {
            // 因为是个文件夹，无需写入内容，关闭即可
            tarArchive.closeArchiveEntry();
            // 读取文件夹下所有文件
            for (File f : file.listFiles()) {
                addFiles(f.getAbsolutePath(), entryName + File.separator, tarArchive);
            }
        }
    }
}
