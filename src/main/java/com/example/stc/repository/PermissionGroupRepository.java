package com.example.stc.repository;

import com.example.stc.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup,Long> {
     PermissionGroup findByName(String name);
}
