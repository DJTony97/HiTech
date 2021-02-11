package it.HiTech.model;

import java.sql.SQLException;
import java.util.Collection;

public interface CategoriaModel <T>{
	
	public Collection<T> doRetrieveAll() throws SQLException;
	
	public void doSave(T categoria) throws SQLException;
	
	public void doDelete(T categoria) throws SQLException;
}
