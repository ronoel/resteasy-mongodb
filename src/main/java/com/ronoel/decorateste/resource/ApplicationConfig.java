/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.resource;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author ronoel
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.ronoel.decorateste.resource.CrossOriginResourceSharingFilter.class);
        resources.add(com.ronoel.decorateste.resource.UserResource.class);
        resources.add(com.ronoel.decorateste.resource.exception.UserExistsExceptionWeb.class);
        resources.add(com.ronoel.decorateste.resource.exception.UserNotExistExceptionWeb.class);
    }
    
}
