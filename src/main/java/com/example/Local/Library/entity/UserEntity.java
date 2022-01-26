package com.example.Local.Library.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_tbl")
public class UserEntity {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private int rentBook;
    private double cost;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public void incrementRentBook() {
        this.rentBook = this.rentBook + 1;
    }
}
