package uy.edu.ucu.pii.obligatorio2.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ConversorNumeros {
	public Double convertirADouble(String str) {
		Locale l = new Locale("en", "EN");
		Locale.setDefault(l);
		NumberFormat nf = NumberFormat.getInstance();
		Double result = 0.0;
		try {
			Number resultadoParse = nf.parse(str);
			if (resultadoParse instanceof Long){
				Long resultadoLng = (Long) resultadoParse;
				result = new Double(resultadoLng.doubleValue());
			} else {
				if (resultadoParse instanceof Integer){
					Integer resultadoInteger = (Integer) resultadoParse;
					result = new Double(resultadoInteger.doubleValue());
				} else {
					if (resultadoParse instanceof Double){
						result = (Double) resultadoParse;
					}
				}
			}
		} catch (ParseException e) {
			System.err.println("Error al parsear string "+str);
		}
		return result;
	}
}
