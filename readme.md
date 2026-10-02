# API - Pedacinho do céu (EM DESENVOLVIMENTO)

Esta é a API REST em desenvolvimento para o **Sistema Pedacinho do Céu**, um sistema de gestão para apoiar as operações de uma ONG de proteção animal. O objetivo é facilitar o acesso a informações para todos os voluntários que fazem parte da ONG.

## Funcionalidades Implementadas (Fase Atual)

*   **Integração com Banco de Dados:** Conexão com PostgreSQL hospedado no Supabase utilizando Spring Data JPA (Hibernate).
*   **Arquitetura Limpa:** Projeto organizado em arquitetura de camadas (Controller, Service, Repository, DTO e Model).
*   **Gestão de Domínios:** Endpoints (CRUDs) completos para o gerenciamento de Animais, Tutores, Usuários, Adoções e Prontuários Médicos.
*   **Documentação Swagger:** Documentação detalhando schemas, DTOs e todos os possíveis códigos HTTP de resposta para facilitar a integração e entedimento da API.
*   **Testes Automatizados:** Testes de integração desenvolvidos e isolados utilizando um banco em memória (H2), garantindo a estabilidade em pipelines de **CI/CD no GitHub** Actions.

## Tecnologias Utilizadas
*   **Java 21**
*   **Spring Boot** (Web, Data JPA, Validation, Security JWT)
*   **PostgreSQL & Supabase**
*   **H2 Database** (para isolamento de testes)
*   **Swagger / OpenAPI**
*   **Maven Wrapper**

## Como executar o projeto localmente

1. Clone este repositório para a sua máquina.
2. Na raiz do projeto backend, crie um arquivo chamado `.env` e adicione suas variáveis de ambiente do banco de dados:
   ```env
   SUPABASE_URL=jdbc:postgresql://[SUA_URL_DO_SUPABASE]
   SUPABASE_USER=[SEU_USUARIO_POSTGRES]
   SUPABASE_PASSWORD=[SUA_SENHA]
   ```

3. Execute a aplicação via linha de comando utilizando o Maven Wrapper:
   ```bash
    .\mvnw spring-boot:run
   ```
   
4. Com a aplicação rodando, acesse a interface interativa do Swagger no seu navegador:
    ```link
   http://localhost:8080/swagger-ui/index.html
   ```