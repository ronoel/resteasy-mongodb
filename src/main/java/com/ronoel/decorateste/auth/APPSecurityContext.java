/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.auth;

import com.ronoel.decorateste.entity.UserEntity;
import java.security.Principal;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author ronoel
 */
public class APPSecurityContext implements SecurityContext {
    
    private final UserPrincipal principal;
    
    public APPSecurityContext() {
        this.principal = null;
    }
    
    public APPSecurityContext(UserEntity user) {
        this.principal = new UserPrincipal(user);
    }

    @Override
    public Principal getUserPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isUserInRole(String role) {
        return this.principal.getUser().getRoles().contains(role);
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.FORM_AUTH;
    }
    
    
}
