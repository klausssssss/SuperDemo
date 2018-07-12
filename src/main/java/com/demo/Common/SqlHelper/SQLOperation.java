package com.demo.Common.SqlHelper;

public enum  SQLOperation  {
//    public final static String LessThan = " < ";
//    public final static String Equal = " = ";
//    public final static String LargeThan = " > ";
//    public final static String LargeEqualThan = " >= ";
//    public final static String LessEqualThan = " <= ";
    LessThan(" < ", 1), Equal(" = ", 2), LargeThan(" > ", 3)
    ,LargeEqualThan(" >= ", 4),LessEqualThan(" <= ", 4);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    SQLOperation(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (SQLOperation c : SQLOperation.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
