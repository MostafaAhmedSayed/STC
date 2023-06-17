package com.example.stc.repository;

import com.example.stc.entity.File;
import com.example.stc.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository

public interface ItemRepository extends JpaRepository<Item,Long> {
    @Query(value = "Select * from item where item.name =:fileName",nativeQuery = true)
     Item findFileByName(@Param("fileName") String fileName);
}
