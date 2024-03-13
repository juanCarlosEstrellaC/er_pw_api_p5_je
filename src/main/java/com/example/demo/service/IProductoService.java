package com.example.demo.service;

import java.util.List;

import com.example.demo.service.to.ProductoDTO_TO;
import com.example.demo.service.to.ProductoTO;

public interface IProductoService {

	public int guardar(ProductoTO productoTO);

	public List<ProductoDTO_TO> consultarTodoDTO();

	public ProductoTO consultarPorCodigo(String codigoBarras);

	public int actualizarParcial(ProductoTO productoTO);

	public int eliminar(String codigoBarras);
}
