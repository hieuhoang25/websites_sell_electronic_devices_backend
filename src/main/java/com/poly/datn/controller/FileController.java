package com.poly.datn.controller;


import com.poly.datn.common.constant.EOrderStatus;
import com.poly.datn.controller.router.Router;
import com.poly.datn.service.FileService;
import com.poly.datn.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Router.FILE_API)
@Tag(name = Router.FILE_API)
public class FileController {
    @Autowired
    private FileService fileService;

    @Autowired
    private MailService mailService;

    //https://firebasestorage.googleapis.com/v0/b/bonik-f7b39.appspot.com/o/menu.png
    /*
    * prefix: https://firebasestorage.googleapis.com/v0/b/bonik-f7b39.appspot.com/o/
    * image_name: your file name
    * sufix: ?alt=media
    * */
    @PostMapping()
    public ResponseEntity<Object> upload(@RequestPart("file") MultipartFile multipartFile) {
//        mailService.sendOrderStatus("12345", EOrderStatus.DELIVERED.name(), "synhatphu2@gmail.com");
        return ResponseEntity.ok(fileService.upload(multipartFile));
    }

    @PostMapping("/multi")
    public ResponseEntity<?> upload(@RequestPart("files")List<MultipartFile> multipartFiles){
       return ResponseEntity.ok(fileService.uploadMultiFiles(multipartFiles));
    }

    @DeleteMapping("/{url}")
    public ResponseEntity<?> delete(@PathVariable("url") String url) {
        try {
            if (fileService.delete(url) == true)
                return ResponseEntity.ok("Delete successfully!");
            else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
        }

    }

}