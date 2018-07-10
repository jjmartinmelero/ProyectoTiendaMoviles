package prSmartStore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.StringTokenizer;

public class CargarDatosTablas {

	public static void main(String[] args) {
		
		//Creamos el objeto conexion:
		MiConexion con = new MiConexion();
		
		
		try {
			
			cargaTablaEmpresas(con.getCon());
			cargaTablaSmartphones(con.getCon());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		System.out.println("Todos los datos cargados con exito");
		//Cerramos la conexion:
		con.cierraConexion();
		
	}//End main
	
	
	
	/**
	 * Metodo que carga la tabla de fabricantes con los datos del fichero
	 * @param con
	 * @throws SQLException ---> Son controladas en el main
	 */
	
	private static void cargaTablaEmpresas(Connection con) throws SQLException {

		String lineaFichero;
		String nombreEmpresa, sede;
		int codigo=0;
		int anioFundada;
		
		BufferedReader br = null;
		Statement stmt = null;
		StringTokenizer st = null;

		//Crear el objeto statement:
		stmt = con.createStatement();

		try{

			//Crear un flujo para poder recorrer el fichero Fabricantes.txt
			br = new BufferedReader(new FileReader(new File("Fabricantes.txt")));

			//Leer la primera linea del fichero
			lineaFichero = br.readLine();

			while(lineaFichero!=null){

				//Sacar los datos de los fabricantes leidos del fichero (estan separados por * )
				st = new StringTokenizer(lineaFichero, "*");

				nombreEmpresa = st.nextToken();
				sede = st.nextToken();
				anioFundada = Integer.valueOf(st.nextToken());
				

				//Formar la sentencia para insertar el fabricante obtenido:
				String sqlString = "insert into smartstore.fabricantes values ("+
						codigo +", '" + nombreEmpresa + "', '"+sede+"', "+
						+anioFundada+")";
				

				//ejecutar la sentencia sql anterior:
				stmt.executeUpdate(sqlString);

				//Leer otra linea del fichero
				lineaFichero = br.readLine();
			}//end while



		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally {

			stmt.close();
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}//End cargaTablaEmpresas
	
	
	/**
	 * Metodo que se encarga de cargar los datos de la tabla smartphones
	 */
	
	private static void cargaTablaSmartphones(Connection con) throws SQLException{

		String lineaFichero;

		int codigo=0;// SIEMPRE ES CERO (es auto_increment en la bdd)
		int codFabricante;//Es la fk de la tabla Fabricantes
		String nombreMovil, sistemaOper, gpu,procesador;
		double precio;

		BufferedReader br = null;
		Statement stmt = null;
		StringTokenizer st = null;

		//Crear el objeto statement:
		stmt = con.createStatement();


		try{
			
			br = new BufferedReader(new FileReader(new File("Smartphones.txt")));

			lineaFichero = br.readLine();

			while(lineaFichero!=null){

				st = new StringTokenizer(lineaFichero, "*");
				codFabricante=Integer.valueOf(st.nextToken());
				nombreMovil=st.nextToken();
				sistemaOper = st.nextToken();
				procesador = st.nextToken();
				gpu = st.nextToken();
				precio = Double.valueOf(st.nextToken());
				
				//Obtener la fecha
				Calendar fechaCalendar = LibreriaFechas.convierteFechaStringACalendar(st.nextToken());
				
				java.sql.Date fechaDate = new java.sql.Date(fechaCalendar.getTimeInMillis());

				String sqlString = "insert into smartstore.smartphones values ("+
						codigo +", " + codFabricante + ", '"+nombreMovil+"', '"+
						sistemaOper+"', '"+procesador+"', '"+gpu+"', "+precio+", '"+fechaDate+"')";

				//ejecutar la sentencia sql anterior:
				stmt.executeUpdate(sqlString);

				//Leer otra linea del fichero
				lineaFichero = br.readLine();
			}//end while


		}//End try
		catch(IOException e){
			e.printStackTrace();
		}
		catch(MiExcepcion e){
			e.printStackTrace();
		}
		finally {

			stmt.close();
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}


	}//End cargaTablaSmartphones
	
	
	
	
	
}//end class CargarDatosTablas
