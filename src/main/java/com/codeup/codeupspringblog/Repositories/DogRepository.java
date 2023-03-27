package com.codeup.codeupspringblog.Repositories;


import com.codeup.codeupspringblog.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    Dog findByName(String name);
    List<Dog> findByGender(String gender);

    @Query("from Dog d where d.name like %:name%")
    List<Dog> findLikeName(@Param("name") String name);

}
