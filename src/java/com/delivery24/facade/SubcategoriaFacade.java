/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.facade;

import com.delivery24.entities.Subcategoria;
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
public class SubcategoriaFacade extends AbstractFacade<Subcategoria> {

    @PersistenceContext(unitName = "delivery24PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SubcategoriaFacade() {
        super(Subcategoria.class);
    }
    
    public List<Subcategoria> findAllSubcategoriaOrderByPosicion() {
        Query query = getEntityManager().createNamedQuery("Subcategoria.findAllOrderByPosicion"); 
        query.setHint("eclipselink.refresh", true);
        List<Subcategoria> resultList = query.getResultList();
        return resultList;
    }
    
    public List<Subcategoria> findAllOderByNombre(){
        Query query = getEntityManager().createNamedQuery("Subcategoria.findAllOderByNombre");
        query.setHint("eclipselink.refresh", true);
        List<Subcategoria> resuList = query.getResultList();
        return resuList;
    }
    
     public boolean existCategoria(String subcatnombre)
    {
        Query query = getEntityManager().createNamedQuery("Subcategoria.findBySubcatnombre");
        query.setParameter("subcatnombre",  subcatnombre);
        query.setHint("eclipselink.refresh", true);
        List<Subcategoria> resultList = query.getResultList();
        return resultList!=null && resultList.size()>0;
    }
     
    public List<Subcategoria> findByColumn(String subcatnombre)
    {
        Query query = getEntityManager().createNamedQuery("Subcategoria.findByColumn");
        query.setParameter("subcatnombre", "%" + subcatnombre + "%");
        List<Subcategoria> resultList = query.getResultList();
        return resultList;
    }
    
    public boolean isCategoryUsed(int idsubcategoria)
    {
        Query query = getEntityManager().createNamedQuery("Producto.findBySubCatId");
        query.setParameter("idsubcategoria",  idsubcategoria);
        List<Subcategoria> resultList = query.getResultList();
        return resultList!=null && resultList.size()>0;
    }
     
}
