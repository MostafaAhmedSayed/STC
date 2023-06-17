package com.example.stc.controller;

import com.example.stc.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("file/api/v1")
public class FileController {
    @Autowired
    private FileService fileService;
}
