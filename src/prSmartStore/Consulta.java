package prSmartStore;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class Consulta extends JDialog {
	
	
	
	private static final long serialVersionUID = 1L;

	// Constructor
	public Consulta(DefaultTableModel datos)
	{ 
		
		// Poner el dialogo modal, darle título y gestor de esquemas
		this.setModal(true);
		this.setTitle("DATOS");
		this.setLayout(new FlowLayout());
		
		//Validar que hay algun resultado a devolver, si no, saldra un dialogo:
		if(datos.getRowCount()==0){
			JOptionPane.showMessageDialog(this,"No se han obtenido resultados","NOT FOUND",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		
		
	
		JTable tablaResultados = new JTable(datos);
	//	tablaResultados.setModel(datos);
		JScrollPane sp = new JScrollPane(tablaResultados);
		
		this.add(sp);
	   
	    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	this.pack(); 
    	this.setLocationRelativeTo(null);
		this.setVisible(true);   // Para mostrar el dialogo
		this.setResizable(false);

	}
	
	
	
	
	
	
	
}//End class
