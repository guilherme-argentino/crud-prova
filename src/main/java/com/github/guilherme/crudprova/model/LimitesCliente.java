package com.github.guilherme.crudprova.model;

import org.hibernate.validator.constraints.NotEmpty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entidade que armazena o limite de credito de um cliente
 * 
 * @author Guilherme
 * @see {@link RiscoCliente}
 */
@Entity
@Table(name = "LIMITES")
public class LimitesCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4981693434126731051L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name = "NOME", nullable = false)
	private String name;

	@Column(name = "LIMITE", nullable = false)
	private double limite;

	@Enumerated(EnumType.STRING)
	@Column(name = "RISCO", nullable = false)
	private RiscoCliente risco;

	@Transient
	private String juros;

	@Transient
	private double totalAPagar;

	@PostLoad
	private void postLoad() {
		switch (risco) {
		case C:
			this.juros = "20%";
			this.totalAPagar = limite * 1.2D;
			break;
		case B:
			this.juros = "10%";
			this.totalAPagar = limite * 1.1D;
			break;
		case A:
		default:
			this.totalAPagar = limite;
			this.juros = "0%";
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	/**
	 * @return the risco
	 */
	public RiscoCliente getRisco() {
		return risco;
	}

	/**
	 * @param risco the risco to set
	 */
	public void setRisco(RiscoCliente risco) {
		this.risco = risco;
	}

	public String getJuros() {
		return juros;
	}

	public void setJuros(String juros) {
		this.juros = juros;
	}

	public double getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		LimitesCliente user = (LimitesCliente) o;

		return Objects.equal(this.id, user.id) && Objects.equal(this.name, user.name)
				&& Objects.equal(this.limite, user.limite) && Objects.equal(this.risco, user.risco);

	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.name, this.limite, this.risco);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).add("id", id).add("name", name).add("limite", limite)
				.add("risco", risco).add("juros", juros).add("totalAPagar", totalAPagar).toString();
	}

}
