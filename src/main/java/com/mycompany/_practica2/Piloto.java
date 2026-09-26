
/**almacena al piloto y sus atributos
 * */
package com.mycompany._practica2;

public class Piloto {
    private String nombre;
    private int puntajeMaximo;
    
    public Piloto(String nombre){
        this.nombre = nombre;
        this.puntajeMaximo = 0; //empiez desde 0 puntos
    }
    //nombre
    public String getNombre(){
        return nombre;
    }
    public int getPuntajeMaximo(){
        return puntajeMaximo;
    }
    public void setPuntajeMaximo(int puntajeMaximo){
        this.puntajeMaximo = puntajeMaximo;
    }
     
}
