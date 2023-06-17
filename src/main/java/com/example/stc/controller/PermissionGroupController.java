package com.example.stc.controller;

import com.example.stc.service.PermissionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("group/api/v1")
public class PermissionGroupController {

    private final PermissionGroupService permissionGroupService;
    @Autowired
    PermissionGroupController(PermissionGroupService permissionGroupService) {
        this.permissionGroupService = permissionGroupService;
    }
}
