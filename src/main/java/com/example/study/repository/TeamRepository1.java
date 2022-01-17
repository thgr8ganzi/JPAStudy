package com.example.study.repository;

import com.example.study.entity.Member;
import com.example.study.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository1 extends JpaRepository<Team, Long> {


}