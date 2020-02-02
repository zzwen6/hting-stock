package top.hting.htingstock.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JuheStockResponseDto implements Serializable {

    private int error_code;

    private String reason;

    private JuheStockResult result;


}
