package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.IProductoRepo;
import com.example.demo.repository.modelo.Producto;
import com.example.demo.repository.modelo.dto.ProductoDTO;
import com.example.demo.service.to.ProductoDTO_TO;
import com.example.demo.service.to.ProductoTO;

@Service
public class ProductoServiceImpl implements IProductoService {

	private final ModelMapper mapper = new ModelMapper();

	public Producto convertirAProducto(ProductoTO productoTO) {
		return this.mapper.map(productoTO, Producto.class);
	}

	public ProductoDTO_TO convertirADTO_TO(ProductoDTO productoDTO) {
		return this.mapper.map(productoDTO, ProductoDTO_TO.class);
	}

	public ProductoTO convertirATO(Producto producto) {
		return this.mapper.map(producto, ProductoTO.class);
	}

	@Autowired
	private IProductoRepo iProductoRepo;

	@Override
	public int guardar(ProductoTO productoTO) {
		return this.iProductoRepo.guardar(this.convertirAProducto(productoTO));
	}

	@Override
	public List<ProductoDTO_TO> consultarTodoDTO() {
		List<ProductoDTO> pdto = this.iProductoRepo.consultarTodoDTO();
		List<ProductoDTO_TO> lsdto_to = new ArrayList<>();
		for (ProductoDTO productoDTO : pdto) {
			lsdto_to.add(this.convertirADTO_TO(productoDTO));
		}
		return lsdto_to;
	}

	@Override
	public ProductoTO consultarPorCodigo(String codigoBarras) {
		return this.convertirATO(this.iProductoRepo.consultarPorCodigo(codigoBarras));
	}

	@Override
	public int actualizarParcial(ProductoTO productoTO) {
		Producto p = this.iProductoRepo.consultarPorCodigo(productoTO.getCodigoBarras());
		p.setNombre(productoTO.getNombre());
		p.setStock(productoTO.getStock());
		p.setFechaCaducidad(productoTO.getFechaCaducidad());
		return this.iProductoRepo.actualizarParcial(p);
	}

	@Override
	public int eliminar(String codigoBarras) {
		return this.iProductoRepo.eliminar(codigoBarras);
	}

}
