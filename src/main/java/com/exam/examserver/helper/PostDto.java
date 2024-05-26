package com.exam.examserver.helper;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostDto {

    private Long id;
    private String name;

    private String content;
    private String postedBy;

    private byte[] byteImg;
    private List<String> tags;
    private int viewContent;

    private MultipartFile img;
}
