package com.example.stc.service;

import com.example.stc.entity.PermissionGroup;
import com.example.stc.repository.PermissionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionGroupService {

    private final PermissionGroupRepository permissionGroupRepository;
    @Autowired
    PermissionGroupService(PermissionGroupRepository permissionGroupRepository) {
        this.permissionGroupRepository = permissionGroupRepository;
    }
    public PermissionGroup insert(PermissionGroup permissionGroup){
        return permissionGroupRepository.save(permissionGroup);
    }

    public List<PermissionGroup> findAll(){
        return permissionGroupRepository.findAll();
    }
    public PermissionGroup findByName(String name){
        return permissionGroupRepository.findByName(name);
    }
}
