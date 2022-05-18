# ONE Microsserviço Modelo

Projeto base para a criação de microsserviços, que segue um modelo de arquitetura hexagonal (ports and adapters).

# Estrutura do microsserviço

O projeto segue o esquema de multi-módulos do maven. E está divido em 5 módulos:
- **adapters**
    - Responśavel por isolar frameworks, banco de dados e outros detalhes de implementação do coração da aplicação (**core**).
    - Neste módulo há as implementações das portas de entrada e saída (inbound e outbound) definidas no módulo **core**: business port, db port, queue port etc.
- **client**
    - Responsável por expor clients do microsserviço atual, para que outros microsserviços possam se comunicar.
    - O client implementa os contratos (interfaces) definidos no módulo **contract**, a fim de evitar que outros microsserviços dupliquem código e tenham a responsabilidade de implementar os clients e DTO's de request e response.
- **contract**
    - Responsável por definir os contratos (interfaces) de como outros microsserviços poderão se comunicar com o microsserviço atual.
- **core**
    - Responsável por manter todo o coração da aplicação.
    - É agnóstico a frameworks, libs (com exceção do lombok), banco de dados, serviços de mensageria etc. Sua característica é manter o coração da aplicação isolado destes detalhes de implementação e evitar dependências.
    - Possui dois pacotes principais:
        - application:
            - responsável por implementar as regras de negócio da aplicação.
        - domain:
            - responsável por implementar as regras de entidade da aplicação.
- **covarage-report**
    - Responsável por gerar e agregar os relatórios de teste do Jacoco, referente aos módulos: **adapters** e **core**

```
📦oneos-ms-base
 ┣ 📂adapters
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂br
 ┃ ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂oneos
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 Adapter que implementa as portas de entrada de negócio (business inbound) definidas no módulo core
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleController.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂db
 Adapter que implementa as portas de saída de banco de dados (db outbound) definidas no módulo core
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂model
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PersonSpringDataJPARepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleModel.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PersonRepositoryPortImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mapper
 Classe utilitária para facilitar o mapeamento entre as entidades de domínio, DTO's e modelos de persistência
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleMapper.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Application.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┃ ┣ 📂db
 Diretório que comtenpla o arquivo de configuração do changelog do liquibase e scripts de banco de dados
 ┃ ┃ ┃ ┃ ┃ ┗ 📂changelog
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂scripts
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜V001_create_person_table.sql
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜V002_create_person_auditing_table.sql
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜master.xml
 ┃ ┃ ┃ ┃ ┗ 📜application.yml
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂br
 ┃ ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂oneos
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜BaseIT.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleControllerIT.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂mock
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MockExample.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MockUUID.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┃ ┃ ┗ 📜application-test.yml
 ┃ ┗ 📜pom.xml
 ┣ 📂client
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂br
 ┃ ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂oneos
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂client
 Client REST que estende a interface de contrato definida no módulo (**contract**) e exponhe os endpoints disponíveis para que outros microsserviços possam se comunicar, através de um Feign Client
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleContractClient.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┗ 📜pom.xml
 ┣ 📂contract
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂br
 ┃ ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂oneos
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂api
 Contrato (interface) que define quais endpoints estão disponíveis para serem expostos via client ou REST Controller.
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleApiContract.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂config
 Classe de condiguração para documentação dos endpoints e DTO's de request e response via swagger
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜SwaggerConfig.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ExampleRequestDTO.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleResponseDTO.java
 ┃ ┃ ┃ ┗ 📂resources
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┗ 📜pom.xml
 ┣ 📂core
 ┃ ┣ 📂src
 ┃ ┃ ┣ 📂main
 ┃ ┃ ┃ ┣ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂br
 ┃ ┃ ┃ ┃ ┃ ┗ 📂com
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂oneos
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂application
 Pacote que contém todas as implementações da aplicação e suas respectivas regras de negócio e validações
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂business
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleServicePortImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂port
 Pacote responsável por definir todas as portas de entrada e saída: db ports, service ports, queue ports etc
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂db
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleRepositoryPort.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂in
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleServicePort.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂out
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 Pacote que contém todas as implementações das entidades e suas respectivas regras de negócio e validações
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Example.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂messages
 Contempla o mapeamento de todas as mensagems (code e key) de regra de negócio de entidade e aplicação que serão lançadas nas exceções
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Messages.java
 ┃ ┃ ┃ ┗ 📂resources
 Arquivo que contém, de fato, as mensagens (key e value) que serão lançadas nas exceções
 ┃ ┃ ┃ ┃ ┗ 📜messages.properties
 ┃ ┃ ┗ 📂test
 ┃ ┃ ┃ ┗ 📂java
 ┃ ┃ ┃ ┃ ┗ 📂poc
 ┃ ┃ ┃ ┃ ┃ ┗ 📂ms
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂hexagonal
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂mock
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MockExample.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📂service
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ExampleServicePortTest.java
 ┃ ┗ 📜pom.xml
 ┣ 📂coverage-report
 Agregador de relatórios swagger, referente aos módulos: **core** e **adapters**
 ┃ ┗ 📜pom.xml
 ┣ 📜.gitignore
 ┣ 📜Dockerfile
 ┗ 📜pom.xml
 
```

# Montagem de ambiente
## Pré-Requisitos

Para a montagem de ambiente é necessário:

- Java
- Maven
- Git Instalado
- Acesso Gitlab: https://gitlab.com/oneosteam
- Configurar ambiente para projeto MAVEN.
- Docker e Docker-compose

## Stack APP

Principais frameworks e libs que compõem o microsserviço e suas respectivas versões.

| Framework/dependência   | Descritivo                                             | Versão      |
|-------------------------|--------------------------------------------------------|-------------|
| Spring Boot             | Base da aplicação                                      | 2.6.7       |
| Java                    | Codificação base do app                                | 17          |
| Maven                   | Gerenciador de dependências                            | 3.8.1       |
| Maven                   | Gestor de dependências                                 | 3           |
| OpenFeign               | Usado para integração Rest do app                      | 2.6.7       |
| Lombok                  | Produtividade e redução de código                      | 1.18.24     |
| Open API 3              | Documentação dos enpoints e exportação de API          | 1.6.8       |
| MapStruct               | Conversões de interfaces de comunicação entre sistemas | 1.4.2.Final |
| Jacoco                  | Agent para verificar a cobertura de código testado     | 0.8.7       |
| JUnit                   | Framework de teste                                     | 5.8.2       |
| H2                      | Banco de dados em memória para teste                   | 2.0.20      |
| Liquibase               | Lib para gerenciamento de scripts de banco de dados    | 4.5.0       |

## Build da aplicação e testes de integração

Com o maven devidamente instalado e configurado, execute:

```
$ mvn clean install
```

## Swagger Open API

A documentação da API se encontra no endereço:

```
- http://{host}:{port}/poc-ms-hexagonal/api/v1/swagger-ui/documentation.html
```

## Jacoco (Cobertura de Testes)

Para acessar o relatório de cobertura de testes é necessário executar:

```
$ mvn verify
```

Após a execução do comando, um relatório HTML será gerando no diretório: dentro da pasta ```../oneos-ms-base/coverage-report/target/site/jacoco-aggregate/index.html ```
Nesta página HTML é possível verificar o percentual de cobertura realizado no desenvolvimento.

# Versões

| Versão | Descrição           | Solicitante                  | Data         |
|:-------|:--------------------|:-----------------------------|:-------------|
| 1.0.0  | Estrutura inicial   | Bruno Chiaroni               | 18/05/2022   |
