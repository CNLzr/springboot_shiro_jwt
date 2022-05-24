package com.lzr.util;

import java.util.HashMap;

public final class ResponseMap extends HashMap {
    private final String CODE="code";
    private final String MSG="msg";
    private final String OBJ = "data";
    public static final ResponseMap SUCCESS=new ResponseMap(2000,"成功");
    public static final ResponseMap ERROR=new ResponseMap(4000,"失败");
    public ResponseMap(){

    }
    public ResponseMap(Object object) {
        super.put(CODE, 2000);
        super.put(MSG,"成功");
        super.put(OBJ,object);
    }
    public ResponseMap(Integer code, String message){
        super.put(CODE,code);
        super.put(MSG,message);
    }
    public ResponseMap(Integer code, String message, Object data) {
        super.put(CODE,code);
        super.put(MSG,message);
        if (data != null) {
            super.put(OBJ,data);
        }
    }

    public ResponseMap putKey(String key,Object obj){
        super.put(key,obj);
        return  this;
    }
}
