package com.delivery24.managedbeans;


import com.delivery24.entities.Producto;
import com.delivery24.entities.Subcategoria;
import com.delivery24.facade.ProductoFacade;
import com.delivery24.facade.SubcategoriaFacade;
import com.delivery24.managedbeans.util.Util;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named("productoController")
@ViewScoped
public class ProductoController implements Serializable {

    @EJB
    private ProductoFacade productoEJB;
    @EJB
    private SubcategoriaFacade subcategoriaEJB;
    private Producto producto;
    private String nombre;
    private UploadedFile uploadedFileImagen;
    private Subcategoria subcategoria;
    private List<Subcategoria> subcategorias;
    
    @PostConstruct
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String prodId = paramMap.get("p");
        
        if (prodId != null && !prodId.equals("")) {
            try {
                int id = Integer.parseInt(prodId);
                producto = productoEJB.find(id);
                if (producto == null) {
                    goProducts();
                }
                else
                {
                    initValues();
                }
            } catch (NumberFormatException e) {
                goProducts();
            }
        } else {
            goProducts();
        }
    }
    
    public void initValues()
    {
    }
    
    private void goProducts() {
        try {
            String uri = Util.projectPath+"/productos/productos.xhtml?i=1";
            FacesContext.getCurrentInstance().getExternalContext().redirect(uri);
        } catch (IOException ex) {
            Logger.getLogger(ProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Producto getProducto() {
        return producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }

    public UploadedFile getUploadedFileImagen() {
        return uploadedFileImagen;
    }

    public List<Subcategoria> getSubcategorias() {
        return subcategorias;
    }
    
    public void abrirEditarNombre()
    {
        nombre = producto.getProdnombre();
        Util.update(":formEditarNombre:nombrePanel");
        Util.openDialog("editarNombreDialog");
    }
    
    public void editarNombre()
    {
        if(!Util.formatText(nombre).equals(producto.getProdnombre()))
        {
            producto.setProdnombre(Util.formatText(nombre));
            productoEJB.edit(producto);
            Util.addInfoMessage(ResourceBundle.
                    getBundle("/Bundle").
                    getString("EditSuccessfull"),ResourceBundle.
                    getBundle("/Bundle").
                    getString("EditSuccessfull"));
            Util.update(":formProducto:messageGrowl");
            Util.update(":formProducto:panelProducto");
        }
        Util.closeDialog("editarNombreDialog");
    }
    
    
    public void abrirEditarImagen()
    {
        uploadedFileImagen = null;
        Util.update(":formEditarImagen:imagenPanel");
        Util.openDialog("editarImagenDialog");
    }
    
    public void editarImagen() throws InterruptedException
    {
        if (this.uploadedFileImagen != null) {
                
            try {
                File directory = new File(Util.PRODUCTIMAGEDIR+producto.getId());
                
                if(!directory.exists())
                {
                    directory.mkdir();
                }
                InputStream fi = uploadedFileImagen.getInputstream();
                
                byte[] buffer = new byte[fi.available()];
                fi.read(buffer);
                
                String imagenActual = producto.getProdimagen();
                
                
                
                ByteArrayInputStream bin = new ByteArrayInputStream(buffer);
                
                String name = uploadedFileImagen.getFileName();
                String []split = name.split(Pattern.quote("."));
                String extension=split[split.length-1];
                
                String fileName; 
                
                if(imagenActual.equals("default.png"))
                {
                   fileName= producto.getId()+File.separator+"1."+extension; 
                }
                else
                {
                    String [] n = imagenActual.split(File.separator);
                    imagenActual = n[1];
                    n = imagenActual.split(Pattern.quote("."));
                    int indice = Integer.parseInt(n[0]);
                    indice = indice +1;
                    fileName= producto.getId()+File.separator+indice+"."+extension;
                    File currentFile = new File(Util.PRODUCTIMAGEDIR+producto.getProdimagen());
                    if(currentFile.exists())
                    {
                        currentFile.delete();
                    }
                }
                
                File file = new File(Util.PRODUCTIMAGEDIR+fileName);
                file.createNewFile();
                FileOutputStream fbo= new FileOutputStream(file);
                byte[] bff = new byte[1024];
                int length;
                while((length=bin.read(bff))>0)
                {
                    fbo.write(bff, 0, length);
                }
                fbo.close();
                fi.close();
                bin.close();
                
                producto.setProdimagen(fileName);
                productoEJB.edit(producto);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Foto Actualizada exitosamente. Fresione F5 para refrescarla", "Foto Actualizada."));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Thread.sleep(2000);
            this.uploadedFileImagen = null;

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se ha cargado una foto.", "No se ha cargado una foto"));
        }
    }
    
    public void cargarImagen(FileUploadEvent event) {
        this.uploadedFileImagen = event.getFile();
    }
    
    
    public void abrirEditarCategoria()
    {
        subcategoria = producto.getIdsubcategoria();
        subcategorias = subcategoriaEJB.findAllOderByNombre();
        Util.update(":formEditarCategoria:categoriaPanel");
        Util.openDialog("editarCategoriaDialog");
    }
    
    public void editarCategoria()
    {
        if(!subcategoria.getId().equals(producto.getIdsubcategoria().getId()))
        {
            producto.setIdsubcategoria(subcategoria);
            productoEJB.edit(producto);
            Util.addInfoMessage(ResourceBundle.
                    getBundle("/Bundle").
                    getString("EditSuccessfull"),ResourceBundle.
                    getBundle("/Bundle").
                    getString("EditSuccessfull"));
            Util.update(":formProducto:messageGrowl");
            Util.update(":formProducto:panelProducto");
        }
        Util.closeDialog("editarCategoriaDialog");
    }
}
