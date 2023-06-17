package com.example.stc.config;

import com.example.stc.entity.PermissionGroup;
import com.example.stc.entity.Permissions;
import com.example.stc.entity.PermissionsLevel;
import com.example.stc.service.PermissionGroupService;
import com.example.stc.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStarter implements CommandLineRunner {
    @Autowired
    private PermissionsService permissionsService;

    @Autowired
    private PermissionGroupService permissionGroupService;
    @Override
    public void run(String... args) throws Exception {


        if(permissionGroupService.findAll().isEmpty()){
            PermissionGroup permissionGroup = new PermissionGroup();
            permissionGroup.setName("admin");
            permissionGroupService.insert(permissionGroup);

            Permissions permissionsView = new Permissions();
            permissionsView.setUserEmail("testview@gmail.com");
            permissionsView.setPermissionsLevel(PermissionsLevel.View);
            permissionsView.setPermissionGroup(permissionGroup);
            permissionsService.insert(permissionsView);

            Permissions permissionsEdit = new Permissions();
            permissionsEdit.setUserEmail("testedit@gmail.com");
            permissionsEdit.setPermissionsLevel(PermissionsLevel.EDIT);
            permissionsEdit.setPermissionGroup(permissionGroup);
            permissionsService.insert(permissionsEdit);


        }
    }
}
