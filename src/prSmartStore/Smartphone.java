package prSmartStore;

import java.sql.Date;

public class Smartphone {
	
	/**
	 * Un smartphone estara formado por: nombre,precio,fechaSalida,so,procesador,gpu
	 */
	
	//Definicion de variables de instancia:
	private String nombre, sistemaOperativo, procesador, gpu;
	private double precio;
	private Date lanzamiento;
	
	//Definicion de constructor:
	public Smartphone(String nombre, String procesador, String gpu, String os, double precio, Date fechaSalida){
		
		this.nombre = nombre;
		this.procesador=procesador;
		this.gpu = gpu;
		this.sistemaOperativo = os;
		this.precio = precio;
		this.lanzamiento = fechaSalida;
		
		
	}//End constructor
	
	/**
	 * Definicion de metodos gets:
	 */

	public String getNombre() {return nombre;}
	public String getSistemaOperativo() {return sistemaOperativo;}
	public String getProcesador() {return procesador;}
	public String getGpu() {return gpu;}
	public double getPrecio() {return precio;}
	public Date getLanzamiento() {return lanzamiento;}
	
	/**
	 * Redefinicion del metodo toString()
	 */
	
	@Override
	public String toString(){
		
		
		return "Smartphone [nombre=" + nombre + ", procesador=" + procesador + ", gpu="
				+ gpu + ", precio =" + precio + ", fecha lanzamiento=" + lanzamiento + "]";
		
	}//End toString
	
	
	
	
	
}//End class Smartphone
