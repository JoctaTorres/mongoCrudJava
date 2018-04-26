package cruds;
import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class Crud implements CrudOperations{
	
	
	DBCollection clientes;
	
	public Crud(DBCollection clientes) {
		
		this.clientes = clientes;
		
	}

	@Override
	public void create(BasicDBObject document) {

		this.clientes.insert(document);
		
	}

	@Override
	public BasicDBList read(BasicDBObject query) {
		
		//ArrayList<DBObject> arr = new ArrayList<DBObject>();
                
        BasicDBList list = new BasicDBList();
		DBCursor cursor = this.clientes.find(query);

		while (cursor.hasNext()) {
			
			list.add(cursor.next());
		}
		
		return list;		
	}
	
	@Override
    public BasicDBList read() {

	DBCursor cursor = this.clientes.find();
    BasicDBList list = new BasicDBList();

	while (cursor.hasNext()) {
		
		list.add(cursor.next());
	}
	
	return list;		
}
	
	@Override
	public void update(BasicDBObject query, String key, Object val) {
		
		
		DBCursor cursor = this.clientes.find(query);
		DBObject queriedDoc;
		
		if (cursor.hasNext()) {
			
			queriedDoc = cursor.next();
			queriedDoc.put(key, val);
			clientes.save(queriedDoc);
		}
	}

	@Override
	public void delete(BasicDBObject query) {
		
		
		DBCursor cursor = this.clientes.find(query);
		DBObject item;
		
		while (cursor.hasNext()) {
			
			item = cursor.next();
			this.clientes.remove(item);
		}
		
	}
	
	

}
