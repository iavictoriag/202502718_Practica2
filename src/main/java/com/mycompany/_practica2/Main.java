package com.mycompany._practica2;

import java.util.Scanner;
import java.util.Vector;
import java.util.Random;

/**
 * Clase principal del sistema Quetzal Space Defender.
 * @author Victoria Yannarett García Gómez (202502718)
 */
public class Main {
    
    private static Vector<Piloto> listaPilotos = new Vector<>();
    private static Piloto pilotoActual = null;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        
        do {
            System.out.println("\n========================================");
            System.out.println("         QUETZAL SPACE DEFENDER         ");
            System.out.println("========================================");
            System.out.println("1. Crear piloto o seleccionar piloto.");
            System.out.println("2. Jugar");
            System.out.println("3. Puntajes y Reportes");
            System.out.println("4. Salir");
            System.out.print("Elige una opcion: ");
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch (opcion) {
                    case 1:
                        menuCrearPiloto(scanner);
                        break;
                    case 2:
                        iniciarJuego(scanner);
                        break;
                    case 3:
                        mostrarPuntajes();
                        break;
                    case 4:
                        System.out.println("\n¡Gracias por jugar! Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("\n[!] Opcion no valida, ingrese un numero del 1 al 4.");
                }
            } else {
                System.out.println("\n[!] Debe ingresar un numero valido.");
                scanner.next();
            }
        } while (opcion != 4);
        
        scanner.close();
    }
    
    public static void menuCrearPiloto(Scanner scanner) {
        System.out.println("\n--- GESTION DE PILOTOS ---");
        System.out.print("Ingrese el nombre del piloto: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("[!] El nombre no puede estar vacio.");
            return;
        }
        
        Piloto pilotoEncontrado = null;
        for (int i = 0; i < listaPilotos.size(); i++) {
            Piloto p = listaPilotos.get(i);
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                pilotoEncontrado = p;
                break;
            }
        }
        
        if (pilotoEncontrado != null) {
            pilotoActual = pilotoEncontrado;
            System.out.println("Piloto encontrado y seleccionado: " + pilotoActual.getNombre());
        } else {
            Piloto nuevoPiloto = new Piloto(nombre);
            listaPilotos.add(nuevoPiloto);
            pilotoActual = nuevoPiloto;
            System.out.println("¡Piloto registrado y seleccionado con éxito: " + nombre + "!");
        }
    }
    
    public static void iniciarJuego(Scanner scanner) {
        if (pilotoActual == null) {
            System.out.println("\n[!] Debe crear o seleccionar un piloto antes de iniciar la partida.");
            return;
        }
        
        System.out.println("\n--- INICIANDO PARTIDA ---");
        System.out.println("Piloto a bordo: " + pilotoActual.getNombre());
        
        int puntajeActual = 0;
        int vidas = 3;
        boolean jugando = true;
        int pasos = 0;
        int posicionNave = 2; // 1: Izquierda, 2: Centro, 3: Derecha
        Random random = new Random();
        
        while (jugando && vidas > 0) {
            pasos++;
            int carrilObstaculo = random.nextInt(3) + 1;
            
            // Simular limpieza de pantalla limpia en consola
            for (int i = 0; i < 3; i++) {
                System.out.println();
            }
            
            // --- INTERFAZ GRÁFICA RENOVADA ---
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|                QUETZAL SPACE DEFENDER                 |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println("|  Vidas: " + vidas + " [❤️]  |  Puntaje: " + puntajeActual + " pts  |  Dist: " + (pasos * 10) + "m  |");
            System.out.println("+-------------------------------------------------------+");
            System.out.println("                      [ ZONA ESPACIAL ]                  ");
            
            // Fila Superior (Obstáculos)
            String o1 = (carrilObstaculo == 1) ? "  [ ⚡ ASTEROIDE ]  " : "  [     ---     ]  ";
            String o2 = (carrilObstaculo == 2) ? "  [ ⚡ ASTEROIDE ]  " : "  [     ---     ]  ";
            String o3 = (carrilObstaculo == 3) ? "  [ ⚡ ASTEROIDE ]  " : "  [     ---     ]  ";
            
            System.out.println("+-------------------+ +-------------------+ +-------------------+");
            System.out.println("|" + o1 + "|" + o2 + "|" + o3 + "|");
            System.out.println("+-------------------+ +-------------------+ +-------------------+");
            System.out.println("|         |         |         |         |         |         |");
            System.out.println("|         |         |         |         |         |  <-- Laser [d]");
            System.out.println("|         |         |         |         |         |         |");
            
            // Fila Inferior (Nave del Jugador)
            String n1 = (posicionNave == 1) ? "    [  Q  ]    " : "    [     ]         ";
            String n2 = (posicionNave == 2) ? "    [  Q  ]     " : "    [     ]         ";
            String n3 = (posicionNave == 3) ? "    [  Q  ]     " : "    [     ]         ";
            
            System.out.println("+-------------------+ +-------------------+ +-------------------+");
            System.out.println("|" + n1 + "|" + n2 + "|" + n3 + "|");
            System.out.println("+-------------------+ +-------------------+ +-------------------+");
            System.out.println("     CARRIL [1]            CARRIL [2]            CARRIL [3]     ");
            System.out.println("=========================================================");
            
            System.out.print("ontroles -> Mover [1, 2, 3] | Disparar [d] | Salir [s]: ");
            String entrada = scanner.nextLine().trim().toLowerCase();
            
            if (entrada.equals("s")) {
                System.out.println("\nSaliendo de la partida actual...");
                jugando = false;
            } else if (entrada.equals("d")) {
                puntajeActual += 15;
                if (posicionNave == carrilObstaculo) {
                    System.out.println("\n¡IMPACTO! Destruiste el asteroide con tu laser. +15 pts.");
                } else {
                    System.out.println("\nDisparaste al espacio vacio. ¡Zona despejada!");
                }
            } else if (entrada.equals("1") || entrada.equals("2") || entrada.equals("3")) {
                posicionNave = Integer.parseInt(entrada);
                puntajeActual += 10;
                
                if (posicionNave == carrilObstaculo) {
                    System.out.println("\n ¡COLISION! Te moviste al carril " + carrilObstaculo + " y chocaste con el asteroide.");
                    vidas--;
                    System.out.println("Has perdido 1 vida. Vidas restantes: " + vidas);
                } else {
                    System.out.println("\nEsquivaste el peligro con exito. +10 pts.");
                }
            } else {
                System.out.println("\n[!] Comando no válido. Usa 1, 2 o 3 para moverte, 'd' para disparar, o 's' para salir.");
            }
        }
        
        System.out.println("\n========================================");
        System.out.println("             FIN DE LA PARTIDA          ");
        System.out.println("========================================");
        if (puntajeActual > pilotoActual.getPuntajeMaximo()) {
            pilotoActual.setPuntajeMaximo(puntajeActual);
            System.out.println("¡Nuevo racord maximo alcanzado: " + puntajeActual + " pts!");
        } else {
            System.out.println("Puntaje obtenido en esta partida: " + puntajeActual + " pts");
        }
    }
    
    public static void mostrarPuntajes() {
        System.out.println("\n========================================");
        System.out.println("          PUNTAJES Y REPORTES           ");
        System.out.println("========================================");
        if (listaPilotos.isEmpty()) {
            System.out.println("No hay pilotos registrados en el sistema todavia.");
            return;
        }
        for (int i = 0; i < listaPilotos.size(); i++) {
            Piloto p = listaPilotos.get(i);
            System.out.println((i + 1) + ". Piloto: " + p.getNombre() + " | Record Maximo: " + p.getPuntajeMaximo() + " pts");
        }
        System.out.println("========================================");
    }
}
