package cruds;

import java.util.Scanner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class SistemaDeVenda {
	
	private DB database;
	  
	private DBCollection clientes;
	private DBCollection produtos;
    
	private Crud crudClientes;
	private Crud crudProdutos;
    
    MongoClient mongoClient;
    
    public SistemaDeVenda(String host, int port, String database) {
    	
    	mongoClient = new MongoClient(host, port);
        
        this.database = mongoClient.getDB(database);
        
        this.clientes = (this.database).getCollection("clientes");
        this.produtos = (this.database).getCollection("produtos");
        
        this.crudClientes = new Crud(clientes);
        this.crudProdutos = new Crud(produtos);
    }
    
    // Funções do crud Cliente:
    
    public void cadastrarCliente() {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	String name, cpf;
    	
    	
    	System.out.println("Digite o Nome: ");
    	name = scanner.nextLine();
    	System.out.println("Digite o CPF: ");
    	cpf = scanner.nextLine();
    	
    	BasicDBObject clienteDoc = new BasicDBObject();
    	clienteDoc.put("name", name);
    	clienteDoc.put("cpf", cpf);
    	System.out.println("Cliente Cadastrado!");
    	
    	crudClientes.create(clienteDoc);
    	
    	
    }
    
    public void listarClientes() {
    	
    	BasicDBList lista = crudClientes.read();
    	
    	if (lista.size() == 0) {
			System.out.println("Não foram encontrados clientes com esse nome." + "\nTente novamente.");
			return;

		} else {
			System.out.println("Foram encontrados: ");

			for (Object line : lista) {

				System.out.println(line);
			}
		}
    	
    }
    
    public void buscarCliente() {
    	
    	BasicDBObject clientebusca = new BasicDBObject();
    	BasicDBList lista;
    	
    	System.out.print("\nBuscar Cliente por: \n"
    			+ "[1] - Nome \n"
    			+ "[2] - CPF\n"
    			+ "\nOpção: ");
    	
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	int opc = scanner.nextInt();
    	scanner.nextLine();
    	
    	switch(opc) {
    	
    	case 1:
    			//query nome
	    		String name;
	    		
	    		System.out.print("\nDigite um NOME para buscar: ");
	        	name = scanner.nextLine();
	        	
	        	
	        	clientebusca.put("name", name);
	        	
	        	lista = crudClientes.read(clientebusca);
	        	
	        	if (lista.size() == 0) {
					System.out.println("Não foram encontrados clientes com esse nome." + "\nTente novamente.");
					return;

				} else {
					System.out.println("Foram encontrados: ");

					for (Object line : lista) {

						System.out.println(line);
					}
				}

	        	break;	        	
    	case 2:
    			//query cpf
    		
	    		String cpf;
	    		
	    		System.out.print("\nDigite um CPF para buscar: ");
	        	cpf = scanner.nextLine();
	        	
	        	
	        	clientebusca.put("cpf", cpf);
	        	
	        	lista = crudClientes.read(clientebusca);
	        	
	        	if (lista.size() == 0) {
					System.out.println("Não foram encontrados clientes com esse nome." + "\nTente novamente.");
					return;

				} else {
					System.out.println("Foram encontrados: ");

					for (Object line : lista) {

						System.out.println(line);
					}
				}
	    	
	        	
	    		break;
    	default:
    		
    			System.out.println("Opção inválida. Tente novamente.");
    			return;
    			//break;
    	}
    	
    }
    	
    public void atualizarCliente() {
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.print("\nPara atualizar o cliente, forneça o CPF deste."
    				+ "\nDeseja fazer uma busca antes de digitar o CPF?, (S/N)? : ");
    		
    		/* OBS
    		 * 1: como cada pessoa possui apenas um cpf, supomos que não há dois ou mais
    		clientes com o mesmo cpf no banco
    		
    		*/
    		
    		char opc = scanner.findInLine(".").charAt(0);
    		opc = Character.toUpperCase(opc);
    		scanner.nextLine();
    		
    		if (opc == 'S') {
	    			//query nome: pois o cpf não é conhecido.
    			
	    			BasicDBObject clientebusca = new BasicDBObject();
	    	    	BasicDBList lista;
		    		String name;
		    		
		    		System.out.println("Digite um NOME para buscar: ");
		        	name = scanner.nextLine();
		        	
		        	
		        	clientebusca.put("name", name);
		        	
		        	lista = crudClientes.read(clientebusca);
		        	
		        	if (lista.size() == 0) {
						System.out.println("Não foram encontrados clientes com esse nome." + "\nTente novamente.");
						return;

					} else {
						System.out.println("Foram encontrados: ");

						for (Object line : lista) {

							System.out.println(line);
						}
					}
		    		}
    		
    		System.out.print("\nDigite o CPF do cliente que deseja atualizar: ");
    		
    		String atualizarcpf = scanner.nextLine();
    		
    		BasicDBObject query = new BasicDBObject();
    		query.put("cpf", atualizarcpf);
    		
    		//achou o documento a ser atualizado
    		DBObject queriedDoc = clientes.findOne(query);

    		
    		System.out.println("Cliente " + queriedDoc.get("name").toString() + " selecionado.");
    		
        	System.out.print("\nDeseja atualizar qual campo? \n"
        			+ "[1] - Nome \n"
        			+ "[2] - CPF\n"
        			+ "\nOpção: ");
        	
        	int opt = scanner.nextInt();
        	scanner.nextLine();
        	
        	
			switch(opt) {
        	
        	case 1:
        			//update nome
	        		String novoNome;
	        		
	        		System.out.print("\nDigite o novo CPF: ");
	        		novoNome = scanner.nextLine();
	        		
	        		crudClientes.update(query, "nome", novoNome);
		        	
    	    		
    	        	break;	        	
        	case 2:
        			//update cpf
        			String novoCpf;
        		
	        		System.out.print("\nDigite o novo CPF: ");
	        		novoCpf = scanner.nextLine();
		        	
	        		crudClientes.update(query, "cpf", novoCpf);
    	        	
    	    		break;
        	default:
        		
        			System.out.println("Opção inválida. Tente novamente.");
        			return;
        			//break;*/
    		
    	}
    	}

    public void removerCliente() {
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.print("\nPara remover o cliente, forneça o CPF deste."
    				+ "\nDeseja fazer uma busca antes de digitar o CPF?, (S/N)? : ");
    		
    		/* OBS
    		 * 1: como cada pessoa possui apenas um cpf, supomos que não há dois ou mais
    		clientes com o mesmo cpf no banco
    		
    		*/
    		
    		char opc = scanner.findInLine(".").charAt(0);
    		opc = Character.toUpperCase(opc);
    		scanner.nextLine();
    		
    		if (opc == 'S') {
	    			//query nome: pois o cpf não é conhecido.
    			
	    			BasicDBObject clientebusca = new BasicDBObject();
	    	    	BasicDBList lista;
		    		String name;
		    		
		    		System.out.println("Digite um NOME para buscar: ");
		        	name = scanner.nextLine();
		        	
		        	
		        	clientebusca.put("name", name);
		        	
		        	lista = crudClientes.read(clientebusca);
		        	
			if (lista.size() == 0) {
				System.out.println("Não foram encontrados clientes com esse nome." + "\nTente novamente.");
				return;

			} else {
				System.out.println("Foram encontrados: ");

				for (Object line : lista) {

					System.out.println(line);
				}
			}
    		}
    		
    		System.out.print("\nDigite o CPF do cliente que deseja remover: ");
    		
    		String removecpf = scanner.nextLine();
    		
    		BasicDBObject query = new BasicDBObject();
    		query.put("cpf", removecpf);
    		
    		//achou o documento a ser atualizado
    		DBObject queriedDoc = clientes.findOne(query);
    		
    		
    		System.out.println("Cliente " + queriedDoc.get("name").toString() + " foi removido.");
    		
    		crudClientes.delete(query);

    	}
    
    
    // Funções do crud Produto:
    
    public void cadastrarProduto() {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	
    	System.out.println("Digite o Produto: ");
    	String produto = scanner.nextLine();
    	System.out.println("Digite o Valor: ");
    	Double valor = scanner.nextDouble();
	    scanner.nextLine();
    	
    	BasicDBObject produtoDoc = new BasicDBObject();
    	produtoDoc.put("produto", produto);
    	produtoDoc.put("valor", valor);
    	System.out.println("Produto Cadastrado!");
    	
    	crudProdutos.create(produtoDoc);
    	
    	
    }
    
    public void listarProduto() {
    	
    	BasicDBList lista = crudProdutos.read();
    	
    	if (lista.size() == 0) {
			System.out.println("Não foram encontrados produtos." + "\nTente novamente.");
			return;

		} else {
			System.out.println("Foram encontrados: ");

			for (Object line : lista) {

				System.out.println(line);
			}
		}
    	
    }
    
    public void buscarProduto() {
    	
    	BasicDBObject produtoBusca = new BasicDBObject();
    	BasicDBList lista;
    	
    	System.out.print("\nBuscar Produto por: \n"
    			+ "[1] - Produto (nome) \n"
    			+ "[2] - Valor\n"
    			+ "\nOpção: ");
    	
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	int opc = scanner.nextInt();
    	scanner.nextLine();
    	
    	switch(opc) {
    	
    	case 1:
    			//query produto
	    		String produto;
	    		
	    		System.out.print("\nDigite um Produto para buscar: ");
	        	produto = scanner.nextLine();
	        	
	        	
	        	produtoBusca.put("produto", produto);
	        	
	        	lista = crudProdutos.read(produtoBusca);
	        	
	        	if (lista.size() == 0) {
					System.out.println("Não foram encontrados produtos com esse nome." + "\nTente novamente.");
					return;

				} else {
					System.out.println("Foram encontrados: ");

					for (Object line : lista) {

						System.out.println(line);
					}
				}

	        	break;	        	
    	case 2:
    			//query valor
    		
	    		Double valor;
	    		
	    		System.out.print("\nDigite um Valor para buscar: ");
	        	valor = scanner.nextDouble();
	        	scanner.nextLine();
	        	
	        	produtoBusca.put("valor", valor);
	        	
	        	lista = crudProdutos.read(produtoBusca);
	        	
	        	if (lista.size() == 0) {
					System.out.println("Não foram encontrados produtos com esse nome." + "\nTente novamente.");
					return;

				} else {
					System.out.println("Foram encontrados: ");

					for (Object line : lista) {

						System.out.println(line);
					}
				}
	    	
	        	
	    		break;
    	default:
    		
    			System.out.println("Opção inválida. Tente novamente.");
    			return;
    			//break;
    	}
    	
    }
    	
    public void atualizarProduto() {
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		System.out.print("\nPara atualizar um produto, forneça o Produto (nome) deste:");
    		
    		
    		String atualizarproduto = scanner.nextLine();
    		
    		BasicDBObject query = new BasicDBObject();
    		query.put("produto", atualizarproduto);
    		
    		//achou o documento a ser atualizado
    		DBObject queriedDoc = produtos.findOne(query);

    		
    		System.out.println("Produto " + queriedDoc.get("produto").toString() + " selecionado.");
    		
        	System.out.print("\nDeseja atualizar qual campo? \n"
        			+ "[1] - Produto (nome) \n"
        			+ "[2] - Valor\n"
        			+ "\nOpção: ");
        	
        	int opt = scanner.nextInt();
        	scanner.nextLine();
        	
        	
			switch(opt) {
        	
        	case 1:
        			//update produto
	        		String novoProduto;
	        		
	        		System.out.print("\nDigite o novo Produto: ");
	        		novoProduto = scanner.nextLine();
	        		
	        		crudProdutos.update(query, "produto", novoProduto);
		        	
    	    		
    	        	break;	        	
        	case 2:
        			//update valor
        			Double novoValor;
        		
	        		System.out.print("\nDigite o novo Valor: ");
	        		novoValor = scanner.nextDouble();
	        		scanner.nextLine();

	        		crudProdutos.update(query, "valor", novoValor);
    	        	
    	    		break;
        	default:
        		
        			System.out.println("Opção inválida. Tente novamente.");
        			return;
        			//break;*/
    		
    	
    		
    		
    		
    	}
    	}

    public void removerProduto() {
    		
    		Scanner scanner = new Scanner(System.in);
    		
    		
    		System.out.print("\nDigite o nome do produto que deseja remover: ");
    		
    		String removerproduto = scanner.nextLine();
    		
    		BasicDBObject query = new BasicDBObject();
    		query.put("produto", removerproduto);
    		
    		//achou o documento a ser atualizado
    		DBObject queriedDoc = produtos.findOne(query);
    		
    		
    		System.out.println("Produto " + queriedDoc.get("produto").toString() + " foi removido.");
    		
    		crudProdutos.delete(query);

    	}

}
