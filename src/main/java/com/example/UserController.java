package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanyaofei
 * @since 2024/4/18
 **/
@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }


    @GetMapping("/user")
    public void getUsers() {
        var user = service.createUser();
        try {
            service.updateUser(user.getId());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // IDLE 状态的连接还给连接池, 污染了连接池, 影响了别的业务查询
        System.out.println("开始测试查询....");
        for (int i = 0; i < 3; i++) {
            try {
                service.getUser(user.getId());
            } catch (Throwable e) {
                System.out.println(e.getMessage()); // IDLE ERROR
            }
        }

    }


}
