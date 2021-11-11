package util;

import java.sql.SQLException;

import igu.VentanaInicial;

public class Main {

	public static void main(String[] args) throws SQLException
	{
//		new CompeticionModel().getCompeticionesArray();
//		new InscripcionModel().getInscripciones();
//		new AtletaModel().getAtletas();	
//		System.out.println();
//		new AtletaModel().atletaAlredyRegistred("natalia@email.com", "Cross de Tineo");
//		System.out.println(new InscripcionModel().findAtletaEmail("jose@email.com"));
		VentanaInicial frame = new VentanaInicial();
		frame.setVisible(true);
	}
	
}
