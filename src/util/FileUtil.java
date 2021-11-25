package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import logica.AtletaDto;

public abstract class FileUtil{


	public static String loadFileTicket (String nombreFicheroEntrada, List<AtletaDto> listaCatalogo) {

		String linea;
		String nombreEquipo = "";
		String[] datosArticulo= null;	   
		int contador =0;
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				if (contador==0) {
					nombreEquipo=linea;
					contador++;
				}else {
					datosArticulo = linea.split("#");
					if (datosArticulo.length ==5) {
						AtletaDto a = new AtletaDto();
						a.setNombre(datosArticulo[0]);
						a.setDni(datosArticulo[1]);
						a.setF_nac(datosArticulo[2]);
						a.setSexo(datosArticulo[3]);
						a.setEmail(datosArticulo[4]);
						listaCatalogo.add(a);
					}
				}
			}
			fichero.close();

		}
		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		}
		catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		} 
		return nombreEquipo;
	}

}
