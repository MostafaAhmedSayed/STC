package com.example.stc.service;

import com.example.stc.entity.File;
import com.example.stc.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    private final FileRepository fileRepository;

    @Autowired
    FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File findByItemId(Long itemId){
        return fileRepository.findByItemId(itemId);
    }


}
