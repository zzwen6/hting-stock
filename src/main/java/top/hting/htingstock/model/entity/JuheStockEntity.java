package top.hting.htingstock.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_stock")
public class JuheStockEntity implements Serializable {

    /**
     * 股票代码带标记
     */
    @TableId("symbol")
    private String symbol;
    /**
     * 股票名称
     */
    private String name;
    /**
     * 简码
     */
    private String code;


    /**
     * 股票所属市场
     */
    private String symMarket;

    /**
     * 创建时间
     */
    private Date sysCreate;




}
