/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.bean;

import com.ronoel.decorateste.config.Config;
import com.ronoel.decorateste.datastore.MongoDecoraDatabaseUser;
import com.ronoel.decorateste.entity.UserEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import org.mongodb.morphia.Datastore;

/**
 *
 * @author ronoel
 */
@Singleton
@Startup
public class StoreAdminUser {
    
    @Inject
    Config config;

    @Inject
    @MongoDecoraDatabaseUser
    private Datastore datastore;

    public StoreAdminUser() {
    }

    /**
     * Cria um usuario admin no banco caso ele ainda nao exista.
     */
    @PostConstruct
    public void checkADMIN() {

        String admin_username = this.config.getProperty("admin.username");
        String admin_fullname = "Administrador";
        String admin_password = this.config.getProperty("admin.password");

        LOG.log(Level.INFO, "Verificando se usuario admin ja foi criado...");

        UserEntity usuario = this.datastore.get(UserEntity.class, admin_username);
        if (usuario == null) {

            LOG.log(Level.INFO, "Criando usuario admin...");

            usuario = new UserEntity(admin_username, admin_fullname, admin_password);
            usuario.getRoles().add(UserEntity.ROLE_ADMIN);
            this.datastore.save(usuario);

            LOG.log(Level.INFO, "Usuario admin salvo.");
        }

    }
    private static final Logger LOG = Logger.getLogger(StoreAdminUser.class.getName());

}
