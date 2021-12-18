package com.example.Local.Library.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book_instance_tbl")
public class BookInstanceEntity {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "book_id")
    private Long bookId;
    private String status;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;
}
