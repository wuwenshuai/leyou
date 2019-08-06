package com.leyou.controller;


import com.leyou.pojo.SpecGroup;
import com.leyou.pojo.SpecParam;
import com.leyou.service.SpecificationService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/spec")
public class SpecificationController {


    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类id查询分组
     *
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    ResponseEntity<List<SpecGroup>> getSpecGropuByCid(@PathVariable("cid") Long cid) {
        List<SpecGroup> groups = specificationService.getSpecGropuByCid(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groups);
    }

    /**
     * 通过gid查询规则参数列表
     * @param gid
     * @return
     */
    @GetMapping("params")
    ResponseEntity<List<SpecParam>> getSpecParamByGid(Long gid) {

        List<SpecParam> specParams = specificationService.getSpecParamByGid(gid);
        if (CollectionUtils.isEmpty(specParams)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(specParams);
    }
}
