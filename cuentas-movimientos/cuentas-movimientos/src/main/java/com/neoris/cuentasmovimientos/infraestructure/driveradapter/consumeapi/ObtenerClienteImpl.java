package com.neoris.cuentasmovimientos.infraestructure.driveradapter.consumeapi;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoris.cuentasmovimientos.domain.model.ClienteModel;


@Service
public class ObtenerClienteImpl implements IObtenerCliente {
     @Autowired
	 private RestTemplate restTemplate;
	 @Value("${uri.cliente}")
	 private String uri;
	 
	 
     
     
	@Override
	public ClienteModel cliente(Long id) {
		ClienteModel rta = null;
		try {
		     String stringCliente= restTemplate.getForObject(uri+id, String.class);	
		     ObjectMapper objectMapper = new ObjectMapper();
		     JsonNode jsonNode = objectMapper.readTree(stringCliente).get("data");
			 rta= objectMapper.treeToValue(jsonNode, ClienteModel.class);
		} catch (JsonProcessingException | IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage());
		}
		return rta;
	}
	


}
