package mx.unam.ciencias.icc;

public class Anilist implements Registro {

   //nombre del anime
    private String nombre;
    //genero de la serie
    private String genero;
    //no de capitulos
    private int capitulos;
    //no de estreno
    private int estreno;
    //calificacion
    private double calificacion;
    
    public Anilist(String nombre,
            String genero,
            int capitulos,
            int estreno,
            double calificacion) {

        this.nombre = nombre;
        this.genero = genero;
        this.capitulos = capitulos;
        this.estreno = estreno;
        this.calificacion = calificacion;
    }
    
    // Getters y Setters

    //nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //genero
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    //capitulos
    public int getCapitulos() {
        return capitulos;
    }
    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    //estreno
    public int getEstreno() {
        return estreno;
    }
    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    //calificacion
    public double getCalificacion() {
        return calificacion;
    }
    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    //regresa una representacion en cadena del objeto
    @Override 
    public String toString() {
        
        String cadena = String.format(
                "Nombre   : %s\n" +
                "Genero    : %d\n",
                "Capitulos   : %09d\n" +
                "Estreno : %09d\n",
                "Calificacion : %2.2f" +
                nombre,  genero, capitulos, estreno, calificacion);
        return cadena;
    }

    //Dice si el anime recibido es el mismo que llama al método
    @Override
    public boolean equals(Object objeto) {
        if(!(objeto instanceof Anilist))
            return false;
        Anilist anime = (Anilist) objeto;
        if(this.toString().equals(anime.toString()))
            return true;
        return false;
    }

    /**
     * Regresa la informacion del anime seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Anilist#deseria}.
     * @return la seriación del anime en una línea de texto.
     */
    @Override 
    public String seria() {
        return(String.format("%s\t%d\t%09d\t%09d\t%2.2f\n", nombre, genero, capitulos, estreno, calificacion));
    }

    /**
     * Deseria una línea de texto en las propiedades del estudiante. La
     * seriación producida por el método {@link Anilist#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un estudiante.
     */
    // Aquí va su código.
    public void deseria(String linea){
        // por si es nula, vacía
        if(linea == null)
            throw new ExcepcionLineaInvalida();
        // campos es un arreglo de 4 elementos
        String[] campos = linea.trim().split("\t");
        // t es un divisor
        // no es una seriación valida de un estudiante
        try{
            nombre = (campos[0]);
            genero = (campos[1]);
            capitulos = Integer.parseInt(campos[2]);
            estreno = Integer.parseInt(campos[3]);
            calificacion = Double.parseDouble(campos[4]);
            
        } catch (Exception e) {
            // excepcion es un objeto (importante, las excepciones son objetps) de tipo ExcepcionLineaInvalida
            throw new ExcepcionLineaInvalida();
        }
    }


    @Override
    public boolean casa(Enum campo, Object valor) {
        // Aquí va su código.
        if (!(campo instanceof CampoAnilist))
            throw new IllegalArgumentException();
        CampoAnilist c = (CampoAnilist)campo;
        switch(c){
          case NOMBRE:
            return casaNombre(valor);
          case GENERO:
            return casaGenero(valor);
          case CAPITULOS:
            return casaCapitulos(valor);
          case ESTRENO:
            return casaEstreno(valor);
          case CALIFICACION:
            return casaCalificacion(valor);
          default:
            return false;
        }
    }
    private boolean casaNombre(Object o){
      if(!(o instanceof String))return false;
      String v = (String) o;
      if(v.isEmpty()) return false;
      return nombre.indexOf(v) !=-1;
    }
    private boolean casaGenero(Object o){
        if(!(o instanceof String))return false;
        String v = (String) o;
        if(v.isEmpty()) return false;
        return nombre.indexOf(v) !=-1;
      }
    private boolean casaCapitulos(Object o){
      if(!(o instanceof Integer)) return false;
      Integer v = (Integer) o;
      return capitulos>= v.intValue();
    }
    private boolean casaEstreno(Object o){
      if(!(o instanceof Integer)) return false;
      Integer v = (Integer) o;
      return estreno>= v.intValue();
    }
    private boolean casaCalificacion(Object o){
      if(!(o instanceof Double)) return false;
      Double v = (Double) o;
      return calificacion>= v.doubleValue();
    }

}
