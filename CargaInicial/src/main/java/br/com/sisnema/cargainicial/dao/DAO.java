package br.com.sisnema.cargainicial.dao;

import org.hibernate.Session;

import br.com.sisnema.cargainicial.model.Categoria;
import br.com.sisnema.cargainicial.model.Conta;
import br.com.sisnema.cargainicial.model.Usuario;
import br.com.sisnema.cargainicial.util.JPAUtil;

public class DAO {
	private Session session;

	public DAO() {
		session = JPAUtil.getEntityManager().unwrap(Session.class);
	}

	public void commit() {
		session.getTransaction().commit();
	}

	public void begin() {
		session.getTransaction().begin();
	}

	public void finalizaTransacao() {
		session.close();
	}

	public void salvar(Usuario usuario) {
		this.session.save(usuario);
	}

	public void salvar(Conta c) {
		this.session.save(c);
	}

	public Categoria salvar(Categoria categoria) {
		Categoria merged = (Categoria) this.session.merge(categoria);
		this.session.flush();
		this.session.clear();
		return merged;
	}

}
