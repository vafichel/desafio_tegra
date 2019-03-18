# desafio_tegra
Repositório com arquivos da api desenvolvida para o desafio Tegra, além de arquivos necessários para funcionamento.
##############################################
Passos necessários para funcionamento da api-busca-voos
###############################################
1) baixar os seguintes arquivos do repositorio:

> JAR : target.zip

> Fonte de Dados:

	* 99planes.json
	* aeroportos.json
	* uberair.csv

################################################	
	
2) Pré - requisitos: 
> Java 8;

> Salvar arquivos de dados no caminho C:\desafioTegra\dados

> Aplicativo Postman para executar chamada direto na api;

#################################################

3) Instalando o JAR da API:

> Abrir cmd;

> entrar no diretório onde salvou a pasta target descompactada; 

> dentro do diretório executar comando java -jar api-busca-voo-1.0.0-SNAPSHOT.jar


#################################################

4) Abrir POSTMAN:

> PRIMEIRO ENDPOINT:
http://localhost:8080/aeroportos

Http: GET

Não necessita passagem de parametros;
Objetivo : Lista todos os dados de todos os aeroportos existentes no arquivo aeroportos.json.




>SEGUNDO ENDPOINT:
http://localhost:8080/voos

Http: GET

####Passando como parâmetro:
keys: Origem, Destino e Data (Formato: yyyy-MM-dd)

Exemplo: http://localhost:8080/voos/BSB/FLN/2019-02-14


Objetivo: Lista todos os voos que atendam os critérios de pesquisa, 
montando conexões conforme combinação de destinos na mesma data, 
respeitando um intervalo menor que 12 horas entre uma conexão e outra.



###########################################


OBSERVAÇÃO######
Para testes, foi utilizado banco de dados H2 que mantém os dados em tempo de execução.
Para tal, ao iniciar o servidor a api realiza carga dos dados dos arquivos e depois manipula-os direto com o banco utilizando Jpa;


Para acesso aos dados na tabela consultar:
http://localhost:8080/h2-console

IMPORTANTE: Ajustar o campo JDBC URL para jdbc:h2:mem:testdb (padrão do Spring)









