/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado.helper;

import bd1.obli2012.versionado.BDDVersionado;
import bd1.obli2012.versionado.Cambio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gnasi
 */
public abstract class VersionadoHelper {

    private static final String VERSION_EXTENSTION = ".ver";
    private static final Logger LOGGER = Logger.getLogger(VersionadoHelper.class.getName());

    public static String obtenerQuery(Cambio cambio) {

        return null;
    }

    /**
     * Obtiene las versiones de la base de datos
     *
     * @param nombreDb
     * @return
     */
    public static BDDVersionado obtenerVersiones(String nombreDb) {
        Object leido = null;
        ObjectInputStream streamArchivo = null;
        try {
            FileInputStream archivo = new FileInputStream(nombreDb + VERSION_EXTENSTION);
            streamArchivo = new ObjectInputStream(archivo);
            leido = streamArchivo.readObject();
        } catch (ClassNotFoundException ex) {
            LOGGER.severe("Esto no deberia suceder");
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.INFO, "No existe versionado para la base de datos {0}", nombreDb);
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        } finally {
            try {
                streamArchivo.close();
            } catch (IOException ex) {
                LOGGER.severe(ex.getMessage());
            }
        }
        BDDVersionado salida = null;
        if (leido != null && leido instanceof BDDVersionado) {
            salida = (BDDVersionado) leido;
        }
        return salida;
    }
}
