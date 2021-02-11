package it.HiTech.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CittaModelDM implements CittaModel<CittaBean>{
	




	@Override
	public Collection<String> doRetrieveByProv(String Prov) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<String> citta = new LinkedList<String>();
		
		String selectSQL = "SELECT Citta FROM citta WHERE Provincia = ?";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, Prov);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
							
				citta.add(rs.getString("Citta"));
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return citta;
	}
	@Override
	public Collection<String> doRetrieveByReg(String reg) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<String> citta = new LinkedList<String>();
		
		String selectSQL = "SELECT Citta FROM citta WHERE Regione = ?";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, reg);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
			
				citta.add(rs.getString("Citta"));
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return citta;
	}
	
	public Collection<String> doRetrieveByCAP(String cap) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<String> citta = new LinkedList<String>();
		
		String selectSQL = "SELECT Citta FROM citta WHERE CAP = ?";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cap);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {

				citta.add(rs.getString("Citta"));
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return citta;
	}
	@Override
	public Collection<CittaBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<CittaBean> citta = new LinkedList<CittaBean>();
		
		String selectSQL = "SELECT * FROM citta";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				CittaBean bean = new CittaBean();
				
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
				
				citta.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return citta;
	}
	@Override
	public Collection<String> doRetrieveRegioni() throws SQLException {
		Collection<String> regioni = new LinkedList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT DISTINCT Regione FROM citta";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
	

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {

				regioni.add(rs.getString("Regione"));
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return regioni;
	}
	@Override
	public Collection<String> doRetrieveProvince(String regione) throws SQLException {
		Collection<String> province = new LinkedList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT DISTINCT Provincia FROM citta WHERE Regione = ?";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, regione);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {

				province.add(rs.getString("Provincia"));
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return province;
	}
	@Override
	public Collection<String> doRetrieveCap(String provincia) throws SQLException {
		Collection<String> cap = new LinkedList<String>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		String selectSQL = "SELECT DISTINCT CAP FROM citta WHERE Provincia = ?";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, provincia);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				cap.add(rs.getString("CAP"));
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return cap;
	}

}
