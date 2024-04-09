Introducción a Ciencias de la Computación
=========================================

Práctica 6: Genéricos
---------------------

### Fecha de entrega: martes 9 de abril, 2024

Deben convertir sus clases
[Lista](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/Lista.java)
y
[BaseDeDatos](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/BaseDeDatos.java)
a que sean genéricas, con los cambios correspondientes a
[BaseDeDatosEstudiantes](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/BaseDeDatosEstudiantes.java)
y a
[Estudiante](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/Estudiante.java)
dado que
[Registro](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/Registro.java)
ahora también es genérica.

Una vez que hayan terminado sus clases, deben compilar al hacer:

```
$ mvn compile
```

No debe haber **ninguna** advertencia al compilar; si su práctica muestra
advertencias al compilar, se considerará como si no compilara. Si utilizan
`@SuppressWarnings` fuera [del método `equals()` de la clase
`Lista`](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/Lista.java#L243)
es motivo automático para reprobarlos.

También deben pasar todas sus pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa escrito en la clase
[Practica6](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6/-/blob/main/src/main/java/mx/unam/ciencias/icc/Practica6.java)
al hacer:

```
$ mvn install
...
$ java -jar target/practica6.jar -g archivo.db # guarda la base de datos
...
$ java -jar target/practica6.jar -c archivo.db # carga la base de datos
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
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica6.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
6](https://aztlan.fciencias.unam.mx/~canek/2024-2-icc/practica6/apidocs/index.html)
