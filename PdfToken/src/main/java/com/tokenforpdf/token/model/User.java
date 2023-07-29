package com.tokenforpdf.token.model;

import com.tokenforpdf.token.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column
    private Long id;
    private String userName;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Document> documents;

}
