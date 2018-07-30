package com.demo.Common.Cloneable;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import com.alibaba.fastjson.JSON;

public class DeepClone {
    public static T JsonDeepClone(T obj){
        String json = JSON.toJSONString(obj);
        return JSON.parseObject(json,T.class);
    }
}
