package it.HiTech.model;

import java.sql.SQLException;
import java.util.Collection;

public interface ContenutoModel <T>{
	
	;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T contenuto) throws SQLException;
	
	public void doUpdate(T contenuto) throws SQLException;
	
	public void doDelete(T contenuto) throws SQLException;

	public ContenutoBean doRetrieveByKey(String codiceP, int codiceO, String username) throws SQLException;
}
