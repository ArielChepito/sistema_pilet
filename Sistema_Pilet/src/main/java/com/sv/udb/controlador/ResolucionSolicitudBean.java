/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.controlador;

import com.sv.udb.ejb.ResolucionSolicitudesFacadeLocal;
import com.sv.udb.ejb.SolicitudesFacadeLocal;
import com.sv.udb.modelo.ResolucionSolicitudes;
import com.sv.udb.modelo.Solicitudes;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 * Esta clase se encuentran los metodos para el manejo de los datos (CRUD) del objeto objeResoSoli
 * @author Israel
 * @version 1.2
 */
@Named(value = "resolucionSolicitudBean")
@ViewScoped
public class ResolucionSolicitudBean implements Serializable{
    @EJB
    private ResolucionSolicitudesFacadeLocal FCDEResoSoli; 
    private ResolucionSolicitudes objeResoSoli;
    private List<ResolucionSolicitudes> listResoSoli;
    private boolean guardar;

    /**
     * Función para obtener  el objeto objeResoSoli
     * @return ResolucionSolicitudes objeResoSoli
     */
    public ResolucionSolicitudes getObjeResoSoli() {
        return objeResoSoli;
    }

    /**
     * Función para definir el objeto objeResoSoli
     * @param objeResoSoli
     */
    public void setObjeResoSoli(ResolucionSolicitudes objeResoSoli) {
        this.objeResoSoli = objeResoSoli;
    }

    /**
     * Función que retorna el valor de la variable guardar para saber si se está guardando o no actualmente
     * @return Boolean guardar
     */
    public boolean isGuardar() {
        return guardar;
    }

    /**
     * Función que retorna la lista de objetos de Departamentos
     * @return List listDepa
     */
    public List<ResolucionSolicitudes> getListResoSoli() {
        return listResoSoli;
    }
    
    /**
     * Creates a new instance of ResolucionSolicitudesBean
     */
    
    public ResolucionSolicitudBean() {
    }
    
    /**
     * Función que se ejecuta después de construir la clase
     */
    @PostConstruct
    public void init()
    {
        this.limpForm();
        this.consTodo();
    }
    
    /**
     * Función para limpiar el formulario
     */
    public void limpForm()
    {
        this.objeResoSoli = new ResolucionSolicitudes();
        this.guardar = true;        
    }

    /**
     * Función para guardar
     */
    public void guar()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            Solicitudes soli = new Solicitudes(SolicitudesBean.codiSoli);
            this.objeResoSoli.setCodiSoli(soli);
            this.objeResoSoli.setFechResoSoli(new Date());
            FCDEResoSoli.create(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli);
            this.limpForm();
            this.guardar = false;
            //this.limpForm(); //Omito para mantener los datos en la modal
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos guardados')");
            ctx.execute("location.reload()");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al guardar ')");
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para modificar un registro
     */
    public void modi()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.objeResoSoli.setFechResoSoli(new Date());
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo
            FCDEResoSoli.edit(this.objeResoSoli);
            this.listResoSoli.add(this.objeResoSoli); //Agrega el objeto modificado
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Modificados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al modificar ')");
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para dar de baja un registro
     */
    public void elim()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        try
        {
            this.listResoSoli.remove(this.objeResoSoli); //Limpia el objeto viejo
            FCDEResoSoli.remove(this.objeResoSoli);
            this.limpForm();
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Datos Eliminados')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al eliminar')");
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para consultar todos los registros de la tabla
     */
    public void consTodo()
    {
        try
        {
            this.listResoSoli = FCDEResoSoli.findAll();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            
        }
    }
    
    /**
     * Función para consultar un registro en específico
     */
    public void cons()
    {
        RequestContext ctx = RequestContext.getCurrentInstance(); //Capturo el contexto de la página
        int codi = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codiPara"));
        try
        {
            this.objeResoSoli = FCDEResoSoli.find(codi);
            this.guardar = false;
            ctx.execute("setMessage('MESS_SUCC', 'Atención', 'Consultado a " + 
                    String.format("%s", this.objeResoSoli.getCodiResoSoli()) + "')");
        }
        catch(Exception ex)
        {
            ctx.execute("setMessage('MESS_ERRO', 'Atención', 'Error al consultar')");
        }
        finally
        {
            
        }
    }
    
    public ResolucionSolicitudes consReso(int codi){
        try{
            this.objeResoSoli = FCDEResoSoli.findReso(codi);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return this.objeResoSoli;
    }
}
