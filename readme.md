# Sistema Gestão ONG de animais - Backend API

API REST desenvolvida para a **ONG Pedacinho do Céu**, um sistema de gestão focado em descentralizar informações e apoiar as operações de uma ONG de proteção animal. O objetivo é facilitar o acesso a informações para todos os voluntários que fazem parte da ONG.

## Funcionalidades Implementadas

*   **Arquitetura Limpa:** Estrutura baseada em camadas (Controller, Service, Repository, DTO e Model) para garantir manutenção facilitada e escalabilidade.
*   **Gestão de Domínios:** Endpoints RESTful completos (CRUD) para Animais, Tutores, Usuários, Adoções e Prontuários Médicos.
*   **Segurança e Autenticação:** Implementação do Spring Security e tokens JWT.
*   **Criptografia e Perfis:** Senhas protegidas no banco de dados utilizando Hash BCrypt. O sistema roles para cada perfil de usuario `ROLE_ADMIN` (acesso total de leitura e escrita) e `ROLE_USER` (acesso apenas de leitura).
*   **Documentação:** Interface Swagger/OpenAPI interativa e configurada com suporte ao esquema de segurança `bearerAuth`, permitindo injetar e testar tokens JWT diretamente pelo navegador.
*   **Testes Automatizados:** Suíte de testes de integração robusta utilizando banco em memória H2. Os testes simulam contextos de segurança utilizando a anotação `@WithMockUser` para validar as regras de permissão em todas as rotas da aplicação.
*   **Integração Contínua (CI/CD):** A pipeline executa a compilação e valida todos os testes integrados automaticamente a cada integração na branch principal.

## Tecnologias Utilizadas
*   **Java 21**
*   **Spring Boot** (Web, Data JPA, Validation, Security)
*   **PostgreSQL** (Supabase)
*   **H2 Database** (para isolamento de testes)
*   **Swagger / OpenAPI**
*   **Maven Wrapper**
*   **GitHub Actions**

## Como executar o projeto localmente

1. Clone este repositório para a sua máquina.
2. Na raiz do projeto backend, crie um arquivo chamado `.env` e adicione suas variáveis de ambiente do banco de dados:
   ```env
   SUPABASE_URL=jdbc:postgresql://[SUA_URL_DO_SUPABASE]
   SUPABASE_USER=[SEU_USUARIO_POSTGRES]
   SUPABASE_PASSWORD=[SUA_SENHA]
   JWT_KEY=[SUA_CHAVE_SECRETA_DO_TOKEN]
   ```

3. Execute a aplicação via linha de comando utilizando o Maven Wrapper:
   ```bash
    .\mvnw spring-boot:run
   ```
   
4. Com a aplicação rodando, acesse a interface interativa do Swagger no seu navegador:

   http://localhost:8080/swagger-ui/index.html

## Como executar os testes

A aplicação conta com uma suíte de testes de integração que validam a comunicação entre as camadas (Controller ➔ Service ➔ Repository ➔ Banco). Para garantir a segurança dos dados de produção, os testes rodam de forma totalmente isolada em um banco de dados em memória (H2)

1. Como rodar os testes: 
   ```bash
   .\mvnw test
   ```

2. Para rodar os testes de uma classe específica de forma isolada:
   ```bash
   .\mvnw test -Dtest=UsuarioControllerTest
   ```