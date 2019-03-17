/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.facade;

import com.delivery24.entities.Formapago;
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
public class FormapagoFacade extends AbstractFacade<Formapago> {

    @PersistenceContext(unitName = "delivery24PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormapagoFacade() {
        super(Formapago.class);
    }
    
    public List<Formapago>findAllActive()
    {
        Query query = getEntityManager().createNamedQuery("Formapago.findAllActive");        
        List<Formapago> resultList = query.getResultList();
        return resultList;
    }
    
}
