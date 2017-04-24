/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.resource;

import com.ronoel.decorateste.bean.UserBean;
import com.ronoel.decorateste.entity.UserEntity;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author ronoel
 */
@Path("user")
public class UserResource {
    
    @EJB
    UserBean userBean;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UserResource() {
    }

    @RolesAllowed({UserEntity.ROLE_ADMIN,UserEntity.ROLE_USER})
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEntity> getAll() {
        
        List<UserEntity> users = this.userBean.getAll();
        return users;
    }
    
    @RolesAllowed({UserEntity.ROLE_ADMIN,UserEntity.ROLE_USER})
    @GET
    @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public UserEntity get( @PathParam("username") String username ) {
        
        return this.userBean.get(username);
    }
    
    @RolesAllowed(UserEntity.ROLE_ADMIN)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save( @Valid UserEntity user) throws Exception {
        
        this.userBean.save(user);
        return Response.status(201).build();
        
    }

    @RolesAllowed(UserEntity.ROLE_ADMIN)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update( @Valid UserEntity user ) throws Exception {
        
        this.userBean.update(user);
        return Response.status(200).build();
    }
    
    @RolesAllowed(UserEntity.ROLE_ADMIN)
    @DELETE
    @Path("{username}")
    public Response delete(
            @PathParam("username") String username ) throws Exception {

        this.userBean.remove(username);
                
        return Response.status(200).build();
    }
}
