/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.model;

import org.apache.http.NameValuePair;

import java.util.List;

/**
 * Simplify the construction of Http parameters
 * @param <T> Http Parameter or Header
 */
public interface HttpParamPairs<T extends NameValuePair> {

   /**
    * Add a param pair.
    * @param name param name
    * @param value param value
    * @return this
    */
   HttpParamPairs addParam(String name, String value);

   /**
    * Remove a param
    * @param name parameter name
    * @return this
    */
   HttpParamPairs removeParam(String name);

   /**
    * Build a param instance.
    * @param name param name
    * @param value param value
    * @return param instance
    */
   T buildPair(String name, String value);

   /**
    * Build a param list.
    * @return param list
    */
   List<T> build();
}
