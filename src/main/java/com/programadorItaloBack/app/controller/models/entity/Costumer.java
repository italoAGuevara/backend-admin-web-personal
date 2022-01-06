package com.programadorItaloBack.app.controller.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

@Entity
@Table(name="costumers")
public class Costumer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@Column(unique = true, nullable = false)
	private long nit;

	@NonNull
	@NotEmpty
	@Column(name="tipo_personeria")
	private String tipoPersoneria;

	@NonNull
	@NotEmpty
	@Size(min = 6, max = 50)
	@Column(nullable = false)
	private String name;

	@NonNull
	@NotEmpty
	@Size(min = 6, max = 50)
	@Column(nullable = false)
	private String phone1;

	private String phone2;

	@NonNull
	@NotEmpty
	@Size(min = 6, max = 60)
	@Column(nullable = false)
	private String adrress1;

	private String adrress2;

	private String email;

	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@OneToMany(mappedBy="costumer" ,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Factura> facturas;

	public Costumer() {
		facturas = new ArrayList<Factura>();
	}

	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNit() {
		return nit;
	}

	public void setNit(long nit) {
		this.nit = nit;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getAdrress1() {
		return adrress1;
	}

	public void setAdrress1(String adrress1) {
		this.adrress1 = adrress1;
	}

	public String getAdrress2() {
		return adrress2;
	}

	public void setAdrress2(String adrress2) {
		this.adrress2 = adrress2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getTipoPersoneria() {
		return tipoPersoneria;
	}

	public void setTipoPersoneria(String tipoPersoneria) {
		this.tipoPersoneria = tipoPersoneria;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	
	public void addFactura(Factura factura) {
		facturas.add(factura);
	}



	private static final long serialVersionUID = 1L;

}
