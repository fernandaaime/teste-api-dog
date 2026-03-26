# Teste API - Dog API

Automacao de testes de API para a Dog API, utilizando RestAssured + TestNG + Allure Reports + Java.

---

## Sumario

- [Visao Geral](#visao-geral)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Pre-requisitos](#pre-requisitos)
- [Instalacao](#instalacao)
- [Executando os Testes](#executando-os-testes)
- [Relatorio de Resultados](#relatorio-de-resultados)
- [Decisoes Tecnicas](#decisoes-tecnicas)
- [Pipeline CI/CD](#pipeline-cicd)

---

## Visao Geral

Este projeto valida a integracao com a Dog API, garantindo que os endpoints estejam respondendo corretamente, que os dados retornados estejam no formato esperado e que a aplicacao se comporte conforme o esperado em diferentes cenarios.

### Endpoints Testados

| ID   | Metodo | Endpoint                  | Descricao                        |
|------|--------|---------------------------|----------------------------------|
| CT01 | GET    | /breeds/list/all          | Lista todas as racas de caes     |
| CT02 | GET    | /breed/{breed}/images     | Retorna imagens de uma raca      |
| CT03 | GET    | /breeds/image/random      | Retorna uma imagem aleatoria     |

### Validacoes por Endpoint

**CT01 - /breeds/list/all**
- Status HTTP 200
- Campo `status` igual a `"success"`
- Lista de racas nao nula e nao vazia

**CT02 - /breed/{breed}/images**
- Status HTTP 200
- Campo `status` igual a `"success"`
- Lista de imagens nao vazia
- URLs terminam em `.jpg` ou `.jpeg`

**CT03 - /breeds/image/random**
- Status HTTP 200
- Campo `status` igual a `"success"`
- URL nao nula
- URL comeca com `https://`

---

## Estrutura do Projeto
```
teste-api-dog/
├── .github/
│   └── workflows/
│       └── ci.yml              # Pipeline GitHub Actions
├── src/
│   └── test/
│       └── java/
│           └── com/
│               └── dog/
│                   └── DogApiTest.java
├── pom.xml                     # Dependencias e configuracoes Maven
└── README.md
```

---

## Pre-requisitos

| Ferramenta | Versao minima | Download                                     |
|------------|---------------|----------------------------------------------|
| Java JDK   | 17            | [adoptium.net](https://adoptium.net)         |
| Maven      | 3.9.x         | [maven.apache.org](https://maven.apache.org) |

---

## Instalacao

**1. Clone o repositorio:**
```bash
git clone https://github.com/fernandaaime/teste-api-dog.git
cd teste-api-dog
```

**2. Verifique o Java:**
```bash
java -version
# Esperado: openjdk version "17.x.x"
```

**3. Verifique o Maven:**
```bash
mvn -version
# Esperado: Apache Maven 3.9.x
```

**4. Baixe as dependencias:**
```bash
mvn dependency:resolve
```

> 💡 Nao e necessaria nenhuma configuracao adicional. A Dog API e publica e nao requer autenticacao.

---

## Executando os Testes

**Executar todos os testes:**
```bash
mvn test
```

**Executar um teste especifico:**
```bash
mvn test -Dtest=DogApiTest#deveRetornarListaDeTodasAsRacas
```

**Executar com log detalhado de request/response:**
```bash
mvn test -X
```

---

## Relatorio de Resultados

### Relatorio Allure (recomendado)

O Allure gera um relatorio visual e detalhado com request, response e status de cada teste.

**Gerar e abrir o relatorio:**
```bash
mvn test
mvn allure:serve
```

O relatorio sera aberto automaticamente no navegador com:
- Status de cada teste (passou/falhou)
- Tempo de execucao
- Request completo (metodo, URL, headers, body)
- Response completo (status code, headers, body JSON)
- Detalhes do erro em caso de falha

**Gerar relatorio estatico:**
```bash
mvn allure:report
# Abra: target/site/allure-maven-plugin/index.html
```

### Relatorio Surefire (basico)
```
target/surefire-reports/
├── DogApiTest.txt
└── TEST-com.dog.DogApiTest.xml
```

---

## Decisoes Tecnicas

**RestAssured**
Biblioteca padrao do mercado para testes de API REST em Java. 

**TestNG**
Escolhido por permitir separar cada endpoint em um `@Test` individual. Se um endpoint falhar, os outros continuam sendo testados e o relatorio mostra exatamente qual falhou.

**Allure Reports**
Gera relatorios visuais e detalhados com todas as informacoes da requisicao e resposta, atendendo ao requisito de relatorio com falhas e sucessos detalhados.

**Arquitetura dos testes**
- `@BeforeClass` configura a `baseURI` uma unica vez
- Cada `@Test` e independente e pode ser executado isoladamente
- Anotacoes `@Epic`, `@Feature`, `@Story` organizam os testes no relatorio Allure

---

## Pipeline CI/CD

O projeto possui pipeline configurada no GitHub Actions que executa automaticamente os testes a cada `push` ou `pull request` na branch `main`.


Acesse os resultados em:
https://github.com/fernandaaime/teste-api-dog/actions