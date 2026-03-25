# Teste API - Dog API

Automacao de testes de API para a Dog API utilizando RestAssured + TestNG + Java.

## Endpoints Testados

- CT01: GET /breeds/list/all - Lista todas as racas
- CT02: GET /breed/{breed}/images - Retorna imagens de uma raca
- CT03: GET /breeds/image/random - Retorna imagem aleatoria

## Tecnologias

- Java 17
- RestAssured 5.4.0
- TestNG 7.9.0
- Maven 3.9.x

## Pre-requisitos

- Java JDK 17+
- Maven 3.9+

## Como executar
```bash
mvn test
```