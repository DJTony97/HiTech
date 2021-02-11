package it.HiTech.model;

import java.io.Serializable;

public class AppartieneBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String Prodotto_Codice;
	String Categoria_Nome;
	
	public AppartieneBean() {
		Prodotto_Codice="";
		Categoria_Nome="";
	}
	
	public String getProdotto_Codice() {
		return Prodotto_Codice;
	}
	public void setProdotto_Codice(String prodotto_Codice) {
		Prodotto_Codice = prodotto_Codice;
	}
	public String getCategoria_Nome() {
		return Categoria_Nome;
	}
	public void setCategoria_Nome(String categoria_Nome) {
		Categoria_Nome = categoria_Nome;
	}
	
	public boolean equals(Object otherObject){
	if (!super.equals(otherObject)) return false;
	AppartieneBean other =
	(AppartieneBean)otherObject;
	return Prodotto_Codice.equals(other.Prodotto_Codice) && Categoria_Nome.equals(other.Categoria_Nome);
	}


	public String toString() {
	
		return Prodotto_Codice+"|"+Categoria_Nome;
	}
}