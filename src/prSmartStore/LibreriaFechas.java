package prSmartStore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class LibreriaFechas {
	/**
	 * Método que comprueba si una fecha que llega como String (dd/mm/aaaa) es correcta o no
	 */
	public static boolean esFechaCorrecta(String fecha) 
	{
	   try {
		SimpleDateFormat formatoFecha = 
			new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
			
		// El método setLenient a false obliga a que la fecha 
		// "tenga sentido estricto", y por lo tanto rechaza un 
		// "30 de febrero" o un "29 de febrero de 2007" como fechas válidas.
		// 	Si no establecemos el lenient a false, al parsear una fecha 
		// "interpretará" la fecha correcta. Un "30 de febrero" se convertirá 
		// en 1 marzo, (en 2 de marzo si es un año no bisiesto)...
		formatoFecha.setLenient(false);
			
		// El método parse devuelve un objeto Date, por tanto si el String que
		// le llega no es una fecha correcta, bien por formato (Ej: 12/hola), 
		// bien porque el día, mes o año sean incorrectos (Ej: 30/02/2011)
		// lanza una excepción del tipo ParseException
		formatoFecha.parse(fecha);  
	   	} 
	    catch (ParseException e) {
		     return false;
	    }

	    return true;
	}

	
	/**
	 * Convierte un fecha que llega en formato String a objeto Calendar
	 * @throws MiExcepcion 
	 */
	public static Calendar convierteFechaStringACalendar(String fechaCadena) throws MiExcepcion {

		Calendar fechaCalendar = Calendar.getInstance(); // fechaNacimiento es de tipo Calendar
		SimpleDateFormat formatoFecha =
					new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()); 
		Date fechaDate=null;
		try {
			formatoFecha.setLenient(false);   // Probar a quitárselo, y pasarle la fecha 29/02/2015"
			fechaDate = formatoFecha.parse(fechaCadena);
			fechaCalendar.setTime(fechaDate);
		} catch (ParseException e) {
			throw new MiExcepcion("Error de parseo");
		} catch (NullPointerException e) {
			throw new MiExcepcion("Puntero nulo");
		}
			
		return fechaCalendar;
	}

	/**
	 * Convierte una fecha en formato Date a Calendar
	 * @param fechaDate
	 * @return
	 */
	public static Calendar convierteDateACalendar(Date fechaDate) {
		Calendar fechaCalendar = new GregorianCalendar();
		fechaCalendar.setTime(fechaDate);
		return fechaCalendar;
	}
	
	/**
	 * Método que recibe una fecha tipo Calendar y la devuelve la fecha en formato completo:
	 *    Ejemplo: domingo 9 de septiembre de 2001)
	 */
	public static String getFechaFull(Calendar fechaCalendar) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		return df.format(fechaCalendar.getTime());
	}
	
	
	/**
	 * Método que recibe una fecha tipo Calendar y la devuelve la fecha en formato corto:
	 *    Ejemplo: 09/10/15)
	 */
	public static String getFechaShort(Calendar fechaCalendar) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		return df.format(fechaCalendar.getTime());
	}

	
	/**
	 * Método que recibe una fecha tipo Calendar y la devuelve la fecha en formato personalizado
	 *    Ejemplo: 09/10/2015)
	 */
	public static String getFechaPersonalizada(Calendar fechaCalendar) {
		SimpleDateFormat formatoFecha =
			new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault()); 
		
		return formatoFecha.format(fechaCalendar.getTime());
	}
	
	
	/**
	 * Este método se encarga de sumar a "fecha", los días indicados
	 * @param fecha
	 * @param dias
	 */
	public static Calendar sumaDias(Calendar fecha, int dias) {
		Calendar fechaNueva = copiaFecha(fecha);
		fechaNueva.add(Calendar.DATE, dias);
		return fechaNueva;
	}
	
	
	/**
	 * Este método nos permite crear un nuevo objeto tipo Calendar, a partir de 
	 * otro objeto Calendar ya existente
	 * @param fecha
	 * @return
	 */
	public static Calendar copiaFecha(Calendar fecha) {
		Calendar nuevaFecha = new GregorianCalendar();
		nuevaFecha.setTime(fecha.getTime());
		return nuevaFecha;
	}
	
	
}
