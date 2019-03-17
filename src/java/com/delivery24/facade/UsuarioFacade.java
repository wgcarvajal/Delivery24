/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.facade;

import com.delivery24.entities.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "delivery24PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public boolean findUsuEmai(String email) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuemail");
        query.setParameter("usuemail", email);
        List<Usuario> resultList = query.getResultList();
        if(resultList.size()>0)
        {
            return true;
        }
        return false;
    }
    
    public List<Usuario> findUsuIdFacebook(String id) {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuidfacebook");
        query.setParameter("usuidfacebook", id);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Usuario>findEmailPassword(String email, String password)
    {
        Query query = getEntityManager().createNamedQuery("Usuario.findByUsuemailPassword");
        query.setParameter("usuemail", email);
        query.setParameter("usupassword", password);
        List<Usuario> resultList = query.getResultList();
        return resultList;
    }
    
}
