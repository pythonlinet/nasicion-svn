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
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author guillermo
 */
public class ExportadorAplicaciones {

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

    public static void main(String[] args) {
        File f = new File(".");
        try {
            ExportadorAplicaciones ae = new ExportadorAplicaciones();
            
            ae.exportarApp(null);
        } catch (IOException ex) {
            Logger.getLogger(ExportadorAplicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public void exportarApp(BaseDeDatos bd) throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, AplicacionExterna.class.getName());
        manifest.getMainAttributes().put(Attributes.Name.CLASS_PATH, "lib/swing-layout-1.0.4.jar lib/postgresql-9.1-902.jdbc4.ja\n"
                + " r");
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream("dist/test.jar"), manifest);


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
        jarOutputStream.write(Util.toByteArray(this.getClass().getResourceAsStream("/bd1/obli2012/database.properties")));
        jarOutputStream.closeEntry();
        jarOutputStream.close();
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
            
            if(is != null){
                jarOutputStream.putNextEntry(new JarEntry(tempPath));

                jarOutputStream.write(Util.toByteArray(is));
                jarOutputStream.closeEntry();
            }
            i++;
        }while(is != null);
    }
}
