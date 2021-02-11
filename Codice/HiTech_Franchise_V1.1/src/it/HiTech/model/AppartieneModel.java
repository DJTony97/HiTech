package it.HiTech.model;

import java.sql.SQLException;
import java.util.Collection;

public interface AppartieneModel <T>{
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T appartenenza) throws SQLException;
	
	public void doDelete(T appartenenza) throws SQLException;
}
