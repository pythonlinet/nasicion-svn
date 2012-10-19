package bd1.obli2012;

import bd1.obli2012.framework.DatabaseManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        //String crearTabla = "CREATE TABLE customer (First_Name CHARACTER(50), Last_Name CHARACTER(50), Address CHARACTER(50), City CHARACTER(50), Country CHARACTER(25), Birth_Date date, Children INTEGER) ";
        //DatabaseManager.getInstance().executeQuery(crearTabla);
        /*List<Database> databases = DatabaseManager.getInstance().getDataBases();

        for (Database database : databases) {
            System.out.println("Database: " + database.getDbName());
            for (Schema s : database.getSchemas()) {
                System.out.println("\tSchema: " + s.getNombre());
                for (Table t : s.getTablas()) {
                    System.out.println("\t\tTable: " + t.getNombre());
                    for (Attribute a : t.getAttributes()) {
                        System.out.println("\t\t\tAttribute: " + a.toString());
                    }
                }
            }
        }
        List<Schema> schemas = DatabaseManager.getInstance().getDataBaseSchemas("%");

        for (Schema s : schemas) {
            System.out.println("Schema: " + s.getNombre());
            for (Table t : s.getTablas()) {
                System.out.println("\tTable: " + t.getNombre());
                for (Attribute a : t.getAttributes()) {
                    System.out.println("\t\tAttribute: " + a.toString());
                }
            }
        }*/
        /*
        String query = "SELECT datname FROM pg_database;";
        ResultSet rs = DatabaseManager.getInstance().executeQueryWithResult(query);
        try {
            while(rs.next()) {
                System.out.println(rs.getString(1));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        DatabaseManager.getInstance().dumpTable("alquiler", "arrendador");
    }
}
