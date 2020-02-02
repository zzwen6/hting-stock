package top.hting.htingstock.service;

import top.hting.htingstock.model.dto.RealFinancingDto;
import top.hting.htingstock.model.entity.StockHistoryEntity;

import java.util.Date;
import java.util.List;

/**
 * 历史记录
 */
public interface StockHistoryService {


    /**
     * 获取历史(网易接口)
     * @return
     */
    List<StockHistoryEntity> getHistoryByNetease(String code, Date start, Date end);


    /**
     * 获取实时资金流向
     *
     * @param symbol 股票代码，带前缀
     * @return
     */
    RealFinancingDto getRealFinancing(String symbol);


}
