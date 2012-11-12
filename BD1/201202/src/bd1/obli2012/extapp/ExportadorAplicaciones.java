package bd1.obli2012.extapp;

import bd1.obli2012.Util;
import bd1.obli2012.framework.ColumnManager;
import bd1.obli2012.framework.ConnectionManager;
import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.ExecutionResult;
import bd1.obli2012.framework.PostgresConnectionManager;
import bd1.obli2012.framework.QueryBuilder;
import bd1.obli2012.framework.QueryCriteria;
import bd1.obli2012.framework.TablaManager;
import bd1.obli2012.framework.definicion.BaseDeDatos;
import bd1.obli2012.framework.definicion.Columna;
import bd1.obli2012.framework.definicion.Schema;
import bd1.obli2012.framework.definicion.Tabla;
import bd1.obli2012.framework.definicion.TipoDato;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author favio.ortelli/guillermo.nasi
 */
public abstract class ExportadorAplicaciones {

    private static final Class[] frameworkClasses = {
        ColumnManager.class,
        DatabaseManager.class,
        PostgresConnectionManager.class,
        QueryCriteria.class,
        ConnectionManager.class,
        ExecutionResult.class,
        QueryBuilder.class,
        TablaManager.class,
        BaseDeDatos.class,
        Columna.class,
        Schema.class,
        Tabla.class,
        TipoDato.class};
    private static final Class[] externalAppClasses = {
        AplicacionExterna.class,
        DialogAltaMod.class,
        DialogoFiltro.class,
        PanelFiltro.class
    };
    public static final Logger LOGGER = Logger.getLogger(ExportadorAplicaciones.class.getName());
    /*
     public static void main(String[] args) {
     File f = new File(".");
     try {
     ExportadorAplicaciones ae = new ExportadorAplicaciones();
            
     ae.exportarApp(null);
     } catch (IOException ex) {
     Logger.getLogger(ExportadorAplicaciones.class.getName()).log(Level.SEVERE, null, ex);
     }
     }
     */

    public static void exportarApp(String path, BaseDeDatos bd) throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, AplicacionExterna.class.getName());
        manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, "lib/driver.jar");
        //Crear el directorio para la app
        new File(path + "/" + bd.getDbName()).mkdir();
        JarOutputStream jarOutputStream =
                new JarOutputStream(
                new FileOutputStream(
                path + "/" + bd.getDbName() + "/" + bd.getDbName() + ".jar"), manifest);


        //Exportar Framework
        for (Class c : frameworkClasses) {
            addClass(c, jarOutputStream);
        }
        //Exportar Aplicacion
        for (Class c : externalAppClasses) {
            addClass(c, jarOutputStream);
        }

        addClass(Util.class, jarOutputStream);
        //addClass(AplicacionExterna.class, jarOutputStream);


        // Add a secret message
        jarOutputStream.putNextEntry(new JarEntry("bd1/obli2012/database.properties"));


        //InputStream propsIs = ExportadorAplicaciones.class.getResourceAsStream("/bd1/obli2012/database.properties");
        InputStream propertiesFileInputStream = ExportadorAplicaciones.class.getResourceAsStream("/bd1/obli2012/database.properties");
        ByteArrayOutputStream propertiesFileOutputStream = new ByteArrayOutputStream();


        byte[] buf = new byte[1024];
        int len;
        while ((len = propertiesFileInputStream.read(buf)) > 0) {
            propertiesFileOutputStream.write(buf, 0, len);
        }
        byte[] entradaDB = ("\ndb.name="+bd.getDbName()).getBytes();
        propertiesFileOutputStream.write(entradaDB);
        propertiesFileInputStream.close();
        propertiesFileOutputStream.close();


        //byte[] entradaProps = Util.toByteArray(propsIs);
        jarOutputStream.write(propertiesFileOutputStream.toByteArray());
        jarOutputStream.closeEntry();
        jarOutputStream.close();
        //Crear el directorio para las librearias
        new File(path + "/" + bd.getDbName() + "/lib").mkdir();
        copiarArchivo("lib/postgresql-9.1-902.jdbc4.jar", path + "/" + bd.getDbName() + "/lib/driver.jar");
    }

    private static void addClass(Class c, JarOutputStream jarOutputStream) throws IOException {
        String path = c.getName().replace('.', '/') + ".class";
        jarOutputStream.putNextEntry(new JarEntry(path));

        jarOutputStream.write(Util.toByteArray(c.getClassLoader().getResourceAsStream(path)));
        jarOutputStream.closeEntry();

        int i = 1;
        InputStream is = null;
        do {
            String tempPath = c.getName().replace('.', '/') + "$" + i + ".class";
            is = c.getClassLoader().getResourceAsStream(tempPath);

            if (is != null) {
                jarOutputStream.putNextEntry(new JarEntry(tempPath));

                jarOutputStream.write(Util.toByteArray(is));
                jarOutputStream.closeEntry();
            }
            i++;
        } while (is != null);
    }

    private static void copiarArchivo(String srFile, String dtFile) {
        try {
            File f1 = new File(srFile);
            File f2 = new File(dtFile);
            InputStream in = new FileInputStream(f1);

            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException ex) {
            LOGGER.severe(ex.getLocalizedMessage());
        } catch (IOException e) {
            LOGGER.severe(e.getLocalizedMessage());
        }
    }
}
