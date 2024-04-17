Introducción a Ciencias de la Computación
=========================================

Práctica 7: Iteradores
----------------------

### Fecha de entrega: martes 16 de abril, 2024

Deben hacer su clase
[Lista](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica7/-/blob/main/src/main/java/mx/unam/ciencias/icc/Lista.java)
iterable. Esto implicará cambios también en la clase
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica7/-/blob/main/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java).

Una vez que hayan terminado con sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas las pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica7](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica7/-/blob/main/src/main/java/mx/unam/ciencias/icc/Practica7.java)
al hacer:

```
$ mvn install
...
$ java -jar target/practica7.jar -g archivo.txt # para guardar en archivo.txt, o
$ java -jar target/practica7.jar -c archivo.txt # para cargar de archivo.txt
```

Los únicos archivos que deben modificar son:

* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java` y
* `Lista.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la
práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica7.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
7](https://aztlan.fciencias.unam.mx/~canek/2024-2-icc/practica7/apidocs/index.html)
