package com.unicode.model;

import javax.swing.*;
import java.util.ArrayList;

public class Usuario {

    private int id;
    private String nombre;
    private String apellido;
    private int saldo;
    private boolean turno;
    public final static int DICE_MINOR = 1; // Dado menor
    public final static int DICE_GREATER = 6; // Dado mayor
    private Juego juego;
    private static  Usuario usuario = new Usuario();

    public Usuario(){
    }

    public static Usuario getInstance(){
        return usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
            this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
            this.apellido = apellido;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        if (saldo > 0) {
            this.saldo = saldo;
        }
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public static int randomOneToSix() {
        return (int)Math.floor( Math.random() * DICE_GREATER);
    }
    /**Verificar Lanzamiento
     * Si el dado es 1 ó 6, en el primer intento
     * Cede el turno*/

    public static int numberDice; // Numero de dado

    static ArrayList<ImageIcon> dados = Juego.listDice();
    private static boolean isSix(int dado) {
        return dados.get( DICE_GREATER - 1 ).equals(dados.get(dado));
    }
    private static boolean isOne(int dado) {
        return dados.get( DICE_MINOR - 1 ).equals(dados.get(dado));
    }
    private static boolean isOneOrSix() {
        return isOne(numberDice) || isSix(numberDice);
    }

    public static Boolean firstLaunch(){ //Lanzamiento
        numberDice = randomOneToSix();
        Boolean verificar = false;
        if (isOneOrSix()){
           verificar = true;
        }
        return verificar;
    }

    public static void secondLaunch(){
        numberDice = randomOneToSix();
    }
}

/**
 * 1. Agregar dados
 * 2. Lanzar dado
 * 3. Ceder el turno*/
