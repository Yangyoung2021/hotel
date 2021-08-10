package com.young.utils;


import java.util.UUID;

public class UUIDUtils {


    public static String UUIDRandom() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
