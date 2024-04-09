public class Fecha{
	private int dia;
	private int mes;
	private int año;

	public Fecha(int dia, int mes, int año){
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}

	public void setDia(int dia){
		this.dia = dia;
	}

	public int getDia(){
		return dia;
	}

	public void setMes(int mes){
		this.mes = mes;
	}

	public int getMes(){
		return mes;
	}

	public void setAño(int año){
		this.año = año;
	}

	public int getAño(){
		return año;
	}
}