package br.com.sisnema.cargainicial.view;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.sisnema.cargainicial.dao.DAO;
import br.com.sisnema.cargainicial.model.Categoria;
import br.com.sisnema.cargainicial.model.Conta;
import br.com.sisnema.cargainicial.model.Usuario;



public class CargaInicialView {

	// class variable
	private static final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

	private static final java.util.Random rand = new java.util.Random();

	// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
	private static final Set<String> identifiers = new HashSet<String>();
	

	public static void main(String[] args) {
		simpleInitialize();
//		fullInitialize();
		
		System.exit(0);
	}


	private static void fullInitialize() {
		DAO dao = new DAO();
		dao.begin();
		for(int i=0; i< 100; i++){
			criarUsuario(dao, i);
		}
		dao.commit();
		dao.finalizaTransacao();
	}
	
	
	private static void simpleInitialize() {
		DAO dao = new DAO();

		criarUsuarioAluno(dao);
		criarUsuarioAdm(dao);
		dao.finalizaTransacao();
	}

	private static void criarUsuarioAdm(DAO dao) {
		Usuario adm = new Usuario();
		adm.setAtivo(true);
		adm.setCelular("123456");
		adm.setEmail("aluno@sisnema.com.br");
		adm.setIdioma("pt_BR");
		adm.setLogin("admin");
		adm.setNascimento(new Date());
		adm.setNome("Usuario Administrador");
		adm.setSenha("123");

		adm.getPermissao().add("ROLE_USUARIO");
		adm.getPermissao().add("ROLE_ADMINISTRADOR");
		
		Conta c = new Conta();
		c.setDescricao("Conta BB do Administrador");
		c.setFavorita(true);
		c.setSaldoInicial(100f);
		c.setUsuario(adm);
		
		dao.begin();
		dao.salvar(adm);
		dao.salvar(c);
		criaCategorias(adm, dao);
		dao.commit();
	}

	private static void criarUsuarioAluno(DAO dao) {
		Usuario aluno = new Usuario();

		aluno.setAtivo(true);
		aluno.setCelular("123456");
		aluno.setEmail("aluno@sisnema.com.br");
		aluno.setIdioma("pt_BR");
		aluno.setLogin("aluno");
		aluno.setNascimento(new Date());
		aluno.setNome("Usuario Aluno da Sisnema");
		aluno.setSenha("123");
		
		aluno.getPermissao().add("ROLE_USUARIO");

		Conta c = new Conta();
		c.setDescricao("Conta BB do Aluno");
		c.setFavorita(true);
		c.setSaldoInicial(100f);
		c.setUsuario(aluno);
		
		dao.begin();
		dao.salvar(aluno);
		dao.salvar(c);
		criaCategorias(aluno, dao);
		dao.commit();
	}
	
	private static void criarUsuario(DAO dao, int i) {
		Usuario usuario = new Usuario();

		String randomIdentifier = randomIdentifier();
		
		usuario.setAtivo(true);
		usuario.setCelular("123456");
		usuario.setEmail(randomIdentifier+"@sisnema.com.br");
		
		String idioma = "pt_BR";
		if(i%3==0){
			idioma = "es_ES";
		} else if(i%2==0){
			idioma = "en_US";
		}
		
		usuario.setIdioma(idioma);
		usuario.setLogin(randomIdentifier);
		usuario.setNascimento(new Date());
		usuario.setNome(randomIdentifier+ " "+ randomIdentifier);
		usuario.setSenha("123");
		
		usuario.getPermissao().add("ROLE_USUARIO");
		
		Conta c = new Conta();
		c.setDescricao("Conta BB");
		c.setFavorita(true);
		c.setSaldoInicial(100f);
		c.setUsuario(usuario);
		
		dao.salvar(usuario);
		dao.salvar(c);
		criaCategorias(usuario, dao);
	}
	
	private static void criaCategorias(Usuario usuario, DAO dao) {

		Categoria despesas = new Categoria(null, usuario, "DESPESAS", -1);
		despesas = dao.salvar(despesas);
		dao.salvar(new Categoria(despesas, usuario, "Moradia", -1));
		dao.salvar(new Categoria(despesas, usuario, "Alimenta��o", -1));
		dao.salvar(new Categoria(despesas, usuario, "Vestu�rio", -1));
		dao.salvar(new Categoria(despesas, usuario, "Deslocamento", -1));
		dao.salvar(new Categoria(despesas, usuario, "Cuidados Pessoais", -1));
		dao.salvar(new Categoria(despesas, usuario, "Educa��o", -1));
		dao.salvar(new Categoria(despesas, usuario, "Sa�de", -1));
		dao.salvar(new Categoria(despesas, usuario, "Lazer", -1));
		dao.salvar(new Categoria(despesas, usuario, "Despesas Financeiras", -1));

		Categoria receitas = new Categoria(null, usuario, "RECEITAS", 1);
		receitas = dao.salvar(receitas);
		dao.salvar(new Categoria(receitas, usuario, "Sal�rio", 1));
		dao.salvar(new Categoria(receitas, usuario, "Restitui��es", 1));
		dao.salvar(new Categoria(receitas, usuario, "Rendimento", 1));
	}


	private  static String randomIdentifier() {
	    StringBuilder builder = new StringBuilder();
	    while(builder.toString().length() == 0) {
	        int length = rand.nextInt(5)+5;
	        for(int i = 0; i < length; i++)
	            builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
	        if(identifiers.contains(builder.toString())) 
	            builder = new StringBuilder();
	    }
	    return builder.toString();
	}
}
