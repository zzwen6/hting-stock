package top.hting.htingstock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.hting.htingstock.model.entity.JuheStockEntity;

//@Mapper
public interface JuheStockMapper extends BaseMapper<JuheStockEntity> {

    void batchInsert();

}
