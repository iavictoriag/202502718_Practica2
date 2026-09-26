/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._practica2;

import java.util.Scanner;
import java.util.Vector;

/**
 *
 * @author vicky
 */
public class Main {
    
    private static Vector<Piloto> listaPilotos = new Vector<>(); //almacena en la memoria los pilotos
    private static Piloto pilotoActual = null;// el piloto que esta en la partica
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0 ;
        do{ //para que se vea el menu hasta que salga el usuario
            System.out.println("--- QUETZAL SPACE DEFENDER - SIDE SCROLLER ---");
            System.out.println("1. Crear piloto o seleccionar piloto.");
            System.out.println("2. Jugar");
            System.out.println("3. Puntajes y Reportes");
            System.out.println("4. Salir");
            System.out.println("Elije una opcion : ");
                    
                if (scanner.hasNextInt()){
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    
                    switch (opcion){
                        case 1:
                            menuCrearPiloto(scanner);
                            break;
                        case 2:
                            iniciarJuego();
                            break;
                        case 3:
                            mostrarPuntajes();
                            break;
                        case 4:
                            System.out.println("Saliendo del sistema...");
                            break;
                        default:
                            System.out.println("opcion no valida, ingrese un numero del 1 al 4.");           
                    }//fin if
                }else{
                        System.out.println("Debe ingresar un numero.");
                        scanner.next();
                        }//fin else
                }while (opcion !=4);
                scanner.close();
    }
    public static void menuCrearPiloto(Scanner scanner){
        System.out.println("--- GESTION DE PILOTOS ---");
        System.out.println("Ingrese el nombre del piloto : ");
        String nombre = scanner.nextLine().trim();//limpia los espacios en blanco
        
        if (nombre.isEmpty()){
            System.out.println("No pueden haber escios en blanco.");
            return;
        }    
        //buscar el piloto si ya se hubiera registrado
        Piloto pilotoEncontrado = null;
        for (int i = 0; i < listaPilotos.size(); i++) { //es un vector
            Piloto p = listaPilotos.get(i);
            if (p.getNombre().equalsIgnoreCase(nombre)) { //para verificar el nombre
            pilotoEncontrado = p;
            break;
    }
}
        if (pilotoEncontrado != null){
            pilotoActual = pilotoEncontrado;
            System.out.println("Piloto encontrado. Se selecciono : " + pilotoActual.getNombre());
        } else {
            Piloto nuevoPiloto = new Piloto(nombre);
            listaPilotos.add(nuevoPiloto);
            pilotoActual = nuevoPiloto;
            System.out.println("Piloto registrado y seleccionado correctamente : " + nombre);
        }
    }
    
    public static void iniciarJuego(){
        if (pilotoActual == null){
        System.out.println("Debe crear o seleccionar un piloto antes de jugar.");
            return;
        }
        System.out.println("Iniciando juego para el piloto: " + pilotoActual.getNombre());
    }
    
    public static void mostrarPuntajes (){
       System.out.println("--- PUNTAJES Y REPORTES ---");
        if (listaPilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados todavía.");
            return;
        }
        for (int i = 0; i < listaPilotos.size(); i++) {
            Piloto p = listaPilotos.get(i);
            System.out.println((i + 1) + ". Piloto: " + p.getNombre() + " | Puntaje Maximo: " + p.getPuntajeMaximo());
        }
    }
}