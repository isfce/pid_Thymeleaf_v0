package org.isfce.pid.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.isfce.pid.controller.error.DuplicateException;
import org.isfce.pid.dao.UeDao;
import org.isfce.pid.model.Cours;
import org.isfce.pid.model.UE;
import org.isfce.pid.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/cours/", produces = "application/json")
@Slf4j
public class CoursControllerRest {

	private CoursService coursService;
	@Autowired
	private MessageSource bundle;

	public CoursControllerRest(CoursService coursService) {
		this.coursService = coursService;
	}

	@GetMapping({ "info/{code}", "info2" })
	ResponseEntity<Cours> getCours(@PathVariable(name = "code") String id) {
		log.info("cours path" + id);

		Optional<Cours> oCours = coursService.getCours(id);

		if (oCours.isPresent())
			return new ResponseEntity<Cours>(oCours.get(), HttpStatus.OK);
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@GetMapping("liste")
	ResponseEntity<List<Cours>> getListeCours() {
		return ResponseEntity.ok(coursService.getListe());
	}

	@PostMapping(path = "add", consumes = "application/json")
	ResponseEntity<Cours> addCoursPost(@Valid @RequestBody Cours cours, Locale locale) {
		if (coursService.existCours(cours.getCode()))
			throw new DuplicateException(bundle.getMessage("err.cours.doublon", null, locale), "code");

		log.info("Ajout d'un cours: " + cours);
		coursService.addCours(cours);
		return new ResponseEntity<>(cours, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "{code}/delete")
	ResponseEntity<String> deleteCours(@PathVariable(value = "code") String code) {
		if (!coursService.existCours(code))
			return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
		coursService.deleteCours(code);
		return ResponseEntity.ok(code);
	}

}
