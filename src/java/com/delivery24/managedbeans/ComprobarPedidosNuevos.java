/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Pedido;
import com.delivery24.facade.PedidoFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.context.RequestContext;

/**
 *
 * @author geovanny
 */
@Named(value = "comprobarPedidosNuevos")
@SessionScoped
public class ComprobarPedidosNuevos implements Serializable {

    @EJB
    private PedidoFacade ejbPedidoFacade;
    private final  List<Pedido> pedidosNotificados;
    /**
     * Creates a new instance of ComprobarPedidosNuevos
     */
    public ComprobarPedidosNuevos() 
    {
        pedidosNotificados = new ArrayList();
    }
    
    public void hayNuevosPedidos()
    {
        List<Pedido>pedidosnuevos = ejbPedidoFacade.findAllNewOrderByFecha();
        int banderaNuevos=0;
        for(Pedido p: pedidosnuevos)
        {
           int bandera=0;
           for(Pedido pn : this.pedidosNotificados)
           {
               if(pn.getId().equals(p.getId()))
               {
                   bandera =1;
               }
           }           
           if(bandera == 0)
           {
               banderaNuevos = 1;
               this.pedidosNotificados.add(p);
           }
        }

        if(banderaNuevos==0)
        {
            RequestContext context = RequestContext.getCurrentInstance();    
            context.addCallbackParam("respuesta", false);
        }
        else
        {
            RequestContext context = RequestContext.getCurrentInstance();    
            context.addCallbackParam("respuesta", true);
            context.execute("PF('nuevospedidos').show()");
        }
    }
    
    public void aceptarNuevosPedidos()
    {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('nuevospedidos').hide()"); 
        context.addCallbackParam("respuesta", true);      
        
    }
    
}
