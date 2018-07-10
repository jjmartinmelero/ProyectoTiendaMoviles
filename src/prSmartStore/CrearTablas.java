package prSmartStore;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {

	public static void main(String[] args) {
		
		//Objeto conexion:
		MiConexion con = new MiConexion();
		
		
		//Crear la tabla Fabricantes y Smartphones:
		try {
			
			creaTablaFabricantes(con.getCon());
			creaTablaSmartphones(con.getCon());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		//Cerramos la conexion cuando las tablas se han creado:
		con.cierraConexion();
		
	}//End Main

	
	/**
	 * Metodo que crea la tabla de Fabricantes:
	 * @param con
	 * @throws SQLException --> Throws Exceptions controladas en el main
	 */

	public static void creaTablaFabricantes(Connection con) throws SQLException {


		String creaTabla = "create table "+CrearBDD.nombreBdd+".fabricantes " +
				"(codFabricante INT AUTO_INCREMENT NOT NULL PRIMARY KEY, "+
				"fabricante VARCHAR(50) NOT NULL, "+
				"sede VARCHAR(50) NOT NULL, "+
				"fecha_fundada INT NOT NULL)";

		Statement stmt = null;
		
		stmt = con.createStatement();
		stmt.executeUpdate(creaTabla);
		
		System.out.println("Se ha creado la tabla Fabricantes");
		stmt.close();
		
		
	}//creaTablaFabricantes
	
	
	/**
	 * Metodo que crea la tabla que almacenara los datos de un determinado smartphone
	 */
	
	public static void creaTablaSmartphones(Connection con) throws SQLException {


		String creaTabla = "create table "+CrearBDD.nombreBdd+".smartphones " +
				"(codSmartphone INT AUTO_INCREMENT NOT NULL PRIMARY KEY, "+
				"codFabricante INT NOT NULL, "+
				"nombre VARCHAR(50) NOT NULL, "+
				"sistema_operativo VARCHAR(50) NOT NULL, "+
				"procesador VARCHAR(50) NOT NULL, "+
				"gpu VARCHAR(50) NOT NULL, "+
				"precio DOUBLE NOT NULL, "+
				"fecha_lanzamiento DATE NOT NULL, "+
				"FOREIGN KEY (codFabricante) REFERENCES FABRICANTES(codFabricante))";

		Statement stmt = null;
		
		stmt = con.createStatement();
		stmt.executeUpdate(creaTabla);
		
		System.out.println("Tabla smartphones creada");
		stmt.close();
		
		
	}//creaTablaDiscos




}//End class CreaCargaTablaFabricantes
