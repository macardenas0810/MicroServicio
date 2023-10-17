package com.neoris.cuentasmovimientos.infraestructure.util;

import java.util.Date;

public class Validaciones {

	
    public static boolean validarNull(String identificacion) {
        return  identificacion.trim().isEmpty()  || "null".equals(identificacion) ;
    }
    
    public static boolean validarInt(Integer  param) {
        return param !=0  || param !=null  ;
    }
    
    public static boolean validarLong(Long  param) {
        return  param !=null  ;
    }
    
    public static boolean validarDate(Date  param) {
    	return param == null;
    }
    


}
