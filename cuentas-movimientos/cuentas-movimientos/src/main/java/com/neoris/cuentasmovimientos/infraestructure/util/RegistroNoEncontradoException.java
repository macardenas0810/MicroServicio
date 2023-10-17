package com.neoris.cuentasmovimientos.infraestructure.util;

public class RegistroNoEncontradoException extends RuntimeException {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8131354515978918505L;

	public RegistroNoEncontradoException(String message) {
        super(message);
    }
}
