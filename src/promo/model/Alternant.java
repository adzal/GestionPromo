package promo.model;

import java.io.Serializable;
import java.time.LocalDate;

//c'est une classe fille qui va h�riter tous les attributs de la classe parent "apprenant" en ajoutant des autres 
//qui correspondent � un alternant(nom d'entreprise et salaire)
public class Alternant extends Apprenant {

	protected String nomEntreprise;
	protected double salaire;

	public Alternant() {
	}

	public Alternant(String nom, String pr�nom, LocalDate dateInscription, String email, String mobile,
			String nomEntreprise, double salaire) {
		super(nom, pr�nom, dateInscription, email, mobile);
		this.nomEntreprise = nomEntreprise;
		this.salaire = salaire;
	}
	
	//getters et setters
	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public Double getSalaire() {
		return salaire;
	}

	public void setSalaire(double salaire) {
		this.salaire = salaire;
	}

	@Override
	public String toString() {
		return "Alternant [nomEntreprise=" + nomEntreprise + ", salaire=" + salaire + "]";
	}

	public void salaire(double percent) {

		this.salaire = this.salaire + (this.salaire * percent / 100);

	}

}
