public class Empleado{
	private String nombre;
	private String apellido;
	Fecha fecha;

	public Empleado(String nombre, String apellido){
		this.nombre = nombre;
		this.apellido = apellido;
	}

	public Fecha getFecha(){
		return fecha;
	}

	public void setFecha(Fecha fecha){
		this.fecha = fecha;
	}

	public void setNombre(String nombre){
		this.nombre = nombre;
	}

	public String getNombre(){
		return nombre;
	}

	public void setApellido(String apellido){
		this.apellido = apellido;
	}

	public String getApellido(){
		return apellido;
	}

}