package prSmartStore;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//DESARROLLADO POR JUAN JESUS MARTIN MELERO
import java.sql.SQLException;
import java.util.ListIterator;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Controlador extends WindowAdapter implements ChangeListener, ActionListener {


	//Definicion de variables de instancia:-------------------------------------------------------------
	private Vista v;
	private ModeloFabricantes modeloFabricantes;
	private ModeloSmartphones modeloSmartphone;
	private MiConexion con;


	//Definicion de constructor:------------------------------------------------------------------------
	public Controlador(Vista miVista){

		this.con = new MiConexion();	
		this.v = miVista;

		this.modeloFabricantes = new ModeloFabricantes(con);
		this.modeloSmartphone = new ModeloSmartphones(con);
		
		//Cargar el combo con los fabricantes: TODO (lo puse al final del constructor 
		//y la primera ejecucion del boton siguiente no funcionaba, comenzaba a la segunda vez
		// de ser pulsado, es porque ejecuta la sentencia del resultset de 'creaConsulta' del modelo (CREO)
		//Investigar mas para esto)
		cargarComboFabricantes();
		

		
		try {

			muestraEmpresa(modeloFabricantes.getSiguiente());

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}//end constructor
	
	

	/**------------------------------------------------------------------------------------
	 * METODOS DE LA INTERFAZ ActionListener
	 * ------------------------------------------------------------------------------------
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// OBTENER EL TAMANIO DEL BUTTON System.out.println("TAMANIO BOTTON"+this.v.getbSiguiente().getSize());
		try {
		
		if(e.getSource()==this.v.getbVerMoviles())
			procesoConsulta();//Busqueda de moviles a partir del nombre del fabricante
		else if (e.getSource()==this.v.getbBuscarMovil())
			procesoConsulta2();//Busqueda de moviles a partir de un precio
		else if (e.getSource()==this.v.getbBuscarPatron())
			procesoConsulta3();//Busqueda fabricante a partir de un patron
		else if(e.getSource()==this.v.getbSiguiente())
			muestraEmpresa(modeloFabricantes.getSiguiente());
		else if(e.getSource()==this.v.getbAnterior())
			muestraEmpresa(modeloFabricantes.getAnterior());
		else if(e.getSource()==this.v.getbEliminar())
			procesoEliminar();
		else if(e.getSource()==this.v.getbModificar())
			permitirModificar(true);
		else if(e.getSource()==this.v.getbCrearFabricante())
			crearEmpresa();
		else // Boton de guardar ha sido pulsado
			guardarModificaciones();
		
		}//End try
		catch(SQLException e1){
			//e1.printStackTrace();
			
			try{
				if (e.getSource()==this.v.getbSiguiente())
					modeloFabricantes.getAnterior();
				else if (e.getSource()==this.v.getbAnterior())
					modeloFabricantes.getSiguiente();
				}
				catch(SQLException e2){
					e2.printStackTrace();
				}
			
		}//End catch
		
		catch(NumberFormatException e1){
			JOptionPane.showMessageDialog(this.v,"Error en el formato numerico","ERROR",JOptionPane.ERROR_MESSAGE);

		}
		//End catch
		catch(MiExcepcion e1){
			
			JOptionPane.showMessageDialog(this.v,"No se ha insertado -- Error en valores","ERROR",JOptionPane.ERROR_MESSAGE);
			
		}
		
		reset();
		
		
	}//end actionPerformed


	/**------------------------------------------------------------------------------------
	 * METODOS DE LA INTERFAZ ChangeListener
	 * ------------------------------------------------------------------------------------
	 */

	@Override
	public void stateChanged(ChangeEvent arg0) {


	}

	
	/**------------------------------------------------------------------------------------
	 * METODOS DE LA CLASE ADAPTADORA WindowAdapter
	 * ------------------------------------------------------------------------------------
	 */



	@Override
	public void windowClosing(WindowEvent e) {
		
		this.con.cierraConexion();
		System.exit(0);
		
	}


	/**------------------------------------------------------------------------------------
	 * METODOS AUXILIARES
	 * ------------------------------------------------------------------------------------
	 */
	
	/**
	 * Metodo que se encarga de cargar los fabricantes de moviles iniciales, y ademas actualiza tras
	 * borrar,modificar e insertar
	 */
	
	private void cargarComboFabricantes(){
		
		this.v.getCbFabricantes().removeAllItems();
		
		ListIterator<String> li;
		
		
		try {
			
			li = modeloFabricantes.cbModeloFabricantes().listIterator();
			
			while(li.hasNext()){
				this.v.getCbFabricantes().addItem(li.next());
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}//End cargarComboFabricantes
	
	
	
	/**
	 * Metodo que se encarga de almacenar un fabricante generado a gusto del usuario:
	 */
	private void crearEmpresa() throws SQLException, MiExcepcion{
		
		String nombre;
		String sede;
		String anio;
		
		nombre = this.v.getTfNombreFabAñadir().getText().toUpperCase();
		sede = this.v.getTfSedeFabAñadir().getText();
		anio = this.v.getTfAnioFundacionAñadir().getText();
		
		
		
		if(nombre.isEmpty()||sede.isEmpty()) throw new MiExcepcion();
		if(anio.length()!=4||Integer.valueOf(anio)<0) throw new MiExcepcion();

		
		
		
		
		Fabricante obFabricante = new Fabricante(0,nombre, sede, Integer.valueOf(anio));
		
			this.modeloFabricantes.insertaFabricante(obFabricante);
		
			
			cargarComboFabricantes();
	}//End guardarEmpresa
	
	
	
	
	/**
	 * Metodo que realiza el proceso de eliminar un registro de la tabla fabricantes
	 * @throws SQLException
	 */
	
	private void procesoEliminar()throws SQLException{
		
			modeloFabricantes.eliminaFabricante(Integer.valueOf(this.v.getTfCodigoFab().getText()));
			
			cargarComboFabricantes();
			muestraEmpresa(modeloFabricantes.getPrimero());
			
	}//End procesoEliminar
	
	
	/**
	 * Metodo que permite actualizar el campo selecionado a los datos deseados, el cual se puede
	 * modificar todos excepto el codigo:
	 */

	private void guardarModificaciones() throws SQLException{

		//Proceso con el que creamos el nuevo fabricante de moviles y se sobreescribe a partir de una 
		//setencia del modelo de fabricantes:
		Fabricante obFabricante = new Fabricante(Integer.valueOf(this.v.getTfCodigoFab().getText()),
				this.v.getTfnombreFab().getText(),
				this.v.getTfSedeFab().getText(),
				Integer.valueOf(this.v.getTfAnioFund().getText()));



		modeloFabricantes.modificaFabricantes(obFabricante);


		permitirModificar(false);//Una vez que se haya ejecutado el boton de guardar se vuelven
									//desactivar los textfields
		
		cargarComboFabricantes();

		muestraEmpresa(modeloFabricantes.getPrimero());

	}//End guardarModificaciones
	
	
	/**
	 * Metodo que cambia el estado de los textfield en editables o no editables en funcion de si se 
	 * quieren modificar los datos y posteriormente guardarlos
	 */
	private void permitirModificar(boolean b){
		
		this.v.getTfnombreFab().setEditable(b);
		this.v.getTfSedeFab().setEditable(b);
		this.v.getTfAnioFund().setEditable(b);
		this.v.getbGuardar().setEnabled(b);
		this.v.getbSiguiente().setEnabled(!b);
		this.v.getbAnterior().setEnabled(!b);
		this.v.getbEliminar().setEnabled(!b);
		this.v.getbModificar().setEnabled(!b);
		
		
	}//End permitirModificaciones
	
	
	
	
	/**
	 * Metodo que resetea los campos
	 */
	
	private void reset(){
		
		this.v.getTfBuscarMovil().setText("");
		this.v.getTfPatron().setText("");
		this.v.getCbFabricantes().setSelectedIndex(0);
		this.v.getTfAnioFundacionAñadir().setText("");
		this.v.getTfNombreFabAñadir().setText("");
		this.v.getTfSedeFabAñadir().setText("");
		
	}//End reset
	
	/**
	 * Metodo que muestra los datos de un fabricante en los correspondientes textfields
	 * @param ob
	 */
	
	private void muestraEmpresa(Fabricante ob){
		
		this.v.getTfCodigoFab().setText(String.valueOf(ob.getCodFabricante()));
		this.v.getTfnombreFab().setText(ob.getNombreFabricante());
		this.v.getTfSedeFab().setText(ob.getNombreSede());
		this.v.getTfAnioFund().setText(String.valueOf(ob.getAnioFundacion()));
		
		 Font myFont = new Font("Courier", Font.BOLD,20);
		
		 this.v.getTfCodigoFab().setFont(myFont);
		 this.v.getTfnombreFab().setFont(myFont);
		 this.v.getTfSedeFab().setFont(myFont);
		 this.v.getTfAnioFund().setFont(myFont);
		
	}//End muestraEmpresa
	
	
	/**
	 * Metodo que prepara el proceso de ver los moviles de un fabricante:
	 */

	private void procesoConsulta() throws SQLException{

		int codigoConsultado = modeloFabricantes.codigoFabricante(this.v.getCbFabricantes().getSelectedItem().toString());
		new Consulta(modeloSmartphone.datosConsulta1(codigoConsultado));//se le pasa el codigo del fabricante seleccionado


	}//End procesoConsulta
	
	
	
	
	/**
	 * Metodo que se encarga de buscar una serie de moviles de un precio inferior al indicado por el usuario
	 */
	private void procesoConsulta2() throws SQLException, NumberFormatException{
		
		new Consulta(modeloSmartphone.datosConsulta2(Float.valueOf(this.v.getTfBuscarMovil().getText())));
		
		
		
	}//End procesoConsulta2
	
	
	
	/**
	 * Metodo que se encarga de buscar una serie de moviles de un precio inferior al indicado por el usuario
	 */
	
	private void procesoConsulta3() throws SQLException, NumberFormatException{
		
		new Consulta(modeloFabricantes.datosConsulta3(this.v.getTfPatron().getText()));
	
	}//End procesoConsulta2
	

}//End class Controlador
