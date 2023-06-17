package com.example.stc.controller;

import com.example.stc.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("permissions/api/v1")
public class PermissionsController {

    private final PermissionsService permissionsService;
    @Autowired
    PermissionsController(PermissionsService permissionsService) {
        this.permissionsService = permissionsService;
    }
}
