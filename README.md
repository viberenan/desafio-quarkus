# desafio-quarkus

Este projeto foi desenvolvido em quarkus com banco de dados relacional H2

## Rodando a aplicação em desenvolvimento:

comando para rodar o projeto em desenvolvimento
```shell script
./mvnw compile quarkus:dev
```


## Docker:

### Se rodar em docker local execute os seguintes comandos na pasta do projeto e na ordem aqui apresentada: 

```shell script
./mvnw package`
```
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t viberenan/desafio-quarkus .
```
```shell script
docker run -i --rm -p 8080:8080 viberenan/desafio-quarkus
```
> Nota: necessário Path do JAVA configurado para executar o primeiro comando 


### Se rodar pelo dockerhub execute em ordem os seguintes comandos: 
```shell script
docker pull viberenan/desafio-quarkus:latest
```
```shell script
docker run -p 8080:8080 viberenan/desafio-quarkus:latest
```


## LINKS

### Metrics:
http://localhost:8080/q/metrics/application



