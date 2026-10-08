CreateList - Microsserviço Enterprise com Clean Architecture & Spring Boot
Projeto desenvolvido com foco em robustez, manutenibilidade e escalabilidade, aplicando os princípios de Clean Architecture (Arquitetura Limpa) 
em conjunto com o ecossistema moderno do Spring Boot em Java.

🛠️ Tecnologias e Ferramentas Utilizadas
Linguagem: Java (versão mais recente)

Framework: Spring Boot (Web, Data JPA, Data MongoDB)

Arquitetura: Clean Architecture (Domain, Use Cases, Application, Infrastructure, Interfaces)

Bancos de Dados:

Relacional (PostgreSQL / H2 para testes) via Spring Data JPA / Hibernate

Não-Relacional (MongoDB) via Spring Data MongoDB

Mapeamento de Objetos: MapStruct (para conversão eficiente entre DTOs, Entidades e Documentos)

Logging: Log4j2 (configurado para logs estruturados e de alta performance)

Build e Versionamento: Maven (gerenciamento de dependências e multi-módulos)

Testes Automatizados: JUnit 5 e Cucumber (para testes orientados a comportamento - BDD)

Arquitetura Limpa (Clean Architecture)
O projeto é estritamente desacoplado em camadas, garantindo que as regras de negócio do domínio fiquem isoladas de detalhes de infraestrutura 
(como frameworks web, bancos de dados ou bibliotecas externas):

src/main/java/edu/dev/createlist/
│
├── domain/                  # Entidades de negócio, regras puras e contratos de repositórios (Ports)
├── application/             # Casos de uso (Use Cases) e lógica de aplicação
├── infrastructure/          # Adaptadores externos: persistência (JPA/Mongo), configs, Log4j2
└── interfaces/              # Controladores REST (Controllers), DTOs de API e Web


Configuração e Execução
Pré-requisitos
Java Development Kit (JDK) instalado.

Maven instalado (ou utilize o wrapper ./mvnw).

Instâncias do PostgreSQL e MongoDB rodando (ou utilize os profiles de teste em memória).

📝 Logging Profissional com Log4j2
O projeto utiliza o Log4j2 para gerenciamento de logs corporativos assíncronos e de alta performance. 
As configurações detalhadas de appenders e níveis de log por pacote podem ser encontradas no arquivo log4j2.xml localizado em src/main/resources.

Testes Automatizados (JUnit e BDD com Cucumber)
A qualidade do código é garantida através de uma suíte de testes robusta:

Testes Unitários e de Integração: Desenvolvidos com JUnit 5 e Spring Boot Test.

Testes de Comportamento (BDD): Cenários descritos em linguagem natural utilizando Cucumber para validar as regras de negócio das listas de desejos (wishlists).

📄 Mapeamento de Objetos com MapStruct
Para evitar código repetitivo (boilerplate) e garantir alta performance em tempo de compilação, o MapStruct é utilizado para mapear os dados entre as camadas de DTOs da API, 
Entidades de Domínio e Modelos de Persistência (JPA/Mongo).









