package cruds;
import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public interface CrudOperations {
	
	public abstract void create(BasicDBObject document);
		
	public abstract ArrayList<DBObject> read(BasicDBObject query);
	
	public abstract void update( BasicDBObject query, String key, Object val);
	
	public abstract void delete(BasicDBObject query);
	
}
