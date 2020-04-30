/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.rest;

import org.apache.http.client.config.RequestConfig;
import org.javafamily.util.StringUtils;

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

   protected RequestConfig requestConfig() {
      return RequestConfig.custom()
         // 设置连接超时时间(单位毫秒)
         .setConnectTimeout(5000)
         // 设置请求超时时间(单位毫秒)
         .setConnectionRequestTimeout(5000)
         // socket读写超时时间(单位毫秒)
         .setSocketTimeout(5000)
         // 设置是否允许重定向(默认为true)
         .setRedirectsEnabled(true)
         .build();
   }

   @Override
   public String baseUrl() {
      return baseUrl;
   }

   public static final String DEFAULT_BASEURL = "localhost";

   private final String baseUrl;
   private final RequestConfig requestConfig;
}
