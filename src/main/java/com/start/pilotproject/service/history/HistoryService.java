package com.start.pilotproject.service.history;

import javax.persistence.EntityNotFoundException;

import com.start.pilotproject.domain.history.History;
import com.start.pilotproject.repository.history.HistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {

    @Autowired
    HistoryRepository historyRepository;

    public Long save() {
        return null;
    }

    public Long update(Long id) {
        return null;
    }

    public History findById(Long id) {
        return historyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteById(Long id) {
    }
    
}
