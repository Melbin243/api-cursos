# API de Gestión de Cursos

API REST desarrollada con Spring Boot para la gestión de cursos, estudiantes y profesores. Permite realizar operaciones CRUD completas para cada entidad y gestionar la inscripción de estudiantes en los cursos.

## ✨ Funcionalidades Principales

- **Gestión de Cursos**: Crear, leer, actualizar y eliminar cursos.
- **Gestión de Estudiantes**: Crear, leer, actualizar y eliminar estudiantes.
- **Gestión de Profesores**: Crear, leer, actualizar y eliminar profesores.
- **Inscripción de Estudiantes**: Inscribir a un estudiante en un curso específico.
- **Consultas Detalladas**: Obtener la información de un curso incluyendo el profesor asignado y la lista de estudiantes inscritos.

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java 17
- **Framework**: Spring Boot 3
- **Base de Datos**: PostgreSQL
- **Gestor de Dependencias**: Maven
- **Documentación**: SpringDoc (OpenAPI 3)

## 📋 Prerrequisitos

Antes de empezar, asegúrate de tener instalado lo siguiente:

- JDK 17 o superior.
- Apache Maven.
- Una instancia de PostgreSQL en ejecución.
- Un IDE de tu elección (opcional, pero recomendado).

## 🚀 Cómo Levantar el Proyecto

1.  **Clonar el repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd api-cursos
    ```

2.  **Configurar la Base de Datos:**
    El proyecto se configura mediante variables de entorno para la conexión a la base de datos. Debes configurar las siguientes variables en tu sistema o en la configuración de ejecución de tu IDE:

    - `DATABASE_HOST`: El host de tu base de datos (ej. `localhost`).
    - `DATABASE_PORT`: El puerto de tu base de datos (ej. `5432`).
    - `DATABASE_NAME`: El nombre de la base de datos que utilizarás.
    - `DATABASE_USERNAME`: El usuario para acceder a la base de datos.
    - `DATABASE_PASSWORD`: La contraseña del usuario.

3.  **Ejecutar la aplicación:**
    Puedes iniciar la aplicación usando el wrapper de Maven incluido:

    ```bash
    ./mvnw spring-boot:run
    ```
    La API estará disponible en `http://localhost:8080/api/v1`.

## 📚 Documentación de la API (Swagger)

Una vez que la aplicación esté en funcionamiento, puedes acceder a la documentación interactiva de la API a través de Swagger UI. Esto te permitirá ver todos los endpoints disponibles y probarlos directamente desde el navegador.

- **URL de Swagger UI**: [http://localhost:8080/api/v1/doc/openApi](http://localhost:8080/api/v1/doc/openApi)

## 🧩 Endpoints Principales

Todos los endpoints están prefijados con `/api/v1`.

### Cursos (`/courses`)
- `GET /`: Lista todos los cursos.
- `GET /{id}`: Obtiene un curso por su ID, incluyendo profesor y estudiantes.
- `POST /`: Crea un nuevo curso.
- `PUT /{id}`: Actualiza un curso existente.
- `DELETE /{id}`: Elimina un curso.
- `POST /enroll`: Inscribe un estudiante en un curso.

### Estudiantes (`/students`)
- `GET /`: Lista todos los estudiantes.
- `GET /{id}`: Obtiene un estudiante por su ID.
- `POST /`: Crea un nuevo estudiante.
- `PUT /{id}`: Actualiza un estudiante existente.
- `DELETE /{id}`: Elimina un estudiante.

### Profesores (`/teachers`)
- `GET /`: Lista todos los profesores.
- `GET /{id}`: Obtiene un profesor por su ID.
- `POST /`: Crea un nuevo profesor.
- `PUT /{id}`: Actualiza un profesor existente.
- `DELETE /{id}`: Elimina un profesor.

