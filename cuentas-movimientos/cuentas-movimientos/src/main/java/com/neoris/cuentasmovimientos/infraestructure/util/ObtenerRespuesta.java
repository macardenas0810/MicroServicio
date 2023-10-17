package com.neoris.cuentasmovimientos.infraestructure.util;

import java.util.HashMap;
import java.util.Map;

public class ObtenerRespuesta {

	
	public static Map<String,Object> devolverRespuesta(Object data, String msg){
		Map<String,Object> respuesta = new HashMap<>();
		respuesta.put("data", data);
		respuesta.put("msg", msg);
		return respuesta;
		
	}
	
}
