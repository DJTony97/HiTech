package it.HiTech.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class OrdineModelDM implements OrdineModel<OrdineBean>{
	
	@Override
	public Collection<OrdineBean> doRetrieveByUser(String Username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		Collection<OrdineBean> orders=new LinkedList<OrdineBean>();
		
		
		String selectSQL = "SELECT * FROM ordine WHERE Utente_Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, Username);
			
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setCodice(rs.getInt("Codice"));
				bean.setRifCliente(rs.getString("RiferimentoCliente"));
				bean.setData(rs.getString("data"));
				bean.setUtente_Username(rs.getString("Utente_Username"));
				bean.setMetodoPagamento(rs.getString("MetodoPagamento"));
				bean.setNumero_Spedizione(rs.getString("Numero_Spedizione"));
				bean.setCorriere(rs.getString("Corriere"));
				bean.setMetodo_Spedizione(rs.getString("Metodo_Spedizione"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCap(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
				
				orders.add(bean);
			}	

		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return orders;
	}
	@Override
	public int doRetriveLastID(String utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int id=0;
		
		String selectSQL = "SELECT max(Codice) FROM ordine WHERE ;";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			

			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
			id=rs.getInt(1);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return id;
	}

	

	@Override
	public void doSave(OrdineBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO ordine" +
				" (Codice, data, RiferimentoCliente, Utente_Username, MetodoPagamento, Metodo_Spedizione, Indirizzo, Citta,"+
				"CAP, Provincia, Regione ) VALUES (  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			int codice=doRetriveLastID(bean.getUtente_Username())+1;
			preparedStatement.setInt(1,codice);
			preparedStatement.setString(2,bean.getRifCliente());
			preparedStatement.setString(3,bean.getData());
			preparedStatement.setString(4,bean.getUtente_Username());
			preparedStatement.setString(5,bean.getMetodoPagamento());
			preparedStatement.setString(6,bean.getMetodo_Spedizione());
			preparedStatement.setString(7,bean.getIndirizzo());
			preparedStatement.setString(8,bean.getCitta());
			preparedStatement.setString(9,bean.getCap());
			preparedStatement.setString(10,bean.getProvincia());
			preparedStatement.setString(11,bean.getRegione());

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
	public void doUpdate(OrdineBean order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE ordine SET " +
				" Numero_Spedizione=?, Corriere=? WHERE Codice = ?"; //controllare ordine
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
		
			preparedStatement.setString(1, order.getNumero_Spedizione());
			preparedStatement.setString(2, order.getCorriere());
			preparedStatement.setInt(3, order.getCodice());

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
	public void doDelete(OrdineBean order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		String deleteSQL1 = "DELETE FROM ordine WHERE Codice = ? AND Utente_Username=?";
		String deleteSQL2 = "DELETE FROM contenuto WHERE Ordine_Codice = ? AND Utente_Username ?" ;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL2);
			preparedStatement2 = connection.prepareStatement(deleteSQL1);
			preparedStatement.setInt(1, order.getCodice());
			preparedStatement.setString(2, order.getUtente_Username());
			preparedStatement2.setInt(1, order.getCodice());
			preparedStatement2.setString(2, order.getUtente_Username());

			preparedStatement.executeUpdate();
			preparedStatement2.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null&& preparedStatement2 != null) {
					preparedStatement.close();
					preparedStatement2.close();}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	}
}
