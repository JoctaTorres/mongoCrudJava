package cruds;

import java.io.IOException;
import java.util.Scanner;

import java.util.logging.*;


public class telaVendas {

	public static void main(String[] args) {
		
		String path = "C:\\Users\\acer\\Documents\\DBSistemaDeVenda";
		
		try {
			
			Runtime.getRuntime().exec("mongod --dbpath " + path);
			
			// Desabilitando o logger do mongo:
			Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
			mongoLogger.setLevel(Level.SEVERE);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//Objeto da  classeSistemaDeVenda
		
		SistemaDeVenda sisvenda = new SistemaDeVenda("localhost", 27017, "DBSistemaDeVenda");
		carrinhoDeCompras carrinho = new carrinhoDeCompras("localhost", 27017, "DBSistemaDeVenda");
		
		Scanner scanner = new Scanner(System.in);
		int opt;
		
		do {
			
			System.out.println("TELA PRINCIPAL");
			
			System.out.println("(1) Área do cliente"); // CRUD Cliente
	        System.out.println("(2) Área do produto"); // CRUD Produto
	        System.out.println("(3) Venda");
	        System.out.println("(0) Sair");
	        System.out.print("Opção: ");
	        
	        opt = scanner.nextInt();
	    	scanner.nextLine();
	    	
	    	
			switch(opt) {
			
			case 1:
				imprimirMenu(opt);
				opt = scanner.nextInt();
		    	scanner.nextLine();
		    	
		    	switch(opt) {
		    	case 1: sisvenda.cadastrarCliente(); break;
		    	case 2: sisvenda.buscarCliente(); break;
		    	case 3: sisvenda.listarClientes(); break;
		    	case 4: sisvenda.atualizarCliente(); break;
		    	case 5: sisvenda.removerCliente(); break;
		    	case 6: break;
		    	default:  System.out.println("Comando Inválido"); break;
		    	}
				
				break;
			case 2:
				imprimirMenu(opt);
				
				opt = scanner.nextInt();
		    	scanner.nextLine();
		    	
		    	switch(opt) {
		    	case 1: sisvenda.cadastrarProduto(); break;
		    	case 2: sisvenda.buscarProduto(); break;
		    	case 3: sisvenda.listarProduto(); break;
		    	case 4: sisvenda.atualizarProduto(); break;
		    	case 5: sisvenda.removerProduto(); break;
		    	case 6: break;
		    	default:  System.out.println("Comando Inválido"); break;
		    	}
		    	
				break;
			case 3:
				imprimirMenu(opt);
				
				opt = scanner.nextInt();
		    	scanner.nextLine();
		    	
		    	switch(opt) {
		    	case 1: carrinho.adicionarItem(); break;
		    	case 2: carrinho.verCarrinho(); break;
		    	case 3: carrinho.finalizarCompra(); break;
		    	case 4: break;
		    	default:  System.out.println("Comando Inválido"); break;
		    	}
				
				break;
			
			}
			
		} while (opt != 0);
		

	}
	
	private static void imprimirMenu(int opt) {
		
		switch(opt) {
		
		case 1:
			
			System.out.println("Área do cliente");
			
			System.out.println("(1) Cadastrar Cliente");
	        System.out.println("(2) Buscar Cliente");
	        System.out.println("(3) Listar Clientes");
	        System.out.println("(4) Atualizar Cliente");
	        System.out.println("(5) Remover Cliente");
	        System.out.println("(6) Voltar");
	        
	        System.out.print("Opção: ");
	        
			break;
		case 2:
			System.out.println("Área do produto");
			
			System.out.println("(1) Cadastrar Produto"); 
	        System.out.println("(2) Buscar Produto"); 
	        System.out.println("(3) Listar Produtos");
	        System.out.println("(4) Atualizar Produto");
	        System.out.println("(5) Remover Produto");
	        System.out.println("(6) Voltar");
	        
	        System.out.print("Opção: ");
			
			break;
		case 3:
			System.out.println("Área de Vendas");
			
			System.out.println("(1) Adicionar Produto ao carrinho");
			System.out.println("(2) Visualizar carrinho");
	        System.out.println("(3) Finalizar Compra");
	        System.out.println("(4) Voltar");
	        
	        System.out.print("Opção: ");
			break;
		default:
			System.out.println("Comando Inválido");
			break;
		
		}
	}

}
