/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ronoel.decorateste.bean.exception;

/**
 *
 * @author ronoel
 */
public class UserNotExistException extends Exception {

    /**
     * Creates a new instance of <code>UserNotExistException</code> without
     * detail message.
     */
    public UserNotExistException() {
    }

    /**
     * Constructs an instance of <code>UserNotExistException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserNotExistException(String msg) {
        super(msg);
    }
}
