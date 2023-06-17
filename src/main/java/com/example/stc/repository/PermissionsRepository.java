package com.example.stc.repository;

import com.example.stc.entity.File;
import com.example.stc.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface PermissionsRepository extends JpaRepository<Permissions,Long> {

     Permissions findByUserEmail(String userEmail);

    @Query(value ="Select * from permissions where permissions.user_email =:email",nativeQuery = true)
     Permissions findByUserEmailNative(@Param("email")String userEmail);
}
