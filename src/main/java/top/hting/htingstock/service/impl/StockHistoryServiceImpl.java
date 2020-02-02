package top.hting.htingstock.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.hting.htingstock.model.dto.RealFinancingDto;
import top.hting.htingstock.model.entity.StockHistoryEntity;
import top.hting.htingstock.service.StockHistoryService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockHistoryServiceImpl implements StockHistoryService {

    /**
     * 网易接口，历史数据
     */
    @Value("${api.netease.history-url}")
    private String neteaseHistoryApi;

    /**
     * 实时资金api
     */
    @Value("${api.tencent.url}")
    private String financeUrl;


    @Autowired
    RestTemplate restTemplate;

    /**
     * 查询参数，股票代码 上海股票前加0 ，深圳股票前加1，时间 yyyyMMdd
     */
    public static final String PARAM = "?code=%s&start=%s&end=%s&fields=TCLOSE;HIGH;LOW;TOPEN;LCLOSE;CHG;PCHG;TURNOVER;VOTURNOVER;VATURNOVER;TCAP;MCAP";


    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    @Override
    public List<StockHistoryEntity> getHistoryByNetease(String code, Date start, Date end) {
        try {

            String url = String.format(neteaseHistoryApi + PARAM, code, format.format(start), format.format(end));

            byte[] bytes = restTemplate.getForObject(url, byte[].class);
            String gbkStr = new String(bytes, "gbk");
            String[] splitStrArray = StringUtils.split(gbkStr, "\r\n");

            List<StockHistoryEntity> list = new ArrayList<>();
            // 第一行开始
            for (int i = 1; i < splitStrArray.length; i++) {
                String[] split = StringUtils.split(splitStrArray[i], ",");
                StockHistoryEntity entity = new StockHistoryEntity();
                entity.setRecordDate(DateUtils.parseDate(split[0], "yyyy-MM-dd"));
                entity.setCode(split[1].replace("'", ""));
                entity.setName(split[2]);
                entity.setClosePri(Double.valueOf(split[3]));
                entity.setMaxPri(Double.valueOf(split[4]));
                entity.setMinPri(Double.valueOf(split[5]));
                entity.setOpenPri(Double.valueOf(split[6]));
                entity.setYestPri(Double.valueOf(split[7]));

                entity.setIncrease(NumberUtils.isDigits(split[8]) ? Double.valueOf(split[8]): 0.0D);

                entity.setIncreasePer(NumberUtils.isDigits(split[9])? Double.valueOf(split[9]): 0.0D);
                entity.setTurnRate(Double.valueOf(split[10]));
                entity.setTraNumber(Double.valueOf(split[11]) /10000);
                entity.setTraAmount(Double.valueOf(split[12]) / 10000);
                entity.setTotalAmount(Double.valueOf(split[13]) / 10000);
                entity.setCirculateAmount(Double.valueOf(split[14]) / 10000);
                list.add(entity);
            }

            return list;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RealFinancingDto getRealFinancing(String symbol) {
        String url = String.format(financeUrl, symbol);

        String object = restTemplate.getForObject(url, String.class);

        String substringBetween = StringUtils.substringBetween(object, "\"");

        String[] split = substringBetween.split("~");

        for (int i = 0; i < split.length; i++) {
            System.out.println(i +"-> "+ split[i]);

        }



        return null;
    }
}
