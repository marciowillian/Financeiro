package br.com.marciowillian.financeiro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory factory;
	static {
		factory = Persistence.createEntityManagerFactory("FinanceiroPU");
	}
	public static EntityManager geEntityManager() {
		return factory.createEntityManager();
	}
}
