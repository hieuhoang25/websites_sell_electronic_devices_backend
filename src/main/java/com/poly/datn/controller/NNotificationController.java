package com.poly.datn.controller;

import com.poly.datn.controller.router.Router;
import com.poly.datn.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(Router.ADMIN_API.BASE + Router.ADMIN_API.NOTIFICATION)
@Tag(name = Router.ADMIN_API.BASE + Router.ADMIN_API.NOTIFICATION)
public class NNotificationController {
    private NotificationService notificationService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(notificationService.getAll());
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> clearAll(){
        return ResponseEntity.ok(notificationService.deleteAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> clearOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(notificationService.delete(id));
    }

}
