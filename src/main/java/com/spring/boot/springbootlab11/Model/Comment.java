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
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer comment_id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "user_id should not be empty")
    private Integer user_id;

    @Column(columnDefinition = "int not null")
    @NotNull(message = "post_id should not be empty")
    private Integer post_id;

    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "content should not be empty")
    private String content;

    @Column(columnDefinition = "datetime not null default now()")
    @NotNull(message = "comment_date should not be empty")
    private LocalDate comment_date;

}
