package com.example.demo.repository;

import java.util.List;

import com.example.demo.repository.modelo.Producto;
import com.example.demo.repository.modelo.dto.ProductoDTO;

public interface IProductoRepo {

	public int guardar(Producto producto);

	public List<ProductoDTO> consultarTodoDTO();

	public Producto consultarPorCodigo(String codigoBarras);

	public int actualizarParcial(Producto producto);

	public int eliminar(String codigoBarras);
}
