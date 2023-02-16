package com.example.test.controller;

import com.example.test.entity.UpLoadFile;
import com.example.test.mapper.UploadFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:8080",allowedHeaders = "*",methods = {},allowCredentials = "true")
public class UploadFileController {
    private static final String path = "D:\\C#\\Idea\\WeChat\\test\\src\\main\\resources\\static\\";

    @Autowired
    private UploadFileMapper uploadFileMapper;

    @PostMapping("/upload")
    public String upload(MultipartFile photo, HttpServletRequest request) throws IOException {
        //获取图片的原始名称
        System.out.println(photo.getOriginalFilename());
//        获取文件类型
        System.out.println(photo.getContentType());

//        String path=request.getServletContext().getRealPath("/upload");
//        System.out.println(path);
        String filename = "李商隐" + "_" + photo.getOriginalFilename();
        UpLoadFile upLoadFile = new UpLoadFile();
        upLoadFile.setFilename(filename);
        upLoadFile.setFilepath("images/" + filename);
        int res = uploadFileMapper.insert(upLoadFile);
        System.out.println("res: " + res);
        saveFile(photo, filename);
        return "Ok";
    }


    public void saveFile(MultipartFile photo, String filename) throws IOException {
        File dir = new File(path);
        if (!dir.exists()) {
            //创建目录
            dir.mkdir();
        }
        File file = new File(path + filename);
        photo.transferTo(file);
    }

}
