package com.nttdata.demo.channel_api_client.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRateException extends Exception{

    private Integer status;
    private String message;
    private String code;


}
