package br.com.tegra.dev.apibuscavoo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tegra.dev.apibuscavoo.domain.Aeroporto;
import br.com.tegra.dev.apibuscavoo.repository.AeroportosRepository;

@Service
public class AeroportosService {
	
	@Autowired
	private AeroportosRepository aeroportosRepository;

	public List<Aeroporto> listar(){
		return aeroportosRepository.findAll();
	}
	
}
