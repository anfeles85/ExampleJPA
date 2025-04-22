/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.examplejpa.controller;

import co.edu.sena.examplejpa.model.EmployeeType;
import co.edu.sena.examplejpa.persistence.DAOFactory;
import co.edu.sena.examplejpa.persistence.EntityManagerHelper;
import java.util.List;

/**
 * Fecha: 25/03/2025
 * @author anfeles
 * Objetivo: implementar la interface para controlar el modelo EmployeeType
 */
public class EmployeeTypeController implements IEmployeeTypeController{

        
    @Override
    public void insert(EmployeeType employeeType) throws Exception {
        if(employeeType == null)
        {
            throw new Exception("El tipo de empleado es nulo");
        }
        
        if("".equals(employeeType.getDescript()))
        {
            throw new Exception("La descripción es obligatoria");
        }
        
        //insertar
        EntityManagerHelper.beginTransaction();
        DAOFactory.getEmployeeTypeDAO().insert(employeeType);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();        
    }

    @Override
    public void update(EmployeeType employeeType) throws Exception {
        if(employeeType == null)
        {
            throw new Exception("El tipo de empleado es nulo");
        }
        
        if(employeeType.getId() == 0)
        {
            throw new Exception("El Id es obligatorio");
        }
        
        if("".equals(employeeType.getDescript()))
        {
            throw new Exception("La descripción es obligatoria");
        }
        
        //consultar si el employeeType existe en la bd
        EmployeeType employeeTypeExists = DAOFactory.getEmployeeTypeDAO().findById(employeeType.getId());
        if(employeeTypeExists == null)
        {
            throw new Exception("El tipo de empleado no existe");
        }
        
        //merge: todos los campos menos la PK
        employeeTypeExists.setDescript(employeeType.getDescript());
        EntityManagerHelper.beginTransaction();        
        DAOFactory.getEmployeeTypeDAO().update(employeeTypeExists);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    @Override
    public void delete(Integer id) throws Exception {
        if(id == 0)
        {
            throw new Exception("El Id es obligatorio");
        }
        
        //consultar si el employeeType existe en la bd
        EmployeeType employeeTypeExists = DAOFactory.getEmployeeTypeDAO().findById(id);
        if(employeeTypeExists == null)
        {
            throw new Exception("El tipo de empleado no existe");
        }
        
        //eliminar
        EntityManagerHelper.beginTransaction();
        DAOFactory.getEmployeeTypeDAO().delete(employeeTypeExists);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    @Override
    public List<EmployeeType> findAll() throws Exception {
        return DAOFactory.getEmployeeTypeDAO().findAll();
    }

    @Override
    public EmployeeType findById(Integer id) throws Exception {
        if(id == 0)
        {
            throw new Exception("El Id es obligatorio");
        }
        
        return DAOFactory.getEmployeeTypeDAO().findById(id);
    }
    
}
