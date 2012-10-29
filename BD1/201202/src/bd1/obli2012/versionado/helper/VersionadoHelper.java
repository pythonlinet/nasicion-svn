/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd1.obli2012.versionado.helper;

import bd1.obli2012.versionado.BDDVersionado;
import bd1.obli2012.versionado.Cambio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            FileInputStream archivo = new FileInputStream("./"+nombreDb + VERSION_EXTENSTION);
            streamArchivo = new ObjectInputStream(archivo);
            leido = streamArchivo.readObject();
            streamArchivo.close();
        } catch (ClassNotFoundException ex) {
            LOGGER.severe("Esto no deberia suceder");
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.INFO, "No existe versionado para la base de datos {0}", nombreDb);
            return new BDDVersionado(nombreDb);
        } catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        } 
        BDDVersionado salida = null;
        if (leido != null && leido instanceof BDDVersionado) {
            salida = (BDDVersionado) leido;
        }
        return salida;
    }

    public static void salvarCambios(BDDVersionado bddVersionado) {
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(bddVersionado.getNombreBDD() + VERSION_EXTENSTION,false);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(bddVersionado);
            oos.close();
        } catch (FileNotFoundException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        }
    }
}
