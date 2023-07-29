package com.tokenforpdf.token.dto;

import com.tokenforpdf.token.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceDto {
    private Long id;
    private Double total;

}
