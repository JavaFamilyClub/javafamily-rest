/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.rest;

import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.*;

public interface QueryEngine {

   /**
    * Api base url.
    */
   String baseUrl();

   /**
    * Query Execution
    * @param api api URI
    * @param params Query parameters
    * @param headers Query headers
    * @return result string
    */
   default String query(String api, List<NameValuePair> params, List<Header> headers) throws Exception {
      URIBuilder uriBuilder = new URIBuilder(baseUrl() + api);

      uriBuilder.setParameters(requiredParameters());
      uriBuilder.setParameters(params);

      HttpGet httpGet = new HttpGet(uriBuilder.build());

      requiredHeaders().stream().forEach(httpGet::addHeader);
      headers.stream().forEach(httpGet::addHeader);

      CloseableHttpClient httpClient = HttpClients.createDefault();

      CloseableHttpResponse response = httpClient.execute(httpGet);

      HttpEntity entity = response.getEntity();

      return EntityUtils.toString(entity, charset());
   }

   default List<NameValuePair> requiredParameters() {
      return Collections.emptyList();
   }

   default List<Header> requiredHeaders() {
      return Collections.emptyList();
   }

   default String charset() {
      return "UTF-8";
   }

}
