package top.hting.htingstock.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import top.hting.htingstock.dao.JuheStockMapper;
import top.hting.htingstock.model.dto.JuheStockDto;
import top.hting.htingstock.model.dto.JuheStockResponseDto;
import top.hting.htingstock.model.enums.JuheApiEnum;
import top.hting.htingstock.service.JuheApiService;

import java.util.ArrayList;
import java.util.List;

@Service
public class JuheApiServiceImpl implements JuheApiService {

    @Value("${api.juhe.url}")
    private String apiUrl;

    @Value("${api.juhe.key}")
    private String apiKey;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<JuheStockDto> getShList() {
        return getStockList(JuheApiEnum.SHALL);

    }

    @Override
    public List<JuheStockDto> getSzList() {
        return getStockList(JuheApiEnum.SZALL);

    }

    @Override
    public List<JuheStockDto> getStockList(JuheApiEnum apiEnum) {
        // 页面大小
        int pageSize = 80;
        // 页面总数
        int totalPage = 2;
        // 是否第一次请求
        boolean first = true;

        String url = apiUrl + "/" +apiEnum.getKey()+"?key=" + apiKey + "&type=4&page=%d";

        List<JuheStockDto> datas = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {

            String getUrl = String.format(url, i);

            String tempStr = restTemplate.getForObject(getUrl, String.class);
            JuheStockResponseDto responseDto = JSON.parseObject(tempStr, JuheStockResponseDto.class);

            List<JuheStockDto> data = responseDto.getResult().getData();
            datas.addAll(data);

            if (first) {
                first = false;
                int totalCount = Integer.parseInt(responseDto.getResult().getTotalCount());
                int page = (totalCount + pageSize - 1) / pageSize;
                // 重新更新页数
                totalPage = page;
            }
        }

        return datas;
    }
}
