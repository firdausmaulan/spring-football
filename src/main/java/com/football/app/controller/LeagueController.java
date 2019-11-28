package com.football.app.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.football.app.model.League;
import com.football.app.repository.LeagueRepository;

@RestController
@RequestMapping("/league")
public class LeagueController {

	@Autowired
	LeagueRepository repository;

	@GetMapping("/")
	public List<League> getAll() {
		return repository.findAll();
	}

	@PostMapping("/")
	public League addLeague(@Valid @RequestBody League param) {
		return repository.save(param);
	}

	@PutMapping("/{id}")
	public ResponseEntity<League> updateLeague(@PathVariable(value = "id") Long id, @Valid @RequestBody League param) {
		Optional<League> optional = repository.findById(id);
		if (optional == null || optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		League model = optional.get();
		if (model == null) {
			return ResponseEntity.notFound().build();
		}
		model.setName(param.getName());
		model.setCountry(param.getCountry());
		model.setLogo(param.getLogo());
		League updatedData = repository.save(model);
		return ResponseEntity.ok(updatedData);
	}

	@DeleteMapping("/{id}")
	public String deleteBuku(@PathVariable(value = "id") Long id) {
		Optional<League> optional = repository.findById(id);
		if (optional == null || optional.isEmpty()) {
			return "id " + id + " not found";
		}
		League model = optional.get();
		if (model == null) {
			return "id " + id + " not found";
		}
		repository.delete(model);
		return "id " + id + " deleted";
	}

	@GetMapping("/{id}")
	public ResponseEntity<League> getBukuById(@PathVariable(value = "id") Long id) {
		Optional<League> optional = repository.findById(id);
		if (optional == null || optional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		League model = optional.get();
		if (model == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(model);
		}
	}

	@GetMapping("/sort")
	public List<League> sortByName(@RequestParam(value = "name") String name) {
		return repository.findByName(name);
	}

	@GetMapping("/sort/{name}")
	public List<League> sortByName2(@PathVariable(value = "name") String name) {
		return repository.findByName(name);
	}
}
