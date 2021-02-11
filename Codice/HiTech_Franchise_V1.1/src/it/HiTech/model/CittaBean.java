package it.HiTech.model;

	import java.io.Serializable;

	public class CittaBean implements Serializable {

		private static final long serialVersionUID = 1L;
		
		String Citta;
		String CAP;
		String Provincia;
		String Regione;

		
		
		
		public CittaBean() {
			Citta = "";
			CAP = "";
			Provincia ="";
			Regione = "";

			
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



		@Override 
		public boolean equals(Object otherObject){
			if (!super.equals(otherObject)) return false;
			CittaBean other =
			(CittaBean)otherObject;
			return Citta.equals(other.Citta)&&CAP.equals(other.CAP);
			}
		
		@Override
		public String toString() {
			String StringCitta = Citta + ", " + CAP +", " + Provincia+ ", " + Regione+ ", ";
			return StringCitta;
		}
}
