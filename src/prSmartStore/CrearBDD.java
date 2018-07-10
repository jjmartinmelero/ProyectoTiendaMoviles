package prSmartStore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearBDD {
	
	//Definicion de variables de clase
	public static final String nombreBdd;
	
	static{
		nombreBdd ="smartstore";
	}

	public static void main(String[] args) {
		
		
		//Objeto conexion:
		MiConexion con = new MiConexion();
		

		
		//Creando la base de datos:
		
		try {
			
			crearBaseDeDatos(con.getCon());
			System.out.println("La base de datos ha sido creada");
			
		} catch (SQLException e) {
			
			System.out.println("La base de datos ya existe");
			
			e.printStackTrace();
		}
		
		
		con.cierraConexion();
		
	}//End main
	
	
	/**
	 * Metodo que se encarga de ejecutar la sentencia para crear una base de datos
	 */
	
	private static void crearBaseDeDatos(Connection con) throws SQLException {


		String creaBdd = "CREATE DATABASE "+nombreBdd;

		Statement stmt = null;
		
		stmt = con.createStatement();
		stmt.executeUpdate(creaBdd);
		
		stmt.close();


	}//creaTablaFabricantes
	
	
	
}//End class CrearBDD
