package br.com.tegra.dev.apibuscavoo.cargainicial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.tegra.dev.apibuscavoo.domain.Aeroporto;
import br.com.tegra.dev.apibuscavoo.domain.Voo;
import br.com.tegra.dev.apibuscavoo.repository.AeroportosRepository;
import br.com.tegra.dev.apibuscavoo.repository.VoosRepository;

@Component
public class ApiBuscaVooCargaInicial {
	
	@Autowired
	private AeroportosRepository aeroportoRepository;
	
	@Autowired
	private VoosRepository voosRepository;
	
	@Bean
	public String inicioCarga() {
		System.out.println("INICIANDO CARGA DE DADOS");
		cargaAeroportos();
		cargaVoo99Planes();
		cargaUberAir();
		
		return "";
	}
	
	

	/**
	 * Iniciando carga do arquivo aeroportos.json
	 */
	private void cargaAeroportos (){
		File arquivo = lerArquivo("C:/desafioTegra/dados/aeroportos.json");
		List<Aeroporto> lista = null;
		
		if(arquivo.exists()){
			lista = deserializeAeroporto(arquivo);
		} else {
			System.out.println("NÃO FOI POSSÍVEL LER O ARQUIVO");
		}
		
		if(lista != null && !lista.isEmpty() ){
			aeroportoRepository.saveAll(lista);
		}
	}
	

	

	/**
	 * @param f
	 * @return
	 * Deserializando os dados do Arquivo aeroportos.json
	 */
	private List <Aeroporto> deserializeAeroporto(final File f){
		final ObjectMapper mapper = new ObjectMapper();
		Aeroporto[] array= null;
		try{
			array = mapper.readValue(f, Aeroporto[].class);
		} catch(IOException e ) {
			e.printStackTrace();
		}
		return Arrays.asList(array);
	}
	
	
	
	

	/**
	 * Iniciando carga do arquivo 99planes.json
	 */
	private void cargaVoo99Planes(){
		File arquivo = lerArquivo("C:/desafioTegra/dados/99planes.json");
		List<Voo99PlanesTo> lista = null;
		
		if(arquivo.exists()){
			lista = deserializeVoo99Planes(arquivo);
		} else {
			System.out.println("NÃO FOI POSSÍVEL LER O ARQUIVO");
		}
		
		if (lista != null && !lista.isEmpty()) {
			List<Voo> voos = parseVoo99Planes(lista);
			voosRepository.saveAll(voos);
		}
	}
	
	
	/**
	 * @param f
	 * @return
	 * Deserializando arquivo 99planes.json
	 */
	private List <Voo99PlanesTo> deserializeVoo99Planes(final File f){
		final ObjectMapper mapper = new ObjectMapper();
		Voo99PlanesTo[] array= null;
		try{
			array = mapper.readValue(f, Voo99PlanesTo[].class);
		} catch(IOException e ) {
			e.printStackTrace();
		}
		return Arrays.asList(array);
	}
	
	
	/**
	 * @param lista
	 * @return
	 * Realizando o parse e adequação de tipos para o objeto de negócio Voo
	 */
	private List<Voo> parseVoo99Planes(List<Voo99PlanesTo> lista){
		List<Voo> listaRetorno = new ArrayList<>();
		
		Iterable<Voo99PlanesTo>  iterable = lista;
		iterable.forEach(item -> {
			Voo voo = new Voo();
			voo.setOperadora("99Planes");
			voo.setIdentificador(item.getVoo());
			voo.setOrigem(item.getOrigem());
			voo.setDestino(item.getDestino());
			voo.setData(LocalDate.parse(item.getData_saida()));
			voo.setHorarioSaida(LocalTime.parse(item.getSaida()));
			voo.setHorarioChegada(LocalTime.parse(item.getChegada()));
			voo.setValor(new BigDecimal(item.getValor()));
			listaRetorno.add(voo);
		});
		
		return listaRetorno;
	}
	
	
	
	/**
	 * Iniciando carga dos dados do arquivo uberair.csv
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void cargaUberAir() {
		List<VooUberAirTo> lista;
		List<Voo> voos;
		try {
			FileReader file = new FileReader(lerArquivo("C:/desafioTegra/dados/uberair.csv"));
			lista = new CsvToBeanBuilder(file).withType(VooUberAirTo.class).build().parse();
			
			voos = parseVooUberAir(lista);
			voosRepository.saveAll(voos);
			
		} catch (IllegalStateException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param lista
	 * @return
	 * Realizando o parse e adequação de tipos para o objeto de negócio Voo
	 */
	private List<Voo> parseVooUberAir(List<VooUberAirTo> lista){
		List<Voo> listaRetorno = new ArrayList<>();
		
		Iterable<VooUberAirTo>  iterable = lista;
		iterable.forEach(item -> {
			Voo voo = new Voo();
			voo.setOperadora("UberAir");
			voo.setIdentificador(item.getNumero_voo());
			voo.setOrigem(item.getAeroporto_origem());
			voo.setDestino(item.getAeroporto_destino());
			voo.setData(LocalDate.parse(item.getData().toString()));
			voo.setHorarioSaida(LocalTime.parse(item.getHorario_saida().toString()));
			voo.setHorarioChegada(LocalTime.parse(item.getHorario_chegada().toString()));
			voo.setValor(new BigDecimal(item.getPreco()));
			listaRetorno.add(voo);
		});
		
		return listaRetorno;
	}
	
	
	
	/**
	 * @param caminho
	 * @return
	 * criando uma instancia do arquivo
	 */
	private File lerArquivo (String caminho) {
		File arquivo = new File(caminho);
		return arquivo;
	}
	

	

}
