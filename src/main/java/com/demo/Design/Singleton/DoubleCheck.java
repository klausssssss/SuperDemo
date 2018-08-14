package com.demo.Design.Singleton;

public class DoubleCheck {
    private static volatile DoubleCheck singleton;

    private DoubleCheck(){}

    public DoubleCheck getInstace(){
        if(singleton==null){
            synchronized (DoubleCheck.class){
                if(singleton==null){
                    singleton = new DoubleCheck();
                    return singleton;
                }
            }
        }
        return singleton;
    }
}
