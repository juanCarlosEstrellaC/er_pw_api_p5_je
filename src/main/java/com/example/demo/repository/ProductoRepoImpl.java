package com.example.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.repository.modelo.Producto;
import com.example.demo.repository.modelo.dto.ProductoDTO;

@Repository
@Transactional
public class ProductoRepoImpl implements IProductoRepo {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int guardar(Producto producto) {
		try {
			this.entityManager.persist(producto);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public List<ProductoDTO> consultarTodoDTO() {
		Query q = this.entityManager.createQuery(
				"SELECT NEW com.example.demo.repository.modelo.dto.ProductoDTO(p.codigoBarras, p.nombre) FROM Producto p");
		return q.getResultList();
	}

	@Override
	public Producto consultarPorCodigo(String codigoBarras) {
		Query q = this.entityManager.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras = :codigoBarras");
		q.setParameter("codigoBarras", codigoBarras);
		return (Producto) q.getSingleResult();
	}

	@Override
	public int actualizarParcial(Producto producto) {
		try {
			this.entityManager.merge(producto);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	@Override
	public int eliminar(String codigoBarras) {
		try {
			Producto producto = this.consultarPorCodigo(codigoBarras);
			this.entityManager.remove(producto);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

}
