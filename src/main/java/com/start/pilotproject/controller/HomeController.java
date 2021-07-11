package com.start.pilotproject.controller;

import com.start.pilotproject.repository.history.HistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    HistoryRepository historyRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("historyList",historyRepository.findAll());
        return "main";
    }
   
}
