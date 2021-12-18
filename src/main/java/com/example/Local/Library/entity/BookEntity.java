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
@Table(name = "book_tbl")
@Entity
public class BookEntity {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String bookName;
    @Column(name = "available")
    private Integer available;
    private Integer rented;
    private Double cost;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public void incrementRent() {
        this.rented = this.rented + 1;
    }
}
