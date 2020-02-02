package top.hting.htingstock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.hting.htingstock.model.entity.StockHistoryEntity;

import java.util.List;

public interface StockHistoryMapper extends BaseMapper<StockHistoryEntity> {

    List<StockHistoryEntity> getMaxRecordDate();

}
