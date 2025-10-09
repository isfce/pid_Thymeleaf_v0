package org.isfce.pid.controller;

import java.util.Optional;

import org.isfce.pid.service.CoursService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cours/")
@Slf4j
public class CoursController {

	private CoursService coursService;

	public CoursController(CoursService coursService) {
		this.coursService = coursService;
	}

	@GetMapping("info")
	String getCours(@RequestParam("code") String id, Model model) {
		log.info("cours param" + id);
		model.addAttribute("cours", coursService.getCours(id).get());
		return "/cours/cours";
	}

	@GetMapping({ "info2/{code}", "info2" })
	String getCours2(@PathVariable(required = false, name = "code") Optional<String> id, Model model) {
		log.info("cours path" + id);
		String c = id.orElse("IPDB");
		model.addAttribute("cours", coursService.getCours(c).get());
		return "/cours/cours";
	}

	@GetMapping("liste")
	String getListeCours(Model model) {
		model.addAttribute("listeCours", coursService.getListe());
		return "/cours/listeCours";
	}

}
