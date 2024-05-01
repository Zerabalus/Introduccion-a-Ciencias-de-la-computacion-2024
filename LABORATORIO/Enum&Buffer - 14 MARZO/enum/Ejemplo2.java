public class Ejemplo2{
    public static void main(String[] args) {
       Dia dia; //declaración de una variable tipo enum

       dia = Dia.MIERCOLES; //Se le asigna un valor

       // dia.name() devuelve un String con el valor de la constante (MIERCOLES)
       System.out.println(dia.name());

       // día.toString() devuelve un String con el valor de la constante (MIERCOLES)                              
       System.out.println(dia.toString());

       // Devuelve un entero con la posición que ocupa dentro del enum (2).
       System.out.println(dia.ordinal());   

       // Comprobar la igualdad mediante equals y ==
       if(dia.equals(Dia.LUNES)){ //Esta condición no se cumple
           System.out.println("El valor es LUNES");
       }
       if(dia.equals(Dia.MIERCOLES)){ //Esta condición se cumple
           System.out.println("El valor es MIERCOLES usando equals");
       }
       if(dia == Dia.MIERCOLES){ //Esta condición se cumple
           System.out.println("El valor es MIERCOLES usando ==");
       }

       //Se crea otra variable y se le asigna un valor 
       Dia otroDia = Dia.MARTES;

       // Comparamos las dos variables enum mediante compareTo
       // Se comparan según el orden en el que se han declarado en el enum                                        
       // En este caso el if se cumple
       // dia contiene MIERCOLES y otroDia contiene MARTES
       // En la declaración del enum MIERCOLES aparece después de MARTES 
       // por lo tanto es mayor
       if (dia.compareTo(otroDia) > 0) { //Esta condición se cumple: dia > otroDia 
            System.out.println(dia + " > " + otroDia);
       } else {
            System.out.println(dia + " <= " + otroDia);
       }
       //Mostrar todos los elementos del enum
       //el método values() devuelve un array con todas las constantes del enum                                   
       for (Dia d : Dia.values()) {
            System.out.print(d + " " + "\n");
       }
    }
}
