package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.enums.ErrorCodeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload/")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private String filePath = "C:\\Users\\wgq\\Desktop\\img\\article\\";
    private String mappingPath = "image/article/";

    @RequestMapping("articleImg")
    public Object articleImg(MultipartFile file) {
        String result = "";
        int code = ErrorCodeEnum.NOFILE.getValue();
        if (file.isEmpty()) {
            result = "文件为空";
        } else {
            String fileName = file.getOriginalFilename();// 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));// 后缀
            // 上传路径
            String newFileName = UUID.randomUUID().toString().substring(0,8).toLowerCase();
            File dest = new File(filePath + newFileName + suffixName);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                result = mappingPath + newFileName + suffixName;
                code = 0;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new JsonResult(true,code,result);
    }
}
