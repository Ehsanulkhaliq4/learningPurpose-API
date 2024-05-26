package com.exam.examserver.model.blog;

import com.exam.examserver.helper.PostDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 5000)
    private String content;
    private String postedBy;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;
    private Date date;
    private int viewContent;
    private List<String> tags;

    public PostDto getDto() {
        PostDto postDto = new PostDto();
        postDto.setId(id);
        postDto.setName(name);
        postDto.setPostedBy(postedBy);
        postDto.setTags(tags);
        postDto.setByteImg(img);
        postDto.setViewContent(viewContent);
        return postDto;
    }
}