package com.delivery24.managedbeans;

import com.delivery24.entities.Administrador;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

@Named("administradorController")
@SessionScoped
public class AdministradorController implements Serializable {

    private Administrador current;
    
    @EJB
    private com.delivery24.facade.AdministradorFacade ejbFacade;

    public AdministradorController() {
    }

   

}
