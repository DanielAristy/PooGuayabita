package com.unicode;

import com.unicode.model.Juego;

public class Main {

    public static void main(String[] args) {
        Juego juego = Juego.getInstance();
        juego.showMenu();
    }
}
