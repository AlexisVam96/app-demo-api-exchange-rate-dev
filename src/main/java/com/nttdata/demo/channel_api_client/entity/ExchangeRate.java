package com.nttdata.demo.channel_api_client.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_exchange_rate")
public class ExchangeRate {

    @Id
    private Integer id;

    private String type;

    private Double valor;

    private LocalDateTime create_at;

}
