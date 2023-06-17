package com.example.stc.controller;

import com.example.stc.entity.File;
import com.example.stc.entity.Item;
import com.example.stc.service.ItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("item/api/v1")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/space")
    public Item createSpace(@RequestBody Item item ){
       return itemService.createSpace(item);
    }

    @PostMapping("/folder/{parentId}")
    public Item createFolder(@RequestBody Item item, @PathVariable Long parentId,@RequestHeader ("Authorization-Email") String userEmail){
        return itemService.createFolder(item,parentId,userEmail);
    }

    @PostMapping("/file/{parentId}")
    public Item createFile(@RequestParam("file")MultipartFile file, @PathVariable Long parentId,@RequestHeader ("Authorization-Email") String userEmail) throws IOException {
        return itemService.createFile(file, parentId,userEmail);
    }

    @GetMapping("/file/{fileName}")
    public Item viewFileMetaData(@PathVariable String fileName,@RequestHeader ("Authorization-Email") String userEmail)  {
        return itemService.viewFileMetadata(fileName,userEmail);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName,@RequestHeader ("Authorization-Email") String userEmail)  {
        File fileObj = itemService.downloadFile(fileName,userEmail);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
                .contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE))
                .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                .body(fileObj.getFileData());


    }


}
