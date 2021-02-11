package it.HiTech.model;

import java.io.Serializable;

public class ContenutoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	int Ordine_Codice;
	String Prodotto_Codice;
	String Utente;
	int quantita;
	
	public ContenutoBean() {
		
		Ordine_Codice= 0;
		Prodotto_Codice="";
		quantita=1;
		Utente="";
	}
	
	public String getUtente() {
		return Utente;
	}

	public void setUtente(String utente) {
		Utente = utente;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public void incQuantita() {
		this.quantita ++;
	}

	public int getOrdine_Codice() {
		return Ordine_Codice;
	}
	public void setOrdine_Codice(int i) {
		Ordine_Codice = i;
	}
	public String getProdotto_Codice() {
		return Prodotto_Codice;
	}
	public void setProdotto_Codice(String prodotto_Codice) {
		Prodotto_Codice = prodotto_Codice;
	}
	
	public boolean equals(Object otherObject){
	if (!super.equals(otherObject)) return false;
	ContenutoBean other =
	(ContenutoBean)otherObject;
	return Prodotto_Codice.equals(other.Prodotto_Codice) && this.Ordine_Codice==other.Ordine_Codice;
	}

	@Override
	public String toString() {
		return "ContenutoBean [Ordine_Codice=" + Ordine_Codice + ", Prodotto_Codice=" + Prodotto_Codice + ", Utente="
				+ Utente + ", quantita=" + quantita + "]";
	}



}