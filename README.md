# Plataforma iLearningHub

## Descripción

La Plataforma iLearningHub es una aplicación web desarrollada para facilitar la gestión de cursos virtuales. El sistema permite que los docentes creen y administren cursos y contenidos educativos, mientras que los estudiantes puedan acceder a dichos cursos y usar los recursos publicados.

La aplicación sigue una arquitectura cliente-servidor, utilizando Angular para el frontend y Spring Boot para el backend, comunicándose mediante una API REST.

---

# Arquitectura del proyecto

El proyecto está compuesto por tres componentes principales:

- **Frontend:** Angular + Angular Material
- **Backend:** Spring Boot + Spring Security + JWT
- **Base de datos:** PostgreSQL + SupaBase

---

# Tecnologías utilizadas

## Frontend

- Angular
- Angular Material
- TypeScript
- HTML
- SCSS

## Backend

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- JWT
- Maven

## Base de datos

- PostgreSQL

---

# Estructura del proyecto

```
e-learning/

│
├── frontend/
│   ├── src/
│   ├── angular.json
│   └── package.json
│
├── backend/
│   ├── src/
│   └── pom.xml
│
└── README.md
```

---

# Requisitos

Para ejecutar el proyecto es necesario contar con:

- Java JDK 21
- Maven
- Node.js 20 o superior
- Angular CLI
- Git

---

# Instalación

## 1. Clonar los repositorios

Backend

```bash
git clone https://github.com/adrianawzw/elearning-backend.git
```

Frontend

```bash
git clone https://github.com/adrianawzw/elearning-frontend.git
```

---

# Configuración del Backend

Ingresar al proyecto

```bash
cd backend
```

Configurar el archivo:

```
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgres://localhost:3306/elearning
spring.datasource.username=root
spring.datasource.password=tu_password
```

Instalar dependencias

```bash
mvn clean install
```

Ejecutar la aplicación

```bash
mvn spring-boot:run
```

El backend estará disponible en

```
http://localhost:8080
```

---

# Configuración del Frontend

Ingresar al proyecto

```bash
cd frontend
```

Instalar dependencias

```bash
npm install
```

Ejecutar Angular

```bash
ng serve
```

La aplicación estará disponible en

```
http://localhost:4200
```

---

# Funcionalidades principales

## Docente

- Inicio de sesión.
- Creación de cursos.
- Edición de cursos.
- Publicación de contenidos.
- Gestión del material educativo.
- Visualización de información del curso.

## Estudiante

- Inicio de sesión.
- Visualización de cursos disponibles.
- Acceso a los contenidos del curso.
- Consulta y uso del material educativo.

---

# Pruebas

El backend fue desarrollado utilizando la metodología Test-Driven Development (TDD).

Para ejecutar las pruebas:

```bash
mvn test
```

---

## Repositorio Frontend

https://github.com/adrianawzw/elearning-frontend

## Repositorio Backend

https://github.com/adrianawzw/elearning-backend

---

# Autores

Proyecto desarrollado para el curso de Desarrollo de Aplicaciones Web.

Equipo de desarrollo.
