package com.demo.Design.Singleton;

public class Static {
    private static final Static Instance = new Static();
    private Static(){}
    public static Static getInstance(){
        return Instance;
    }
}
