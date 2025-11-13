package org.isfce.pid.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TACQUIS")
public class Acquis {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 500)
	private String acquis;
	
	@Size(min = 1, max = 100, message = "{err.aquis.pourcentage}")
	private Integer pourcentage;
	
	
	public Acquis(String acquis, int pourcentage) {
		this.acquis = acquis;
		this.pourcentage = pourcentage;
	}
}
