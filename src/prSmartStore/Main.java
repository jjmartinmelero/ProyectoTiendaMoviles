package prSmartStore;
//DESARROLLADO POR -- ** JUAN JESUS MARTIN MELERO ** --
import javax.swing.JFrame;


public class Main {

	public static void main(String[] args) {
		
		
		Vista miVista = new Vista();
		JFrame ventana = new JFrame("Gestor de fabricantes y moviles");
		Controlador ctr = new Controlador(miVista);
		
		miVista.control(ctr);
		ventana.add(miVista);
		
		ventana.setSize(675,600);

		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		ventana.setResizable(false);
		
		ventana.addWindowListener(ctr);

		
		
	}//End main

}
