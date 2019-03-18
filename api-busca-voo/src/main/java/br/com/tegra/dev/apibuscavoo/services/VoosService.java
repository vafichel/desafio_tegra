package br.com.tegra.dev.apibuscavoo.services;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tegra.dev.apibuscavoo.domain.TrechoVoo;
import br.com.tegra.dev.apibuscavoo.domain.Voo;
import br.com.tegra.dev.apibuscavoo.repository.VoosRepository;
import br.com.tegra.dev.apibuscavoo.services.exceptions.DadosNaoEncontradosException;
import br.com.tegra.dev.apibuscavoo.utils.SiteUtil;

@Service
public class VoosService {

	@Autowired
	VoosRepository voosRepository;

	/**
	 * @param origem
	 * @param destino
	 * @param data
	 * @return Retorna para o Client voos que atendem aos critérios de pesquisa
	 */
	public List<TrechoVoo> buscarVoos(String origem, String destino, String data) {

		List<TrechoVoo> listaVoos = new ArrayList<>();
		
		//buscando voos que atendem os criterios de pesquisa
		List<Voo> resultVoo = voosRepository.findByOrigemAndDestinoAndData(origem, destino, LocalDate.parse(data));
		
		if(!"null".equals(resultVoo) || !resultVoo.isEmpty()){
			for (Voo voo : resultVoo) {
				String dataHoraSaida = SiteUtil.getInstance().concatDataHora(voo.getData(), voo.getHorarioSaida());
				String dataHoraChegada = SiteUtil.getInstance().concatDataHora(voo.getData(), voo.getHorarioChegada());
				
				TrechoVoo trechoVoo = new TrechoVoo();
				trechoVoo.setOrigem(voo.getOrigem());
				trechoVoo.setDestino(voo.getDestino());
				trechoVoo.setSaida(SiteUtil.getInstance().formatStringToLocalDateTime(dataHoraSaida, "yyyy-MM-dd HH:mm:ss.SSS"));
				trechoVoo.setChegada(SiteUtil.getInstance().formatStringToLocalDateTime(dataHoraChegada,"yyyy-MM-dd HH:mm:ss.SSS"));
				
				List<Voo> voos = new ArrayList<>();
				voos.add(voo);
				trechoVoo.setTrechos(voos);
				listaVoos.add(trechoVoo);
			}
		}
		
		//buscando voos que possam compor conexão
		List<Voo> resultOrigem = voosRepository.findByOrigemAndData(origem, LocalDate.parse(data));

		List<Voo> resultDestino = voosRepository.findByDestinoAndData(destino, LocalDate.parse(data));
		
		for (Voo vooOrigem : resultOrigem) {
			for (Voo vooDestino : resultDestino) {
				if (vooOrigem.getDestino() == vooDestino.getOrigem()){
					Duration intervalo = Duration.between(vooOrigem.getHorarioChegada(), vooDestino.getHorarioSaida());
					if (intervalo.toHours() <= 12 &&  intervalo.toHours() > 0) {
						
						String dataHoraSaida = SiteUtil.getInstance().concatDataHora(vooOrigem.getData(), vooOrigem.getHorarioSaida());
						String dataHoraChegada = SiteUtil.getInstance().concatDataHora(vooDestino.getData(), vooDestino.getHorarioChegada());
						
						TrechoVoo trechoVoo = new TrechoVoo();
						trechoVoo.setOrigem(vooOrigem.getOrigem());
						trechoVoo.setDestino(vooDestino.getDestino());
						trechoVoo.setSaida(SiteUtil.getInstance().formatStringToLocalDateTime(dataHoraSaida, "yyyy-MM-dd HH:mm:ss.SSS"));
						trechoVoo.setChegada(SiteUtil.getInstance().formatStringToLocalDateTime(dataHoraChegada, "yyyy-MM-dd HH:mm:ss.SSS"));
						
						List<Voo> voos = new ArrayList<>();
						voos.add(vooOrigem);
						voos.add(vooDestino);
						trechoVoo.setTrechos(voos);
						listaVoos.add(trechoVoo);
					}

				}
			}

		}

		if (listaVoos.isEmpty()) {
			throw new DadosNaoEncontradosException("Não foram encontrados dados para a pesquisa.");
		}

		return listaVoos;

	}

	/**
	 * @param origem
	 * @param data
	 * @return Todos os Voos com a origem e data selecionada
	 */
	public List<Voo> buscarOrigem(String origem, String data) {
		Voo voo = new Voo();
		voo.setOrigem(origem);
		voo.setData(LocalDate.parse(data));
		List<Voo> voosOrigem = voosRepository.findByOrigemAndData(voo.getOrigem(), voo.getData());

		if (voosOrigem == null) {
			throw new DadosNaoEncontradosException("Não foram encontrados dados para a pesquisa.");
		}

		return voosOrigem;
	}

	/**
	 * @param destino
	 * @param data
	 * @return Todos os Voos com o destino e data selecionado
	 */
	public List<Voo> buscarDestino(String destino, String data) {
		Voo voo = new Voo();
		voo.setOrigem(destino);
		voo.setData(LocalDate.parse(data));

		List<Voo> voosDestino = voosRepository.findByOrigemAndData(voo.getOrigem(), voo.getData());

		if (voosDestino == null) {
			throw new DadosNaoEncontradosException("Não foram encontrados dados para a pesquisa.");
		}

		return voosDestino;

	}
	
	
	public Optional<Voo> consultar(Long id){
		Optional<Voo> voo = voosRepository.findById(id);
		
		return voo;
		
	}

}
