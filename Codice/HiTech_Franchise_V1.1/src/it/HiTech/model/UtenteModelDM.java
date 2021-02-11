package it.HiTech.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class UtenteModelDM implements UtenteModel<UtenteBean>{
	@Override
	public UtenteBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();
		
		String selectSQL = "SELECT * FROM utente WHERE Username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setCF(rs.getString("CF"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getNString("email"));
				bean.setType(rs.getInt("Type"));
				bean.setIndirizzo(rs.getString("Indirizzo"));	
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
			}
			

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
	public UtenteBean doRetrieveByEmail(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();
		
		String selectSQL = "SELECT * FROM utente WHERE email = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setCF(rs.getString("CF"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getNString("email"));
				bean.setType(rs.getInt("Type"));
				bean.setIndirizzo(rs.getString("Indirizzo"));	
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
			}
			

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
	public UtenteBean doRetrieveByCF(String cf) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		UtenteBean bean = new UtenteBean();
		
		String selectSQL = "SELECT * FROM utente WHERE CF = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cf);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setUsername(rs.getString("Username"));
				bean.setCF(rs.getString("CF"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getNString("email"));
				bean.setType(rs.getInt("Type"));
				bean.setIndirizzo(rs.getString("Indirizzo"));	
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
			}
			

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
	public Collection<UtenteBean> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<UtenteBean> products = new LinkedList<UtenteBean>();
		
		String selectSQL = "SELECT * FROM utente";
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				UtenteBean bean = new UtenteBean();
				
				bean.setUsername(rs.getString("Username"));
				bean.setCF(rs.getString("CF"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getNString("email"));
				bean.setType(rs.getInt("Type"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
				products.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return products;
	}
	@Override
	public Collection<UtenteBean> doRetrieveByProv(String prov) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<UtenteBean> products = new LinkedList<UtenteBean>();
		
		String selectSQL = "SELECT * FROM utente WHERE Provincia = ?";
		

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, prov);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				UtenteBean bean = new UtenteBean();
				
				bean.setUsername(rs.getString("Username"));
				bean.setCF(rs.getString("CF"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getNString("email"));
				bean.setType(rs.getInt("Type"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
				products.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return products;
	}
	@Override
	public Collection<UtenteBean> doRetrieveByReg(String Reg) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<UtenteBean> products = new LinkedList<UtenteBean>();
		
		String selectSQL = "SELECT * FROM utente WHERE Regione = ?";
		

		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, Reg);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				UtenteBean bean = new UtenteBean();
				
				bean.setUsername(rs.getString("Username"));
				bean.setCF(rs.getString("CF"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("Nome"));
				bean.setCognome(rs.getString("Cognome"));
				bean.setEmail(rs.getNString("email"));
				bean.setType(rs.getInt("Type"));
				bean.setIndirizzo(rs.getString("Indirizzo"));
				bean.setCitta(rs.getString("Citta"));
				bean.setCAP(rs.getString("CAP"));
				bean.setProvincia(rs.getString("Provincia"));
				bean.setRegione(rs.getString("Regione"));
				products.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return products;
	}
	@Override
	public void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO utente (`Username`, `CF`, `password`, `Nome`, `Cognome`, `email`, `Type`, `Indirizzo`, `Citta`, `CAP`, `Provincia`, `Regione`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getCF());
			preparedStatement.setString(5, utente.getCognome());
			preparedStatement.setString(3, utente.getPassword());
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(6, utente.getEmail());
			preparedStatement.setString(9, utente.getCitta());
			preparedStatement.setInt(7, utente.getType());
			preparedStatement.setString(8, utente.getIndirizzo());
			preparedStatement.setString(10, utente.getCAP());
			preparedStatement.setString(11, utente.getProvincia());
			preparedStatement.setString(12, utente.getRegione());

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
	public void doUpdate(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE utente SET " +
				" CF = ?, password = ?, Nome = ?, Cognome = ?, email =?, Type = ?, Indirizzo = ?, Citta = ?,  CAP = ?,  Provincia = ?,  Regione = ? WHERE Username = ?"; 
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(12, utente.getUsername());
			preparedStatement.setString(1, utente.getCF());
			preparedStatement.setString(4, utente.getCognome());
			preparedStatement.setString(2, utente.getPassword());
			preparedStatement.setString(3, utente.getNome());
			preparedStatement.setString(5, utente.getEmail());
			preparedStatement.setString(8, utente.getCitta());
			preparedStatement.setInt(6, utente.getType());
			preparedStatement.setString(7, utente.getIndirizzo());
			preparedStatement.setString(9, utente.getCAP());
			preparedStatement.setString(10, utente.getProvincia());
			preparedStatement.setString(11, utente.getRegione());
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
	public void doDelete(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		String selectSQL = "SELECT * FROM ordine WHERE Utente_Username = ?";
		String deleteSQL = "DELETE FROM utente WHERE Username = ?";
		Collection<OrdineBean> orders = new LinkedList<OrdineBean>();
		OrdineModelDM model=new OrdineModelDM();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			
			preparedStatement2=connection.prepareStatement(selectSQL);
			preparedStatement2.setString(1, utente.getUsername());
			ResultSet rs = preparedStatement2.executeQuery();
			
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
			if(orders != null && orders.size() > 0) {
				
				Iterator<?> itc  = orders.iterator();
				while(itc.hasNext()) {
				OrdineBean bean2 = (OrdineBean)itc.next();
				model.doDelete(bean2);
				}
			}
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, utente.getUsername());

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
