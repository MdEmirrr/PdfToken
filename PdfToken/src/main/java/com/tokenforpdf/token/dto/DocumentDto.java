package com.tokenforpdf.token.dto;

import com.tokenforpdf.token.model.Document;
import com.tokenforpdf.token.model.User;
import lombok.*;

import java.net.URLConnection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDto {

    private Long id;
    private String documentName;
    private Long documentSize;
    private Long documentWordSize;
    private String tokenCost;





}

