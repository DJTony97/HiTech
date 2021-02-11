package it.HiTech.model;

	import java.io.Serializable;

	public class UtenteBean implements Serializable {

		private static final long serialVersionUID = 1L;
		
		String Username;
		String CF;
		String password;
		String Nome;
		String Cognome;
		String email;
		int Type;
		String Indirizzo;
		String Citta;	
		String CAP;
		String Provincia;
		String Regione;
		
		
		public UtenteBean() {
			Username = "";
			CF = "";
			password = "";
			Nome = "";
			Cognome ="";
			email = "";
			Type = 0;
			Indirizzo = "";
			Citta = "";
			CAP = "";
			Provincia = "";
			Regione ="";			
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

		public String getUsername() {
			return Username;
		}


		public void setUsername(String username) {
			this.Username = username;
		}


		public String getCF() {
			return CF;
		}


		public void setCF(String cF) {
			this.CF = cF;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getNome() {
			return Nome;
		}


		public void setNome(String nome) {
			this.Nome = nome;
		}


		public int getType() {
			return Type;
		}


		public void setType(int type) {
			this.Type = type;
		}


		public String getIndirizzo() {
			return Indirizzo;
		}


		public void setIndirizzo(String indirizzo) {
			this.Indirizzo = indirizzo;
		}


		@Override 
		public boolean equals(Object otherObject){
			if (!super.equals(otherObject)) return false;
			UtenteBean other =
			(UtenteBean)otherObject;
			return Username.equals(other.Username);
			}
		
		@Override
		public String toString() {
			String utente=Username + ": " + Nome + Cognome + " (" + CF +") " + email +", " + Indirizzo + Citta + CAP + Provincia + Regione + ", ";;
			if(Type == 9 ) utente.concat("Utente");
				else utente.concat("Admin");
			return utente;
		}
		public String getCompleteIndirizzo() {
			String indirizzo= Indirizzo +", "+ Citta+", "+ CAP+", " + Provincia+", " + Regione  ;
			return indirizzo;
		}
		public String getCognome() {
			return Cognome;
		}
		public void setCognome(String cognome) {
			Cognome = cognome;
		}
		public String getCitta() {
			return Citta;
		}
		public void setCitta(String citta) {
			Citta = citta;
		}
		public String getCAP() {
			return CAP;
		}
		public void setCAP(String cAP) {
			CAP = cAP;
		}
		public String getProvincia() {
			return Provincia;
		}
		public void setProvincia(String provincia) {
			Provincia = provincia;
		}
		public String getRegione() {
			return Regione;
		}
		public void setRegione(String regione) {
			Regione = regione;
		}
}
