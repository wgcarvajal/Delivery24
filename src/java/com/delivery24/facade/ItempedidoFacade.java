/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.facade;

import com.delivery24.entities.Itempedido;
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
public class ItempedidoFacade extends AbstractFacade<Itempedido> {

    @PersistenceContext(unitName = "delivery24PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItempedidoFacade() {
        super(Itempedido.class);
    }
    
     public List<Itempedido> findByPedidoId(Integer idpedido) {
        Query query = getEntityManager().createNamedQuery("Itempedido.findByPedidoId"); 
        query.setParameter("idpedido", idpedido);
        List<Itempedido> resultList = query.getResultList();
        return resultList;
    }
}
