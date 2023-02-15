package com.codegym.blog_application.controller;

import com.codegym.blog_application.model.Blog;
import com.codegym.blog_application.model.Category;
import com.codegym.blog_application.service.IBlogService;
import com.codegym.blog_application.service.ICategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBlogService blogService;

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAll() {
        List<Category> categoryList = categoryService.findAllCategory();
        if (categoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }
    }

    @GetMapping("api/blog")
    public ResponseEntity<List<Blog>> getAllBlog() {
        List<Blog> blogList = blogService.findAll();
        if (blogList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(blogList, HttpStatus.OK);
        }
    }

    @GetMapping("api/blog/category")
    public ResponseEntity<List<Blog>> getBlogByCategory(@RequestParam int id) {
        Category category = categoryService.findById(id);
        List<Blog> blogList = blogService.findByCategory(category);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(blogList, HttpStatus.OK);
        }
    }

//    @GetMapping("api/blog/category/{id}")
//    public ResponseEntity<List<Blog>> getBlogByCategory(@PathVariable int id){
//        Category category = categoryService.findById(id);
//        List<Blog> blogList = blogService.findByCategory(category);
//        if (category==null){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<>(blogList,HttpStatus.OK);
//        }
//    }

    @GetMapping("api/blog/{id}")
    public ResponseEntity getBlog(@PathVariable int id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(blog, HttpStatus.OK);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable int id) {
        Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            blogService.remove(id);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PostMapping("api/create")
    public ResponseEntity createBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("api/update/{id}")
    public ResponseEntity updateBlog(@PathVariable int id, @RequestBody Blog blogDto) {
       Blog blog = blogService.findById(id);
        if (blog == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        BeanUtils.copyProperties(blogDto,blog);
            blogService.save(blog);
            return new ResponseEntity( HttpStatus.OK);
    }
}
