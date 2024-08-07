package promo.model;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//on va cr�er les attributs de la promotion (nomPromotion, dateDebutPromotion, dureeTotale, listApprenant) 
//des m�thodes pour g�rer(joursPasses, listeDetails, tempsRetard, jourAbsence)

public class Promotion implements Serializable {
	
	//d�finir le nom de la promotion, le d�lai et l'absence et les mettre par la suite dans une liste
	protected String nomPromotion;

	//getters et setters 
	public int getDelai() {
		return delai;
	}

	public void setDelai(int delai) {
		this.delai = delai;
	}

	public int getAbsence() {
		return absence;
	}

	public void setAbsence(int absence) {
		this.absence = absence;
	}

	public void setNomPromotion(String nomPromotion) {
		this.nomPromotion = nomPromotion;
	}

	public void setApprenants(List<Apprenant> apprenants) {
		this.apprenants = apprenants;
	}

	public String getNomPromotion() {
		return nomPromotion;
	}

	protected LocalDate dateDebutPromotion;

	//m�thode pour la date de d�but de promotion avec retour
	public LocalDate getDateDebutPromotion() {
		return dateDebutPromotion;
	}

	protected int dureeTotal;
	//m�thode pour la dur�e total de la formation avec retour
	public int getDureeTotal() {
		return dureeTotal;
	}

	protected List<Apprenant> apprenants;
	
	//m�thode pour r�cup�rer la liste des apprenants
	public List<Apprenant> getApprenants() {
		return apprenants;
	}
	
	protected int delai;
	protected int absence;

	//m�thode pour la dur�e de la formation et l'absence
	public Promotion(String nomPromotion, LocalDate dateDebutPromotion, int dureeTotal, List<Apprenant> apprenants) {
		super();
		this.nomPromotion = nomPromotion;
		this.dateDebutPromotion = dateDebutPromotion;
		this.dureeTotal = dureeTotal;
		this.apprenants = apprenants;
	}

	public Promotion() {
		this.nomPromotion = "";
		this.dateDebutPromotion = LocalDate.now(Clock.systemDefaultZone());
		this.dureeTotal = 0;
		this.apprenants = new ArrayList<>();
	}

	public void setDateDebutPromotion(LocalDate dateDebutPromotion) {
		this.dateDebutPromotion = dateDebutPromotion;
	}

	public void setDureeTotal(int dureeTotal) {
		this.dureeTotal = dureeTotal;
	}

	// M�thode pour les jours restants
	public Long joursPasses() {

		LocalDate dateDuJour = LocalDate.now(Clock.systemDefaultZone());

		Long diff = ChronoUnit.DAYS.between(dateDebutPromotion, dateDuJour);

		return diff;

	}


	@Override
	public String toString() {
		String s = "Promotion [nomPromotion=" + nomPromotion + ", dateDebutPromotion=" + dateDebutPromotion
				+ ", dureeTotal=" + dureeTotal + ", delai=" + delai + "]";
		for (Apprenant apprenant : apprenants) {
			s += "\n" + apprenant;
		}
		return s;
	}

}
