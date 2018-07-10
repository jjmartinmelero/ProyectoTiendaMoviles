package prSmartStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiConexion {
	
	
	//Definicion de variables de instancia:
	private Connection con; // Objecto con la conexi�n a la BD
	
	//Definicion de constructor:
	public MiConexion(){
		
		try{
		
		// Registrar la conexi�n / Levantas el JDBC
		String driver = "com.mysql.jdbc.Driver";
		Class.forName(driver);
		System.out.println("Conexi�n registrada");

		// Establecer la conexi�n
		String url = "jdbc:mysql://localhost:3306/"+CrearBDD.nombreBdd;
		String usuario = "admin";
		String clave   = "1234";
		
		con = DriverManager.getConnection(url, usuario, clave);

		System.out.println("Conexi�n establecida");
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
	 * M�todo que cierra la conexi�n con la BD
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
