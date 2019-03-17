/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.facade;

import com.delivery24.entities.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author geovanny
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "delivery24PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }
    
    public List<Producto> findAllProductoOrderByPosicion() {
        Query query = getEntityManager().createNamedQuery("Producto.findAllOrderByPosicion");        
        List<Producto> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Producto> findAllProductoOrderBySubCategoria() {
        Query query = getEntityManager().createNamedQuery("Producto.findAllOrderBySubCategoria");        
        List<Producto> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Producto> busacarPorNombreProducto(String nombre) {
        Query query = getEntityManager().createNamedQuery("Producto.findByNameProducto");
        query.setParameter("nombre", "%" + nombre + "%");
        List<Producto> resultList = query.getResultList();
        return resultList;
    }
    
}
