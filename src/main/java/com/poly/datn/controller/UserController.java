package com.poly.datn.controller;

import com.poly.datn.service.AuthorityService;
import com.poly.datn.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.poly.datn.controller.router.Router.ADMIN_API.BASE;
import static com.poly.datn.controller.router.Router.ADMIN_API.USER;
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE+USER)
@Tag(name = BASE+USER)
public class UserController {

    private final UserService userService;
    private final AuthorityService authorityService;

    @GetMapping()
    public ResponseEntity<?> fetchData(){
        return ResponseEntity.ok(userService.findAll());
    }

   @PutMapping("/{roleId}/{accountId}")
    public ResponseEntity<?> setRole(@PathVariable ("roleId") Integer roleId,@PathVariable("accountId") Integer accountId){
         authorityService.setRole(roleId,accountId);
         return ResponseEntity.ok().build();

   }
}
