package com.example.web.util;

public enum FetchType {
    EAGER, LAZY;

    public static boolean matchesFetchType(FetchType[] fetchTypes, FetchType fetchType) {
        for (FetchType type : fetchTypes) {
            if (type == fetchType) {
                return true;
            }
        }
        return false;
    }
}

