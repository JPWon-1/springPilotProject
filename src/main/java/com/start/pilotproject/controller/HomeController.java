package com.start.pilotproject.controller;

import com.start.pilotproject.domain.history.History;
import com.start.pilotproject.repository.history.HistoryRepository;
import com.start.pilotproject.service.history.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    HistoryRepository historyRepository;

    @Autowired
    HistoryService historyService;

    @GetMapping("/")
    public String home(Model model){
        History testHistory = History.builder()
            .id(1L)
            .content("content")
            .source("source")
            .build();

        History testHistory2 = History.builder()
            .id(2L)
            .content("content2")
            .source("source2")
            .build();

        historyRepository.save(testHistory);
        historyRepository.save(testHistory2);

        model.addAttribute("histories",historyRepository.findAll());
        return "main";
    }
   
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Long id){
        History history = historyService.findById(id);
        // history.getComments();
        model.addAttribute("history",history);
        return "detail";
    }
   
}
