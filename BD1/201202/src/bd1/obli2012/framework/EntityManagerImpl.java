package bd1.obli2012.framework;

import bd1.obli2012.framework.estructura.ColumnDefinition;
import bd1.obli2012.framework.estructura.Entidad;
import bd1.obli2012.framework.estructura.TableDefinition;

public class EntityManagerImpl implements EntityManager {

	private DatabaseManager db;

	public EntityManagerImpl(DatabaseManager db){
		this.db = db;
	}
	
	@Override
	public boolean createTable(Entidad entidad) {
		StringBuilder strBld = new StringBuilder("CREATE TABLE ");
		strBld.append(entidad.getTabla());
		TableDefinition td = entidad.getTableDefinition();
		for(ColumnDefinition cd : td.getDefColumnas()){
			
		}
		return false;
	}

}
