/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Seguimiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author eduardo
 */
@Local
public interface SeguimientoFacadeLocal {

    void create(Seguimiento seguimiento);

    void edit(Seguimiento seguimiento);

    void remove(Seguimiento seguimiento);

    Seguimiento find(Object id);

    List<Seguimiento> findAll();

    List<Seguimiento> findRange(int[] range);
    
    List<Seguimiento> findByEstaSegu();
    
    List<Seguimiento> findByEmpr();
    
    List<Seguimiento> findBySoli();
    
    List<Seguimiento> findByEmprInSpec(Object id);
    
    List<Seguimiento> findBySoliInSpec(Object id);
        
    List<Seguimiento> findByEmprU(int codi);
    
    List<Seguimiento> findByAll();
    
    Seguimiento findByCodiSegu(Seguimiento codi);

    int count();
    
}
