# InfoQuik | Eurofarma

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge) <br>
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
 
## Descrição
 
Projeto desenvolvido para o Challenge FIAP 2024 em parceria com a empresa Eurofarma. O projeto consiste em um aplicação de gerenciamento de treinamentos, com funcionalidades como: criação e atualização de treinamentos, registro de presença, e cadastro e login de usuários.
 
## Técnologias Utilizadas
 
- `HTML`
- `CSS`
- `Bootstrap`
- `JavaScript`
- `Java`
- `SpringBoot`
- `Spring Validation`
- `Spring Security`
- `Maven`
- `Lombok`
- `H2`
- `JWT`
 
## Endpoints
 
Endpoint `/treinamentos`
 
> Listar todos treinamentos:
> ``` http
> GET http://localhost:8080/treinamentos
> ```
> Listar treinamento por id:
> ``` http
> GET http://localhost:8080/treinamentos/{id}
> ```
> Criar treinamento:
> ``` http
> POST http://localhost:8080/treinamentos
> ```
> Alterar treinamento:
> ``` http
> PUT http://localhost:8080/treinamentos/{id}
> ```
> Deletar treinamento:
> ``` http
> DELETE http://localhost:8080/treinamentos/{id}
> ```
___
 
Endpoint `/tags`
 
> Listar todas as tags:
> ``` http
> GET http://localhost:8080/tags
> ```
> Listar tag por id:
> ``` http
> GET http://localhost:8080/tags/{id}
> ```
> Criar tag:
> ``` http
> POST http://localhost:8080/tags
> ```
> Alterar tag:
> ``` http
> PUT http://localhost:8080/tags/{id}
> ```
> Deletar tag:
> ``` http
> DELETE http://localhost:8080/tags/{id}
> ```
> 
___
 
Endpoint `/treinadores`
 
> Listar todas os treinadores:
> ``` http
> GET http://localhost:8080/treinadores
> ```
> Listar treinador por id:
> ``` http
> GET http://localhost:8080/treinadores/{id}
> ```
> Criar treinador:
> ``` http
> POST http://localhost:8080/treinadores
> ```
> Alterar treinador:
> ``` http
> PUT http://localhost:8080/treinadores/{id}
> ```
> Deletar treinador:
> ``` http
> DELETE http://localhost:8080/treinadores/{id}
> ```
 
___
 
Endpoint `/funcionarios`
 
> Listar todas os funcionarios:
> ``` http
> GET http://localhost:8080/funcionarios
> ```
> Listar funcionario por id:
> ``` http
> GET http://localhost:8080/funcionarios/{id}
> ```
> Criar funcionario:
> ``` http
> POST http://localhost:8080/funcionarios
> ```
> Alterar funcionario:
> ``` http
> PUT http://localhost:8080/funcionarios/{id}
> ```
> Deletar funcionario:
> ``` http
> DELETE http://localhost:8080/funcionarios/{id}
> ```
___
 
Endpoint `/departamentos`
 
> Listar todas os departamentos:
> ``` http
> GET http://localhost:8080/departamentos
> ```
> Listar departamento por id:
> ``` http
> GET http://localhost:8080/departamentos/{id}
> ```
> Criar departamento:
> ``` http
> POST http://localhost:8080/departamentos
> ```
> Alterar departamento:
> ``` http
> PUT http://localhost:8080/departamentos/{id}
> ```
> Deletar departamento:
> ``` http
> DELETE http://localhost:8080/departamentos/{id}
> ```
> 
___
 
endpoint `/auth`
> Login
> ``` http
> POST http://localhost:8080/auth/login
> ```
> Registrar treinador
> ``` http
> POST http://localhost:8080/auth/register
> ```
> Registrar funcionario
> ``` http
> POST http://localhost:8080/auth/register/funcionarios
> ```
