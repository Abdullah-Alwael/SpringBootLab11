package com.spring.boot.springbootlab11.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "email should not be empty")
    private String email;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "userName should not be empty")
    private String userName;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "password should not be empty")
    private String password;

    @Column(columnDefinition = "datetime not null default now()")
    @NotNull(message = "registration_date should not be empty")
    private LocalDate registration_date;
}
