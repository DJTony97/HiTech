package it.HiTech.model;

import java.io.InputStream;
import java.io.Serializable;

public class ProdottoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String codice;
	String nome;
	String marca;
	String descrizione;
	float prezzo;
	int scorte;
	public int getScorte() {
		return scorte;
	}

	public void setScorte(int scorte) {
		this.scorte = scorte;
	}

	private String img;
	InputStream input;
	 
	 
    public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getImg() {
        return img;
    }
 
    public void setImg(String base64Image) {
        this.img = base64Image;
    }
	public ProdottoBean() {
		codice = "";
		nome = "";
		marca="";
		scorte=0;
		descrizione = "";
		prezzo = 0;
		img="";
		input=null;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public boolean isEmpty() {
		return codice.equals("");
	}

	
	@Override 
	public boolean equals(Object otherObject){
		if (!super.equals(otherObject)) return false;
		ProdottoBean other =
		(ProdottoBean)otherObject;
		return codice.equals(other.codice);
		}
	
	@Override
	public String toString() {
		return marca + ": " + nome + " (" + codice +") " + prezzo + ", "+ descrizione;
	}
	
}
