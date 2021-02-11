package it.HiTech.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class AppartieneModelDM implements AppartieneModel<AppartieneBean>{
	

	@Override
	public Collection<AppartieneBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<AppartieneBean> appartenenze = new LinkedList<AppartieneBean>();
		
		String selectSQL = "SELECT * FROM appartiene";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				AppartieneBean bean = new AppartieneBean();
				
				bean.setProdotto_Codice(rs.getString("Prodotto_Codice"));
				bean.setCategoria_Nome(rs.getString("Categoria_Nome"));
				
				
				appartenenze.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return appartenenze;
	}

	@Override
	public void doSave(AppartieneBean appart) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO appartiene" +
				" (Prodotto_Codice , Categoria_Nome) VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, appart.getProdotto_Codice());
			preparedStatement.setString(2, appart.getCategoria_Nome());
			
		
			preparedStatement.executeUpdate();
		
			connection.commit();

		} finally {
				try {
					if(preparedStatement != null) 
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}		
	}



	@Override
	public void doDelete(AppartieneBean appart) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM appartiene WHERE Prodotto_Codice = ? AND Categoria_Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, appart.getProdotto_Codice());
			preparedStatement.setString(1, appart.getCategoria_Nome());
			

			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	}

}
