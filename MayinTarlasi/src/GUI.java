import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame  {
	
	public boolean resetleyici=false;
	public boolean flg=false;

	Date startDate=new Date();
	Date endDate;
	
	
	int p=0;
  int aralik=1;
  
  String zaferMesaj="HENÜZ DEÐÝL!";
  public int mx=-100;
   public int my=-100;
   
   public int smileyX=605;
   public int smileyY=5;
   
   public int zMX=740;
   public int zMY=-50;
   
   public int smileyCenterX=smileyX+35; 
   public int smileyCenterY=smileyY+35; 
   
   public int flgX=445;
   public int flgY=5;
   public int flgCenterX=flgX+35;
   public int flgCenterY=flgY+35;
   
   public int aralikX=90; 
   public int aralikY=10; 
   
   public int minusX=aralikX+160;
   public int minusY=aralikY;
    
  public int plusX=aralikX+240;
  public int plusY=aralikY;
   
   public boolean mutluluk=true;
   public boolean zafer=false;
   public boolean yenilgi=false;
   
   public int zamanX=1130;
   public int zamanY=5;
   public int sn=0;
   
   
   
   
   Random rand=new Random();
   
   int [][] mayinlar=new int[16][9];
   int [][] komsular=new int[16][9];
   boolean [][] belli_etmek=new boolean [16][9];
   boolean [][] isaretli=new boolean [16][9];
	boolean[][] flagged= new boolean[16][9];
  
	public GUI() {
		this.setTitle("MAYIN TARLASI");
		this.setSize(1286, 829);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
				
				if(rand.nextInt(100)<20) {
					mayinlar[i][j]=1;
					
				}else {
					mayinlar[i][j]=0;
				}
				belli_etmek[i][j]=false ;
			 }	
		}
		
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
				 p=0;
				for(int m=0; m<16; m++) {
					for(int n=0; n<9;n++) {
						if(!(m==i && n==j)) {
							if(isN(i,j,m,n)==true) {
								p++;
							}
						}
						
					}
					komsular[i][j]=p;
				}
			 }		
		}
		Tahta tahta=new Tahta();
		this.setContentPane(tahta);
		Move move=new Move();
		this.addMouseMotionListener(move);
		Click click =new Click();
		this.addMouseListener(click);
	}
	public class Tahta extends JPanel{
		public void paintComponent(Graphics g) {
			g.setColor(Color.DARK_GRAY );
			g.fillRect(0, 0, 1280, 800);	
			for(int i=0; i<16;i++) {
				for(int j=0;j<9;j++) {
					g.setColor(Color.GRAY);
					
					if(belli_etmek[i][j]==true) {
						g.setColor(Color.white);
						if(mayinlar[i][j]==1) {
							g.setColor(Color.red);
						}
					}
					if(mx>=aralik+i*80 &&  mx<i*80+80-aralik && my>=aralik+j*80+80+106 && my<j*80+186-aralik) {
						g.setColor(Color.lightGray);
					}
					g.fillRect(aralik+i*80, aralik+j*80+80, 80-2*aralik, 80-2*aralik);		
					if(belli_etmek[i][j]==true) {
						g.setColor(Color.black);
						if(mayinlar[i][j]==0 && komsular[i][j]!=0) {
							 if( komsular[i][j]==1) {
								g.setColor(Color.blue);
							}else if(komsular[i][j]==2) {
								g.setColor(Color.green);
							}else if(komsular[i][j]==3) {
								g.setColor(Color.red);
							}else if(komsular[i][j]==4) {
								g.setColor(new Color(0,0,128));
							}else if(komsular[i][j]==5) {
								g.setColor(new Color(178,34,34));
							}else if(komsular[i][j]==6) {
								g.setColor(new Color(72,209,204));
							}else if(komsular[i][j]==7) {
								g.setColor(Color.black);
							}else if(komsular[i][j]==8) {
								g.setColor(Color.DARK_GRAY);
							}
							
							g.setFont(new Font("Tahoma", Font.BOLD, 40));
							g.drawString(Integer.toString(komsular[i][j]), i*80+27, j*80+80+55);
						}else if(mayinlar[i][j]==1){
							g.fillRect(i*80+10+20, j*80+80+20, 20, 40);
							g.fillRect(i*80+20, j*80+80+10+20, 40, 20);
							g.fillRect(i*80 +5+20, j*80+80+5+20, 30, 30);
							g.fillRect(i*80+38 , j*80+80+15, 4, 50);
							g.fillRect(i*80+15 , j*80+80+38, 50, 4);
						}
					
					}
					
					if(flagged[i][j]==true) {
						
						g.setColor(Color.BLACK);
						g.fillRect(i*80+32+5, j*80+80+10+5, 5, 40);
						g.fillRect(i*80+20+5, j*80+80+50+5, 30, 10 );
						g.setColor(Color.red);
						g.fillRect(i*80+16+5, j*80+80+15+5, 20, 15 );
						g.setColor(Color.black);
						g.drawRect(i*80+16+5, j*80+80+15+5, 20, 15 );
						g.drawRect(i*80+17+5, j*80+80+16+5, 18, 13 );
						g.drawRect(i*80+18+5, j*80+80+17+5, 16, 11 );
					}
				}
			}
			
			
			// ARALIK + - 
			g.setColor(Color.black);
			g.fillRect(aralikX, aralikY, 300, 60);
			
			g.setColor(Color.white);
			g.fillRect(minusX+5, minusY+10, 40, 40);
			g.fillRect(plusX+5, plusY+10, 40, 40);
			g.setFont(new Font("Tahoma",Font.PLAIN,35));
			g.drawString("ARALIK", aralikX+20, aralikY+45);
			
			g.setColor(Color.black);
			g.fillRect(minusX+15, minusY+27, 20, 6);
			g.fillRect(plusX+15, plusY+27, 20, 6);
			g.fillRect(plusX+22, plusY+20, 6, 20);
			
			g.setColor(Color.white);
			g.setFont(new Font("Tahoma",Font.PLAIN,30));
			if(aralik<10) {
				g.drawString("0"+Integer.toString(aralik),minusX+49,minusY+40);
			}else {
			g.drawString(Integer.toString(aralik),minusX+49,minusY+40);
			}
			// GÜLEN YÜZ BOYAMA
			
			g.setColor(Color.yellow);
			g.fillOval(smileyX, smileyY, 70, 70);
			g.setColor(Color.black);
			g.fillOval(smileyX+15, smileyY+20, 10, 10);
			g.fillOval(smileyX+45, smileyY+20, 10, 10);
			
			if(mutluluk==true) {
				g.fillRect(smileyX+20, smileyY+50, 30, 5);
				g.fillRect(smileyX+17, smileyY+45, 5, 5);
				g.fillRect(smileyX+48, smileyY+45, 5, 5);
			}else {
				g.fillRect(smileyX+20, smileyY+45, 30, 5);
				g.fillRect(smileyX+17, smileyY+50, 5, 5);
				g.fillRect(smileyX+48, smileyY+50, 5, 5);
			}
			
			// bayrak boyama
			
			g.setColor(Color.black);
			g.fillRect(flgX+32, flgY+10, 5, 40);
			g.fillRect(flgX+20, flgY+50, 30, 10 );
			g.setColor(Color.red);
			g.fillRect(flgX+16, flgY+15, 20, 15 );
			g.setColor(Color.black);
			g.drawRect(flgX+16, flgY+15, 20, 15 );
			g.drawRect(flgX+17, flgY+16, 18, 13 );
			g.drawRect(flgX+18, flgY+17, 16, 11 );
			
			if(flg==true) {
				g.setColor(Color.red);
			}
			g.drawOval(flgX, flgY, 70, 70);
			g.drawOval(flgX+1, flgY+1, 68, 68);
			g.drawOval(flgX+2, flgY+2, 66, 66 );
			
			// ZAMAN SAYACI 
			g.setColor(Color.black);
            g.fillRect(zamanX, zamanY, 140, 70);
            if(yenilgi==false && zafer==false) {
           sn=  (int) ((new Date().getTime()- startDate.getTime()) /1000);
            }
           if(sn>999) {
        	   sn=999;
           }
           g.setColor(Color.white);
           if(zafer==true) {
        	   g.setColor(Color.green);  
           }else if(yenilgi==true) {
        	   g.setColor(Color.red);  
           }
           g.setFont(new Font("Tahoma",Font.PLAIN,80));
           
           if(sn<10) {
        	   g.drawString("00"+Integer.toString(sn), zamanX, zamanY+65);
           }else if(sn<100) {
        	   g.drawString("0"+Integer.toString(sn), zamanX, zamanY+65);
           }else {
        	   g.drawString(Integer.toString(sn), zamanX, zamanY+65);
           }
           
           //ZAFER MESAJI
           if(zafer==true) {
        	   g.setColor(Color.GREEN);
        	   zaferMesaj="YOU WÝN";
        	   
        	   
           }else if(yenilgi==true) {
        	   g.setColor(Color.red);
        	   zaferMesaj="YOU LOSE";
           }
           if(zafer==true || yenilgi==true) {
        	   zMY=-50+ (int) (new Date().getTime()-endDate.getTime()) /10;
        	   if(zMY>65) {
        		   zMY=65;
        	   }
        	   g.setFont(new Font("Tahoma",Font.PLAIN,70));
        	   g.drawString(zaferMesaj, zMX, zMY);
           }
	}
	
	}
	public class Move implements MouseMotionListener{
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub			
			mx=	e.getX();
			my=	e.getY();
		}
		
	}
	public class Click implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
			mx=	e.getX();
			my=	e.getY();
			
			if(mx>=minusX+20  && mx < minusX+60 && my>=minusY+20  && my < minusY+60) {
				aralik--;
				if(aralik <1) {
					aralik=1;
				}
			}else if(mx>=plusX+20  && mx < plusX+60 && my>=plusY+20  && my < plusY+60) {
				aralik++;
				if(aralik>20) {
					aralik=20;
				}
			}
		
			if(inBoxX()!=-1 && inBoxY()!=-1) {
				 System.out.println("Fare ["+ inBoxX()+" ,"+inBoxY()+"], Komþu mayýn sayýsý: "+ komsular[inBoxX()][inBoxY()]);
				 if(flg==true && belli_etmek[inBoxX()][inBoxY()]==false) {
				  if(flagged[inBoxX()][inBoxY()]==false) {
					  flagged[inBoxX()][inBoxY()]=true;
				  }else {
					  flagged[inBoxX()][inBoxY()]=false;
				  }
				 }else {
					 if(flagged[inBoxX()][inBoxY()]==false) {
						 belli_etmek[inBoxX()][inBoxY()]=true; 
					 }
					 
				 }
				  
			}else {
				System.out.println("Ýmleç herhangi bir kutunun içinde deðil !");
			}	
			if(inSmiley()==true) {
				System.out.println("imleç gülen yüzün içinde !");
			}else {
				System.out.println("imleç gülen yüzün içinde deðil !");
			}
			
			if(inSmiley()==true ) {
				resetAll();
				System.out.println("Gülen yüz içinde= true!");
			}
			if(inFlagger()==true) {
				if(flg==false) {
					flg=true;
					System.out.println("Bayrak içinde = true!");
				}else {
					flg=false;
					System.out.println("Bayrak içinde deðil = false!");
				}
					
			}
			
		
			
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
	
	public void zaferDurumKontrol() {
		
		if(yenilgi==false) {
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
			      if(belli_etmek[i][j]==true && mayinlar[i][j]==1) {
			    	  yenilgi=true;
			    	  mutluluk=false; 	  			
			    	  endDate=new Date();
			      }
	}
		}
		}
		if(acikKutu()>=144-toplamMayin() && zafer==false) {
			zafer=true;
			 endDate=new Date();
			
		}
		
	}
	public int toplamMayin() {
	      int toplam =0;
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
			      if(mayinlar[i][j]==1) {
			    	  toplam++;
			    	  
			      }
			 }	
		}
		return toplam;
	}
	public int acikKutu() {
		int toplam =0;
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
			      if(belli_etmek[i][j]==true) {
			    	  toplam++;
			      }
			 }
	}
			return toplam;
	}
	
	public void resetAll() {
		resetleyici=true;
		
		flg=false;
		startDate =new Date();
		
         zMY=-50;
         
         zaferMesaj="HENÜZ DEÐÝL!";
		
		mutluluk=true;
		zafer=false;
		yenilgi=false;
		
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
				
				if(rand.nextInt(100)<20) {
					mayinlar[i][j]=1;
					
				}else {
					mayinlar[i][j]=0;
				}
				belli_etmek[i][j]=false ;
				isaretli[i][j]=false;
			 }	
		}
		
		for(int i=0; i<16;i++) {
			 for(int j=0;j<9;j++) {
				 p=0;
				for(int m=0; m<16; m++) {
					for(int n=0; n<9;n++) {
						if(!(m==i && n==j)) {
							if(isN(i,j,m,n)==true) {
								p++;
							}
						}
						
					}
					komsular[i][j]=p;
				}
			 }
			 resetleyici=false;	
		}
	}
	
	public boolean inSmiley() {
		
		int dif=(int) Math.sqrt(Math.abs(mx-smileyCenterX)*Math.abs(mx-smileyCenterX)+Math.abs(my-smileyCenterY)*Math.abs(my-smileyCenterY));
		if(dif<35) {
			return true;
			
		}
		return false;
		
	}
	
public boolean inFlagger() {
		
		int dif=(int) Math.sqrt(Math.abs(mx-flgCenterX)*Math.abs(mx-flgCenterX)+Math.abs(my-flgCenterY)*Math.abs(my-flgCenterY));
		if(dif<35) {
			return true;
			
		}
		return false;
		
	}
	public int inBoxX() {
		for(int i=0; i<16;i++) {
			for(int j=0;j<9;j++) {
				if(mx>=aralik+i*80 &&  mx<aralik+i*80+80-2*aralik && my>=aralik+j*80+80+26 && my<aralik+j*80+26+80+80-2*aralik) {
					return i;
				}
				 
			}
		}
		
		return -1;
	}
public int inBoxY() {
	for(int i=0; i<16;i++) {
		for(int j=0;j<9;j++) {
			if(mx>=aralik+i*80 &&  mx<aralik+i*80+80-2*aralik && my>=aralik+j*80+80+26 && my<aralik+j*80+26+80+80-2*aralik) {
				return j;
			}
			 
		}
	}
		
		return -1;
	}
public boolean isN(int mX, int mY, int cX, int cY) {
	if(mX-cX<2 && mX-cX>-2 && mY-cY<2 && mY-cY >-2 && mayinlar[cX][cY]==1 ) {
		return true;
	}
	return false;
}
}
