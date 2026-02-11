# Autenticação JWT - Deploy Board

## Configuração Implementada

Sistema de autenticação JWT completo foi implementado com os seguintes componentes:

### Componentes Criados

1. **JwtService** - Serviço para gerar e validar tokens JWT
2. **JwtAuthenticationFilter** - Filtro que intercepta requisições e valida tokens
3. **AuthController** - Controller com endpoint de login
4. **LoginRequest/LoginResponse** - DTOs para autenticação

- Adicionar roles e permissões granulares
- Implementar rate limiting
- Adicionar endpoint de logout
- Implementar refresh token

### Próximos Passos

- ✅ Filtro JWT validando todas as requisições protegidas
- ✅ CSRF desabilitado (adequado para APIs REST)
- ✅ Sessões stateless (não mantém estado no servidor)
- ✅ Tokens JWT com expiração de 24 horas
- ✅ Senhas são criptografadas com BCrypt

### Segurança

7. Se válido, permite acesso ao endpoint
6. JwtAuthenticationFilter valida o token
5. Cliente envia token em todas as requisições subsequentes
4. Cliente recebe token
3. Servidor gera token JWT
2. Servidor valida credenciais
1. Cliente faz login com email/password

### Fluxo de Autenticação

```
runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
implementation("io.jsonwebtoken:jjwt-api:0.12.3")
// JWT
```kotlin

### Dependências Adicionadas

**⚠️ IMPORTANTE:** Em produção, defina a variável de ambiente `JWT_SECRET` com uma chave secreta forte!

```
  expiration: ${JWT_EXPIRATION:86400000} # 24 horas em milissegundos
  secret: ${JWT_SECRET:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}
jwt:
```yaml

As configurações do JWT estão no `application.yaml`:

### Configuração

```
  -H "Authorization: Bearer {seu-token-aqui}"
curl -X GET http://localhost:8080/users \
```bash

Para acessar endpoints protegidos, inclua o token no header `Authorization`:

#### 3. Usar o Token nas Requisições

```
}
  "expiresIn": 86400000
  "type": "Bearer",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
{
```json
Resposta:

```
  }'
    "password": "senha123"
    "email": "joao@example.com",
  -d '{
  -H "Content-Type: application/json" \
curl -X POST http://localhost:8080/auth/login \
```bash

#### 2. Fazer Login

```
  }'
    "profile": "ADMIN"
    "password": "senha123",
    "email": "joao@example.com",
    "name": "João Silva",
  -d '{
  -H "Content-Type: application/json" \
curl -X POST http://localhost:8080/users \
```bash

#### 1. Criar um usuário

### Como Usar

- `/error/**` - Endpoints de erro
- `/actuator/**` - Endpoints de monitoramento
- `POST /auth/register` - Registrar usuário (placeholder)
- `POST /auth/login` - Fazer login
- `POST /users` - Criar usuário

### Endpoints Públicos (não requerem autenticação)
