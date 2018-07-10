package prSmartStore;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ModeloFabricantes {
	
	
	//Definicion de variables de instancia:
	
	private Connection con; // Objecto con la conexión a la BD
	private Statement stmt; // Objeto que permite ejecutar sentencias SQL
	private ResultSet rs;   // Resultados de la consulta
	
	
	//Definicion de constructor:
	public ModeloFabricantes(MiConexion con){
		
		this.con = con.getCon();
		try {
			this.crearStatement();
			this.crearConsulta();      // Crear la consulta a la BD (SELECT)
			
			
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		}
		

	}//End constructor
	
	
	/**
	 * Crea el objeto Statement, el cual nos va a permitir ejecutar 
	 * i's SQL
	 */
	
	public void crearStatement() throws SQLException {
		
		
		this.stmt = con.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,//Indica que el cursor es bidireccional y que refleja los cambios en la bdd
				ResultSet.CONCUR_UPDATABLE);//Los datos del resultset son actualizables
		

	}//End crearStatement
	
	
	/**
	 * Método que cierra el objeto Statement
	 */
	
	public void cierraStatement() {
		
		
		try {
			this.stmt.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//End cierraStatement
	
	
	/**
	 * Método que permite crear la consulta SQL
	 */
	
	public void crearConsulta() throws SQLException{
		
		
		String sqlString = "SELECT * FROM fabricantes";
		
		this.rs = this.stmt.executeQuery(sqlString);
		
		
	}//End crearConsulta
	
	
	/**
	 * Método que permite añadir un nuevo fabricante a la base de datos
	 * @param ob -- disco que se va a añadir
	 * @throws SQLException
	 */
	
	public void insertaFabricante(Fabricante ob) throws SQLException {
		
		String sqlString = "insert into fabricantes values ("+
				0 +", '" + ob.getNombreFabricante() + "', '"+ob.getNombreSede()+"', "+
				+ob.getAnioFundacion()+")";
		
		
		this.stmt.executeUpdate(sqlString);
		
		//Volver a crear la consulta, ya que parece que la cierra tras
		//hacer la actualizacion de la BD
		this.crearConsulta();
		
	}//End metodo de insertarFabricante
	
	 
	/**
	 * Método que permite modificar un fabricante existente en la talba fabricantes, el fabricante
	 * con los datos modificados llega como parámetro, se puede modificar todo
	 * excepto el código del mismo
	 */
	public void modificaFabricantes(Fabricante ob) throws SQLException {
		
		String sqlString = "UPDATE fabricantes SET "+
							"fabricante = '"+ob.getNombreFabricante()+"', "+
							"sede = '"+ob.getNombreSede()+"', "+
							"fecha_fundada = "+ob.getAnioFundacion()+" "+
						" WHERE codFabricante = "+ob.getCodFabricante();
		
		//Ejecutar la sentencia sql
		this.stmt.executeUpdate(sqlString);
		
		this.crearConsulta();
		
	}//End modificarFabricantes
	
	
	
	/**
	 * Método que permite borrar elfabricante con el codigo que le llega al metodo como parametro
	 */
	public void eliminaFabricante(int cod) throws SQLException{

		
		//Se debe de eliminar antes los moviles relacionados con ese fabricante:
		String sqlString1 = 
				"DELETE FROM smartstore.smartphones where smartphones.codFabricante = "+cod;
		
		stmt.executeUpdate(sqlString1);
		
		
		//Una vez eliminados los moviles, se elimina el fabricante:
		
		String sqlString = 
				"DELETE FROM smartstore.fabricantes WHERE fabricantes.codFabricante = " + cod;
		
		
			// Ejecutar la sentencia anterior
			stmt.executeUpdate(sqlString);

			// Volver a crear la consulta, ya que parece que la cierra
			// tras hacer la actualización de la BD
			this.crearConsulta();
		
		
	}//End eliminaFabricante
	
	
	
	/**
	 * Método que devuelve el primer registro (tupla) de la consulta 
	 */
	
	public Fabricante getPrimero() throws SQLException {
		rs.first();
		return this.creaEmpresa();
	}
	
	
	
	/**	 
	 * Método que devuelve el registro siguiente al acual
	 */
	
	public Fabricante getSiguiente() throws SQLException {
		rs.next();
		return this.creaEmpresa();
	}
		
	/**
	 * Método que devuelve el registro anterior al actual
	 */
	
	public Fabricante getAnterior() throws SQLException {
		rs.previous();
		return this.creaEmpresa();
	}
	
	
	private Fabricante creaEmpresa() throws SQLException {
		return new Fabricante(rs.getInt("codFabricante"),
				 rs.getString("fabricante"),
				 rs.getString("sede"),
				 rs.getInt("fecha_fundada"));
	}
	
	
	/**
	 * Metodo que devuelve una lista con todos los fabricantes que existen en la base de datos
	 *
	 */
	
	public ArrayList<String> cbModeloFabricantes() throws SQLException{
		
		ArrayList<String> listaFabricantes = new ArrayList<String>();

		// Crear la consulta	
		String consulta = "select * from fabricantes ";
		
		ResultSet rsConsulta = this.stmt.executeQuery(consulta);
		
		
		// Añadir los datos 	
		while (rsConsulta.next()) {
			listaFabricantes.add(rsConsulta.getString(2));

		}//End while
		
		
		this.crearConsulta();
		
		
		return listaFabricantes;
	}//End cbModeloFabricantes
	
	
	/**
	 * Metodo con el que obtenemos el codigo de un determinado fabricante a partir de su nombre
	 * 
	 */
	
	public int codigoFabricante(String nombreFabri) throws SQLException{
		
		int code=0;
		
		// Crear la consulta	
		String consulta = "select codFabricante from fabricantes WHERE fabricante = '"+nombreFabri+"' ";
			
		ResultSet rsConsulta = this.stmt.executeQuery(consulta);
		
		rsConsulta.next();
		code=rsConsulta.getInt(1);
		
		this.crearConsulta();
		return code;
	}//End codigoFabricante
	
	
	public DefaultTableModel datosConsulta3(String patron) throws SQLException{
		
		// Crear la consulta	
		String consulta = "select fabricante, sede, fecha_fundada from fabricantes where fabricante like '"+patron+"' ";
		
	
		ResultSet rsConsulta = this.stmt.executeQuery(consulta);
			
		// Obtener un objeto ResultSetMetaData, para obtener información de la tabla
		ResultSetMetaData rsmd = rsConsulta.getMetaData();
		
		// Obtener el número de columnas de esta consulta
		int numColumnas = rsmd.getColumnCount();
		

	
		// Crear un objeto DefaultTableModel, para guardar el resultado de la consulta
		DefaultTableModel datos = new DefaultTableModel();
		

			String [] nombreColumnas = {"nombre","sede_central","fundacion"};
			
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
	
	
	
	
}//End class ModeloFabricantes
