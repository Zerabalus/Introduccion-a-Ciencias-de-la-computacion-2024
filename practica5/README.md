Introducción a Ciencias de la Computación
=========================================

Práctica 5: Excepciones, entrada/salida y enumeraciones
-------------------------------------------------------

### Fecha de entrega: martes 2 de abril, 2023

Deben implementar los métodos faltantes de las clases
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5/-/blob/main/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java),
[BaseDeDatosEstudiantes](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5/-/blob/main/src/main/java/mx/unam/ciencias/icc/BaseDeDatosEstudiantes.java),
[Estudiante](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5/-/blob/main/src/main/java/mx/unam/ciencias/icc/Estudiante.java),
[Lista](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5/-/blob/main/src/main/java/mx/unam/ciencias/icc/Lista.java),
así como la enumeración
[CampoEstudiante](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5/-/blob/main/src/main/java/mx/unam/ciencias/icc/CampoEstudiante.java).

Una vez que hayan terminado sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas sus pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica5](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5/-/blob/main/src/main/java/mx/unam/ciencias/icc/Practica5.java)
al hacer:

```
$ mvn install
...
$ java -jar target/practica5.jar -g archivo.db # guarda la base de datos
...
$ java -jar target/practica5.jar -c archivo.db # carga la base de datos
```

Los únicos archivos que deben modificar son:

* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java` y
* `Lista.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica5.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
5](https://aztlan.fciencias.unam.mx/~canek/2024-2-icc/practica5/apidocs/index.html)
