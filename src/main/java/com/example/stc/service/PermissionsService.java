package com.example.stc.service;

import com.example.stc.entity.Permissions;
import com.example.stc.repository.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionsService {

    private final PermissionsRepository permissionsRepository;
    @Autowired
    PermissionsService(PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }
    public Permissions insert(Permissions permissions){
        return permissionsRepository.save(permissions);
    }
    public Permissions findByUserEmail(String userEmail){
        return permissionsRepository.findByUserEmail(userEmail);
    }

    public Permissions findByUserEmailNative(String userEmail){
        return permissionsRepository.findByUserEmailNative(userEmail);
    }
}
