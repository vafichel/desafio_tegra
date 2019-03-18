package br.com.tegra.dev.apibuscavoo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.tegra.dev.apibuscavoo.domain.TrechoVoo;
import br.com.tegra.dev.apibuscavoo.services.VoosService;

@RestController
@RequestMapping("/voos")
public class VoosResources {

	@Autowired
	VoosService voosService;

	@RequestMapping(value = "/{origem}/{destino}/{data}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("origem") String origem, @PathVariable("destino") String destino,
			@PathVariable("data") String data) {

		List<TrechoVoo> trechoVoo = voosService.buscarVoos(origem, destino, data);
		return ResponseEntity.status(HttpStatus.OK).body(trechoVoo);

	}

}
