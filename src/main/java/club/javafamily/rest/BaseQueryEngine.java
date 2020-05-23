/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package club.javafamily.rest;

import club.javafamily.util.string.StringUtils;
import org.apache.http.client.config.RequestConfig;

public abstract class BaseQueryEngine implements QueryEngine {

   protected BaseQueryEngine(String baseUrl) {
      if(StringUtils.isEmpty(baseUrl)) {
         baseUrl = DEFAULT_BASEURL;
      }

      if(!baseUrl.endsWith("/")) {
         baseUrl += "/";
      }

      this.baseUrl = baseUrl;
      this.requestConfig = requestConfig();
   }

   private RequestConfig requestConfig() {
      return RequestConfig.custom()
         .setConnectTimeout(5000)
         .setConnectionRequestTimeout(5000)
         .setSocketTimeout(5000)
         .setRedirectsEnabled(true)
         .build();
   }

   @Override
   public String baseUrl() {
      return baseUrl;
   }

   @Override
   public RequestConfig getRequestConfig() {
      return requestConfig;
   }

   public static final String DEFAULT_BASEURL = "localhost";

   private final String baseUrl;
   private final RequestConfig requestConfig;
}
