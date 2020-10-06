/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery24.managedbeans;

import com.delivery24.entities.Subcategoria;
import com.delivery24.facade.SubcategoriaFacade;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Wilson Carvajal
 */
@ManagedBean(name = "categoriaController")
@ViewScoped
public class CategoriaController implements Serializable {

    @EJB 
    private SubcategoriaFacade subCategoriaEJB;
    private String nombre;
    private List<Subcategoria> subCategorias;
    private String subCategoria;
    private Subcategoria subCategoriaSelected;

    public String getSubCategoria() {
        return subCategoria;
    }

    public void setSubCategoria(String subCategoria) {
        this.subCategoria = subCategoria;
    }
    
    public List<Subcategoria> getSubCategorias()
    {
        if(subCategorias==null)
        {
            subCategorias = subCategoriaEJB.findAllOderByNombre();
        }
        return subCategorias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    public void openRegisterCategoriaDialog() {
        this.nombre = "";
        PrimeFaces.current().executeScript("PF('addCategoryDialog').show()");
    }
    
    public void saveCategories()
    {
        if(!nombre.equals(""))
        {
         nombre = nombre.substring(0,1).toUpperCase() + nombre.substring(1).toLowerCase();
         if(!subCategoriaEJB.existCategoria(nombre))
         {
             Subcategoria s = new Subcategoria();
             s.setSubcatnombre(nombre);
             s.setSubcatipo(0);
             s.setSubcatposicion(0);
             subCategoriaEJB.create(s);
             subCategorias  = null;
             nombre = "";
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", ResourceBundle.getBundle("/Bundle").getString("CategorySavedSuccessfully")));
             PrimeFaces.current().ajax().update("categoryForm:categoryPanel");
         }
         else
           {
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ResourceBundle.getBundle("/Bundle").getString("CategorySavedError")));
              PrimeFaces.current().ajax().update("categoryForm:categoryPanel"); 
           }
        }
    }
    
    public void openEditarCategoryDialog(Subcategoria s)
    {         
       this.nombre = s.getSubcatnombre();
       this.subCategoriaSelected = s;
       PrimeFaces.current().executeScript("PF('editCategoryDialog').show()");
    }
    
    
    public void openDeleteCategoryDialog(Subcategoria s)
    {  
       this.subCategoriaSelected = s;
       FacesContext.getCurrentInstance().addMessage("deleteMessage", new FacesMessage(FacesMessage.SEVERITY_WARN, "", ResourceBundle.getBundle("/Bundle").getString("YouWantToDeleteTheCategory")+" "+s.getSubcatnombre()+"?"));
       PrimeFaces.current().executeScript("PF('deteletCategoryDialog').show()");
    }
    
    
    public void searchCategory()
    {
        subCategorias = subCategoriaEJB.findByColumn(subCategoria);
    }
    
    public void editCategory()
    {
        if(!nombre.equals(""))
        {
           nombre = nombre.substring(0,1).toUpperCase() + nombre.substring(1).toLowerCase();
           if(!nombre.equals(subCategoriaSelected.getSubcatnombre()))
           {
               if(!subCategoriaEJB.existCategoria(nombre))
                {
                    subCategoriaSelected.setSubcatnombre(nombre);
                    subCategoriaEJB.edit(subCategoriaSelected);
                    subCategorias = null;
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info:", ResourceBundle.getBundle("/Bundle").getString("CategoryEditSuccessfully")));
                    PrimeFaces.current().ajax().update("editCategoryForm:editCategoryPanel");
                }
                else
                {
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ResourceBundle.getBundle("/Bundle").getString("CategoryEditError")));
                   PrimeFaces.current().ajax().update("editCategoryForm:editCategoryPanel"); 
                }
           }
        }
    }
    
    public void deleteCategory()
    {
        subCategorias=null;
        if(!subCategoriaEJB.isCategoryUsed(subCategoriaSelected.getId()))
        {
            subCategoriaEJB.remove(subCategoriaSelected);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", ResourceBundle.getBundle("/Bundle").getString("CategoryDeletedSuccessfully")));
        }
        else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ResourceBundle.getBundle("/Bundle").getString("CategoryDeletedError")));
        }
        PrimeFaces.current().executeScript("PF('deteletCategoryDialog').hide()");
        PrimeFaces.current().executeScript("PF('deteletCategoryResultDialog').show()");
    
    }
    
}
