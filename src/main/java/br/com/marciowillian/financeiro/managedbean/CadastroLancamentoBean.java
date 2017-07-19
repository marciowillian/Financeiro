package br.com.marciowillian.financeiro.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.marciowillian.financeiro.model.Lancamento;
import br.com.marciowillian.financeiro.model.Pessoa;
import br.com.marciowillian.financeiro.model.TipoLancamento;
import br.com.marciowillian.financeiro.repository.Lancamentos;
import br.com.marciowillian.financeiro.repository.Pessoas;
import br.com.marciowillian.financeiro.service.CadastroLancamentos;
import br.com.marciowillian.financeiro.service.NegocioException;
import br.com.marciowillian.financeiro.util.JpaUtil;

public class CadastroLancamentoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	public void prepararCadastro() {
		EntityManager manager = JpaUtil.geEntityManager();
		try {
			Pessoas pessoas = new Pessoas(manager);
			this.todasPessoas = pessoas.todas();
		} finally {
			manager.close();
		}
	}
	
	public void salvar() {
		EntityManager manager = JpaUtil.geEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			trx.begin();
			CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(manager));
			cadastro.salvar(this.lancamento);
			
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso"));
			trx.commit();
		} catch (NegocioException e) {
			trx.rollback();
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}finally {
			manager.close();
		}
	}
	
	public List<Pessoa> getTodasPessoas(){
		return this.todasPessoas;
	}
	
	public TipoLancamento[] getTipoLancamentos(){
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
}


