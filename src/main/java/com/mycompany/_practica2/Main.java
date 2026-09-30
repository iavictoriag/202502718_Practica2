package com.mycompany._practica2;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * Clase principal del sistema Quetzal Space Defender.
 * @author Victoria Yannarett García Gómez (202502718)
 */
public class Main {
    
    // Arreglo para almacenar a los pilotos
    private static Piloto[] listaPilotos = new Piloto[50];
    private static int totalPilotos = 0;
    private static Piloto pilotoActual = null;
    
    public static void main(String[] args) {
        int opcion = 0;
        
        do {
            String menu = "========================================\n" +
                          "         QUETZAL SPACE DEFENDER         \n" +
                          "========================================\n" +
                          "1. Crear piloto o seleccionar piloto.\n" +
                          "2. Jugar\n" +
                          "3. Puntajes y Reportes\n" +
                          "4. Salir\n\n" +
                          "Elige una opción:";
        
            // Para cuando cierren la ventana se salga del programa
            String entrada = JOptionPane.showInputDialog(null, menu, "Menú Principal", JOptionPane.QUESTION_MESSAGE);
            if (entrada == null) {
                break;            
            }
            
            // si le dan ok
            if (entrada.equals("")) {
                continue;
            }
            
            opcion = Integer.parseInt(entrada);
            
            // Menú principal usando switch
            switch (opcion) {
                case 1:
                    menuCrearPiloto();
                    break;
                case 2:
                    if (pilotoActual == null) {
                        JOptionPane.showMessageDialog(null, "¡Debes crear o seleccionar un piloto!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        iniciarJuego();
                    }
                    break;
                case 3:
                    mostrarPuntajes();
                    break;
                case 4:
                    JOptionPane.showMessageDialog(null, "¡Gracias por jugar! Saliendo del juego...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida. Intenta de nuevo.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }     
        } while (opcion != 4);
    }
    
    //registrar o buscar el piloto 
    //JOptionPane como Scanner
    public static void menuCrearPiloto() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del piloto:", "Gestion de Pilotos", JOptionPane.QUESTION_MESSAGE);
        
        if (nombre != null && !nombre.equals("")) {
            nombre = nombre.trim();
            Piloto pilotoEncontrado = null;
            
            // Buscamos si el piloto ya existe
            for (int i = 0; i < totalPilotos; i++) {
                if (listaPilotos[i].getNombre().equalsIgnoreCase(nombre)) {
                    pilotoEncontrado = listaPilotos[i];
                    break;
                }
            }
            
            // Si ya existía, lo seleccionamos
            if (pilotoEncontrado != null) {
                pilotoActual = pilotoEncontrado;
                JOptionPane.showMessageDialog(null, "Piloto seleccionado : " + pilotoActual.getNombre() + "!");
            } else {
                // Si no existe se crea y se guarda en el arreglo
                if (totalPilotos < listaPilotos.length) {
                    Piloto nuevoPiloto = new Piloto(nombre);
                    listaPilotos[totalPilotos] = nuevoPiloto;
                    totalPilotos++;
                    pilotoActual = nuevoPiloto;
                    JOptionPane.showMessageDialog(null, "¡Nuevo piloto creado y seleccionado: " + nombre + "!");
                } else {
                    JOptionPane.showMessageDialog(null, "El arreglo de pilotos esta lleno.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // iniciar juego 
    public static void iniciarJuego() {
        JOptionPane.showMessageDialog(null, "Iniciando partida para: " + pilotoActual.getNombre());
    }

    // mostrar puntajes
    public static void mostrarPuntajes() {
        if (totalPilotos == 0) {
            JOptionPane.showMessageDialog(null, "Todavía no hay pilotos registrados.", "Reportes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String reporte = "=== REPORTE DE PILOTOS Y PUNTAJES ===\n\n";
        for (int i = 0; i < totalPilotos; i++) {
            reporte = reporte + (i + 1) + ". " + listaPilotos[i].getNombre() + " - Récord: " + listaPilotos[i].getPuntajeMaximo() + " pts\n";
        }
        
        JOptionPane.showMessageDialog(null, reporte, "Puntajes", JOptionPane.INFORMATION_MESSAGE);
    }
}