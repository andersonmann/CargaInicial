package br.com.sisnema.cargainicial.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.NaturalId;

@Entity
public class Usuario {

	private static final long serialVersionUID = 1362689342593873275L;

	public static final String ROLE_USUARIO = "ROLE_USUARIO";
	public static final String ROLE_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
	public static final String ROLE_VIP = "ROLE_VIP";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "cod_usuario")
	private Integer codigo;

	private String nome;

	@NaturalId
	@Column(updatable = false)
	private String login;
	private String email;
	private String senha;
	private String celular;
	private String idioma;
	private Date nascimento;
	private boolean ativo;

	@ElementCollection(targetClass = String.class)
	@JoinTable(name = "usuario_permissao", uniqueConstraints = { @UniqueConstraint(columnNames = {
			"usuario", "permissao" }) }, joinColumns = @JoinColumn(name = "usuario"))
	@Column(name = "permissao", nullable = false, length = 50)
	private Set<String> permissao = new HashSet<String>();

	private byte[] photo;

	@Version
	protected int version;
	
	@Column(updatable=false)
	protected Date dtCreated;
	
	protected Date dtUpdated;
	
	/**
	 * Invocado sempre da cria��o do registro
	 */
	@PrePersist
	void onPersist(){
		dtCreated = new Date();
		dtUpdated = new Date();
	}
	
	/**
	 * Invocado sempre da altera��o do registro
	 */
	@PreUpdate
	void onUpdate() {
		dtUpdated = new Date();
	}
	
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 16777215)
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Set<String> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<String> permissao) {
		this.permissao = permissao;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getDtCreated() {
		return dtCreated;
	}

	public void setDtCreated(Date dtCreated) {
		this.dtCreated = dtCreated;
	}

	public Date getDtUpdated() {
		return dtUpdated;
	}

	public void setDtUpdated(Date dtUpdated) {
		this.dtUpdated = dtUpdated;
	}

}
