package com.elearning.Beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idReview;
	@Column
	private String commentaire;
	@Column
	private int note_Etablissement;
	@Column
	private int note_Formateur;
	@Column
	private int note_Contenu;
	@OneToOne
	private Element element;
	@OneToOne
	private User beneficiaire;
	
	public long getIdReview() {
		return idReview;
	}
	public void setIdReview(long idReview) {
		this.idReview = idReview;
	}
	public String getCommentaire() {
		return commentaire;
	}
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}
	public int getNote_Etablissement() {
		return note_Etablissement;
	}
	public void setNote_Etablissement(int note_Etablissement) {
		this.note_Etablissement = note_Etablissement;
	}
	public int getNote_Formateur() {
		return note_Formateur;
	}
	public void setNote_Formateur(int note_Formateur) {
		this.note_Formateur = note_Formateur;
	}
	public int getNote_Contenu() {
		return note_Contenu;
	}
	public void setNote_Contenu(int note_Contenu) {
		this.note_Contenu = note_Contenu;
	}
	public Review() {
		super();
	}
	public Review(long idReview, String commentaire, int note_Etablissement, int note_Formateur, int note_Contenu) {
		super();
		this.idReview = idReview;
		this.commentaire = commentaire;
		this.note_Etablissement = note_Etablissement;
		this.note_Formateur = note_Formateur;
		this.note_Contenu = note_Contenu;
	}
	
	
	
	
	
}
