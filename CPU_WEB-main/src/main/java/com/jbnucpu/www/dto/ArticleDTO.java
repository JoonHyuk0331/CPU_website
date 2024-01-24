package com.jbnucpu.www.dto;

// 공지사항과 자유글 DTO 통일

import com.jbnucpu.www.entity.ContentEntity;
import com.jbnucpu.www.entity.NoticeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {

    private String title; // mustache form name 속성 기반으로 DTO에 넣어줌

    private String content;

    public ContentEntity toContentEntity(){

        ContentEntity contentEntity = new ContentEntity();
        
        contentEntity.setName("홍길동");

        contentEntity.setTitle(title);

        contentEntity.setContent(content);

        return contentEntity;
    }

    public NoticeEntity toNoticeEntity(){

        NoticeEntity noticeEntity = new NoticeEntity();
        
        noticeEntity.setName("임꺽정");

        noticeEntity.setTitle(title);

        noticeEntity.setContent(content);

        return noticeEntity;
    }

}
