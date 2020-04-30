/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.model;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * Build a http headers instance.
 */
public class HttpHeaders extends BasicHttpParamPairs<Header> {
   @Override
   public Header buildPair(String name, String value) {
      return new BasicHeader(name, value);
   }
}
