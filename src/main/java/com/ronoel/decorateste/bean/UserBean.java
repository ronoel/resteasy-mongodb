/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.bean;

import com.ronoel.decorateste.bean.exception.UserExistsException;
import com.ronoel.decorateste.bean.exception.UserNotExistException;
import com.ronoel.decorateste.datastore.MongoDecoraDatabaseUser;
import com.ronoel.decorateste.entity.UserEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author ronoel
 */
@Stateless
public class UserBean {

    @Inject
    @MongoDecoraDatabaseUser
    private Datastore datastore;

    public void save(UserEntity usuario) throws UserExistsException, Exception {
        
        if(this.get(usuario.getUsername())!=null){
            throw new UserExistsException();
        }

        try {
            this.datastore.save(usuario);

        } catch (Exception ex) {

            LOG.log(Level.SEVERE, null, ex);

            throw new Exception(ex);
        }

    }
    
    
    
    public void update(UserEntity usuario) throws UserNotExistException, Exception {
        
        if(this.get(usuario.getUsername())==null){
            throw new UserNotExistException();
        }

        try {
            this.datastore.save(usuario);

        } catch (Exception ex) {

            LOG.log(Level.SEVERE, null, ex);

            throw new Exception(ex);
        }

    }

    public List<UserEntity> getAll() {

        Query<UserEntity> query = this.datastore.createQuery(UserEntity.class);
        List<UserEntity> usuarios = query.asList();

        return usuarios;
    }

    
    public UserEntity get(String username) {

        UserEntity user = this.datastore.get(UserEntity.class, username);
        if (user == null) {
            return null;
        }

        return user;
    }

    
    public void remove(String username) {

        UserEntity user = this.get(username);
        if (user != null) {
            this.datastore.delete(user);
        }
        
    }
    
    
    public UserEntity getByUsernamePassword(String username, String password){
        
        UserEntity user = this.get(username);
        if(user.checkPassword(password)){
            return user;
        }
        
        return null;
    }

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

}
