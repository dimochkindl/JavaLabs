package com.example._Laba.cache;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component(value = "cash")
public class MyCash<T,U> {
    Map<T,U> myCache = Collections.synchronizedMap(new HashMap<T,U>());


    public void push(T request, U response){

        if(!myCache.containsKey(request))
            myCache.put(request, response);
    }

    public U get(T request){
        if(myCache.containsKey(request))
            return myCache.get(request);
        return null;
    }
}