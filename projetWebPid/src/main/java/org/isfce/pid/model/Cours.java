package org.isfce.pid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="TCOURS")
public class Cours {
	@Id
	private String code;
	@Column(nullable = false,length = 50)
	private String nom;
	@Column(nullable = false)
	private short nbPeriodes;
}
