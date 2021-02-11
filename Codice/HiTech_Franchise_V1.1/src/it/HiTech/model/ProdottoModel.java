package it.HiTech.model;


import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

	public interface ProdottoModel<T> {
		
		public T doRetrieveByKey(String chiave) throws SQLException, IOException;
		
		public Collection<T> doRetrieveAll(String order) throws SQLException, IOException;
		
		public Collection<T> doRetrieveByCategory(String order) throws SQLException, IOException;
		
		public void doSave(T prodotto) throws SQLException;
		
		public void doUpdate(T prodotto) throws SQLException;
		
		public void doDelete(T prodotto) throws SQLException;

		public void RiduciScorte(T prodotto, int num) throws SQLException;
		public int ControlloScorte(String codice) throws SQLException;


}
