package com.mycompany._practica2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JuegoVentana extends JDialog {

    public JuegoVentana() {
        // la ventana
        setTitle("Quetzal Space Defender");
        setSize(850, 600);
        
        //pausa el Main hasta que se cierre el juego
        setModal(true);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //  panel del juego  con los gráficos 
        PanelJuego panel = new PanelJuego();
        add(panel);
    }
}

//  gráficos del juego
class PanelJuego extends JPanel implements ActionListener {
    
    // Variables 
    private Timer timer;
    private int naveX = 80;
    private int naveY = 250;
    private int puntaje = 0;
    private int vidas = 3;
    
    // Posición del asteroide 1
    private int obstaculoX = 600;
    private int obstaculoY = 250;
    
    // Posición del  asteroide 2
    private int obstaculo2X = 900;
    private int obstaculo2Y = 380;
    
    //disparo
    private int disparoX = -100;
    private int disparoY = -100;
    private boolean disparoActivo = false;

    public PanelJuego() {
        // Fondo de color del juego
        setBackground(Color.BLACK);
        setFocusable(true);

        // Detectar las teclas 
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int tecla = e.getKeyCode();

                // Mover nave hacia arriba
                if (tecla == KeyEvent.VK_UP && naveY > 120) {
                    naveY -= 20;
                }
                // Mover nave hacia abajo
                if (tecla == KeyEvent.VK_DOWN && naveY < 500) {
                    naveY += 20;
                }
                // Disparar con la barra de espacio
                if (tecla == KeyEvent.VK_SPACE) {
                    if (!disparoActivo) { // Solo dispara si no hay otro láser en pantalla
                        disparoX = naveX + 35; //desde la punta de la nave
                        disparoY = naveY;      // al centro de la nave
                        disparoActivo = true;
                    }
                }
            }
        });

        // Timer cada 20 milisegundos
        timer = new Timer(20, this);
        timer.start();
    }

    // pantalla
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //  texto en la parte superior
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Quetzal Space Defender", 30, 40);
        g.drawString("Puntaje: " + puntaje, 30, 70);
        g.drawString("Vidas: " + vidas, 160, 70);
        g.drawString("↑ ↓ Mover   |   ESPACIO: Disparar", 30, 95);

        // Línea divisoria
        g.setColor(Color.GRAY);
        g.drawLine(30, 110, 800, 110);

        //laser
        if (disparoActivo) {
            g.setColor(Color.YELLOW);
            g.fillRect(disparoX, disparoY, 12, 4);
        }

        //la nave
        g.setColor(Color.CYAN);
        int[] xPuntos = {naveX, naveX + 35, naveX};
        int[] yPuntos = {naveY + 15, naveY, naveY - 15};
        g.fillPolygon(xPuntos, yPuntos, 3);

        //el obstaculo
        g.setColor(new Color(255, 0, 127)); 
        g.fillOval(obstaculoX, obstaculoY, 40, 40);
        g.fillOval(obstaculo2X, obstaculo2Y, 40, 40);
        
        //las estrellas de fondo 
        g.setColor(Color.WHITE); 
        g.fillRect(200, 150, 2, 2); g.fillRect(234, 765, 2, 3); g.fillRect(190, 431, 2, 3); g.fillRect(334, 121, 2, 2);
        g.fillRect(500, 380, 3, 3); g.fillRect(432, 567, 3, 2); g.fillRect(222, 315, 3, 2); g.fillRect(234, 113, 3, 3);
        g.fillRect(700, 180, 2, 2); g.fillRect(345, 654, 2, 3); g.fillRect(303, 143, 3, 2); g.fillRect(434, 131, 3, 2);
        g.fillRect(400, 250, 2, 3); g.fillRect(543, 456, 3, 2); g.fillRect(444, 416, 2, 3); g.fillRect(734, 146, 2, 3);
        g.fillRect(360, 480, 3, 2); g.fillRect(456, 543, 2, 3); g.fillRect(505, 651, 2, 3); g.fillRect(434, 519, 3, 2);
        g.fillRect(478, 155, 2, 3); g.fillRect(654, 345, 3, 2); g.fillRect(660, 517, 3, 2); g.fillRect(234, 131, 3, 3);
        g.fillRect(367, 255, 3, 2); g.fillRect(567, 432, 2, 3); g.fillRect(707, 165, 2, 2); g.fillRect(434, 143, 2, 2);
        g.fillRect(267, 355, 2, 3); g.fillRect(765, 234, 3, 2); g.fillRect(102, 311, 2, 3); g.fillRect(634, 512, 3, 3);
        g.fillRect(153, 455, 3, 2); g.fillRect(123, 321, 2, 3); g.fillRect(211, 112, 2, 3); g.fillRect(234, 631, 3, 2);
    }

    //verifica colisiones 
    @Override
    public void actionPerformed(ActionEvent e) {
       //a laizquierda
        obstaculoX -= 4;//asteroide 1
        obstaculo2X -= 5; // asteroide 2

        //reiniciar obstáculo 1 si pasa de los borde
        if (obstaculoX < -50) {
            obstaculoX = 850;
            obstaculoY = (int) (Math.random() * 400) + 120;
            puntaje += 5;
        }
        //reiniciar obstáculo 2 si pasa del borde
        if (obstaculo2X < -50) {
            obstaculo2X = 999;
            obstaculo2Y = (int) (Math.random() * 400) + 120;
            puntaje += 5;
        }
        
        // vida
        Rectangle rectNave = new Rectangle(naveX, naveY - 15, 35, 30);
        Rectangle rectObs1 = new Rectangle(obstaculoX, obstaculoY, 40, 40);
        Rectangle rectObs2 = new Rectangle(obstaculo2X, obstaculo2Y, 40, 40);
        
        // Si choca con  asteroide 1
        if (rectNave.intersects(rectObs1)) {
            vidas--;
            obstaculoX = 850; // para que no quite vidas consecutivas por error
            obstaculoY = (int) (Math.random() * 400) + 120;
        }

        // Si choca con asteroide 2
        if (rectNave.intersects(rectObs2)) {
            vidas--;
            obstaculo2X = 900;
            obstaculo2Y = (int) (Math.random() * 400) + 120;
        }

        // muerte
        if (vidas <= 0) {
            timer.stop(); // Detiene el bucle del juego
            JOptionPane.showMessageDialog(this, "¡Moriste! Fin del juego.", "Game Over", JOptionPane.WARNING_MESSAGE);
            
            //regresa x al menú principal
            Window ventanaPadre = SwingUtilities.getWindowAncestor(this);
            if (ventanaPadre != null) {
                ventanaPadre.dispose();
            }
        }

        // moviientos y choque
        if (disparoActivo) {
            disparoX += 65;

            //choque con asteroide 1
            if (disparoX >= obstaculoX && disparoX <= obstaculoX + 40 &&
                disparoY >= obstaculoY && disparoY <= obstaculoY + 40) {
                puntaje += 15;
                obstaculoX = 850;
                obstaculoY = (int) (Math.random() * 400) + 120;
                disparoActivo = false;
            }

            //choque con asteroide 2
            if (disparoX >= obstaculo2X && disparoX <= obstaculo2X + 40 &&
                disparoY >= obstaculo2Y && disparoY <= obstaculo2Y + 40) {
                puntaje += 15;
                obstaculo2X = 990;
                obstaculo2Y = (int) (Math.random() * 400) + 120;
                disparoActivo = false;
            }

            //quitar el láser si sale de la pantalla
            if (disparoX > 850) {
                disparoActivo = false;
            }
        }

        repaint();
    }
}