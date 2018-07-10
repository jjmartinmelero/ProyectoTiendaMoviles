package prSmartStore;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
//DESARROLLADO POR ****************************JUAN JESUS MARTIN MELERO
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Vista extends JPanel {

	private static final long serialVersionUID = 1L;
	
	//Variables de instancia:
	//Para consultar los moviles que tiene un determinado fabricante:
	private JComboBox<String> cbFabricantes;
	private JButton bVerMoviles;
	//Para buscar moviles por precio:
	private JButton bBuscarMovil;
	private JTextField tfBuscarMovil;
	//Buscar fabricantes por patron
	private JTextField tfPatron;
	private JButton bBuscarPatron;
	//Definiciendo las variables del siguiente panel
	private JTextField tfCodigoFab, tfnombreFab,tfSedeFab, tfAnioFund;
	private JButton bSiguiente, bAnterior;
	private JButton bEliminar, bModificar;
	private JButton bGuardar;
	//Definicion de variables de instancia que permiten añadir un nuevo fabricante
	private JTextField tfNombreFabAñadir, tfSedeFabAñadir, tfAnioFundacionAñadir;
	private JButton bCrearFabricante;
	
	/**
	 * Definicion de constructor:
	 */
	
	public Vista(){

		//Gestor de esquemas para el panel principal
		this.setLayout(new BorderLayout());


		//Titulo a la cabecera de la aplicacion:
		this.add(new JLabel(new ImageIcon("images/tittle.png")),BorderLayout.NORTH);
		this.add(this.panelPestanias(),BorderLayout.CENTER);
		
		
		//***************************LOOK AND FEEL***************************************************
		
		// Cambiar el Look and Feel PARA LOS QUE TENEMOS EN EL SISTEMA
/*		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			//UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI(this);
	*/	
		
		
		//LOOK AND FEEL (Libreria "JTATTOO" anadida a partir de un jar externo)
		JFrame.setDefaultLookAndFeelDecorated(true);

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		SwingUtilities.updateComponentTreeUI(this);

		
		//*************************************LOOK AND FEEL ******************************************************
		
		

	}//End constructor

	
	/**
	 * Metodo que prepara todas las pestanias del programa
	 * @return
	 */


	public JTabbedPane panelPestanias(){

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);

		
		// Anadiendo panel principal ---------------
		tabbedPane.addTab("Telefonia Movil    ", panelPrincipal());
		tabbedPane.setToolTipTextAt(0, "Informacion sobre fabricantes y smartphones");
		// Anadiendo panel de mantenimiento ---------------
		tabbedPane.addTab("Mantenimiento    ", panelMantenimientoFabr());
		tabbedPane.setToolTipTextAt(1, "Mantenimiento de fabricantes");
		// Anadiendo panel de crear un fabricante ---------------
		tabbedPane.addTab("Crear fabricante  ", panelAnadirFabricante());
		tabbedPane.setToolTipTextAt(2, "Creacion de fabricantes");

		
		tabbedPane.setIconAt(0, new JLabel(new ImageIcon("images/movil.png")).getIcon());
		tabbedPane.setIconAt(1, new JLabel(new ImageIcon("images/settings.png")).getIcon());
		tabbedPane.setIconAt(2, new JLabel(new ImageIcon("images/create.png")).getIcon());
		
		
		return tabbedPane;
		
	}//End panelPestanias

	
	
	/**
	 * Metodo que prepara la primera pestania que realiza consultas varias
	 */
	
	private JPanel panelPrincipal(){
		
		//Panel a devolver:
		JPanel panelContenedor = new JPanel();
		panelContenedor.setLayout(new BoxLayout(panelContenedor, BoxLayout.Y_AXIS));
		
		//Pedir memoria para las variables de instancia:
		this.cbFabricantes = new JComboBox<String>();
		this.bVerMoviles = new JButton("Ver moviles");
		
		this.bBuscarMovil = new JButton("Buscar movil");
		this.tfBuscarMovil = new JTextField(5);

		this.bBuscarPatron = new JButton("Buscar fabricante");
		this.tfPatron = new JTextField(10);
		
		JPanel panelFabMov = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,20));
		panelFabMov.add(cbFabricantes);
		panelFabMov.add(bVerMoviles);
		panelFabMov.setBorder(new TitledBorder("Ver moviles de un fabricante"));
		
		
		JPanel panelBuscarPrecio = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.tfBuscarMovil.setText("200.00");
		panelBuscarPrecio.add(this.tfBuscarMovil);
		panelBuscarPrecio.add(new JLabel("€               "));
		panelBuscarPrecio.add(this.bBuscarMovil);
		panelBuscarPrecio.setBorder(new TitledBorder("Moviles por precio maximo"));
		
		JPanel panelBuscarFabricante = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
		this.tfPatron.setText("S%");
		panelBuscarFabricante.add(this.tfPatron);
		panelBuscarFabricante.add(this.bBuscarPatron);
		panelBuscarFabricante.setBorder(new TitledBorder("Buscar fabricante por expresion regular"));
		
		panelContenedor.add(panelFabMov);
		panelContenedor.add(panelBuscarPrecio);
		panelContenedor.add(panelBuscarFabricante);
		
		
		return panelContenedor;
		
	}//End panelPeliculas
	
	
	/**
	 * Metodo que se encarga de preparar la pestania con todos los datos para hacer el mantenimiento
	 * sobre dicha tabla de la base de datos
	 * @return
	 */
	
	private JPanel panelMantenimientoFabr(){
		
		this.tfCodigoFab = new JTextField(5);
		this.tfnombreFab = new JTextField(15);
		this.tfSedeFab = new JTextField(15);
		this.tfAnioFund = new JTextField(5);
		this.bSiguiente = new JButton("Siguiente");
		this.bAnterior = new JButton("Anterior");
		this.bEliminar = new JButton("Eliminar");
		this.bModificar = new JButton("Actualizar");
		this.bGuardar = new JButton("Guardar");
		this.bGuardar.setEnabled(false);
		
		this.tfCodigoFab.setEditable(false);
		this.tfnombreFab.setEditable(false);
		this.tfSedeFab.setEditable(false);
		this.tfAnioFund.setEditable(false);
		
		
		JPanel panelMantenimiento = new JPanel();
		
		panelMantenimiento.setLayout(new BoxLayout(panelMantenimiento, BoxLayout.Y_AXIS));
		
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));


		panelDatos.add(this.tfCodigoFab);
		panelDatos.add(this.tfnombreFab);
		panelDatos.add(this.tfSedeFab);
		panelDatos.add(this.tfAnioFund);
		
	//	panelDatos.setPreferredSize(new Dimension(400,200));
		
		
		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
		panelBotones.add(this.bEliminar);
		panelBotones.add(this.bAnterior);
		panelBotones.add(this.bSiguiente);
		panelBotones.add(this.bModificar);
		

		JPanel panelGuardar = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelGuardar.add(this.bGuardar);
		
		
		
		
		panelMantenimiento.add(panelDatos);
		panelMantenimiento.add(panelBotones);
		panelMantenimiento.add(panelGuardar);

		
		TitledBorder centerBorder = BorderFactory.createTitledBorder("Datos Fabricantes - Moviles");
		centerBorder.setTitleJustification(TitledBorder.CENTER);
		panelMantenimiento.setBorder(centerBorder);
		
		
		
		
		return panelMantenimiento;
		
	}//End panelMantenimientoFabr
	
	
	/**
	 * Metodo que devuelve un panel que va a contener los datos para crear un nuevo fabricante a la base de 
	 * datos
	 */
	
	private JPanel panelAnadirFabricante(){
		
		//Dar memoria a las variables de instancia que vamos a utilizar
		this.tfAnioFundacionAñadir = new JTextField(5);
		this.tfNombreFabAñadir = new JTextField(15);
		this.tfSedeFabAñadir = new JTextField(15);
		this.bCrearFabricante = new JButton("Crear");
		
		
		JPanel panelContenedor = new JPanel();
		
		
		
		JPanel panelDatos = new JPanel();
		panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS));
		
		
		JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelNombre.add(new JLabel("Nombre de empresa: "));
		panelNombre.add(this.tfNombreFabAñadir);
		
		
		JPanel panelSede = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSede.add(new JLabel("Sede:                              "));
		panelSede.add(this.tfSedeFabAñadir);
		
		JPanel panelFundacion = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelFundacion.add(new JLabel("Fundacion:                                                         "));
		panelFundacion.add(this.tfAnioFundacionAñadir);
		
		
		panelDatos.add(panelNombre);
		panelDatos.add(panelSede);
		panelDatos.add(panelFundacion);
		panelDatos.add(this.bCrearFabricante);
		
		panelDatos.setPreferredSize(new Dimension(400,200));
		
		
		panelContenedor.add(panelDatos);
		
		
		
		TitledBorder centerBorder = BorderFactory.createTitledBorder("Datos del fabricante");
		centerBorder.setTitleJustification(TitledBorder.CENTER);
		panelContenedor.setBorder(centerBorder);
		
		
		
		return panelContenedor;
	}//End panelAnadirFabricante
	
	
	
	

	/**-----------------------------------------------------------------------------------------
	 * DEFINICION DE METODOS GET:
	 * -----------------------------------------------------------------------------------------
	 */
	
	public JComboBox<String> getCbFabricantes() {return cbFabricantes;}
	public JButton getbVerMoviles() {return bVerMoviles;}
	public JButton getbBuscarMovil() {return bBuscarMovil;}
	public JTextField getTfBuscarMovil() {return tfBuscarMovil;}
	public JTextField getTfPatron() {return tfPatron;}
	public JButton getbBuscarPatron() {return bBuscarPatron;}
	public JTextField getTfCodigoFab() {return tfCodigoFab;}
	public JTextField getTfnombreFab() {return tfnombreFab;}
	public JTextField getTfSedeFab() {return tfSedeFab;}
	public JTextField getTfAnioFund() {return tfAnioFund;}
	public JButton getbSiguiente() {return bSiguiente;}
	public JButton getbAnterior() {return bAnterior;}
	public JButton getbEliminar() {return bEliminar;}
	public JButton getbModificar() {return bModificar;}
	public JButton getbGuardar() {return bGuardar;}
	public JTextField getTfAnioFundacionAñadir() {return tfAnioFundacionAñadir;}
	public JTextField getTfNombreFabAñadir() {return tfNombreFabAñadir;}
	public JTextField getTfSedeFabAñadir() {return tfSedeFabAñadir;}
	public JButton getbCrearFabricante() {return bCrearFabricante;}
	
	/**------------------------------------------------------------------------------------------
	 * ANADIR CONTROL A LOS COMPONENTES:
	 * ------------------------------------------------------------------------------------------
	 */
	
	public void control(Controlador ctr){
		
		//Botones de la primera pestania (telefonia movil)
		this.bVerMoviles.addActionListener(ctr);
		this.bBuscarMovil.addActionListener(ctr);
		this.bBuscarPatron.addActionListener(ctr);
		
		//Botones de la segunda pestania(mantenimiento de fabricantes)
		this.bSiguiente.addActionListener(ctr);
		this.bAnterior.addActionListener(ctr);
		this.bModificar.addActionListener(ctr);
		this.bEliminar.addActionListener(ctr);
		this.bGuardar.addActionListener(ctr);
		
		//Pestania de crear un fabricante
		this.bCrearFabricante.addActionListener(ctr);
		
		
	}//End control
	

}//End class Vista
