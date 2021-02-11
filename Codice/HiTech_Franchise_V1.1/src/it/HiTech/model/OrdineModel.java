package it.HiTech.model;

import java.sql.SQLException;
import java.util.Collection;

public interface OrdineModel<T> {


	
	public void doSave(T ordine) throws SQLException;
	
	public void doUpdate(T ordine) throws SQLException;
	
	public void doDelete(T ordine) throws SQLException;

	public int doRetriveLastID(String utente) throws SQLException;

	public Collection<T> doRetrieveByUser(String Username) throws SQLException;
}
