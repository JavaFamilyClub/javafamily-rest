/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package club.javafamily.model;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 * Build a http parameters instance.
 */
public class HttpParameters extends BasicHttpParamPairs<NameValuePair> {

   @Override
   public NameValuePair buildPair(String name, String value) {
      return new BasicNameValuePair(name, value);
   }
}
