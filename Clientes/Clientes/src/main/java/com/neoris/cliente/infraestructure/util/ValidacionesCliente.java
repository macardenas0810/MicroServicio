package com.neoris.cliente.infraestructure.util;

public final class ValidacionesCliente {
	
	
    public static boolean validarNull(String identificacion) {
        return identificacion != null && !identificacion.trim().isEmpty();
    }
    
    public static boolean validarInt(Integer  param) {
        return param !=0  || param !=null  ;
    }
    
    public static boolean validarLong(Long  param) {
        return  param !=null  ;
    }

}
