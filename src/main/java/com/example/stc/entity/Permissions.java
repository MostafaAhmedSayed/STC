package com.example.stc.entity;

import javax.persistence.*;

@Entity
@Table(name ="permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userEmail;

    private PermissionsLevel permissionsLevel;

    @ManyToOne
    @JoinColumn (name = "group_id", referencedColumnName = "id")
    private PermissionGroup permissionGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public PermissionsLevel getPermissionsLevel() {
        return permissionsLevel;
    }

    public void setPermissionsLevel(PermissionsLevel permissionsLevel) {
        this.permissionsLevel = permissionsLevel;
    }

    public PermissionGroup getPermissionGroup() {
        return permissionGroup;
    }

    public void setPermissionGroup(PermissionGroup permissionGroup) {
        this.permissionGroup = permissionGroup;
    }
}
