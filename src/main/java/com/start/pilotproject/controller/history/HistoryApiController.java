package com.start.pilotproject.controller.history;

import com.start.pilotproject.domain.history.History;
import com.start.pilotproject.repository.history.HistoryRepository;
import com.start.pilotproject.service.history.HistoryService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/history")
@RestController
public class HistoryApiController {
    private final HistoryService historyService;
    private final HistoryRepository historyRepository;

    @PostMapping("/api/v1")//작성
    public Long save(){
        return historyService.save();
    }

    @PutMapping("/api/v1/{id}")//수정
    public Long update(@PathVariable Long id ){
        return historyService.update(id);
    }

    @GetMapping("/api/v1/{id}")//단건 조회
    public History findById(@PathVariable Long id,Model model){
        return historyService.findById(id);
    }

    @DeleteMapping("/api/v1/{id}")//삭제
    public Long delete(@PathVariable Long id){
        historyService.deleteById(id);
        return id;
    }

}