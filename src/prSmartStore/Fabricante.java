package prSmartStore;

public class Fabricante {
	
	
	//Definicion de variables de instancia
	private int codFabricante;
	private String nombreFabricante, nombreSede;
	private int anioFundacion;
	
	//Definicion de constructor:
	public Fabricante(int codigo,String nombreEmpresa, String nombreSede, int anioFundada){
		
		this.codFabricante = codigo;
		this.nombreFabricante = nombreEmpresa;
		this.nombreSede = nombreSede;
		this.anioFundacion = anioFundada;
		
	}//End constructor
	
	/**
	 * Definicion de metodos gets:
	 */
	
	public int getCodFabricante() {return codFabricante;}
	public String getNombreFabricante() {return nombreFabricante;}
	public String getNombreSede() {return nombreSede;}
	public int getAnioFundacion() {return anioFundacion;}
	
	
	
	/**
	 * Redefinicion del metodo toString
	 */
	
	@Override
	public String toString(){
		
		return "EMPRESA: "+this.nombreFabricante.toUpperCase()+
				"\n\t Sede Central: "+this.nombreSede+
				"\n\t Fundacion: "+this.anioFundacion;
		
		
	}//End toString
	
	
	
	
	
	
	
	
}//End class Fabricante
