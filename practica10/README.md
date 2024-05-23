Introducción a Ciencias de la Computación
=========================================

Práctica 10: Hilos de ejecución y enchufes
------------------------------------------

### Fecha de entrega: martes 4 de junio, 2024

Deben completar los métodos de las clases en el paquete
[mx.unam.ciencias.icc.red](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica10/-/blob/main/src/main/java/mx/unam/ciencias/icc/red).

Una vez que hayan terminado con sus clases, deben compilar al hacer:

```
$ mvn compile
```

También deben pasar todas las pruebas unitarias al hacer:

```
$ mvn test
```

Por último, se debe ejecutar correctamente el programa del servidor, escrito en la clase
[ServidorPractica10](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica10/-/blob/main/src/main/java/mx/unam/ciencias/icc/ServidorPractica10.java)
al hacer:

```
$ mvn install
...
$ ./bin/servidor-practica10 1234 estudiantes.bd
```

y también el programa del cliente, escrito en la clase
[ClientePractica10](https://aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica10/-/blob/main/src/main/java/mx/unam/ciencias/icc/ClientePractica10.java)
al hacer:

```
$ ./bin/cliente-practica10
```

Los únicos archivos que deben modificar son:

* `Arreglos.java`,
* `BaseDeDatos.java`,
* `BaseDeDatosEstudiantes.java`,
* `CampoEstudiante.java`,
* `Estudiante.java`,
* `Lista.java`,
* `igu/ControladorFormaBusquedaEstudiantes.java`,
* `igu/ControladorFormaEstudiante.java`,
* `igu/ControladorForma.java`,
* `igu/ControladorInterfazEstudiantes.java`,
* `igu/EntradaVerificable.java`,
* `igu/FabricaFormatoCelda.java`
* `red/Conexion.java`,
* `red/Mensaje.java`,
* `red/ServidorBaseDeDatos.java` y
* `red/ServidorBaseDeDatosEstudiantes.java`.

*No deben modificar de ninguna manera ninguno de los otros archivos de la
práctica*.

### Repositorio

Pueden clonar la práctica con el siguiente comando:

```
$ git clone https://canek@aztlan.fciencias.unam.mx/gitlab/2024-2-icc/practica10.git
```

### Documentación

La documentación generada por JavaDoc la pueden consultar aquí:

[Documentación generada por JavaDoc para la práctica
10](https://aztlan.fciencias.unam.mx/~canek/2024-2-icc/practica10/apidocs/index.html)
