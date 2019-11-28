package com.football.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.football.app.model.League;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
	List<League> findByName(String name);
}
