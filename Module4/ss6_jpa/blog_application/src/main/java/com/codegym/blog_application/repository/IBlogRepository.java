package com.codegym.blog_application.repository;

import com.codegym.blog_application.model.Blog;
import com.codegym.blog_application.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IBlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByNameContaining(String name);

    //    Phân trang (list + search)
    Page<Blog> findByNameContaining(String name, Pageable pageable);

    @Query(value = "select * from blog where blog.name like concat ('%',:name,'%') and blog.category_id like concat ('%',:id,'%')",
            nativeQuery = true)
    Page<Blog> findByNameAndCategory(@Param("name") String name, @Param("id") String id, Pageable pageable);




    //    Phân trang (list)
    Page<Blog> findAll(Pageable pageable);
}
