/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.rest;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.javafamily.model.HttpHeaders;
import org.javafamily.model.HttpParameters;

public interface QueryEngine {

   /**
    * Api base url.
    * @return Rest api base url
    */
   String baseUrl();

   /**
    * Build RequestConfig for Every Request.
    * @return RequestConfig
    */
   RequestConfig getRequestConfig();

   /**
    * Query Execution
    * @param api api URI
    * @param params Query parameters
    * @param headers Query headers
    * @return result string
    * @exception Exception query error
    */
   default String query(String api, HttpParameters params, HttpHeaders headers) throws Exception {
      URIBuilder uriBuilder = new URIBuilder(baseUrl() + api);

      uriBuilder.addParameters(requiredParameters().build());
      uriBuilder.addParameters(params.build());

      HttpGet httpGet = new HttpGet(uriBuilder.build());

      httpGet.setConfig(getRequestConfig());

      requiredHeaders().build().stream().forEach(httpGet::addHeader);
      headers.build().stream().forEach(httpGet::addHeader);

      CloseableHttpClient httpClient = HttpClients.createDefault();

      CloseableHttpResponse response = httpClient.execute(httpGet);

      HttpEntity entity = response.getEntity();

      return EntityUtils.toString(entity, charset());
   }

   default HttpParameters requiredParameters() {
      return new HttpParameters();
   }

   default HttpHeaders requiredHeaders() {
      return new HttpHeaders();
   }

   default String charset() {
      return "UTF-8";
   }

}
