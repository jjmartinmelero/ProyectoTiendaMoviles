package prSmartStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConexion {
	
	
	//Definicion de variables de instancia:
	private Connection con; // Objecto con la conexión a la BD
	
	//Definicion de constructor:
	public MiConexion(){
		
		try{
		
		// Registrar la conexión / Levantas el JDBC
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		System.out.println("Conexión registrada");

		// Establecer la conexión
		String url = "jdbc:mysql://localhost:3306/"+CrearBDD.nombreBdd;
		String usuario = "admin";
		String clave   = "1234";
		
		con = DriverManager.getConnection(url, usuario, clave);

		System.out.println("Conexión establecida");
		}catch(SQLException e){
			//Solo entra la primera vez a la hora de crear la bdd, porque la Url anterior con el nombre no existe
			String url = "jdbc:mysql://localhost:3306";
			String usuario = "admin";
			String clave   = "1234";
			
			try {
				con = DriverManager.getConnection(url, usuario, clave);
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			
		}
		catch(Exception e){
			
			e.printStackTrace();
		}
		
	}//End constructor
	
	
	/**
	 * Método que cierra la conexión con la BD
	 */
	
	public void cierraConexion() {


		try {
			con.close();

			System.out.println("Conexion cerrada");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}




	}//End cierraConexion


	/**
	 * Metodo get de la conexion
	 * @return
	 */
	
	public Connection getCon() {return con;}
	
	
	
	
	
	
}//End class conection
