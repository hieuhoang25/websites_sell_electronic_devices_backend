package com.poly.datn.service.serviceImpl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.poly.datn.service.FileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class FileServiceImpl implements FileService {

    private String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/image-cloud-98533.appspot.com/o/%s?alt=media";



    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("image-cloud-98533.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        ClassPathResource serviceAccount = new ClassPathResource("image-cloud-98533-firebase-adminsdk-egb09-95daffa97d.json");
        Storage storage = StorageOptions.newBuilder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                setProjectId("springboot-mall").build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));

        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public Object upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            // to generated random string values for file name.
            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return fileName;                     // Your customized response
        } catch (Exception e) {
            throw new RuntimeException("Upload file unsuccessfully!");
        }
    }

    @Override
    public Object download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
        String destFilePath = "Z:\\New folder\\" + destFileName;                                    // to set destination file path

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("springboot-mall-firebase-adminsdk-cjn0f-aad2c519fc.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("springboot-mall.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "Successfully Downloaded!";
    }

    @Override
    public boolean delete(String fileName) throws IOException {
        // TODO Auto-generated method stub
        BlobId blobId = BlobId.of("image-cloud-98533.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        ClassPathResource serviceAccount = new ClassPathResource("image-cloud-98533-firebase-adminsdk-egb09-95daffa97d.json");

        Storage storage = StorageOptions.newBuilder().
                setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream())).
                setProjectId("springboot-mall").build().getService();
        return storage.delete(blobId);
    }

    @Override
    public List<Object> uploadMultiFiles(List<MultipartFile> multipartFiles) {

       return multipartFiles.stream()
                .map(multipartFile -> {
                    try {
                        String fileName = multipartFile.getOriginalFilename();                        // to get original file name
                        File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
                        String TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
                        file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
                        return fileName;
                    } catch (Exception e) {
                        throw new RuntimeException("Upload files unsuccessfully!");
                    }
                }).collect(Collectors.toList());


    }
}