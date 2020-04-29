/*
 * Copyright (c) 2020, JavaFamily Technology Corp, All Rights Reserved.
 */

package org.javafamily.util;

public final class StringUtils {
   private StringUtils() {
   }

   public static boolean isEmpty(String str) {
      return str == null || str.isEmpty();
   }
}
