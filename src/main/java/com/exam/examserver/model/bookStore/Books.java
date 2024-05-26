package com.exam.examserver.model.bookStore;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "book_catalog")
@Data
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_title")
    private String title;
    @Column(name = "book_author_name")
    private String author;
    private Date postedDate;
    private String contentType;
    @Column(name = "book_description",length = 5000)
    private String description;
    @Lob
    @Column(name = "book_picture",columnDefinition = "longblob")
    private byte[] bookPicture;
    @Lob
    @Column(name="book",columnDefinition = "longblob")
    private byte[] pdfBook;
}
