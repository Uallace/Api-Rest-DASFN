# Desafio Taxa de Juros 

O projeto foi desenvolvido para ler, salvar e apresentar dados das taxas de juros de operações de crédito por instituição financeira do catálogo de dados abertos do sistema financeiro nacional (DASFN) do Banco Central do Brasil.

## 🚀 Começando

O projeto foi desenvolvido seguindo as premissas e avaliando as principais ferramentas para tal. O 
 padrão utilizado para o desenvolvimento do projeto foi o **MVC (Model View Controller)**.

### 📋 Pré-requisitos

Criar uma rotina na aplicação que fará a buscar das informações na API Taxa de Juros Mensal e realize a inserção na base de dados (processo de onboarding). Após concluir o processo de onboarding, escrever para cada verbo HTTP (GET, POST, PUT e DELETE) as suas respectivas operações CRUD (consultar por id, consultar tudo, salvar, deletar e atualizar). Crie mais três endpoints conforme especificações abaixo:

1. ConsultartaxadejurosporID.
2. Listartaxadejuroscompaginação.
3. ListardetaxadejurosporanoMes.


## 🛠️ Construído com:

- [JAVA](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) - Java JDK 11
- [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - SpringBoot
- [MySql](https://www.mysql.com/downloads/) - Banco de dados (MySQL)
- [Git](https://git-scm.com) - Git
- [Lombok](https://projectlombok.org) - Lombok
- [Jacoco](https://www.jacoco.org) - Jacoco
- [Open Feign](http://cloud.spring.io/spring-cloud-openfeign/reference/html/) - Open Feign
- [MapStruct](https://mapstruct.org) - MapStruct
- [JUnit 5](https://junit.org) - JUnit 5
- [Feign Mock](https://mvnrepository.com/artifact/io.github.openfeign/feign-mock/12.2) - Feign Mock

### 🔧 Preparando o ambiente:

Criar projeto **Java 11 com Maven**, adicionar as dependências  básicas como **Spring Web e JPA**.

Configurando **application.properties** para conexão ao **MySql** e trabalhar com o **Jpa** com nosso banco de dados já criado.

```
spring.datasource.url=jdbc:mysql://localhost:3306/desafio?useSSL=false&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=****
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect


#SQL.
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=update
   
```
Em seguida foram adicionadas algumas dependências de acordo com o que era necessário. Fica da seguinte forma:

pom.xml
```

	<dependencies>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.mapstruct</groupId>
			<artifactId>mapstruct</artifactId>
			<version>${org.mapstruct.version}</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-openfeign</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
```

## 📦 Implementação

As classes Entity **(TaxaMensal)** e DTO **(TaxaMensalDTO)**
foram criadas com modelo no objeto Json a ser consumido e foram mapeadas com **MapStruct**!
Em seguida  criamos a classe **TaxaServiceMensal** e o controller **TaxaMensalController** para 
organizar a lógica de negócio e as requisições Http.

#### Link da API de Taxa de juros: 
https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$top=100&$format=json

Para consumir os dados usamos **Feign Client**, criando uma interface e adicionando a configuração na classe principal do projeto.


DesafioApplication:
```java
@EnableFeignClients
@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class DesafioApplication  {
    public static void main(String[] args) {
        SpringApplication.run(DesafioApplication.class, args);
    }

}
```
Interface: 
```java
@FeignClient(name = "TaxaMensal", url = "https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$top=100&$format=json")
public interface TaxaMensalClient {
    @GetMapping()
    RootDTO getAllApi();
}
```
A injeção de dependência com Feign é adicionada a classe TaxaMensalController.
O metodo do controller faz a integração:

```java
import br.com.desafio.client.TaxaMensalClient;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class TaxaMensalController {

 @Autowired
 private TaxaMensalClient client;

 @GetMapping

 public List<TaxaMensalDTO> getAllApi() {
  return client.getAllApi().value;
 }
}
```
Abaixo temos um exemplo de retorno:

<img width="720" alt="Captura de Tela 2023-03-06 às 10 33 23" src="https://media.github.ibm.com/user/419821/files/95851dbe-fbf8-4fc1-9a8b-fcc6c1958602">

Nesse ponto temos a aplicação preparada para salvar os dados, entao a lógica é criada no service e chamada no controller.
O endpoint do controller se encarrega de fazer o processo.
```java
 @PostMapping("/onbording")
    @ResponseBody
    public ResponseEntity salveApiTaxaMensal(@RequestBody @Valid List<TaxaMensalDTO> taxaMensalDTO){
        return ResponseEntity.ok(service.saveApi(taxaMensalDTO));
    }
```
Após o processo, os dados são manipulados para fazer o processo de CRUD e realizar requisições através dos endpoints.

#### EndPoints:

Retorna dados da Api externa:

````
GET - localhost:8080/api 
````
Salva dados da Api externa:

````
POST - localhost:8080/api/onbording
````

Retorna dados salvos no Banco de dados:
````
    GET - localhost:8080/api/taxas 
````
Retorna lista com paginação:
````
    GET - localhost:8080/api/pages
````
Paginação com parametros para indice e tamanho:

````
    GET - localhost:8080/api/pages?page=0&size=20
````

Retorna dados pelo AnoMes definido na requisição:
````
    GET - localhost:8080/api/anomes/{anoMes} 
````
Retorna dados pelo Id definido na requisição:
````
    GET - localhost:8080/api/taxa/{id} 
````

Salva uma nova Taxa Mensal:
````
    POST - localhost:8080/api/salvar 
````
Deleta uma Taxa Mensal:
````
    DELETE - localhost:8080/api/taxa/{id}
````

Atualiza uma Taxa Mensal:
````
    PUT - localhost:8080/api/taxa/{id}
````


## ⚙️ Executando os testes

Os testes foram executados como testes unitários e testes de integração.
Para isso adicionamos mais dependências ao projeto!

```
       <dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>io.github.openfeign</groupId>
		<artifactId>feign-mock</artifactId>
		<version>11.0</version>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>io.github.openfeign</groupId>
		<artifactId>feign-jackson</artifactId>
		<version>12.2</version>
	</dependency>
```

Adicionamos JUnit 5, MockMvc e FeignMock.
Para testes unitários e testes de integração, no geral, usamos Mockito e MockMVC.
Já no caso do Feign Client, criamos uma classe onde trabalhamos com Feign Mock na execução dos testes.

```java
public class TaxaMensalClientTest {

    @Autowired
    private TaxaMensalClient client;

    private void builderFeignClient(MockClient mockClient){
        client = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(TaxaMensalClient.class, BASE_URL);
    }

    @Test
    public void deveRetornarListaParaOnbording(){

        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                BASE_URL,
                RESPONSE
        ));

        List<TaxaMensalDTO> taxaMensalEntity = client.getAllApi().value;

        assertThat(taxaMensalEntity).isNotEmpty();
        assertThat(taxaMensalEntity).hasSize(1);
    }
    
}
```

Foram criados ojetos estáticos na pasta common para auxiliar nos testes. 

### ⌨️ Mais exemplos:

Teste unitário com classe service:

```java
 @Test
    public void deveAtualizarTaxaMensalExistente(){

        when(repo.findById(1L)).thenReturn(Optional.of(TAXA_MENSAL));
        when(repo.save(TAXA_MENSAL)).thenReturn(TAXA_MENSAL);

        TaxaMensalDTO dto = taxaMensalService.update(TAXA_MENSAL,1L);
        TaxaMensal entity = TaxaMensalMapper.INSTANCE.dtoToEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity).isEqualTo(TAXA_MENSAL);

        verify(repo, times(1)).findById(anyLong());
        verify(repo, times(1)).save(any(TaxaMensal.class));

    }
```
Teste de integração com classe controller:
```java
     @Test
public void deveSalvarDaApiExternaComDadosValidosRetornandoOk() throws Exception{

        when(taxaMensalService.saveApi(listTaxaMensalDTO())).thenReturn(listTaxaMensal());

        mockMvc.perform(post("/api/onbording")
        .content(objectMapper.writeValueAsString(listTaxaMensalDTO()))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));

        verify(taxaMensalService, times(1)).saveApi(listTaxaMensalDTO());
        }
```

Para verificar a porcentagem de cobertura dos nossos testes, utilizamos **Jacoco**.

Plugin Jacoco:

```
                       <plugin>
				<groupId>org.jacoco</groupId>
				<artifactId>jacoco-maven-plugin</artifactId>
				<configuration>
					<excludes>
						<exclude>com/ibm/desafio/DesafioApplication.class</exclude>
						<exclude>com/ibm/desafio/handler/RestExceptionHandler.class</exclude>
						<exclude>com/ibm/desafio/entities/dto/*</exclude>
						<exclude>com/ibm/desafio/entities/*</exclude>
						<exclude>com/ibm/desafio/exception/*</exclude>
					</excludes>
				</configuration>
				<executions>
					<execution>
						<goals>
							<goal>prepare-agent</goal>
						</goals>
					</execution>

					<execution>
						<id>report</id>
						<phase>prepare-package</phase>
						<goals>
							<goal>report</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
```

Utilizamos o comando abaixo para criar nosso arquivo jacoco.exec:

```
mvn clean test
```
Esse comando gera o nosso relatório:

```
mvn clean test jacoco:report
```

Com isso podemos acessar a pasta **target/site**, clicar em index.html e ver a cobertura de código.

<img width="1031" alt="Captura de Tela 2023-03-05 às 16 41 24" src="https://media.github.ibm.com/user/419821/files/d4b380c3-2f43-4673-be66-5b493ffb4f6c">

## ✒️ Autor

<p align="left">
	<img src="https://media.github.ibm.com/user/419821/files/1057a49c-5ccd-4450-a0bf-b84b792c231d" width=80px height=80px>
</p>

Desenvolvido por **Uallace Gomes da Silva**.

---
⌨️ com ❤️ * **Uallace** - [Github](https://github.ibm.com/uallace-gomes-silva)
😊
