/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.facade;

import com.delivery24.entities.Pedido;
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
public class PedidoFacade extends AbstractFacade<Pedido> {

    @PersistenceContext(unitName = "delivery24PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    public List<Pedido> findAllNewOrderByFecha() {
        Query query = getEntityManager().createNamedQuery("Pedido.findAllNewOrderByFecha");        
        List<Pedido> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Pedido> findAllConfirmadosOrderByFecha() {
        Query query = getEntityManager().createNamedQuery("Pedido.findAllConfirmadosOrderByFecha");        
        List<Pedido> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Pedido> findAllDespachadosOrderByFecha() {
        Query query = getEntityManager().createNamedQuery("Pedido.findAllDespachadosOrderByFecha");        
        List<Pedido> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Pedido> findAllCanceladosOrderByFecha() {
        Query query = getEntityManager().createNamedQuery("Pedido.findAllCanceladosOrderByFecha");        
        List<Pedido> resultList = query.getResultList();
        return resultList;
    }
    
    
    
}
