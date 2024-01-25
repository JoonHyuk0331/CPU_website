package com.jbnucpu.www.controller.study;

import org.springframework.ui.Model;
import com.jbnucpu.www.dto.StudyDTO;
import com.jbnucpu.www.entity.StudyEntity;
import com.jbnucpu.www.service.StudyService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudyController {

    private final StudyService studyService;

    public StudyController(StudyService studyService){
        this.studyService = studyService;
    }

    
    // 스터디 메인 (스터디 목록 보여줌)
    @GetMapping("/study")
    public String studyList(Model model){
        model.addAttribute("studydatas",studyService.getList());
        return "studylist";
    }

    // 개별 상세 스터디 페이지 조회
    @GetMapping("/study/{no}")
    public String studyNo(@PathVariable("no")Long no, Model model){

        model.addAttribute("studydata", studyService.findStudy(no).get());
        model.addAttribute("studyMemberdata", studyService.findStudyMember(no));

        return "studyno";
    }

    // 스터디 개설
    @GetMapping("/study/open")
    public String studyOpen(){

        return "studyopen";
    }

    @PostMapping("/study/open")
    public String processStudyOpen(@Valid StudyDTO studyDTO){ //@Valid 어노테이션으로 null 값 들어오면 에러 발생 시키기

        Boolean isSigned = studyService.processOpenStudy(studyDTO);

        return "redirect:/study";
    }

    // 스터디 수정
    @GetMapping("/study/edit/{no}")
    public String studyUpdateForm(@PathVariable("no")Long no, Model model){
        model.addAttribute("studydata", studyService.findStudy(no).get());
        return "studyedit";
    }

    @PostMapping("/study/edit/{no}")
    public String studyUpdate(@PathVariable("no")Long no,@Valid StudyDTO studyDTO){
        studyService.editStudy(studyDTO,no);//studyDTO로 no번째 엔티티를 수정
        return "redirect:/study/{no}";
    }

    //스터디 삭제
    @GetMapping("/study/delete/{no}")
    public String studyDelete(@PathVariable("no")Long no){
        studyService.deleteStudy(no);

        return "redirect:/study";
    }

    // 스터디 신청
    @PostMapping("/study/apply/{no}")
    public String studyApply(@PathVariable("no")Long no){
        studyService.applyStudy(no);

        return "redirect:/study/{no}";
    }

    // 스터디 삭제
    @PostMapping("/study/quit/{no}")
    public String studyQuit(@PathVariable("no")Long no){
        studyService.quitStudy(no);

        return "redirect:/study/{no}";
    }
}
