package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.entity.file.Chunk;
import com.wugq.blog.entity.file.FileInfo;
import com.wugq.blog.enums.ErrorCodeEnum;
import com.wugq.blog.service.AsyncService;
import com.wugq.blog.service.ChunkService;
import com.wugq.blog.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/upload/")
@Slf4j
public class UploadController {

    @Autowired
    ChunkService chunkService;
    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    AsyncService asyncService;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    private String filePath = "C:\\Users\\wgq\\Desktop\\img\\article\\";
    private String mappingPath = "image/article/";

    private String uploadFolder = "C:/Users/wgq/Desktop/file";

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


    @PostMapping("chunk")
    public Object uploadFile(Chunk chunk) {
        MultipartFile file = chunk.getFile();
        chunk.setType(file.getContentType());
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(generatePath(uploadFolder, chunk));
            Files.write(path, bytes);
            logger.debug("文件 {} 写入成功, uuid:{}", chunk.getFilename(), chunk.getIdentifier());
            chunkService.saveChunk(chunk);
            return new JsonResult("文件上传成功！");
        } catch (IOException e) {
            e.printStackTrace();
            return new JsonResult(false,0,"后端异常！");
        }
    }

    @GetMapping("chunk")
    public Object checkChunk(Chunk chunk,HttpServletResponse response) {
        if (!chunkService.checkChunk(chunk.getIdentifier(), chunk.getChunkNumber())) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
        return chunk;
    }

    @PostMapping("mergeFile")
    public Object mergeFile(FileInfo fileInfo) {
        String filename = fileInfo.getFilename();
        String file = uploadFolder + "/" + fileInfo.getIdentifier() + "/" + filename;
        String folder = uploadFolder + "/" + fileInfo.getIdentifier();
        fileInfo.setLocation("/" + fileInfo.getIdentifier() + "/" + filename);
        fileInfoService.save(fileInfo);
        asyncService.merge(file, folder, filename);
        return new JsonResult("合并成功");
    }



    public String generatePath(String uploadFolder, Chunk chunk) {
        StringBuilder sb = new StringBuilder();
        sb.append(uploadFolder).append("/").append(chunk.getIdentifier());
        //判断uploadFolder/identifier 路径是否存在，不存在则创建
        if (!Files.isWritable(Paths.get(sb.toString()))) {
            log.info("path not exist,create path: {}", sb.toString());
            try {
                Files.createDirectories(Paths.get(sb.toString()));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return sb.append("/")
                .append(chunk.getFilename())
                .append("-")
                .append(chunk.getChunkNumber()).toString();
    }

}
