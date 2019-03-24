package com.leyou.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.dao.BrandMapper;
import com.leyou.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;
    public PageResult<Brand> queryBrandByPageAndSort(Integer page, Integer rows, String sortBy, Boolean desc, String key) {

        //select * from Brand where key like '%'xx'%' or
        //分页
        PageHelper.startPage(page,rows);
        //过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)){
            //创建查询条件
            example.createCriteria().andLike("name","%"+key+"%").orEqualTo("letter",key.toUpperCase());
        }
        //排序

        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        //查询
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        //返回结果
        return  new PageResult<>(brands.getTotal(),brands);
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        try {
            int insert = brandMapper.insert(brand);
            //插入品牌和分类的关联表
            for (Long cid : cids){
                brandMapper.insertCategoryBrand(cid,brand.getId());
            }
        }catch (Exception e){
            throw new LyException(ExceptionEnum.GOODS_SAVE_ERROR);
        }

    }
}
