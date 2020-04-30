package com.elearning.Beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idFormation;
	@Column
	private String titreFormation;
	@Column
	private String categorie;
	@Column
	private String objectif;
	@Column
	private String prerequis;
	@Column
	private String etablissement;
	
	@OneToMany(mappedBy = "formation")
	private List<Element> elements;
	
	@ManyToOne(targetEntity = User.class)
	private User formateur;
	
	public long getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(long idFormation) {
		this.idFormation = idFormation;
	}

	public String getTitreFormation() {
		return titreFormation;
	}
	public void setTitreFormation(String titreFormation) {
		this.titreFormation = titreFormation;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getObjectif() {
		return objectif;
	}
	public void setObjectif(String objectif) {
		this.objectif = objectif;
	}
	public String getPrerequis() {
		return prerequis;
	}
	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}
	public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}


	public List<Element> getElements() {
		return elements;
	}
	public void setElements(List<Element> elements) {
		this.elements = elements;
	}
	public User getFormateur() {
		return formateur;
	}
	public void setFormateur(User formateur) {
		this.formateur = formateur;
	}
	public Formation() {
		super();
	}
	public Formation(long idFormation, String titreFormation, String categorie, String objectif, String prerequis,
			String etablissement) {
		super();
		this.idFormation = idFormation;
		this.titreFormation = titreFormation;
		this.categorie = categorie;
		this.objectif = objectif;
		this.prerequis = prerequis;
		this.etablissement = etablissement;
	}
	@Override
	public String toString() {
		return "Formation [idFormation=" + idFormation + ", titreFormation=" + titreFormation + ", categorie="
				+ categorie + ", objectif=" + objectif + ", prerequis=" + prerequis + ", etablissement=" + etablissement
				+ "]";
	}

	
	
	
	
	
	
}
