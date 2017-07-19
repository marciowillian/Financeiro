package br.com.marciowillian.financeiro.model;

import javax.persistence.Persistence;

public class CriarTabelas {
	
	//private static EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("FinanceiroPU");
		System.out.println("ola, mundo");
		

	}

}
