package com.senac.assister.backend.rest.Util;

import org.modelmapper.ModelMapper;

public class Util {
    private final static ModelMapper modelMapper = new ModelMapper();
    //Convert obj in objDto generic
    public static Object convertReflect(Object obj, Class classObj) {
        return  modelMapper.map(obj, classObj);
    }

}
