package com.example.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("uploadfile")
public class UpLoadFile {
    private String filename;
    private String filepath;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "UpLoadFile{" +
                "filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
