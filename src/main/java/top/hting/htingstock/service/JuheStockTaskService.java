package top.hting.htingstock.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.hting.htingstock.dao.JuheStockMapper;
import top.hting.htingstock.model.dto.JuheStockDto;
import top.hting.htingstock.model.entity.JuheStockEntity;

import java.util.Date;
import java.util.List;

/**
 * 自动获取当前市场股票
 */
@Component
public class JuheStockTaskService {
    @Autowired
    private JuheApiService juheApiService;
    @Autowired
    private JuheStockMapper stockMapper;

    /**
     * 每天15.30同步
     *
     */
    @Scheduled(cron = "0 30 15 * * ?")
    public void synStock() {

        List<JuheStockDto> szList = juheApiService.getShList();
        szList.forEach(p -> {
            JuheStockEntity entity = new JuheStockEntity();
            BeanUtils.copyProperties(p, entity);
            entity.setSysCreate(new Date());
            if (stockMapper.selectById(entity.getSymbol()) ==null) {
                stockMapper.insert(entity);

            }


        });


    }

}
