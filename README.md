# Notas API

API desarrollada en **Spring Boot** con **Spring Security** y **Spring WebFlux** para la gestión de notas. La aplicación incluye un sistema de autenticación y autorización, documentación interactiva con **Swagger**, y está preparada para ejecutarse en un contenedor **Docker**.

---

## Tabla de Contenidos
- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías)
- [Instalación y Configuración](#instalación-y-configuración)
- [Ejecución en Docker](#ejecución-en-docker)
- [Documentación Swagger](#documentación-de-la-api)

---

## Características

- **Spring WebFlux**: Manejo de peticiones de forma reactiva para mejorar el rendimiento.
- **Spring Security**: Implementación de autenticación y autorización con soporte para JWT.
- **Swagger**: Documentación interactiva para explorar y probar los endpoints.
- **Docker**: Contenedor para despliegue rápido de la aplicación.
- **Base de Datos H2**: Base de datos en memoria para pruebas y desarrollo rápido.

---

## Tecnologías

- **Java 17**: Lenguaje de programación.
- **Spring Boot 3.0.2**: Framework principal para el desarrollo de aplicaciones.
- **Spring WebFlux**: Framework reactivo para manejar las solicitudes de manera eficiente.
- **Spring Security**: Seguridad y autenticación en la aplicación.
- **Swagger (Springfox)**: Documentación interactiva de la API generada automáticamente.
- **Docker**: Virtualización y despliegue en contenedores.
- **H2 Database**: Base de datos en memoria para pruebas rápidas y desarrollo.

---

## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/Jefferson1801/api-notas
cd api-notas

```
### 2. Construcción del proyecto

```bash
mvn clean install
```
### 3. Ejecución local
```bash
mvn spring-boot:run
```
La aplicación estará disponible en http://localhost:9191


## Ejecución en Docker

Para construir la imagen Docker, utiliza el siguiente comando:
```bash
docker build -t chaupez-dev/api-notas  .
```

Para ejecutar el contenedor:
```bash
docker run --name apiNotas  -p:9191:9191 -d chaupez-dev/api-notas
```

La aplicación estará disponible en http://localhost:9191

## Documentación de la API
La configuración de Swagger se encuentra en un archivo YAML ubicado en el directorio src/main/resources. Puedes probar esta documentación en el Swagger Editor.


