/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.resource;

import com.ronoel.decorateste.bean.UserBean;
import com.ronoel.decorateste.entity.UserEntity;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * REST Web Service
 *
 * @author ronoel
 */
@Path("auth")
@RequestScoped
public class AuthResource {

    @EJB
    private UserBean userBean;

    /**
     * Creates a new instance of AuthResource
     */
    public AuthResource() {
    }

    
    @GET
    @Path("me")
    @Produces(MediaType.APPLICATION_JSON)
    public Principal getMe(@Context SecurityContext securityContext) {
        
        Principal principal = securityContext.getUserPrincipal();
        return principal;
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) // "application/x-www-form-urlencoded"
    @Produces(MediaType.APPLICATION_JSON)
    public Response save( 
            @NotNull @FormParam("username") String username,
            @NotNull @FormParam("password") String password
    ) throws Exception {
        
//        LOG.log(Level.WARNING, "Credentials: {0} - {1}", new Object[]{username, password});
        
        UserEntity user = this.userBean.getByUsernamePassword(username, password);
        
        if(user==null) {
            LOG.log(Level.INFO, "FAIL LOGIN");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        NewCookie cookie = new NewCookie(
                //    AUTHCONFIG.getInstance().getProperty("Cookie.Name"), // nome do cookie
                "auth-token",
                user.getUsername(), // valor do cookie
                "/", // path
                null, // dominio
                1, // versao
                "", // comentario
                -1,
                false // cookie nao eh enviado so em https
        );
        
//        LOG.log(Level.INFO, "SUCCESS LOGIN - ");
        return Response.ok(user).cookie(cookie).build();
        
    }
    private static final Logger LOG = Logger.getLogger(AuthResource.class.getName());
    
    
}
