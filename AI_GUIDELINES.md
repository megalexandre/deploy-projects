Objetivo

Este arquivo descreve convenções e preferências para que a IA (ou colaborador) gere código consistente com as expectativas do projeto "deploy-board". Use-o como guia antes de editar/implementar código.

Visão geral do projeto

- Linguagem: Kotlin (JVM)
- Build: Gradle (Kotlin DSL) — arquivo `build.gradle.kts`
- Framework: Spring Boot (3.x), Spring Data JPA, Flyway, Testcontainers, Cucumber
- Estrutura de pacotes: `projects.*` (mantê-la)

Regras gerais de implementação

1. Mantenha os nomes dos pacotes e das classes existentes. Prefira adicionar novos arquivos sob os pacotes já usados (por exemplo `projects.core.*`, `projects.resources.*`, `projects.steps`).
2. Faça mudanças mínimas e localizadas: evite reformatar arquivos não relacionados.
3. Antes de criar novos endpoints ou alterar `application.yaml`, tente reutilizar as propriedades/beans existentes. Não crie um segundo arquivo `application-*.yaml` salvo necessidade explícita.
4. Sempre adicione/atualize testes (unit/integration) quando mudar comportamento público.
5. Ao introduzir dependências, edite `build.gradle.kts`. Prefira bibliotecas populares e estáveis. Depois execute a verificação de erros (ferramenta do repositório) para garantir compilação.

Kotlin / Estilo de código

- Prefira `data class` para DTOs e domínio simples.
- Prefira funções e mapeamentos como extensões: `fun ProjectEntity.toDomain(): Project` e `fun Project.toResponse(): ProjectResponse`.
- Use `val` sempre que possível; `var` apenas para JPA entities ou quando realmente necessário.
- Siga nullability corretamente (use `?` quando campo pode ser nulo).

Persistência

- Entidades JPA: ficar em `projects.resources.persistence`.
- Repositório Spring Data: interface estendendo `JpaRepository<Entity, String>` quando a PK for `String` (ULID/UUID). A implementação do repositório customizado pode viver em `projects.resources`.
- IDs devem ser gerados pela classe central `ID` dentro do pacote `comons` (por exemplo `comons.ID`). Ou seja, em vez de chamar `ULID().nextValue()` ou `UUID.randomUUID()` diretamente em várias classes, chame `comons.ID.generate()` (ou método equivalente). A classe `ID` encapsula a estratégia (ULID por padrão) e torna fácil trocar a estratégia globalmente no futuro.
- Auditoria: inclua `createdAt` e `updatedAt` do tipo `Instant`. Use `@PreUpdate` para atualizar `updatedAt`.
- Migrations Flyway: coloque scripts em `src/main/resources/db/migration/` com padrão `V1__create_projects_table.sql`.

Exemplo DDL para Flyway (arquivo `V1__create_projects_table.sql`):

CREATE TABLE projects (
  id CHAR(26) PRIMARY KEY,
  client_id TEXT NOT NULL,
  utility_company TEXT NOT NULL,
  utility_protocol TEXT NOT NULL,
  customer_class TEXT NOT NULL,
  integrator TEXT NOT NULL,
  modality TEXT NOT NULL,
  framework TEXT NOT NULL,
  dc_protection TEXT,
  system_power DOUBLE PRECISION,
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

API / Controllers

- Controllers em `projects.resources.web` ou `projects.resources`.
- Use DTOs para requests/responses; valide requests com `@Valid` e constraints JSR-380 (`@NotBlank`, `@Size`, etc.).
- Evite retornar entidades JPA diretamente em APIs.

Mapeamentos

- Centralize mapeamentos simples em arquivos com funções de extensão:
  - `ProjectEntity.from(domain: Project): ProjectEntity`
  - `ProjectEntity.toDomain(): Project`
  - `Project.toResponse(): ProjectResponse`

Testes

- Use JUnit 5 + Spring Boot Test para integration tests.
- Use Testcontainers for Postgres quando executar testes de integração que dependam do banco.
- Cucumber: step definitions em `src/test/kotlin/projects/steps/`.
- Forneça factories em `src/test/kotlin/projects/factories/ProjectFactory.kt` para criar domínio e entidade.

Cucumber / Steps

- Coloque steps pequenos e específicos. Exemplo para Given que insere projeto no DB:
  - Desserializar JSON para `Project` com `ObjectMapper`, converter para `ProjectEntity` e salvar com `SpringDataProjectRepository`.
- Capture o `id` salvo em `RestSteps.lastCreatedId` quando necessário.

Boas práticas para PRs e commits

- Mensagens de commit claras (ex: "feat: add flyway migration for projects table").
- Pequenos commits atômicos.

Checklist que a IA deve seguir antes de qualquer edição

- [ ] Encontre arquivos relevantes (model, entity, repository, feature) com `file_search` ou leitura.
- [ ] Proponha mudanças com mínimo impacto (edits locais).
- [ ] Adicione/atualize testes para cobrir o comportamento.
- [ ] Rode a verificação de erros do projeto e corrija problemas.
- [ ] Informe o usuário do que foi alterado e os próximos passos para executar testes.

Procedimentos de verificação automatizados (sugestão)

- Sempre após editar um arquivo: executar `get_errors` para validar mudanças.
- Se alterar código executável: rodar os testes relevantes com Gradle (`./gradlew test`) — opcionalmente em CI.

Notas finais

- Se o usuário disser "não quero outro application!" — não criar novos perfis `application-*.yaml`. Reutilizar `SPRING_PROFILES_ACTIVE` e `application.yaml` existente.
- Se o usuário estiver em modo "não quero docker-compose automático", evite tocar na configuração do Spring Boot Docker Compose no `application.yaml`.
- Pergunte apenas quando for estritamente necessário.

--
Este documento pode ser editado pelo mantenedor para ajustar preferências futuras.
