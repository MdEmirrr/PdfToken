package com.tokenforpdf.token.dto;

import com.tokenforpdf.token.model.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private String email;
    private String password;


}
