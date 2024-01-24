package com.jbnucpu.www.service;

import com.jbnucpu.www.dto.ArticleDTO;
import com.jbnucpu.www.entity.ContentEntity;
import com.jbnucpu.www.entity.NoticeEntity;
import com.jbnucpu.www.repository.ContentRepository;
import com.jbnucpu.www.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
public class SaveService {

    private final NoticeRepository noticeRepository;

    private final ContentRepository contentRepository;

    public SaveService(NoticeRepository noticeRepository, ContentRepository contentRepository){
        this.noticeRepository = noticeRepository;
        this.contentRepository = contentRepository;
    }

    //공지사항 저장
    public Boolean processNoticeSave(ArticleDTO articleDTO){

        System.out.println("service aaaa");
        String title = articleDTO.getTitle();
        String content = articleDTO.getContent();

        System.out.println(title);
        System.out.println(content);
        // 제목 유효한지
        if(title == null){
            return false;
        }else if(title.isEmpty()){
            return false;
        }else if(title.isBlank()){
            return false;
        }

        // 내용 유효한지
        if(content == null){
            return false;
        }else if(content.isEmpty()){
            return false;
        }else if(content.isBlank()){
            return false;
        }

        NoticeEntity noticeEntity = articleDTO.toNoticeEntity();

        noticeRepository.save(noticeEntity);

        return true;
    }
    
    //자유글 저장
    public Boolean processContentSave(ArticleDTO articleDTO){

        String title = articleDTO.getTitle();
        String content = articleDTO.getContent();

        // 제목 유효한지
        if(title == null){
            return false;
        }else if(title.isEmpty()){
            return false;
        }else if(title.isBlank()){
            return false;
        }

        // 내용 유효한지
        if(content == null){
            return false;
        }else if(content.isEmpty()){
            return false;
        }else if(content.isBlank()){
            return false;
        }

        ContentEntity contentEntity = articleDTO.toContentEntity();

        contentRepository.save(contentEntity);

        return true;
    }
}
