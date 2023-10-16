package ldn.cs.access.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class UploadConfig {
    public static final String Path = "/opt/files";   //上传文件保存地址

    @Bean
    public MultipartConfigElement getMultipartConfig() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize(DataSize.ofMegabytes(5));     // 设置上传文件的单个大小限制为 10MB
        config.setMaxRequestSize(DataSize.ofMegabytes(20)); // 设置总的上传的大小限制为 20MB
        config.setLocation(Path);                           // 设置临时保存目录
        return config.createMultipartConfig();              // 创建一个上传配置
    }
}
