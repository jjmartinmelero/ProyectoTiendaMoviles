package prSmartStore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ModeloSmartphones {
	
	
	//Definicion de variables de instancia:
	private Statement stmt; // Objeto que permite ejecutar sentencias SQL
	private ResultSet rs;   // Resultados de la consulta
	private Connection con; // Objecto con la conexión a la BD
	
	//Definicion de constructor
	public ModeloSmartphones(MiConexion con){
		
		this.con = con.getCon();
		
		try {
			this.crearStatement();
			this.crearConsulta();      // Crear la consulta a la BD (SELECT)
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void crearStatement() throws SQLException {
		
		
		this.stmt = con.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,//Indica que el cursor es bidireccional y que refleja los cambios en la bdd
				ResultSet.CONCUR_UPDATABLE);//Los datos del resultset son actualizables
		

	}//End crearStatement
	
	/**
	 * Método que permite crear la consulta SQL
	 */
	
	public void crearConsulta() throws SQLException{
		String sqlString = "SELECT * FROM smartphones";
		
		rs = stmt.executeQuery(sqlString);
	}
	
	
	/**
	 * Método que devuelve un objeto DefaultTableModel
	 * que contiene el resultado de una consulta
	 */
	
	public DefaultTableModel datosConsulta1(int codFabri) throws SQLException{
		
		// Crear la consulta	
		//String consulta = "select * from smartphones where codFabricante = "+codFabri;
		
		String consulta = "select fabricante, nombre, precio "
				+ "from fabricantes "
				+ "join smartphones using (codFabricante) "
				+ "where codFabricante = "+codFabri;
		
	
		ResultSet rsConsulta = this.stmt.executeQuery(consulta);
			
		// Obtener un objeto ResultSetMetaData, para obtener información de la tabla
		ResultSetMetaData rsmd = rsConsulta.getMetaData();
		
		// Obtener el número de columnas de esta consulta
		int numColumnas = rsmd.getColumnCount();
		

	
		// Crear un objeto DefaultTableModel, para guardar el resultado de la consulta
		DefaultTableModel datos = new DefaultTableModel();
		

			String [] nombreColumnas = {"fabricante", "smartphone", "precio"};
			
			datos.setColumnIdentifiers(nombreColumnas);
		
			
		// Añadir los datos 	
		while (rsConsulta.next()) {
			Object [] tupla = new Object[numColumnas];
			for (int i=0; i<numColumnas; i++) {
				
					tupla[i] = rsConsulta.getObject(i+1);
			}

			
			datos.addRow(tupla);
		}
		
		this.crearConsulta();
		return datos; 
	}//End datosConsulta1
	
	
	
	/**
	 * Metodo que devuelve una coleccion con todos los datos de los moviles:
	 */
	
	public ArrayList<String> listaMoviles() throws SQLException{
		
		//Crear la coleccion a devolver:
		ArrayList<String> listaMoviles = new ArrayList<String>();
		
		
		// Crear la consulta	
		String consulta = "select * from fabricantes ";
		
		ResultSet rsConsulta = this.stmt.executeQuery(consulta);
		
		
		// Añadir los datos 	
		while (rsConsulta.next()) {
					listaMoviles.add(rsConsulta.getString(3));
			}
		
		this.crearConsulta();

		
		return listaMoviles;
		
	}//end arrayList
	
	
	
	public DefaultTableModel datosConsulta2(float precio) throws SQLException{
		
		// Crear la consulta	
		String consulta = "select nombre, precio from smartphones where precio < "+precio;
		
	
		ResultSet rsConsulta = this.stmt.executeQuery(consulta);
			
		// Obtener un objeto ResultSetMetaData, para obtener información de la tabla
		ResultSetMetaData rsmd = rsConsulta.getMetaData();
		
		// Obtener el número de columnas de esta consulta
		int numColumnas = rsmd.getColumnCount();
		

	
		// Crear un objeto DefaultTableModel, para guardar el resultado de la consulta
		DefaultTableModel datos = new DefaultTableModel();
		

			String [] nombreColumnas = {"nombre","precio"};
			
			datos.setColumnIdentifiers(nombreColumnas);
		
			
		// Añadir los datos 	
		while (rsConsulta.next()) {
			Object [] tupla = new Object[numColumnas];
			for (int i=0; i<numColumnas; i++) {
				
					tupla[i] = rsConsulta.getObject(i+1);
			}

			
			datos.addRow(tupla);
		}
		
		this.crearConsulta();
		return datos; 
	}//End datosConsulta2
	
	
	
	
	
}//End class
