package it.HiTech.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ContenutoModelDM implements ContenutoModel<ContenutoBean>{
	@Override
	public ContenutoBean doRetrieveByKey(String codiceP, int codiceO, String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ContenutoBean bean = new ContenutoBean();
		
		String selectSQL = "SELECT * FROM contenuto WHERE Ordine_Codice = ? AND Prodotto_Codice = ? Utente_Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codiceO);
			preparedStatement.setString(2, codiceP);
			preparedStatement.setString(3, username);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setOrdine_Codice(rs.getInt("Ordine_Codice"));
				bean.setProdotto_Codice(rs.getString("Prodotto_Codice"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setUtente(rs.getString("Utente_Username"));
				
			}			
			
			System.out.println(bean);
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}

	@Override
	public Collection<ContenutoBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ContenutoBean> contents = new LinkedList<ContenutoBean>();
		
		String selectSQL = "SELECT * FROM contenuto";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ContenutoBean bean = new ContenutoBean();
				
				bean.setOrdine_Codice(rs.getInt("Ordine_Codice"));
				bean.setProdotto_Codice(rs.getString("Prodotto_Codice"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setUtente(rs.getString("Utente_Username"));
				

				
				contents.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return contents;
	}

	@Override
	public void doSave(ContenutoBean content) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO contenuto" +
				" (Prodotto_Codice, Ordine_Codice, quantita, Utente_Username) VALUES (?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, content.getProdotto_Codice());
			preparedStatement.setInt(2, content.getOrdine_Codice());
			preparedStatement.setInt(3, content.getQuantita());
			preparedStatement.setString(4, content.getUtente());

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
	public void doUpdate(ContenutoBean content) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String updateSQL = "UPDATE contenuto SET " +
				" quantita = ? WHERE Ordine_Codice = ? AND Prodotto_Codice = ? AND Utente_Username = ?"; //controllare ordine
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			content.incQuantita();
			preparedStatement.setInt(1, content.getQuantita());
			preparedStatement.setInt(2, content.getOrdine_Codice());
			preparedStatement.setString(3, content.getProdotto_Codice());
			preparedStatement.setString(4, content.getUtente());

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
	public void doDelete(ContenutoBean content) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM contenuto WHERE Ordine_Codice = ? AND Prodotto_Codice = ? AND Utente_Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, content.getOrdine_Codice());
			preparedStatement.setString(2, content.getProdotto_Codice());
			preparedStatement.setString(3, content.getUtente());
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
