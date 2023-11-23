package fr.tp2.utils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Individu {
	private String nom;
	private String prenom;
	private int taille;
	private Date dateDeNaissance;
	private Ville lieuDeNaissance;
	private int poids;
	private List<Hobby> hobbys;
	
	public Individu(String nom,String prenom,int taille,Date dateDeNaissance,Ville lieuDeNaissance,int poids,List<Hobby>  hobbys) {
		this.nom = nom;
		this.prenom = prenom;
		this.taille = taille;
		this.dateDeNaissance = dateDeNaissance;
		this.lieuDeNaissance = lieuDeNaissance;
		this.poids = poids;
		this.hobbys = hobbys;
	}
	
	@Override
	public String toString() {
		return 
				"Je suis " + getNom()
				+ " "+getPrenom()
				+ " je pèse "+ getPoids() + "kg,"
				+ " je mesure "+ getTaille() + "cm, "
				+ " je suis né le " + getDateDeNaissance() + " à " + getLieuDeNaissance()
				+ " et j'aime " + getHobbys().stream().map(Object::toString).collect(Collectors.joining(", "))
			;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public int getTaille() {
		return taille;
	}

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public Ville getLieuDeNaissance() {
		return lieuDeNaissance;
	}

	public int getPoids() {
		return poids;
	}

	public List<Hobby> getHobbys() {
		return hobbys;
	} 
	
}
