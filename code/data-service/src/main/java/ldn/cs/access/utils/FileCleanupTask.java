package ldn.cs.access.utils;

import ldn.cs.access.config.UploadConfig;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileCleanupTask {

    /**
     * 删除7天前上传的文件
     */
    @Scheduled(cron = "0 0 0 * * *") // 每天凌晨执行一次
    public void cleanupFiles() {
        File directory = new File(UploadConfig.Path);

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory doesn't exist or is not a directory.");
            return;
        }

        long currentTimeMillis = System.currentTimeMillis();
        long sevenDaysAgoMillis = currentTimeMillis - 7 * 24 * 60 * 60 * 1000; // 7天前的时间戳

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".csv")) {
                    long lastModifiedMillis = file.lastModified();
                    if (lastModifiedMillis <= sevenDaysAgoMillis) {
                        if (file.delete()) {
                            System.out.println("Deleted file: " + file.getName());
                        } else {
                            System.out.println("Failed to delete file: " + file.getName());
                        }
                    }
                }
            }
        }
    }
}

