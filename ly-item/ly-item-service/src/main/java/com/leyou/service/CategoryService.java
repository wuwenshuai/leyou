package com.leyou.service;


import com.leyou.dao.CategoryMapper;
import com.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    //提供一个根据parentId查询分类集合
    @Autowired
    private CategoryMapper categoryMapper;
    public List<Category> queryListByParent(Long pid){
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        return categoryList;
    }
}
