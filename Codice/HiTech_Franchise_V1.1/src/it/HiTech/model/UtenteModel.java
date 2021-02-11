package it.HiTech.model;

import java.sql.SQLException;
import java.util.Collection;

	public interface UtenteModel<T> {
		
		public T doRetrieveByKey(String chiave) throws SQLException;
		
		public T doRetrieveByEmail(String chiave) throws SQLException;
		
		public T doRetrieveByCF(String chiave) throws SQLException;
		
		public Collection<T> doRetrieveAll() throws SQLException;
		public Collection<T> doRetrieveByProv(String Prov) throws SQLException;
		public Collection<T> doRetrieveByReg(String Reg) throws SQLException;
		public void doSave(T utente) throws SQLException;
		
		public void doUpdate(T utente) throws SQLException;
		
		public void doDelete(T utente) throws SQLException;
}
