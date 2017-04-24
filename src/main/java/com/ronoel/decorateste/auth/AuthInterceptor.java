/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.auth;

import com.ronoel.decorateste.bean.UserBean;
import com.ronoel.decorateste.entity.UserEntity;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ronoel
 */
@Provider
@PreMatching
@Priority(Priorities.AUTHENTICATION)
public class AuthInterceptor implements ContainerRequestFilter {

    @EJB
    private UserBean userBean;

    @Context
    private UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
        Cookie cookie = requestContext.getCookies().get("auth-token");
        
        if (cookie != null) {
            UserEntity user = userBean.get(cookie.getValue());

            if (user != null) {
//                LOG.log(Level.WARNING, "Authenticated user: {0}", user.getFullname());
                requestContext.setSecurityContext(new APPSecurityContext(user));
            }
        }
    }
    private static final Logger LOG = Logger.getLogger(AuthInterceptor.class.getName());

}
