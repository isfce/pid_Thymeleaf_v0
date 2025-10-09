package org.isfce.pid.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.isfce.pid.model.Cours;
import org.springframework.stereotype.Service;

@Service
public class CoursService {
	private List<Cours> listeCours = new ArrayList<>();

	public CoursService() {
		listeCours.add(new Cours("IPID", "Projet d'intégration", (short) 100));
		listeCours.add(new Cours("IPDB", "Projet de SGBDR", (short) 80));
		listeCours.add(new Cours("IIBD", "Initiation aux bases de données", (short) 60));
	}

	public List<Cours> getListe() {
		return listeCours;
	}

	public Optional<Cours> getCours(String id) {
		return listeCours.stream().filter((e) -> e.getCode().equals(id)).findFirst();
	}

}
