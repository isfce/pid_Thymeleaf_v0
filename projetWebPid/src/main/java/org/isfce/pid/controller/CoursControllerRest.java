package org.isfce.pid.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.isfce.pid.model.Cours;
import org.isfce.pid.service.CoursService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
	ResponseEntity<Cours> addCoursPost(@Valid @RequestBody Cours cours, BindingResult errors) {
		if (errors.hasErrors()) {
			log.error(errors.getAllErrors().toString());
			return new ResponseEntity<Cours>(cours, HttpStatus.BAD_REQUEST);

		}
		if (coursService.existCours(cours.getCode()))
			throw new NoSuchElementException("Le code" + cours.getCode() + "existe déjà");
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

	/*
	 * @GetMapping("liste") String getListeCours(Model model) {
	 * model.addAttribute("listeCours", coursService.getListe()); return
	 * "/cours/listeCours"; }
	 * 
	 * @GetMapping("add") String addCoursGet(@ModelAttribute("cours") Cours cours) {
	 * return "/cours/addCours"; }
	 * 
	 * @PostMapping("add") String addCoursPost(@Valid @ModelAttribute("cours") Cours
	 * cours, BindingResult errors, Model model, RedirectAttributes rModel) { if
	 * (errors.hasErrors()) { log.error(errors.getAllErrors().toString()); return
	 * "/cours/addCours";
	 * 
	 * } if (coursService.existCours(cours)) { errors.rejectValue("code",
	 * "err.cours.doublon"); return "/cours/addCours"; }
	 * 
	 * log.info("Ajout d'un cours: " + cours); coursService.addCours(cours);
	 * rModel.addFlashAttribute(cours); return "redirect:/cours/info?code=" +
	 * cours.getCode(); }
	 */
}
