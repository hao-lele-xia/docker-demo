package com.example.dockerdemo.util;

/**
 * @author chenhao
 * @description 文件相关的常量<p>
 * created by chenhao 2020/5/13 15:54
 */
public final class FileCons {

    private FileCons(){}

    /**
     * 文件分隔符
     */
    public static final String FILE_SPLIT_STR = "/";

    /**
     * 分片数据文件夹名
     */
    public static final String CHUNK_TEMP_FOLDER = "chunk";

    /**
     * 导出数据文件夹名
     */
    public static final String TAG_EXPORT_FOLDER = "export";

    /**
     * 原始数据文件夹名
     */
    public static final String ORIGIN_DATA_FOLDER = "origin-data";

    /**
     * 标注数据文件夹名
     */
    public static final String TAG_DATA_FOLDER = "tag-data";

    /**
     * 缩略图后缀
     */
    public static final String THUMBNAIL_PARAM = "&thumbnail=1";

    /**
     * 图片获取路径
     */
    public static final String IMAGE_GET_URL = "/data/origin/pic";

    /**
     * 检测任务--图片数据文件夹名
     */
    public static final String ZIP_TAG_TESTING_IMAGE_FOLDER_NAME = "JPEGImages";

    /**
     * 检测任务--标注数据文件夹名
     */
    public static final String ZIP_TAG_TESTING_ANNOTATION_FOLDER_NAME = "Annotations";

    /**
     * class_map文件名
     */
    public static final String ZIP_TAG_CLASSMAP_NAME = "class_map.txt";

    /**
     * 检测 任务--标注数据后缀
     */
    public static final String ZIP_TAG_TESTING_ANNATATION_SUFFIX = ".json";

    /**
     * 1024字节单位
     */
    public static final float BYTE_UNIT = 1024f;

    public static final String COMPRESS_TAR_GZ = ".tar.gz";

    public static final String COMPRESS_TAR = ".tar";

    public static final String COMPRESS_ZIP = ".zip";

}
