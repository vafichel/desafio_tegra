package br.com.tegra.dev.apibuscavoo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tegra.dev.apibuscavoo.domain.Voo;

public interface VoosRepository extends JpaRepository<Voo, Long>{
	
	
	List<Voo> findByOrigemAndData(String pOrigem, LocalDate pData);
	
	List<Voo> findByDestinoAndData(String pDestino, LocalDate pData);
	
	List<Voo> findByOrigemAndDestinoAndData(String pOrigem, String pDestino, LocalDate pData);

}
