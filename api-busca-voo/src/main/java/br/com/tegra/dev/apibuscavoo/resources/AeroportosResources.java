package br.com.tegra.dev.apibuscavoo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tegra.dev.apibuscavoo.domain.Aeroporto;
import br.com.tegra.dev.apibuscavoo.services.AeroportosService;

@RestController
@RequestMapping("/aeroportos")
public class AeroportosResources {
	
	@Autowired
	AeroportosService aeroportosService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aeroporto>> listar() {

		return ResponseEntity.status(HttpStatus.OK).body(aeroportosService.listar());
	}

}
