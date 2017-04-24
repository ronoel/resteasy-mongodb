/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.auth;

import com.ronoel.decorateste.entity.UserEntity;
import java.security.Principal;
import javax.security.auth.Subject;

/**
 *
 * @author ronoel
 */
public class UserPrincipal implements Principal {
    
    private UserEntity user;

    public UserPrincipal(UserEntity user) {
        this.user = user;
    }
    
    public UserEntity getUser(){
        return this.user;
    }
    

    @Override
    public String getName() {
        return user.getUsername();
    }

    @Override
    public boolean implies(Subject sbjct) {
        return Principal.super.implies(sbjct); //To change body of generated methods, choose Tools | Templates.
    }
}
