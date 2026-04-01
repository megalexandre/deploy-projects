# 🧪 Guia para Executar os Testes com Sucesso

Este projeto utiliza **Testcontainers** para executar testes de integração, o que requer acesso ao Docker.

## 🎯 Solução Rápida

### Opção 1: Script Automático (Recomendado)

```bash
# Execute o script que verifica tudo automaticamente
./run-tests.sh
```

### Opção 2: Comando Gradle Direto

```bash
# Limpar e executar testes
./gradlew clean test

# Ver relatório dos testes
# Abra: build/reports/tests/test/index.html no navegador
```

## 🐳 Pré-requisitos

### 1. Docker deve estar rodando

Verifique se o Docker está acessível:

```bash
docker ps
```

Se você receber um erro de permissão, aplique uma das correções abaixo:

#### ⚡ Correção Rápida (Temporária)

```bash
sudo chmod 666 /var/run/docker.sock
```

**Nota:** Esta correção é perdida após reiniciar o computador.

#### ✅ Correção Permanente (Recomendada)

```bash
# 1. Adicione seu usuário ao grupo docker
sudo usermod -aG docker $USER

# 2. Aplique as mudanças (escolha UMA opção):

# Opção A: No terminal atual
newgrp docker

# Opção B: Faça logout e login novamente

# Opção C: Reinicie o computador

# 3. Verifique se funcionou
docker ps
```

### 2. Java 21 deve estar instalado

```bash
# Verificar versão do Java
java -version

# Deve mostrar Java 21 ou superior
```

## 📋 Estrutura dos Testes

O projeto contém:

- **Testes de Integração**: Usam Testcontainers com PostgreSQL
- **Testes BDD/Cucumber**: Cenários em formato Gherkin (arquivos `.feature`)

### Executar testes específicos

```bash
# Executar apenas os testes Cucumber
./gradlew test --tests CucumberTest

# Executar apenas testes de integração
./gradlew test --tests TestcontainersIntegrationTest

# Executar testes com mais detalhes
./gradlew test --info

# Executar testes com stacktrace completo
./gradlew test --stacktrace
```

## 🔍 Verificação de Problemas Comuns

### Problema: "Cannot connect to the Docker daemon"

**Causa:** Docker não está rodando ou sem permissão de acesso.

**Solução:**
```bash
# Verificar se Docker está rodando
systemctl status docker

# Se não estiver, iniciar Docker
sudo systemctl start docker

# Aplicar correção de permissão (veja seção acima)
```

### Problema: "Image not found: postgres:18-alpine"

**Causa:** Imagem Docker do PostgreSQL não está baixada.

**Solução:**
```bash
# Baixar a imagem manualmente
docker pull postgres:18-alpine
```

### Problema: "Address already in use"

**Causa:** Outra instância do PostgreSQL está rodando na porta 5432.

**Solução:**
```bash
# Verificar o que está usando a porta
sudo lsof -i :5432

# Parar containers PostgreSQL existentes
docker ps | grep postgres
docker stop <container-id>
```

## 📊 Relatórios de Teste

Após executar os testes, você pode visualizar os relatórios:

### Relatório JUnit/Gradle

```bash
# Abrir no navegador
xdg-open build/reports/tests/test/index.html

# Ou navegue manualmente para:
# build/reports/tests/test/index.html
```

### Relatório Cucumber

```bash
# Abrir no navegador
xdg-open target/cucumber-report.html

# Ou navegue manualmente para:
# target/cucumber-report.html
```

## 🧹 Limpeza

```bash
# Limpar build anterior
./gradlew clean

# Parar todos os containers testcontainers
docker ps -a | grep testcontainers | awk '{print $1}' | xargs docker rm -f
```

## 🎯 Melhores Práticas

1. **Execute `./gradlew clean` antes dos testes** se houver problemas de compilação
2. **Certifique-se de que o Docker está rodando** antes de executar os testes
3. **Use container reuse** (já configurado em `testcontainers.properties`) para testes mais rápidos
4. **Verifique os logs** em caso de falha: `build/reports/tests/test/index.html`

## 🚀 Execução Alternativa (Sem Testcontainers)

Se você tiver problemas com Testcontainers, pode executar os testes contra um banco PostgreSQL real:

```bash
# Usar o script alternativo
./run-tests-with-compose.sh
```

Este script:
1. Inicia um container PostgreSQL usando Docker Compose
2. Executa os testes contra este banco
3. Limpa os recursos após os testes

## ❓ Precisa de Ajuda?

1. Verifique o arquivo `DOCKER_FIX.md` para problemas de permissão do Docker
2. Consulte os logs em `build/reports/tests/test/index.html`
3. Execute com `--info` ou `--stacktrace` para mais detalhes:
   ```bash
   ./gradlew test --info --stacktrace
   ```

## ✨ Exemplo de Execução Bem-Sucedida

```
$ ./gradlew test

> Task :test

CucumberTest > Scenario: Create a address with all fields PASSED
CucumberTest > Scenario: Successful login with valid credentials PASSED
...

BUILD SUCCESSFUL in 45s
5 actionable tasks: 5 executed
```

🎉 **Parabéns! Seus testes estão funcionando!**

