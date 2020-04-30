package com.elearning.Beans;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.elearning.Beans.Formation;;

@Entity
public class Element {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idElement;
	@Column
	private String titreElement;
	@Column
	private int nbrPlace;
	@Column
	private int temps;
	@Column
	private float prix;
	@Column
	private Date date;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_formation")
	private Formation formation;
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="user_element" , joinColumns=@JoinColumn(name="idElement") , inverseJoinColumns=@JoinColumn(name="idUser"))
	private List<User> users;
	
	
	public long getIdElement() {
		return idElement;
	}
	public void setIdElement(long idElement) {
		this.idElement = idElement;
	}
	public String getTitreElement() {
		return titreElement;
	}
	public void setTitreElement(String titreElement) {
		this.titreElement = titreElement;
	}
	public int getNbrPlace() {
		return nbrPlace;
	}
	public void setNbrPlace(int nbrPlace) {
		this.nbrPlace = nbrPlace;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTemps() {
		return temps;
	}
	public void setTemps(int temps) {
		this.temps = temps;
	}
	public Element() {
		super();
	}

	public Formation getFormation() {
		return formation;
	}
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public Element(long idElement, String titreElement, int nbrPlace, int temps, float prix, Date date) {
		super();
		this.idElement = idElement;
		this.titreElement = titreElement;
		this.nbrPlace = nbrPlace;
		this.temps = temps;
		this.prix = prix;
		this.date = date;
	}
	@Override
	public String toString() {
		return "element [idElement=" + idElement + ", titreElement=" + titreElement + ", nbrPlace=" + nbrPlace
				+ ", temps=" + temps + ", prix=" + prix + ", date=" + date + "]";
	}

	
	
	
	
}
