/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.resource.exception;

import com.ronoel.decorateste.bean.exception.UserExistsException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ronoel
 */
@Provider
public class UserExistsExceptionWeb implements ExceptionMapper<UserExistsException> {

    @Override
    public Response toResponse(UserExistsException exception) {
        return Response.status(409).
                entity(exception.getMessage()).
                type("text/plain").
                build();
    }
    
}
