package org.isfce.pid.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cours {
	private String code;
	private String nom;
	private short nbPeriodes;
}
