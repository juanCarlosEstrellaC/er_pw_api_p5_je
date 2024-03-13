package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.IProductoService;
import com.example.demo.service.to.ProductoDTO_TO;
import com.example.demo.service.to.ProductoTO;

@CrossOrigin
@RestController
@RequestMapping(path = "/productos")
public class ProductoControllerRestful {

	@Autowired
	private IProductoService iProductoService;

	// GUARDAR
	// http://localhost:8082/API/v1.0/Inventario/productos POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> guardar(@RequestBody ProductoTO productoTO) {
		var r = this.iProductoService.guardar(productoTO);
		return ResponseEntity.status(HttpStatus.OK).body(r);
	}

	// CONSULTAR TODOS DTO
	// http://localhost:8082/API/v1.0/Inventario/productos GET
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductoDTO_TO>> consultarTodosDTO() {
		var ls = this.iProductoService.consultarTodoDTO();
		for (ProductoDTO_TO prod : ls) {
			Link link = linkTo(
					methodOn(ProductoControllerRestful.class).consultarPorCodigoBarras(prod.getCodigoBarras()))
					.withRel("enlaces");
			prod.add(link);
		}
		return ResponseEntity.status(HttpStatus.OK).body(ls);
	}

	// CONSULTAR POR CODIGO DE BARRAS
	// http://localhost:8082/API/v1.0/Inventario/productos/{codigoBarras} GET
	@GetMapping(path = "/{codigoBarras}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductoTO> consultarPorCodigoBarras(@PathVariable String codigoBarras) {
		var p = this.iProductoService.consultarPorCodigo(codigoBarras);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}

	// ACTUALIZAR PARCIAL
	// http://localhost:8082/API/v1.0/Inventario/productos/{codigoBarras} PATCH
	@PatchMapping(path = "/{codigoBarras}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> actualizarParcial(@PathVariable String codigoBarras,
			@RequestBody ProductoTO productoTO) {
		productoTO.setCodigoBarras(codigoBarras);
		var p = this.iProductoService.actualizarParcial(productoTO);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}

	// ELIMINAR
	// http://localhost:8082/API/v1.0/Inventario/productos/{codigoBarras} DELETE
	@DeleteMapping(path = "/{codigoBarras}")
	public ResponseEntity<Integer> eliminar(@PathVariable String codigoBarras) {
		var p = this.iProductoService.eliminar(codigoBarras);
		return ResponseEntity.status(HttpStatus.OK).body(p);
	}

}
