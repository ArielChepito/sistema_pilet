/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sv.udb.ejb;

import com.sv.udb.modelo.Mantenimientos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gersonfrancisco
 */
@Local
public interface MantenimientosFacadeLocal {

    void create(Mantenimientos mantenimientos);

    void edit(Mantenimientos mantenimientos);

    void remove(Mantenimientos mantenimientos);

    Mantenimientos find(Object id);

    List<Mantenimientos> findAll();
    
    List<Mantenimientos> findTodo();
    
    List<Mantenimientos> findRange(int[] range);

    int count();
    
}
