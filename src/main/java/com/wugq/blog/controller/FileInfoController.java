package com.wugq.blog.controller;

import com.wugq.blog.common.JsonResult;
import com.wugq.blog.common.PageInfo;
import com.wugq.blog.entity.file.FileInfo;
import com.wugq.blog.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@RestController
@Slf4j
public class FileInfoController {

    @Autowired
    FileInfoService fileInfoService;

    private String fileDir = "C:/Users/wgq/Desktop/file";

    @GetMapping("/file/public")
    public Object getPublicFiles() {
        List<FileInfo> files = fileInfoService.getPublicFiles();
        return new JsonResult(files);
    }

    @GetMapping("/file/private")
    public Object getPrivateFiles(Integer uid) {
        List<FileInfo> files = fileInfoService.getPrivateFiles(uid);
        return new JsonResult(files);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable("id") Integer id,
                     HttpServletRequest request, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoService.get(id);
        File file = new File(fileDir + fileInfo.getLocation());
        String name = file.getName();
        if(file.exists()){
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));

                byte[] buffer = new byte[1024];
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (UnsupportedEncodingException e) {
                log.debug(name + "   下载文件名编码错误");
            } catch (FileNotFoundException e) {
                log.debug(name + "   下载文件不存在");
            } catch (IOException e) {
                log.debug(name + "   下载文件出错！！！");
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @GetMapping("/admin/files/public")
    public Object getAdminPublic(int page, int pageSize) {
        PageInfo pageInfo = new PageInfo(page, pageSize);
        fileInfoService.getAdminFile(0,pageInfo);
        return new JsonResult(pageInfo);
    }

    @GetMapping("/admin/files/private")
    public Object getAdminPrivate(int page, int pageSize) {
        PageInfo pageInfo = new PageInfo(page, pageSize);
        fileInfoService.getAdminFile(1, pageInfo);
        return new JsonResult(pageInfo);
    }

    @PutMapping("/admin/files")
    public Object updateFile(int id, int status) {
        FileInfo fileInfo = fileInfoService.get(id);
        fileInfo.setStatus(status);
        fileInfoService.update(fileInfo);
        return new JsonResult(true);
    }

    @DeleteMapping("/admin/files/{id}")
    public Object deleteFile(@PathVariable("id") int id){
        // 删除数据库记录，不删除文件
        fileInfoService.delete(id);
        return new JsonResult(true);
    }
}
