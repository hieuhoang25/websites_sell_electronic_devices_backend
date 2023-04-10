package com.poly.datn.controller;

import com.poly.datn.common.MessageResponse;
import com.poly.datn.common.ResponseBody;
import com.poly.datn.service.AuthorityService;
import com.poly.datn.service.CURDAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.poly.datn.controller.router.Router.ADMIN_API.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(BASE+ACCOUNT)
@Tag(name = BASE+ ACCOUNT)
public class CURDAccount {

    private final CURDAccountService curdAccountService;
    private final AuthorityService authorityService;
    @GetMapping()
    private ResponseEntity<?> fetchAll(@RequestParam("size") Optional<Integer> size,
                                       @RequestParam("page") Optional<Integer> page){
        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(5));
        return ResponseEntity.ok(curdAccountService.findAll(pageable));
    }

    @GetMapping("/updateRole")
    private ResponseEntity<?> updateRole(@RequestParam("roleId") Integer roleId,
                                       @RequestParam("accountId") Integer accountId){

       authorityService.setRole(roleId,accountId);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<?> fetchDataFilter(@RequestParam("size")Optional<Integer> size,
                                             @RequestParam("page") Optional<Integer> page,
                                             @RequestParam("search") Optional<String> search,
                                             @RequestParam("roleId") Optional<Integer> roleId) {

        Pageable pageable = PageRequest.of(page.orElse(0),size.orElse(10));
    
        if(roleId.get() != -1){
            return ResponseEntity.ok(
                    new ResponseBody<>(
                            1,
                            MessageResponse.MESSAGE_SUCCESS,
                            curdAccountService.findAllByFilterWithDeleted(pageable,"%"+search.get()+"%",roleId.get())
                    )
            );
        }else {
           
            return ResponseEntity.ok(
                    new ResponseBody<>(
                            1,
                            MessageResponse.MESSAGE_SUCCESS,
                            curdAccountService.findAllByFilter(pageable,"%"+search.get()+"%")
                    )
            );

        }

    }


}
