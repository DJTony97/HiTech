package it.HiTech.model;

import java.sql.SQLException;
import java.util.Collection;

	public interface CittaModel<T> {
						
		public Collection<T> doRetrieveAll() throws SQLException;
		
		public Collection<String> doRetrieveByProv(String Provincia) throws SQLException;
		public Collection<String> doRetrieveByCAP(String cap) throws SQLException;
		public Collection<String> doRetrieveByReg(String reg) throws SQLException;
		public Collection<String> doRetrieveRegioni() throws SQLException;
		public Collection<String> doRetrieveProvince(String regione) throws SQLException;
		public Collection<String> doRetrieveCap(String provincia) throws SQLException;
}
