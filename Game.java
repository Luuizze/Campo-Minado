package br.com.game.app;

//Class principal do jogo
public class Game {
	public static void main(String args[] ) {
        new Game();
    }

	protected Game() {
		menu m = new menu();
		m.telaInicial();
    }
    
}