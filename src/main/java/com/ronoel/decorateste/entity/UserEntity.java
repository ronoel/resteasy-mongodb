/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.entity;

import com.ronoel.decorateste.util.CipherGaias;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.PreLoad;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.utils.IndexType;

/**
 *
 * @author ronoel
 */
@Entity("user")
@Indexes(@Index(fields = @Field(value = "$**", type = IndexType.TEXT)))
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.NONE)
public class UserEntity implements Serializable {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";

    
    @Id
    @XmlElement
    @NotNull
    @Size(min = 4, max = 20)
    private String username;
    
    @XmlElement
    @NotNull
    @Size(min = 4, max = 50)
    private String fullname;

    @Property("password")
    private String passwordciphered; // password to be persisted
    
    @Transient
    @XmlElement
    @NotNull
    @Size(min = 6, max = 20)
    private String password;    // password 

    @XmlElement
    private Set<String> roles = new HashSet<>();

    public UserEntity() {
        this.roles.add(UserEntity.ROLE_USER);
    }

    public UserEntity(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username, String fullname, String password) {
        this(username,password);
        this.fullname = fullname;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    @PrePersist
    private void encryptPassword() {
        
        try {
            this.passwordciphered = CipherGaias.encrypt(this.password);
        } catch (Exception ex) {
            Logger.getLogger(UserEntity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @PreLoad
    private void hidePassword(){
        this.password = "xxxxxxxx";
    }
    
    
    public boolean checkPassword(String password){
        
        String encryptedPassword;
        try {
            encryptedPassword = CipherGaias.encrypt(password);
//            LOG.log(Level.WARNING, "CHECK PASSWORD: {0} <--> {1}", new Object[]{encryptedPassword, this.passwordciphered});
            return this.passwordciphered.equals(encryptedPassword);
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    private static final Logger LOG = Logger.getLogger(UserEntity.class.getName());
    
    
}
