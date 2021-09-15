package com.start.pilotproject.repository.history;

import java.util.List;

import com.start.pilotproject.domain.history.History;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History,Long>{
    List<History> findAll();

    List<History> findByMonthAndDay(int month, int day, Sort sort);
}
