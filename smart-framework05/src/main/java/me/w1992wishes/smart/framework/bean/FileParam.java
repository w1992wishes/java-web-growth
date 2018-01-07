package me.w1992wishes.smart.framework.bean;

import java.io.InputStream;

/**
 * 封装上传文件参数
 *
 * Created by w1992wishes
 * on 2018/1/3.
 */
public class FileParam {

    private String fieldName;//文件表单字段名
    private String fileName;//上传文件的文件名
    private Long fileSize;//上传文件的文件大小
    private String contentType;//上传文件的Content-Type
    private InputStream inputStream;//上传文件的的字节输入流

    public FileParam(String fieldName, String fileName, Long fileSize, String contentType, InputStream inputStream) {
        this.fieldName = fieldName;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
        this.inputStream = inputStream;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}
