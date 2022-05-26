package com.lzr.controller;
import com.lzr.model.LoginEntity;
import com.lzr.service.LoginAuthService;
import com.lzr.util.ResponseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LoginAuthController {
    @Autowired
    private LoginAuthService loginAuthService;

    @RequestMapping("/login")
    public ResponseMap login(@RequestBody LoginEntity loginEntity){
        System.out.println(loginEntity.getUsername()+","+loginEntity.getPassword());
        return loginAuthService.login(loginEntity.getUsername(),loginEntity.getPassword());
    }
    @RequestMapping("/401")
    public ResponseMap response401(){
        return new ResponseMap(401,"认证失败",null);
    }
}
