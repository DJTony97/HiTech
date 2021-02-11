package it.HiTech.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class OrdineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int Codice;
	String RifCliente;
	String data;
	String Utente_Username;
	String MetodoPagamento;
	String Numero_Spedizione;
	String Corriere;
	String Metodo_Spedizione;
	String Indirizzo;
	String Citta;
	String Cap;
	String provincia;
	String regione;

	@Override
	public String toString() {
		return "OrdineBean [Codice=" + Codice + ", RifCliente=" + RifCliente + ", data=" + data + ", Utente_Username="
				+ Utente_Username + ", MetodoPagamento=" + MetodoPagamento + ", Numero_Spedizione=" + Numero_Spedizione
				+ ", Corriere=" + Corriere + ", Metodo_Spedizione=" + Metodo_Spedizione + ", Indirizzo=" + Indirizzo
				+ ", Citta=" + Citta + ", Cap=" + Cap + ", provincia=" + provincia + ", regione=" + regione + "]";
	}


	public OrdineBean() {
		Codice = 0;
		RifCliente="";
		data = "";
		Utente_Username = "";
		MetodoPagamento="";
		Numero_Spedizione="";
		Corriere="";
		Metodo_Spedizione="";
		Indirizzo= "";
		Citta= "";
		Cap= "";
		provincia= "";
		regione= "";
	}


	public String getRifCliente() {
		return RifCliente;
	}


	public void setRifCliente(String rifCliente) {
		RifCliente = rifCliente;
	}


	public String getMetodoPagamento() {
		return MetodoPagamento;
	}


	public void setMetodoPagamento(String metodoPagamento) {
		MetodoPagamento = metodoPagamento;
	}


	public String getMetodo_Spedizione() {
		return Metodo_Spedizione;
	}


	public void setMetodo_Spedizione(String metodo_Spedizione) {
		Metodo_Spedizione = metodo_Spedizione;
	}


	public String getCitta() {
		return Citta;
	}


	public void setCitta(String citta) {
		Citta = citta;
	}


	public String getCap() {
		return Cap;
	}


	public void setCap(String cap) {
		Cap = cap;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getRegione() {
		return regione;
	}


	public void setRegione(String regione) {
		this.regione = regione;
	}


	public int getCodice() {
		return Codice;
	}


	public void setCodice(int codice) {
		this.Codice = codice;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}

	public void setDataAuto() {
		GregorianCalendar gc = new GregorianCalendar();
		this.data=gc.get(Calendar.YEAR)+"-"+gc.get(Calendar.MONTH)+"-"+gc.get(Calendar.DAY_OF_MONTH);
	}
	



	public String getUtente_Username() {
		return Utente_Username;
	}


	public void setUtente_Username(String utente_Username) {
		this.Utente_Username = utente_Username;
	}





	public String getIndirizzo() {
		return Indirizzo;
	}


	public void setIndirizzo(String indirizzo) {
		this.Indirizzo = indirizzo;
	}
	public String getNumero_Spedizione() {
		return Numero_Spedizione;
	}


	public void setNumero_Spedizione(String numero_Spedizione) {
		Numero_Spedizione = numero_Spedizione;
	}


	public String getCorriere() {
		return Corriere;
	}


	public void setCorriere(String corriere) {
		Corriere = corriere;
	}

	public boolean isEmpty() {
		return Codice==0 ;
	}
	
	@Override 
	public boolean equals(Object otherObject){
		if (!super.equals(otherObject)) return false;
		OrdineBean other = (OrdineBean)otherObject;
		return Codice==other.Codice;
		}
	

}
