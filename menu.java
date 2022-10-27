package br.com.game.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.awt.event.*;

public class menu extends tabuleiro implements MouseListener{
	private static final long serialVersionUID = 1L;	
	private boolean fim = false;
	public JButton easy, medium, hard;
	JButton novo;
	ImageIcon imagem;
	private int salva=0;
	public JFrame f = new JFrame();
	public int marca=0;
	public JFrame tela = new JFrame();
	public menu() {
	}
	
	public void telaInicial() {
		Font fonte = new Font("Fonte", Font.BOLD,25);
		Font fontinha = new Font("Fontinha", Font.BOLD,30);
		Font escolha = new Font("Escolha", Font.BOLD,20);
		
		JLabel texto = new JLabel("CAMPO MINADO");
		texto.setBounds(165,0,300,110);
		texto.setFont(fontinha);
		
		JLabel texto1 = new JLabel("Escolha a Dificuldade");
		texto1.setBounds(185,0,500,220);
		texto1.setFont(escolha);
		
		
		tela.setLayout(null);
		
		easy = new JButton("Fácil");
		easy.addMouseListener(this);
		easy.setForeground(Color.black);
		easy.setFont(fonte);
		easy.setBounds(213,170,150,70);
		
		medium = new JButton("Médio");
		medium.addMouseListener(this);
		medium.setForeground(Color.black);
		medium.setFont(fonte);
		medium.setBounds(213,270,150,70);
		
		hard = new JButton("Difícil");
		hard.addMouseListener(this);
		hard.setForeground(Color.black);
		hard.setFont(fonte);
		hard.setBounds(213,370,150,70);
		
		
		tela.add(easy);
		tela.add(medium);
		tela.add(hard);
		tela.add(texto);
		tela.add(texto1);
		
		tela.setTitle("Campo Minado");
		tela.setSize(600,600);
		tela.setLocationRelativeTo(null);
		tela.setResizable(false);
		tela.setVisible(true);
		tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	JPanel p = new JPanel();
	public void inicia(int tam, int fon, int dif) {
		Font fonte = new Font("Fonte", Font.BOLD,fon);
		getContentPane();
		f.setLayout(null);
		
		for(int i=0; i<dif; i++) {
	   		for(int j=0; j<dif; j++) {
	   			botoes[i][j] = new JButton("");
	   			botoes[i][j].addMouseListener(this);
	   			botoes[i][j].setBounds(100,300,100,60);
	   			botoes[i][j].setBackground(Color.DARK_GRAY);
	   			botoes[i][j].setForeground(Color.white);
	   			botoes[i][j].setFont(fonte);
	   			f.add(botoes[i][j]);
	   		}
	    }
		
		f.setLayout(new GridLayout(dif,dif, 5, 5));
	    f.setTitle("Campo Minado"); //titulo
	    f.setSize(tam, tam); //setar o tamanho da tela
	    f.setLocationRelativeTo(null); //inicializa a janela no meio da tela do computador
	    //f.setResizable(false); //bloqueia redimensionameto da tela, nao da pra aumetar-la
	    f.setVisible(true); //define a janela como visivel
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //permissao de encerrar o app	 
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == 1) {
			if(e.getSource() == easy) {
				//salva = facil;
				JOptionPane.showMessageDialog(null, "Em Manutenção!!");
				//inicia(600, 25, salva);
			}
			else if(e.getSource() == medium) {
				salva = medio;
				tela.setVisible(false);
				inicia(600, 25, salva);
				//inicia(1920, 25, salva);
			}
			else if(e.getSource()  == hard) {
				//salva = dificil;
				JOptionPane.showMessageDialog(null, "Em Manutenção!!");
				//inicia(1000, 15, salva);
			}
			
			for(int i=0; i<salva; i++) { //ao apertar os botoes mostra sua info abaixo, ou seja a sua acao
	            for(int j=0; j<salva; j++) {
	                if(e.getSource() == botoes[i][j]) {
	                    if(bombas[i][j]>0) {
	                        botoes[i][j].setText(""+bombas[i][j]);
	                        botoes[i][j].setEnabled(false);
	                        botoes[i][j].setBackground(Color.white);
	                    }
	                    else if(bombas[i][j]== -1) {
	                        botoes[i][j].setText("#");
	                        botoes[i][j].setEnabled(false);
	                    }
	                    else if(bombas[i][j]==0) {
	                    	botoes[i][j].setEnabled(false);
                			if(bombas[i][j]==0) {
                				abrirAoRedor(i,j,salva);
                			}
	                    }
	                }
	            }
	         }
			
	        for(int i=0; i<salva; i++) {
               for(int j=0; j<salva; j++) {
                   if (!fim) {
                       if (e.getSource() == botoes[i][j]) {
                           if(bombas[i][j]>0) {
                               fim = false;
                           }
                           else if(bombas[i][j]==0) {
                               fim = false;
                           }
                           else if(bombas[i][j]== -1) {
                               fim = true;
                               botoes[i][j].setText("#");
                               botoes[i][j].setEnabled(false);
                               JOptionPane.showMessageDialog(null, "Você Perdeu");
                               abreJogo(0);
                           }
                       }
                   } 
                }
	        }
		}
		
		if (e.getButton() == 3) {
			int coord1 = 0, coord2 = 0;
			for(int i=0; i<salva; i++) {
	               for(int j=0; j<salva; j++) {
	            	   if(botoes[i][j]==e.getSource()) {
	            		   coord1 = i;
	            		   coord2 = j;
	            	   }
	               }
    	    }
			if(botoes[coord1][coord2].isEnabled()==true) {
				if(botoes[coord1][coord2].getText()=="") {
					botoes[coord1][coord2].setText("X");
					marca++;
				}
				else if(botoes[coord1][coord2].getText()=="X") {
					botoes[coord1][coord2].setText("");
					marca--;
				}
				int nBombas = (salva * salva)/10;
				if(marca>nBombas) {
					JOptionPane.showMessageDialog(null, "PASSOU DO LIMITE DE MARCAÇÕES");
					botoes[coord1][coord2].setText("");
					marca--;
				}
				if(ganha(coord1, coord2)==1) {
					JOptionPane.showMessageDialog(null, "Você Ganhou!!!");
				}
			}
		}
		
	}
	
	public int ganha(int coord1, int coord2) {
		for(int i=0; i<salva; i++) {
            for(int j=0; j<salva; j++) {
            	if((botoes[i][j].getText()=="")&& bombas[i][j]==-1) {
            		return 0;
            	}
            }
        }
		abreJogo(1);
		return 1;
    }
	
	private void abrirAoRedor(int coord1, int coord2, int tam){
		if((coord1!=-1 && coord1!=tam && coord2!=-1 && coord2!=tam)&& bombas[coord1][coord2]>=0) {
			botoes[coord1][coord2].setEnabled(false);
			if(bombas[coord1][coord2]==0) {
				botoes[coord1][coord2].setText("");
			}
			else {
				botoes[coord1][coord2].setText(""+bombas[coord1][coord2]);
			}
			botoes[coord1][coord2].setBackground(Color.white);
			if(!(bombas[coord1][coord2]>0)) {
				if((coord1!=-1 && coord1!=tam && coord2+1!=-1 && coord2+1!=tam)&& (botoes[coord1][coord2+1].isEnabled()==true)) {
					abrirAoRedor(coord1,coord2+1,tam);				
				}
				if((coord1!=-1 && coord1!=tam && coord2-1!=-1 && coord2-1!=tam)&& (botoes[coord1][coord2-1].isEnabled()==true)) {
					abrirAoRedor(coord1,coord2-1,tam);
				}
				if((coord1+1!=-1 && coord1+1!=tam && coord2!=-1 && coord2!=tam)&& (botoes[coord1+1][coord2].isEnabled()==true)) {
					abrirAoRedor(coord1+1,coord2,tam);
				}
				if((coord1+1!=-1 && coord1+1!=tam && coord2-1!=-1 && coord2-1!=tam)&& (botoes[coord1+1][coord2-1].isEnabled()==true)) {
					abrirAoRedor(coord1+1,coord2-1,tam);
				}
				if((coord1+1!=-1 && coord1+1!=tam && coord2+1!=-1 && coord2+1!=tam)&& (botoes[coord1+1][coord2+1].isEnabled()==true)) {
					abrirAoRedor(coord1+1,coord2+1,tam);
				}
				if((coord1-1!=-1 && coord1-1!=tam && coord2!=-1 && coord2!=tam)&& (botoes[coord1-1][coord2].isEnabled()==true)) {
					abrirAoRedor(coord1-1,coord2,tam);
				}
				if((coord1-1!=-1 && coord1-1!=tam && coord2-1!=-1 && coord2-1!=tam)&& (botoes[coord1-1][coord2-1].isEnabled()==true)) {
					abrirAoRedor(coord1-1,coord2-1,tam);
				}
				if((coord1-1!=-1 && coord1-1!=tam && coord2+1!=-1 && coord2+1!=tam)&& (botoes[coord1-1][coord2+1].isEnabled()==true)) {
					abrirAoRedor(coord1-1,coord2+1,tam);
				}
			}
		}
		else {
			return;
		}
	}
	
	private void abreJogo(int resul) {
		for(int i=0; i<salva; i++) {
	   		for(int j=0; j<salva; j++) {
	   			botoes[i][j].setText(""+bombas[i][j]);
	   			botoes[i][j].setBackground(Color.white);
	   			botoes[i][j].setEnabled(false);
	   			if(bombas[i][j]==0) {
	   				botoes[i][j].setText("");
	   				botoes[i][j].setBackground(Color.white);
	   			}
	   			else if(bombas[i][j]==-1) {
	   				botoes[i][j].setText("#");	
	   				if(resul==1) {
	   					botoes[i][j].setBackground(Color.green);
	   				}
	   				else {
	   					botoes[i][j].setBackground(Color.red);
	   				}
	   			}
	   			//botoes[i][j].doClick();
	   		}
		}
	}
	
	public void setSalva(int salva) {
		this.salva = salva;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
