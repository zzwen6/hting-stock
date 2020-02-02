package top.hting.htingstock;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hting.htingstock.dao.JuheStockMapper;
import top.hting.htingstock.dao.StockHistoryMapper;
import top.hting.htingstock.model.dto.JuheStockDto;
import top.hting.htingstock.model.entity.JuheStockEntity;
import top.hting.htingstock.model.entity.StockHistoryEntity;
import top.hting.htingstock.service.JuheApiService;
import top.hting.htingstock.service.StockHistoryService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class HtingStockApplicationTests {

    @Autowired
    private JuheApiService juheApiService;
    @Autowired
    private JuheStockMapper stockMapper;
    @Autowired
    private StockHistoryService historyService;
    @Autowired
    private StockHistoryMapper stockHistoryMapper;

    @Test
    void contextLoads() {

        List<JuheStockDto> szList = juheApiService.getShList();
        szList.forEach(p -> {
            JuheStockEntity entity = new JuheStockEntity();
            BeanUtils.copyProperties(p, entity);
            entity.setSysCreate(new Date());

            stockMapper.insert(entity);

        });


    }

    @Test
    public void historyTest() {
        List<JuheStockEntity> juheStockEntities = stockMapper.selectByMap(null);
        juheStockEntities.forEach(p -> {
            String code = p.getCode();
            String symMarket = p.getSymMarket();

            int prefix = 1;
            if (symMarket.equals("sh")) {
                prefix = 0;
            }
            try {
                List<StockHistoryEntity> list = historyService.getHistoryByNetease(prefix + code,
                    DateUtils.parseDate("20190601", "yyyyMMdd"),
                    new Date());

                for (StockHistoryEntity entity : list) {
                    stockHistoryMapper.insert(entity);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

        });


    }


    @Test
    public void historyDataTask() {

        List<StockHistoryEntity> maxRecordDate = stockHistoryMapper.getMaxRecordDate();
        // 转换为 code-date形式
        Map<String, Date> dateMap = maxRecordDate.stream().collect(Collectors.toMap(StockHistoryEntity::getCode, StockHistoryEntity::getRecordDate));

        List<JuheStockEntity> juheStockEntities = stockMapper.selectByMap(null);

        juheStockEntities.forEach(p -> {
            String code = p.getCode();
            String symMarket = p.getSymMarket();

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

                for (StockHistoryEntity entity : list) {
                    stockHistoryMapper.insert(entity);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });


    }

    @Test
    public void getRealFinancingTest() {
        String symcol = "sh600776";
        historyService.getRealFinancing(symcol);


    }

}
