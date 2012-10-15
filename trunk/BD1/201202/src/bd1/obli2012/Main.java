package bd1.obli2012;


import bd1.obli2012.framework.Attribute;
import bd1.obli2012.framework.DatabaseManager;
import bd1.obli2012.framework.Schema;
import bd1.obli2012.framework.Table;
import java.util.List;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		//String crearTabla = "CREATE TABLE customer (First_Name CHARACTER(50), Last_Name CHARACTER(50), Address CHARACTER(50), City CHARACTER(50), Country CHARACTER(25), Birth_Date date, Children INTEGER) ";
		//DatabaseManager.getInstance().executeQuery(crearTabla);
            List<Schema> schemas = DatabaseManager.getInstance().getDataBaseSchemas();
            for(Schema s : schemas){
                System.out.println("Schema: " + s.getNombre());
                for(Table t : s.getTablas()){
                    System.out.println("\tTable: " + t.getNombre());
                    for(Attribute a : t.getAttributes()){
                        System.out.println("\t\tAttribute: " + a.toString());
                    }
                }
            }
        }
}
