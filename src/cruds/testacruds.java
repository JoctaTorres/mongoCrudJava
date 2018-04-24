package cruds;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class testacruds {
	
	public static void main (String[] args) {
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		DB database = mongoClient.getDB("testacruds");
		System.out.println("Connected to database: " + database.getName());
		
		
		DBCollection clientes = database.getCollection("clientes");
		DBCollection produtos = database.getCollection("produtos");
	
		Crud crudClientes = new Crud(clientes);
		Crud crudProdutos = new Crud(produtos);
		
		
		// adicionando um cliente:
		
		BasicDBObject cliente1 = new BasicDBObject();
		cliente1.put("name", "bruno");
		cliente1.put("cpf", "111-111-111-11");
		 
		 crudClientes.create(cliente1);
		 
	}

}
