package top.hting.htingstock.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JuheStockResult implements Serializable {
    private String totalCount;

    private String page;

    private String num;

    private List<JuheStockDto> data;

}
