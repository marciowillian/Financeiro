package br.com.marciowillian.financeiro.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

import br.com.marciowillian.financeiro.model.*;
import br.com.marciowillian.financeiro.repository.Lancamentos;
import br.com.marciowillian.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentosBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		EntityManager manager = JpaUtil.geEntityManager();
		Lancamentos lancamentos = new Lancamentos(manager);
		this.lancamentos = lancamentos.todos();
		
		/*TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
		this.lancamentos = query.getResultList();*/
		
		manager.close();
	}
	
	public List<Lancamento> getLancamentos(){
		return lancamentos;
	}
}
