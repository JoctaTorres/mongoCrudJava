package cruds;
import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public interface CrudOperations {
	
	public abstract void create(BasicDBObject document);
		
	public abstract BasicDBList read(BasicDBObject query);
    
    	public BasicDBList read();
	
	public abstract void update( BasicDBObject query, String key, Object val);
	
	public abstract void delete(BasicDBObject query);
	
}
