/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.datastore;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.ronoel.decorateste.config.Config;
import com.ronoel.decorateste.entity.UserEntity;
import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author ronoel
 */
@Singleton
public class MorphiaDataStoreFactory {
    
    @Inject
    Config config;

    private Datastore datastore;
    

    @Produces
    @MongoDecoraDatabaseUser
    public Datastore getDataStore() {

        if (this.datastore == null) {
            final Morphia morphia = new Morphia();

            morphia.map(UserEntity.class);

            this.datastore = morphia.createDatastore(new MongoClient(new MongoClientURI(this.config.getProperty("database.URI"))), this.config.getProperty("database.name"));
            datastore.ensureIndexes();
        }

        return this.datastore;
    }
    
    
    private static final Logger LOG = Logger.getLogger(MorphiaDataStoreFactory.class.getName());

}
