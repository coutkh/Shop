package com.cout.shop.util;

public class TypeRe {
    public static String forward(String to) {
        return String.format("forward:%s", to);
    }
    public static String redirect(String to) {
        return String.format("redirect:%s", to);
    }
}
