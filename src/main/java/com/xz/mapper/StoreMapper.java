package com.xz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xz.entity.Store;
import com.xz.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StoreMapper extends BaseMapper<Store> {
    @Select(" select * from t_store where merchant_id =  #{merchantId} ")
    List<Store> getStoresByMerchantId(Store store);

}
