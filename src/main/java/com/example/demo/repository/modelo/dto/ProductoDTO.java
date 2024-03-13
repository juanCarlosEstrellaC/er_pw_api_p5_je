package com.example.demo.repository.modelo.dto;

public class ProductoDTO {

	private String codigoBarras;

	private String nombre;

	public ProductoDTO() {
		super();
	}

	public ProductoDTO(String codigoBarras, String nombre) {
		super();
		this.codigoBarras = codigoBarras;
		this.nombre = nombre;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
