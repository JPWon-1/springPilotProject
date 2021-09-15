package com.start.pilotproject.controller;

import java.time.LocalDate;
import java.util.List;

import com.start.pilotproject.domain.comment.Comments;
import com.start.pilotproject.domain.history.History;
import com.start.pilotproject.repository.history.HistoryRepository;
import com.start.pilotproject.service.history.HistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        List<History> findByMonthAndDay = historyRepository.findByMonthAndDay(month,day,Sort.by("year").ascending());
        model.addAttribute("histories",findByMonthAndDay);
        // model.addAttribute("histories",historyRepository.findAll());
        return "main";
    }
   
    @GetMapping("/{id}")
    public String detail(Model model, @PathVariable Long id){
        History history = historyService.findById(id);
        List<Comments> comments = history.getComments();
        model.addAttribute("history",history);
        model.addAttribute("comments",comments);
        return "detail";
    }
   
}
