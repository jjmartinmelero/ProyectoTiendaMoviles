package prSmartStore;
// Clase de manejo de excepciones

public class MiExcepcion extends Exception {
	private static final long serialVersionUID = 1L;

	public MiExcepcion() {
		super();
	}
	
	public MiExcepcion(String msg) {
		super(msg);
	}
}
