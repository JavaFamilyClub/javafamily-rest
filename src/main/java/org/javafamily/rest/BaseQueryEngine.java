/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.rest;

import org.javafamily.util.StringUtils;

public abstract class BaseQueryEngine implements QueryEngine {

   public BaseQueryEngine(String baseUrl) {
      if(StringUtils.isEmpty(baseUrl)) {
         baseUrl = DEFAULT_BASEURL;
      }

      if(!baseUrl.endsWith("/")) {
         baseUrl += "/";
      }

      this.baseUrl = baseUrl;
   }

   @Override
   public String baseUrl() {
      return baseUrl;
   }

   public static final String DEFAULT_BASEURL = "localhost";

   private final String baseUrl;
}
