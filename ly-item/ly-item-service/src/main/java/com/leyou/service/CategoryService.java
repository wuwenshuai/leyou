package com.leyou.service;


import com.leyou.dao.CategoryMapper;
import com.leyou.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     *提供一个根据parentId查询子节点分类集合
     * @param pid
     * @return
     */
    public List<Category> queryListByParent(Long pid){
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categoryList = categoryMapper.select(category);
        return categoryList;
    }

    /**
     * 根据cids查询分类列表
     * @param ids
     * @return
     */
    public List<String> getCateNameByCids(List<Long> ids) {

        List<Category> categories = categoryMapper.selectByIdList(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());

    }
}
