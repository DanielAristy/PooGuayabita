package com.unicode.model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Juego {


    private int id;
    private int pote;
    private List<Usuario> jugadores = new ArrayList<>(2);
    private static Juego instance = new Juego();
    private static ArrayList<ImageIcon> dados;
    public static final String TITULO = "Guayabita";
    private static final String INICIO_JUEGO = "Bienvenido al juego de la Guayabita !\n\n ¿Que quieres hacer?";
    private static final ImageIcon ICONO = new ImageIcon("src\\com\\unicode\\util\\guayabas.png");
    private static final ImageIcon UNO = new ImageIcon("src\\com\\unicode\\util\\uno.png");
    private static final ImageIcon DOS = new ImageIcon("src\\com\\unicode\\util\\dos.png");
    private static final ImageIcon TRES = new ImageIcon("src\\com\\unicode\\util\\tres.png");
    private static final ImageIcon CUATRO = new ImageIcon("src\\com\\unicode\\util\\cuatro.png");
    private static final ImageIcon CINCO = new ImageIcon("src\\com\\unicode\\util\\cinco.png");
    private static final ImageIcon SEIS = new ImageIcon("src\\com\\unicode\\util\\seis.png");
    private static String INSTRUCCIONES;
    private static final int VALOR_MINIMO_APUESTA = 200;
    private static int turno;
    private Juego() {
        this.pote = 0;
        this.INSTRUCCIONES = "Sí el jugador saca 1 o 6 entonces pierde la posibilidad de apostar y por ende\n"
                + "cede el turno al otro jugador.\n\n"
                + "Sí por el contrario saca un número del 2 al 5 tiene la posibilidad de apostar por\n"
                + "el pote que hay en juego. Si elige que no quiere apostar cede su turno, pero sí\n"
                + "quiere hacerlo el juego le debe permitir ingresar el monto por el que desea\n"
                + "apostar y luego tirar nuevamente el dado.\n\n"
                + "El jugador puede apostar por la totalidad del pote o por una parte (por ejemplo,\n"
                + "si el pote es de $1200 el jugador puede apostar $1200 o un valor inferior). Se\n"
                + "debe controlar que el jugador si cuente con el valor de la apuesta que desea\n"
                + "realizar.\n\n"
                + "Sí el jugador saca un número mayor al que sacó en la tirada anterior entonces\n"
                + "se lleva el dinero del pote (la parte que apostó). Si por el contrario el jugador\n"
                + "saca un número igual o inferior entonces tendrá que entregar lo que apostó al\n"
                + "pote y así este irá aumentando.\n\n"
                + "Sí después de una jugada el pote queda en $0, el juego vuelve a comenzar con\n"
                + "$200 (Ambos jugadores aportan de a $100).\n\n"
                + "Después de esto al otro jugador se le pregunta si desea lanzar el dado y\n"
                + "comienza su flujo nuevamente.\n\n"
                + "El juego termina cuando uno de los dos jugadores no tenga dinero suficiente\n"
                + "para iniciar una ronda.";
    }

    public static Juego getInstance() {
        return instance;
    }

    public String getTITULO() {
        return TITULO;
    }

    public static String getINSTRUCCIONES() {
        return INSTRUCCIONES;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPote() {
        return pote;
    }

    public void setPote(int pote) {
        this.pote = pote;
    }

    public List<Usuario> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Usuario> jugadores) {
        this.jugadores = jugadores;
    }


    public ArrayList<ImageIcon> getDados() {
        return dados;
    }

    public static ArrayList<ImageIcon> listDice() {
        ArrayList<ImageIcon> dados = new ArrayList<>();
        dados.add(UNO);
        dados.add(DOS);
        dados.add(TRES);
        dados.add(CUATRO);
        dados.add(CINCO);
        dados.add(SEIS);

        return dados;
    }
//    public static void addPlayers(){
//        ArrayList<Usuario> jugadores = new ArrayList<>(2);
//        jugadores.add(getUsuario(Usuario.getInstance().getNombre(), Usuario.getInstance().getApellido(), ));
//    }

    private static boolean verificar = false;

    public void showMenu() {
        boolean verificar = false;

        do {
            int opcion = verOpciones();

            switch (opcion) {
                case 0:
                    logica();
                    break;
                case 1:
                    verInstrucciones();
                    showMenu();
                case 2:
                    verificar = true;
                    break;
            }

        }while (!verificar);
    }


    private static int verOpciones() {
        return JOptionPane.showOptionDialog(null, INICIO_JUEGO, TITULO, JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, ICONO, new String[]{"Jugar", "Ver instrucciones", "Salir"}, null);
    }

    private static String agregarNombreDeJugador() {
        return (String) JOptionPane.showInputDialog(null, "Nombre del jugador"
                , TITULO, JOptionPane.PLAIN_MESSAGE, ICONO, null, null);
    }

    private static int agregarSaldoDeJugador() {
        return Integer.parseInt((String) JOptionPane.showInputDialog(null, "Saldo del jugador, apuesta minima "+VALOR_MINIMO_APUESTA
                , TITULO, JOptionPane.DEFAULT_OPTION, ICONO, null, null));
    }

    public static void verInstrucciones() {
        JOptionPane.showMessageDialog(null, Juego.getINSTRUCCIONES(), TITULO, JOptionPane.INFORMATION_MESSAGE, ICONO);
    }

    /*Mensaje*/
    public static void mensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public static boolean validarNombre(String data) {
        Predicate<String> estaVacio = dato -> dato.trim().isEmpty();
        Predicate<String> datoValido = dato ->
                !estaVacio.test(dato);
        return datoValido.test(data);
    }

    private String agregarNombreVerificado() {
        String nombre;
        do {
            nombre = agregarNombreDeJugador();
        } while (!validarNombre(nombre));
        return nombre;
    }

    private int agregarSaldoVerificado() {
        int saldoJugador;
        do {
            saldoJugador = agregarSaldoDeJugador();
        } while (saldoJugador < VALOR_MINIMO_APUESTA);

        return saldoJugador;
    }

    private Usuario getUsuario() {
        String nombre = agregarNombreVerificado();
        int saldo = agregarSaldoVerificado();
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setSaldo(saldo);
        return usuario;
    }

    private void agregarUsuariosEnLaLista() {
        Usuario usuario = getUsuario();
        jugadores.add(usuario);
    }

    private void ingresoDeUsuarios() {
        for (int i = 0; i < 2; i++) {
            agregarUsuariosEnLaLista();
        }
    }

    public void apuestaJugadores(){
        if (getPote() == 0) {
            jugadores.forEach(jugador -> jugador.setSaldo(jugador.getSaldo() - VALOR_MINIMO_APUESTA));
            setPote(VALOR_MINIMO_APUESTA * jugadores.size());
        }
    }

    private void logica() {

        turno = 0;
        boolean verificar = false;

        ingresoDeUsuarios();
        apuestaJugadores();
        System.out.println(getPote());
        int apuesta = getApuesta(turno);

        switch (apuesta){
            case 0:
                quieroApostar();
                break;
            case 1:
                turno++;
                break;
        }

    }

    private void quieroApostar() {
        int dadoJugador = getApuesta(turno);

    }

    private int getApuesta(int turno) {

        return JOptionPane.showOptionDialog(null, jugadores.get(turno).getNombre() + "," + " el pote actual es de " + getPote() +
                        "\n\n  ¿Deseas lanzar el dado?", TITULO, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, ICONO, new String[]{"Si", "No"}, null);
    }

}
