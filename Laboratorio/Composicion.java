public class Composicion{
	public static void main(String[] args){
		Fecha fn1 = new Fecha(5, 3,1986);
		//Fecha fc1 = new Fecha(1, 8, 2023);

		Empleado e1 = new Empleado("Carlos ", "Rivera");
		e1.setFecha(fn1);

		System.out.println(e1.getNombre() + e1.getApellido());
		System.out.println("Su fecha de nacimiento es: ");
		System.out.println(e1.getFecha().getDia());
		System.out.println(e1.getFecha().getMes());
		System.out.println(e1.getFecha().getAño());		
	}

	/**
	 * Como ejercicio, deben ponerle formato a la fecha.
	 */
}