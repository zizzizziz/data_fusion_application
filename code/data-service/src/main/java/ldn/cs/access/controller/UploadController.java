package ldn.cs.access.controller;

import ldn.cs.access.config.UploadConfig;
import ldn.cs.access.service.UploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/rest/data/access/file")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {

        String originalFilename = file.getOriginalFilename();
        if (file.isEmpty() || !FilenameUtils.getExtension(originalFilename).equalsIgnoreCase("csv")) {
            return "uploadFailure";
        } else {
            try {
                uploadService.processCsvFile(file);

                String uniqueFilename = uploadService.generateUniqueFilename(originalFilename);
                File destFile = new File(UploadConfig.Path + File.separator + uniqueFilename);
                file.transferTo(destFile);

            } catch (IOException e) {
                e.printStackTrace();
                return "uploadFailure"; // 返回上传失败视图
            }
            model.addAttribute("filename", originalFilename); // 保存上传文件名列表到模型
            return "Upload " + originalFilename + " success";
        }
    }
}

