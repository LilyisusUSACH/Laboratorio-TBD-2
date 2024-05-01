Manual de usuario.

-- Este documento proporciona las instrucciones necesarias para la instalación y el uso de las tecnologías requeridas para ejecutar la aplicación de la entrega 1 del laboratorio.

<h1>Requisitos del Sistema:</h1>

<hr>
<h2>Para la base de datos, se debe instalar lo siguiente:</h2>
<ul>
<li>PostgreSQL (versión 11+)</li>
<li>pgAdmin 4</li>
</ul>
<hr>
<h2>Para el backend (desarrollado como una API RESTful en Spring), se debe instalar lo siguiente:</h2>
<ul>
<li>JDK (Versión 17)</li>
<li>Postman (opcional)</li>
<li>IntelliJ IDEA (para backend y opcionalmente frontend)</li>
<li>Editor de texto de su preferencia para el frontend</li>
</ul>
<hr>
<h2>Para el frontend, se debe instalar lo siguiente:</h2>
<ul>
<li>Vue.js (versión 3.x)</li>
</ul>
Pasos para Hacer Funcionar la Aplicación:
<ol>
<li>Configuración de la Base de Datos:</li>
<ol>
<li> Abrir pgAdmin4 y crear una nueva base de datos llamada "lab1TBD". ( o realizarlo a traves de la terminal )</li>
<li> Abrir "query tools" y cargar el script "dbCreate.sql" ubicado en "./db" para crear las tablas necesarias.</li>
<li> Luego, cargar el script <span>"loadData.sql"</span> para poblar las tablas con datos.</li>
  <li> Posteriormente, para cumplir con las funcionalidades se requiere cargar mas archivos:</li>
  <ol> 
  <li>Primero se necesita el archivo "dbLogTriggerCreate.sql" que se encarga de crear un trigger por cada una de las tablas</li>
    <li>En segundo lugar, para el requerimiento que solicita una procedimiento almacenado tenemos 2 opciones</li>
    <ul> 
      <li>Usar el archivo "FuncReq17.sql" que se encarga de hacer una funcion, la cual muestra una tabla a detalle, donde la columna consultas se puede expandir y asi revisar el total de las consultas realizadas</li>
      <li>Usar el archivo "FuncReq17.sql" que hace lo mismo, pero en un procedimiento almacenado, este no logramos que devolviera una tabla, pero imprime el detalle en la query</li>
    </ul>
  </ol>
</ol>
<li> Ejecución del Backend:</li>
<ol><li> Iniciar IntelliJ (u otro ide, o compilador) y ejecutar el backend para habilitar el servidor necesario, el cual por defecto corre en el puerto 8090.</li></ol>


<li> Ejecución del Frontend:</li>
<ol>
<li>Abrir IntelliJ y navegar a la carpeta "frontend" dentro de "tbdFrontEnd".</li>
<li>3.2. Abrir una terminal en esta ubicación.</li>
<li>Ejecutar los siguientes comandos:</li>

  
```npm install```
  
```npm run serve``` 


Estos pasos permitirán ejecutar el frontend del código fuente.
en la carpeta, ademas de tener instalado
- nodejs
  </ol>
</ol>


