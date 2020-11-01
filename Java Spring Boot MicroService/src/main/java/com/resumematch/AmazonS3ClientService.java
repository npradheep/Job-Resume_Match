package com.resumematch;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3ClientService
{
    void uploadFileToS3Bucket(String fileName, MultipartFile multipartFile, boolean enablePublicReadAccess);

    void deleteFileFromS3Bucket(String fileName);
    
    String getURL(String fileName);
}