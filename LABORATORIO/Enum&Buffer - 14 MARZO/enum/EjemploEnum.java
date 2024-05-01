public class EjemploEnum{

    public static void main(String[] args) {

        Dia d; // declaramos una variable del tipo Dia                                                

        d = Dia.DOMINGO; //asignamos a d un valor
 
        //Ejemplo de if para comparar una variable de tipo enum con un valor
        if (d == Dia.DOMINGO || d == Dia.SABADO)  
            System.out.println("Estamos en fin de semana");
        else 
            System.out.println("Aún no ha llegado el fin de semana");

        //Ejemplo de uso de una variable de tipo enum en un switch                                                
        switch (d) {  
            case LUNES:
            case MARTES:
            case MIERCOLES:
            case JUEVES:
            case VIERNES:
                System.out.println("Aún no ha llegado el fin de semana");                                         
                break;
            default:
                System.out.println("Estamos en fin de semana");
        }
    }
}