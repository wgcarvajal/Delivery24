package com.delivery24.managedbeans;

import com.delivery24.entities.Pedido;

import com.delivery24.managedbeans.util.PaginationHelper;


import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
//import javax.enterprise.context.SessionScoped;


@Named("pedidoController")
@RequestScoped
public class PedidoController implements Serializable {

    private Pedido current;
    private List<Pedido> items = null;
    @EJB
    private com.delivery24.facade.PedidoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PedidoController() 
    {
    }

    public List<Pedido> getItems()
    {
        if(items == null)
        {
           items = ejbFacade.findAllNewOrderByFecha(); 
        }
        
        return items;
    }
}
