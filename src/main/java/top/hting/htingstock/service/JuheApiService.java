package top.hting.htingstock.service;

import top.hting.htingstock.model.dto.JuheStockDto;
import top.hting.htingstock.model.enums.JuheApiEnum;

import java.util.List;

/**
 * 聚合数据接口
 */
public interface JuheApiService {
    /**
     * 获取沪市所有的股票信息
     * @return
     */
    List<JuheStockDto> getShList();

    /**
     * 获取所有的深市股票信息
     * @return
     */
    List<JuheStockDto> getSzList();

    /**
     * 获取股票数据
     * @return
     */
    List<JuheStockDto> getStockList(JuheApiEnum apiEnum);

}
