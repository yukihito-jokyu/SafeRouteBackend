package com.osc.saferoute.controller;

import com.osc.saferoute.application.service.UserApplicationService;
import com.osc.saferoute.domain.model.UserId;
import com.osc.saferoute.domain.model.UserName;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PutMapping("/{id}/name")
    public void changeUserName(@PathVariable String id, @RequestBody String newName) {
        userApplicationService.changeUserName(new UserId(id), new UserName(newName));
    }

    @GetMapping("/{id}")
    public String getUserName(@PathVariable String id) {
        return userApplicationService.getUserName(new UserId(id));
    }

    @GetMapping("/get/point/{id}")
    public Integer getUserPoints(@PathVariable String id) {
        return userApplicationService.getCurrentPoints(new com.osc.saferoute.domain.model.UserId(id));
    }
}