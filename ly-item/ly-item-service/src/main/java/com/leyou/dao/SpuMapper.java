package com.leyou.dao;

import com.leyou.pojo.Spu;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.select.SelectAllMapper;

public interface SpuMapper extends Mapper<Spu>,SelectAllMapper<Spu> {
}
