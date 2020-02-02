package top.hting.htingstock.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 股票历史数据(只包括基本数据)
 */
@Data
@TableName("t_stock_history")
@ToString
public class StockHistoryEntity {

    /**
     * 股票代码，简码
     */
    @Excel(name = "股票代码")
    private String code;
    /**
     * 股票名称
     */
    @Excel(name = "股票名称")
    private String name;

    /**
     * 股票日期,开盘日期
     */
    @Excel(name = "日期",format = "yyyy/MM/dd")
    private Date recordDate;

    /**
     * 收盘价
     */
    @Excel(name = "收盘价")
    private Double closePri;

    /**
     * 最高价
     */
    @Excel(name = "最高价")
    private Double maxPri;

    /**
     * 最低价
     */
    @Excel(name = "最低价")
    private Double minPri;

    /**
     * 开盘价
     */
    @Excel(name = "开盘价")
    private Double openPri;

    /**
     * 上一个交易日收盘价
     */
    @Excel(name = "前收盘")
    private Double yestPri;


    /**
     * 涨跌额
     */
    @Excel(name = "涨跌额")
    private Double increase;

    /**
     * 涨跌百分比 * 10 之后的结果
     */
    @Excel(name = "涨跌百幅")
    private Double increasePer;

    /**
     * 换手率
     */
    @Excel(name = "换手率")
    private Double turnRate;

    /**
     * 成交量 万手?
     */
    @Excel(name = "成交量")
    private Double traNumber;
    /**
     * 成交额  万元
     */
    @Excel(name = "成交金额")
    private Double traAmount;

    /**
     * 总市值 万元
     */
    @Excel(name = "总市值")
    private Double totalAmount;
    /**
     * 流通值 万元
     */
    @Excel(name = "流通市值")
    private Double circulateAmount;

    // TODO 主力注入，主力流出，主力净流入， 超大单，大单，中单，小单


}
