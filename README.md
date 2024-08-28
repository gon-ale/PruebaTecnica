# PruebaTecnica
Prueba Tecnica con Boostrap


# Versiones

Java 22

## Installation

Utilizar Mvn.

```bash
mvn install
```

## Uso
Modificar
resources/application.properties

en nuestra base de datos crear una DB llamada "usercrud"
o modificar según gusto
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/usercrud
spring.datasource.username=xxxx
spring.datasource.password=xxxx
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## Librerías

- lombok
- mysql-connector-java
- spring-boot-starter-web
- spring-boot-starter-validation
- spring-boot-starter-security
- jjwt-api
- jjwt-jackson
- jjwt-impl
