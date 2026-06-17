# ZonaFit - Sistema de Administración de Clientes

**ZonaFit** es una aplicación de consola desarrollada en Java diseñada para la gestión eficiente de clientes y sus membresías en un centro de acondicionamiento físico. Esta herramienta permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la base de datos de clientes de forma sencilla y directa.

## Características

* **Listado de Clientes:** Visualización completa de todos los clientes registrados.
* **Búsqueda de Clientes:** Localización rápida mediante el ID del cliente.
* **Gestión de Clientes:** * **Agregar:** Registro de nuevos miembros con nombre, apellido y tipo de membresía.
    * **Modificar:** Actualización de datos de clientes existentes mediante su ID.
    * **Eliminar:** Gestión de bajas de clientes del sistema.
* **Interfaz de Consola:** Menú interactivo fácil de usar para el administrador.

## Tecnologías Utilizadas

* **Lenguaje:** Java
* **Patrón de diseño:** DAO (Data Access Object) para la capa de persistencia.

## Estructura del Proyecto

El proyecto está organizado en paquetes siguiendo buenas prácticas de desarrollo:

* `zona_fit.presentacion`: Contiene la clase `ZonaFitApp`, responsable de la interacción con el usuario a través de la consola.
* `zona_fit.datos`: Contiene las interfaces y clases encargadas de la comunicación con la base de datos.
* `zona_fit.dominio`: Contiene la clase de entidad `Cliente`.

## Instalación y Ejecución

1. Asegúrate de tener instalado **JDK 17** o superior.
2. Clona el repositorio:
   ```bash
   git clone https://github.com/ceballos25/zonaFit-Java.git

## Autor
Cristian Ceballos: `https://cristianceballos.com`
