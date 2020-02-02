package top.hting.htingstock.service;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.hting.htingstock.dao.JuheStockMapper;
import top.hting.htingstock.dao.StockHistoryMapper;
import top.hting.htingstock.model.dto.RealFinancingDto;
import top.hting.htingstock.model.entity.JuheStockEntity;
import top.hting.htingstock.model.entity.StockHistoryEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 定时获取历史数据
 */
@Component
@EnableScheduling
public class HistoryTaskService {

    @Autowired
    private JuheStockMapper stockMapper;
    @Autowired
    private StockHistoryService historyService;
    @Autowired
    private StockHistoryMapper stockHistoryMapper;



    @Scheduled(cron = "0 30 16 * * ?")
    public void historyDataTask() {

        List<StockHistoryEntity> maxRecordDate = stockHistoryMapper.getMaxRecordDate();
        // 转换为 code-date形式
        Map<String, Date> dateMap = maxRecordDate.stream().collect(Collectors.toMap(StockHistoryEntity::getCode, StockHistoryEntity::getRecordDate));

        List<JuheStockEntity> juheStockEntities = stockMapper.selectByMap(null);

        juheStockEntities.forEach(p -> {
            String code = p.getCode();
            String symMarket = p.getSymMarket();
            String symbol = p.getSymbol();

            int prefix = 1;
            if (symMarket.equals("sh")) {
                prefix = 0;
            }
            try {
                // 开始天数为半年前，如果存在则加一天
                Date startDate = null;
                if (dateMap.get(code) !=null ) {
                    startDate = DateUtils.addDays(dateMap.get(code), 1);
                } else {
                    startDate = DateUtils.parseDate("yyyyMMdd", "20190601");
                }


                List<StockHistoryEntity> list = historyService.getHistoryByNetease(prefix + code,
                    startDate,
                    new Date());

                // 由于资金获取接口只能获取当天的，这里取第一条 todo
                // if (list != null && list.size() > 0) {
                //    StockHistoryEntity todayStockHistory = list.get(0);
                //    RealFinancingDto realFinancing = historyService.getRealFinancing(symbol);
                //
                //
                //
                // }



                for (StockHistoryEntity entity : list) {
                    stockHistoryMapper.insert(entity);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });





    }



}
