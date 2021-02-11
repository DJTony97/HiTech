package it.HiTech.model;

public class CategoriaBean {
		
	String Nome;
	
	public CategoriaBean(){
		Nome= "";
	}
	
	public String getNome() {
		return Nome;
	}


	public void setNome(String username) {
		this.Nome = username;
	}
	
	
	public boolean equals(Object otherObject){
		if (!super.equals(otherObject)) return false;
		CategoriaBean other =
		(CategoriaBean)otherObject;
		return Nome.equals(other.Nome);
		}
	
	@Override
	public String toString() {
		return Nome;
	}
}
