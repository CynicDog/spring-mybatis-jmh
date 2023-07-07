package com.example.web.util;

public enum FetchType {
    EAGER, LAZY;

    public static boolean containsFetchType(FetchType[] fetchTypes, FetchType fetchType) {
        for (FetchType type : fetchTypes) {
            if (type == fetchType) {
                return true;
            }
        }
        return false;
    }
}
