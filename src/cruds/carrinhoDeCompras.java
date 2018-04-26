package cruds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class carrinhoDeCompras {
	
	/*
	 * 
	Adicionar Produto ao carrinho
	Remover Produto ao carrinho
	Ver Carrinho
	Finalizar Compra
	
	*/
	
	//ArrayList <BasicDBList> listacarrinho	= new ArrayList<>();
	
	private DB database;
	  
	private DBCollection clientes;
	private DBCollection produtos;
    
	private Crud crudClientes;
	private Crud crudProdutos;
    
    MongoClient mongoClient;
    
    private double valorCompra =0;
    ArrayList<DBObject> itemAdquiridos = new ArrayList<>();
    
    public carrinhoDeCompras(String host, int port, String database) {
    	
    	mongoClient = new MongoClient(host, port);
        
        this.database = mongoClient.getDB(database);
        
        this.clientes = (this.database).getCollection("clientes");
        this.produtos = (this.database).getCollection("produtos");
        
        this.crudClientes = new Crud(clientes);
        this.crudProdutos = new Crud(produtos);
    }
    
    public void adicionarItem() {
    	
    	Scanner scanner = new Scanner(System.in);
		
		
		System.out.print("\nDigite o nome do produto que adquirir: ");
		
		String adquirirproduto = scanner.nextLine();
		
		BasicDBObject query = new BasicDBObject();
		query.put("produto", adquirirproduto);
		
		//achou o produto
		DBObject queriedDoc = produtos.findOne(query);
		
		System.out.println("Produto " + queriedDoc.get("produto").toString()
				+ " de valor: R$" +  queriedDoc.get("valor")
				+ "foi adquirido");
		
		this.itemAdquiridos.add(queriedDoc);
    }
    
    public void verCarrinho() {
    	
    	if (this.itemAdquiridos.size() == 0) {
			System.out.println("Não foram encontrados produtos no carrinho" + "\nTente novamente.");
			return;

		} else {
			System.out.println("Produtos no carrinho: ");

			for (Object prod : this.itemAdquiridos) {

				System.out.println(prod);
			}
		}
    	
    } //this.valorCompra += (double) prod.get("valor");
    
    public void finalizarCompra() {
    	
    	// buscando cliente comprador
    	
    	Scanner scanner = new Scanner(System.in);
    	
		System.out.print("\nDigite o CPF do cliente que realizando a compra: ");
		
		String comprador = scanner.nextLine();
		
		BasicDBObject query = new BasicDBObject();
		query.put("cpf", comprador);
		
		//achou o documento a ser atualizado
		DBObject clienteComprador = clientes.findOne(query);
	
		System.out.println("Cliente " + clienteComprador.get("name").toString() + " está realizando a compra.");
    	
		//visualizando itens adiquiridos
		
		if (this.itemAdquiridos.size() == 0) {
			System.out.println("Não foram encontrados produtos no carrinho" + "\nTente novamente.");
			return;

		} else {
			System.out.println("Produtos no carrinho: ");

			for (Object prod : this.itemAdquiridos) {

				System.out.println(prod);
			}
		}
		
		
    	// somando os valores
    	if (this.itemAdquiridos.size() == 0) {
			System.out.println("Não foram encontrados produtos no carrinho" + "\nTente novamente.");
			//return;

		} else {
			System.out.println("Produtos no carrinho: ");

			for (DBObject prod : this.itemAdquiridos) {

				this.valorCompra += ((Number) prod.get("valor")).doubleValue();
			}
		}
    	
    	System.out.println("O valor total da compra é: R$" + this.valorCompra);
    	
    	Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        System.out.println("A compra ocorreu em: " + sdf.format(cal.getTime()) );
    	
    }
    
    
	


}
