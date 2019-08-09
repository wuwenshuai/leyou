package com.leyou.bo;

import com.leyou.pojo.Sku;
import com.leyou.pojo.Spu;
import com.leyou.pojo.SpuDetail;
import lombok.Data;

import java.util.List;

@Data
public class SpuBo extends Spu {
    private String cname;
    private String bname;
    SpuDetail spuDetail;// 商品详情
    List<Sku> skus;// sku列表
}
