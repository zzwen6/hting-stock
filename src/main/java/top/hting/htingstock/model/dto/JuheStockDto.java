package top.hting.htingstock.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 聚合股票实体
 */
@NoArgsConstructor
@Data
public class JuheStockDto implements Serializable {

    /**
     * symbol : sz000001
     * name : 平安银行
     * trade : 16.590
     * pricechange : 0.040
     * changepercent : 0.242
     * buy : 16.580
     * sell : 16.590
     * settlement : 16.550
     * open : 16.550
     * high : 16.680
     * low : 16.440
     * volume : 644478
     * amount : 1067869780
     * code : 000001
     * ticktime : 15:00:03
     */

    /**
     * 股票代码带标记
     */
    private String symbol;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 最新价
     */
    private String trade;
    /**
     * 涨跌额
     */
    private String pricechange;
    /**
     * 涨跌比
     */
    private String changepercent;
    /**
     * 买入
     */
    private String buy;
    /**
     * 卖出
     */
    private String sell;
    /**
     * 昨收
     */
    private String settlement;
    /**
     * 今开
     */
    private String open;
    /**
     * 最高
     */
    private String high;
    /**
     * 最低
     */
    private String low;
    /**
     * 成交量
     */
    private Long volume;
    /**
     * 成交额
     */
    private Long amount;
    /**
     * 简码
     */
    private String code;
    /**
     * 时间
     */
    private String ticktime;


    /**
     * 股票所属市场
     */
    private String symMarket;


    public String getSymMarket() {
        return symbol.substring(0,2);
    }
}
