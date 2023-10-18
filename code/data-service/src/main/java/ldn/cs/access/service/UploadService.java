package ldn.cs.access.service;

import ldn.cs.access.utils.DataFusion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UploadService {

    @Autowired
    private DataFusion dataFusion;

    @Autowired
    private OriginalService originalService;

    /**
     * 上传文件名修改
     *
     * @param originalFilename 原上传文件名称
     * @return 定义上传文件名称：上传时间+UUID+原文件名
     */
    public String generateUniqueFilename(String originalFilename) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        String randomUUID = UUID.randomUUID().toString();

        String prefix = timestamp + "_" + randomUUID + "_";

        return prefix + originalFilename;
    }

    /**
     * csv文件数据提取
     *
     * @param file 上传的文件
     * @throws IOException
     */
    public void processCsvFile(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim().parse(reader)) {

            List<CSVRecord> records = csvParser.getRecords();
            Map<String, Integer> headerMap = csvParser.getHeaderMap();

            for (CSVRecord record : records) {
                Map<String, Object> dataMap = new HashMap<>();

                for (String header : headerMap.keySet()) {
                    int columnIndex = headerMap.get(header);
                    Object value = record.get(columnIndex);
                    dataMap.put(header.replaceAll("\uFEFF", ""), value);
                }
                System.out.println(dataMap);//输出数据
                dataFusion.fusion(dataMap);
                originalService.addOriginalInfos(dataMap);
            }
        }
    }
}