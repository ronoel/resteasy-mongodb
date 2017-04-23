/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.resource.exception;

import com.ronoel.decorateste.bean.exception.UserNotExistException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author ronoel
 */
@Provider
public class UserNotExistExceptionWeb implements ExceptionMapper<UserNotExistException> {

    @Override
    public Response toResponse(UserNotExistException exception) {
        return Response.status(428).
                entity(exception.getMessage()).
                type("text/plain").
                build();
    }
    
}
