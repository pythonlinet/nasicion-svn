package bd1.obli2012;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Util {
	public static Properties getProperties() {
		Properties props = new Properties();
		try {
			//props.load(Util.class.getResourceAsStream("/uy/edu/ucu/bd1/gnasi/database.properties"));
                    props.load(Util.class.getResourceAsStream("/bd1/obli2012/database.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
}
