package com.leyou.controller;


import com.leyou.pojo.Category;
import com.leyou.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
@Slf4j
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    /**
     * 根据父节点查询商品类目
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>>queryListByParent( @RequestParam(value = "pid", defaultValue = "0") Long pid){

        //校验pid，省略
        List<Category> categoryList = categoryService.queryListByParent(pid);
        if (categoryList==null||categoryList.size()<=0){
            //返回404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoryList);
    }


}
