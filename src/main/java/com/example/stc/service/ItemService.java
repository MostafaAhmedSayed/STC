package com.example.stc.service;

import com.example.stc.entity.File;
import com.example.stc.entity.Item;
import com.example.stc.entity.Permissions;
import com.example.stc.entity.PermissionsLevel;
import com.example.stc.repository.FileRepository;
import com.example.stc.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileRepository fileRepository;
    private final PermissionsService permissionsService;
    private final PermissionGroupService permissionGroupService;
    private final FileService fileService;

    @Autowired
    ItemService(ItemRepository itemRepository, FileRepository fileRepository, PermissionsService permissionsService, PermissionGroupService permissionGroupService, FileService fileService) {
        this.itemRepository = itemRepository;
        this.fileRepository = fileRepository;
        this.permissionsService = permissionsService;
        this.permissionGroupService = permissionGroupService;
        this.fileService = fileService;
    }
    public Item createSpace(Item item){
        item.setPermissionGroup(permissionGroupService.findByName("admin"));
        item.setType("Space");
        return itemRepository.save(item);
    }

    public Item createFolder(Item item, Long parentId, String userEmail){
         Permissions permissionsObj =permissionsService.findByUserEmail(userEmail);
        if(permissionsObj==null || permissionsObj.getPermissionsLevel()== PermissionsLevel.View)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"This user not authorized to create folder");
        Optional <Item> parentItem = itemRepository.findById(parentId);
        if(parentItem.isPresent())
            item.setParent(parentItem.get().getParent());
        item.setPermissionGroup(permissionGroupService.findByName("admin"));
        item.setType("Folder");

        return itemRepository.save(item);
    }

    public Item createFile(MultipartFile file, Long parentId, String userEmail) throws IOException {
        Permissions permissionsObj =permissionsService.findByUserEmail(userEmail);
        if(permissionsObj==null || permissionsObj.getPermissionsLevel()== PermissionsLevel.View)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"This user not authorized to create file");

        Optional <Item> parentItem = itemRepository.findById(parentId);

        Item item = new Item();
        item.setName(file.getOriginalFilename());
        item.setType(file.getContentType());
        if(parentItem.isPresent())
            item.setParent(parentItem.get());
        item.setPermissionGroup(permissionGroupService.findByName("admin"));
        item.setType("File");
        Item persistItem = itemRepository.save(item);

        File fileObj = new File();
        fileObj.setFileData(file.getBytes());
        fileObj.setItem(persistItem);
        fileRepository.save(fileObj);
        return persistItem;
    }

    public Item viewFileMetadata(String fileName, String userEmail) {
        Permissions permissionsObj =permissionsService.findByUserEmailNative(userEmail);
        Item itemObj = itemRepository.findFileByName(fileName);
        if(itemObj==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This file not found");
        if(permissionsObj==null || permissionsObj.getPermissionGroup()==null ||
                itemObj.getPermissionGroup()==null||
                permissionsObj.getPermissionGroup().getId()!=itemObj.getPermissionGroup().getId())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"This user not authorized to view file metadata");
        return itemObj;
    }

    @Transactional
    public File downloadFile(String fileName, String userEmail) {
        Permissions permissionsObj =permissionsService.findByUserEmailNative(userEmail);
        Item itemObj = itemRepository.findFileByName(fileName);
        if(itemObj==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"This file not found");
        if(permissionsObj==null || permissionsObj.getPermissionGroup()==null ||
                itemObj.getPermissionGroup()==null||
                permissionsObj.getPermissionGroup().getId()!=itemObj.getPermissionGroup().getId())
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"This user not authorized to view file metadata");

        return fileService.findByItemId(itemObj.getId());
    }
}

