package it.HiTech.model;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedList;


public class ProdottoModelDM implements ProdottoModel<ProdottoBean> {

	@Override
	public ProdottoBean doRetrieveByKey(String codice) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProdottoBean bean = new ProdottoBean();
		
		String selectSQL = "SELECT * FROM prodotto WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				Blob blob = rs.getBlob("img");
                if(blob!=null) {
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                bean.setImg(base64Image); 
                inputStream.close();
                outputStream.close();
			}
				bean.setCodice(rs.getString("Codice"));
				bean.setNome(rs.getString("nome"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setScorte(rs.getInt("quantita"));
								
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
	public  Collection<ProdottoBean> doRetrieveByCategory(String cat) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();
		
		String selectSQL = "SELECT * FROM prodotto, appartiene WHERE Codice=Prodotto_Codice AND Categoria_Nome=? ";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cat);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProdottoBean bean = new ProdottoBean();
				
				Blob blob = rs.getBlob("img");
                if (blob!=null) {
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                bean.setImg(base64Image);  
                inputStream.close();
                outputStream.close();
				}
				bean.setCodice(rs.getString("Codice"));
				bean.setNome(rs.getString("nome"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setScorte(rs.getInt("quantita"));
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
	public Collection<ProdottoBean> doRetrieveAll(String order) throws SQLException, IOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ProdottoBean> products = new LinkedList<ProdottoBean>();
		
		String selectSQL = "SELECT * FROM prodotto";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				ProdottoBean bean = new ProdottoBean();
				
				Blob blob = rs.getBlob("img");
                if (blob!=null) {
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                bean.setImg(base64Image); 
                inputStream.close();
                outputStream.close(); 
                }
                
                
				bean.setCodice(rs.getString("Codice"));
				bean.setNome(rs.getString("nome"));
				bean.setMarca(rs.getString("marca"));
				bean.setDescrizione(rs.getString("descrizione"));
				bean.setPrezzo(rs.getFloat("prezzo"));
				bean.setScorte(rs.getInt("quantita"));
				
				
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
	public void doSave(ProdottoBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO prodotto" +
				" (Codice, nome, descrizione, prezzo, marca, quantita, img) VALUES (?, ?, ?, ?, ?,?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, product.getCodice());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getDescrizione());
			preparedStatement.setFloat(4, product.getPrezzo());
			preparedStatement.setString(5, product.getMarca());
			preparedStatement.setBlob(7,product.getInput());
			preparedStatement.setInt(6,product.getScorte());
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
	public void doUpdate(ProdottoBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE prodotto SET " +
				" marca = ?, nome = ?, descrizione = ?, prezzo = ?  WHERE Codice = ?"; //controllare ordine
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, product.getMarca());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getDescrizione());
			preparedStatement.setFloat(4, product.getPrezzo());
			
			preparedStatement.setString(5, product.getCodice());
			

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
	public void doDelete(ProdottoBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		
		String deleteSQL = "DELETE FROM prodotto WHERE Codice = ?";
		String deleteSQL1 = "DELETE FROM contenuto WHERE Prodotto_Codice = ?";
		String deleteSQL2 = "DELETE FROM appartiene WHERE Prodotto_Codice = ?";
		String deleteSQL3 = "DELETE FROM recensione WHERE Prodotto_Codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement1 = connection.prepareStatement(deleteSQL1);
			preparedStatement2 = connection.prepareStatement(deleteSQL2);
			preparedStatement3 = connection.prepareStatement(deleteSQL3);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement1.setString(1, product.getCodice());
			preparedStatement2.setString(1, product.getCodice());
			preparedStatement3.setString(1, product.getCodice());
			preparedStatement.setString(1, product.getCodice());
			

			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			preparedStatement3.executeUpdate();
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
	public void RiduciScorte(ProdottoBean prodotto, int num) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE prodotto SET " +
				" quantita = ?  WHERE Codice = ?"; //controllare ordine
		int scorte=prodotto.getScorte()-num;
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setInt(1, scorte);

			
			preparedStatement.setString(2, prodotto.getCodice());
			

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
	public int ControlloScorte(String codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int scorte;
		
		String selectSQL = "SELECT quantita FROM prodotto WHERE CODICE = ?";
	
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, codice);

			ResultSet rs = preparedStatement.executeQuery();
			
			          scorte=rs.getInt("quantita");
				
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return scorte;
	}

}
