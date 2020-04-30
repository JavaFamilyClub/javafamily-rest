/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.model;

import org.apache.http.NameValuePair;

import java.util.*;

public abstract class BasicHttpParamPairs<T extends NameValuePair> implements HttpParamPairs<T> {

   protected BasicHttpParamPairs() {
      this.params = new HashMap<>();
   }

   @Override
   public HttpParamPairs addParam(String name, String value) {
      params.compute(name, (key, oldValue) -> {
         if(oldValue == null) {
            return value;
         }
         else {
            return new Object[]{ oldValue, value };
         }
      });

      return this;
   }

   @Override
   public HttpParamPairs removeParam(String name) {
      if(params.containsKey(name)) {
         params.remove(name);
      }

      return this;
   }

   @Override
   public List<T> build() {
      List<T> paramList = new ArrayList<>();

      params.entrySet()
         .stream()
         .forEach(entry -> {
            String name = entry.getKey();
            Object valueObj = entry.getValue();

            if(valueObj instanceof String[]) {
               String[] values = (String[]) valueObj;

               Arrays.stream(values).forEach(value -> {
                  paramList.add(this.buildPair(name, value));
               });
            }
            else {
               paramList.add(this.buildPair(name, String.valueOf(valueObj)));
            }
         });

      return paramList;
   }

   private Map<String, Object> params;
}
