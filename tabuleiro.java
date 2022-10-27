package br.com.game.app;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.Random;

public abstract class tabuleiro extends JFrame implements constant{
	private static final long serialVersionUID = 1L;
	public int[][] bombas;
	//private int salva=0;
	int dificuldade;
	public JButton[][] botoes;
	public tabuleiro() {
		botoes = new JButton[medio][medio];
		bombas = new int [medio][medio];
        iniciaBombas(medio); // coloca 0 em todas as posições do tabuleiro de bombas
        sorteiaBombas(medio); //coloca, aleatoriamente, dificuldade bombas no tabuleiro de bombas
        preencheDicas(medio);//preenche o tabuleiro de bombas com o número de bombas vizinhas
	}
	
	public void iniciaBombas(int tam) {
		for(int i=0; i<tam; i++) {
			for(int j=0; j<tam; j++) {
				bombas[i][j] = 0;
			}
		}
	}
	
	public void sorteiaBombas(int tam) {
		int cont=0;
		Random rand = new Random();
		int nBombas = (tam * tam)/10;
		while(cont<nBombas) {
			int aux1 = rand.nextInt(tam);
			int aux2 = rand.nextInt(tam);
			if(bombas[aux1][aux2]==0) {
				bombas[aux1][aux2] = -1;
				cont++;
			}
		}
	}
	
	public void preencheDicas(int tam){
		
		for(int a=0; a<1; a++) { //Canto Esquerda Cima
			for(int b=0; b<1; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=0; c<=1; c++) {
						for(int d=0; d<=1; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=tam-1; a<tam; a++) { //Canto Esquerda Baixo
			for(int b=0; b<1; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=-1; c<=0; c++) {
						for(int d=0; d<=1; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=0; a<1; a++) { //Canto Direita Cima
			for(int b=tam-1; b<tam; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=0; c<=1; c++) {
						for(int d=-1; d<=0; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=tam-1; a<tam; a++) { //Canto Direita Baixo
			for(int b=tam-1; b<tam; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=-1; c<=0; c++) {
						for(int d=-1; d<=0; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=0; a<1; a++) { //Teto
			for(int b=1; b<=tam-2; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=0; c<=1; c++) {
						for(int d=-1; d<=1; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=1; a<=tam-2; a++) { //Parede Esquerda
			for(int b=0; b<1; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=-1; c<=1; c++) {
						for(int d=0; d<=1; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=tam-1; a<tam; a++) { //Piso
			for(int b=1; b<=tam-2; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=-1; c<=0; c++) {
						for(int d=-1; d<=1; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
		for(int a=1; a<=tam-2; a++) { //Parede Direita
			for(int b=tam-1; b<tam; b++) {
				if(bombas[a][b]!= -1) {
					for(int c=-1; c<=1; c++) {
						for(int d=-1; d<=0; d++) {
							if(bombas[a+c][b+d] == -1) {
								bombas[a][b]++;
							}
						}
					}
				}
			}
		}
		
        for(int i=1; i<tam-1; i++) { //Miolo
            for(int j=1; j<tam-1; j++) {
                if(bombas[i][j]!= -1) {
                    for(int l=-1; l<=1; l++) {
                        for(int g=-1; g<=1; g++) {
                            if(bombas[i+l][j+g] == -1) {
                                bombas[i][j]++;
                            }
                        }
                    }
                }
            }
        }
    }
	/*
	public int getSalva() {
		return this.getTabuleiro;
	}*/

}
