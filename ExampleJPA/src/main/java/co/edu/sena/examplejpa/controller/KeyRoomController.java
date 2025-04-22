/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.sena.examplejpa.controller;

import co.edu.sena.examplejpa.model.KeyRoom;
import co.edu.sena.examplejpa.persistence.DAOFactory;
import co.edu.sena.examplejpa.persistence.EntityManagerHelper;
import java.util.List;

/**
 * Fecha: 25/03/2025
 * @author anfeles
 * Objetivo: implementar la interface para controlar el modelo Key
 */
public class KeyRoomController implements IKeyRoomController{
        
    @Override
    public void insert(KeyRoom keyRoom) throws Exception {
        if(keyRoom == null)
        {
            throw new Exception("La llave es nula");
        } 
        
        if("".equals(keyRoom.getName()))
        {            
            throw new Exception("El nombre es obligatorio");
        }
        
        if("".equals(keyRoom.getRoom()))
        {            
            throw new Exception("El aula es obligatoria");
        }
        
        if(keyRoom.getCount() < 1)
        {            
            throw new Exception("La cantidad de llaves es incorrecta, debe ser mínimo 1");
        }      
               
        EntityManagerHelper.beginTransaction();
        DAOFactory.getKeyRoomDAO().insert(keyRoom);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    @Override
    public void update(KeyRoom keyRoom) throws Exception {
        if(keyRoom == null)
        {
            throw new Exception("La llave es nula");
        } 
        
        if(keyRoom.getId() == 0 )
        {
            throw new Exception("El Id es obligatorio");
        }
        
        if("".equals(keyRoom.getName()))
        {            
            throw new Exception("El nombre es obligatorio");
        }
        
        if("".equals(keyRoom.getRoom()))
        {            
            throw new Exception("El aula es obligatoria");
        }
        
        if(keyRoom.getCount() < 1)
        {            
            throw new Exception("La cantidad de llaves es incorrecta, debe ser mínimo 1");
        }      
        
        KeyRoom keyExists = DAOFactory.getKeyRoomDAO().findById(keyRoom.getId());
        if(keyExists == null)
        {
            throw new Exception("No existen las llaves");
        }
          
        keyExists.setCount(keyRoom.getCount());
        keyExists.setName(keyRoom.getName());
        keyExists.setObservation(keyRoom.getObservation());
        keyExists.setRoom(keyRoom.getRoom());
        
        EntityManagerHelper.beginTransaction();
        DAOFactory.getKeyRoomDAO().update(keyExists);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
    }

    @Override
    public void delete(Integer id) throws Exception {
        if(id == 0)
        {
            throw new Exception("El Id es obligatorio");
        }
        
        KeyRoom keyExists = DAOFactory.getKeyRoomDAO().findById(id);
        if(keyExists == null)
        {
            throw new Exception("No existen las llaves");
        }
        
        EntityManagerHelper.beginTransaction();
        DAOFactory.getKeyRoomDAO().delete(keyExists);
        EntityManagerHelper.commit();
        EntityManagerHelper.closeEntityManager();
        
    }

    @Override
    public List<KeyRoom> findAll() throws Exception {
        return DAOFactory.getKeyRoomDAO().findAll();
    }

    @Override
    public KeyRoom findById(Integer id) throws Exception {
        if(id == 0)
        {
            throw new Exception("El Id es obligatorio");
        }
        
        return DAOFactory.getKeyRoomDAO().findById(id);
    }
    
}
