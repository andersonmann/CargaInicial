package br.com.sisnema.cargainicial.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@Entity
public class Categoria  {
	
	@Id
	@GeneratedValue
	private Integer codigo;
	
	@ManyToOne
	@JoinColumn(name="categoria_pai", nullable=true)
	private Categoria pai;
	
	@ManyToOne
	@JoinColumn(name = "usuario", nullable=false)
	private Usuario usuario;
	
	private String descricao;

	private int fator;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "categoria_pai", updatable = false)
	@OrderBy(value = "descricao asc")
	private List<Categoria> filhos;


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
	
	public Categoria() {}

	public Categoria(Categoria pai, Usuario usuario, String descricao, int fator) {
		super();
		this.pai = pai;
		this.usuario = usuario;
		this.descricao = descricao;
		this.fator = fator;
	}

	public Categoria(Integer codigo) {
		super();
		this.codigo = codigo;
	}

	public Categoria(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Categoria getPai() {
		return pai;
	}

	public void setPai(Categoria pai) {
		this.pai = pai;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getFator() {
		return fator;
	}

	public void setFator(int fator) {
		this.fator = fator;
	}

	public List<Categoria> getFilhos() {
		return filhos;
	}

	public void setFilhos(List<Categoria> filhos) {
		this.filhos = filhos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
		if (codigo == null) {
			if (other.codigo != null) {
				return false;
			}
		} else if (!codigo.equals(other.codigo)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Categoria [codigo=" + codigo + ", pai=" + pai + ", usuario="
				+ usuario + ", descricao=" + descricao + ", fator=" + fator
				+ "]";
	}

}
