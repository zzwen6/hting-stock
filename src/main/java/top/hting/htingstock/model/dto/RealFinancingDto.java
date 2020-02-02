package top.hting.htingstock.model.dto;

import lombok.Data;

import java.util.Date;

/**
 * http://qt.gtimg.cn/q=ff_sh600519
 *
 *  0: 股票代码
 *   1: 主力流入
 *   2: 主力流出
 *   3: 主力净流入
 *   4: 主力净流入/资金流入流出总和
 *   5: 散户流入
 *   6: 散户流出
 *   7: 散户净流入
 *   8: 散户净流入/资金流入流出总和
 *   9: 资金流入流出总和1+2+5+6
 *  10: 未知
 *  11: 未知
 *  12: 名字
 *  13: 日期
 *
 * 实时资金
 */
@Data
public class RealFinancingDto {

    /**
     * 代码 带前缀
     */
    private String symbol;

    /**
     * 主力流入
     */
    private Double mainEnter;

    /**
     * 主办流出
     */
    private Double mainOut;

    /**
     * 主力净注入
     */
    private Double mainNetEnter;

    /**
     * 主力净流入流出总和
     */
    private Double mainTotal;

    /**
     * 散户流入
     */
    private Double retailEnter;

    /**
     * 散户流出
     */
    private Double retailOut;

    /**
     * 散户净注入
     */
    private Double retailNetEnter;

    /**
     * 散户净流入流出总和
     */
    private Double retailTotal;


    /**
     * 资金注入流出总和
     */
    private Double financingTotal;


    /**
     * 交易日期
     */
    private Date recordDate;


}
