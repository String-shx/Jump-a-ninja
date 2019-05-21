package case01;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//����ͼƬ����
@SuppressWarnings("serial")
public class DrawPicture extends JPanel
{ 
	boolean istrue = true;
	Image img;
	People people;
	Background background;
	Board[] board;
	GameMain gamemain;
	//�����Ĳ���������Ƶ����ݡ��������ͱ�����
	public DrawPicture(People people,GameMain gamemain,Background background) 
	{  
		img = Toolkit.getDefaultToolkit().getImage("images/back4.gif");
		// TODO Auto-generated constructor stub;
		this.people=people;
		this.background=background;
		this.gamemain=gamemain;
		board = gamemain.board;
		doAction();    
	} 
                              
	private void doAction()
	{
		new Thread(){                      
	    	public void run()
	    	{                
	    		this.setPriority( MAX_PRIORITY);
				while(true)  
	    		{
					                       
	    			if(!gamemain.isStop) //���û����ͣ��Ϸ���͸��»�ͼ
	            	{      
	    				repaint();	
	            	}
	            	try {       
	    				sleep(1);
	    			} catch (InterruptedException e) {   	        				
	    				e.printStackTrace();
	    			} 
	            	
	    		}          		
	    	}
	    }.start(); 		
	}

//	˫����,�������ѵ�һ���㣬�ٶ��˺ܶ��֪��
	public void update(Graphics g)
	{     //����update��������ȡĬ�ϵĵ��ù���
	
	    Image ImageBuffer = createImage(this.getWidth(), this.getHeight());   //����ͼ�λ�����  
	    Graphics GraImage = ImageBuffer.getGraphics();       //��ȡͼ�λ�������ͼ��������  
	    paint(GraImage);        //��paint�����б�д�Ļ�ͼ���̶�ͼ�λ�������ͼ  
	    GraImage.dispose();     //�ͷ�ͼ����������Դ  
	    g.drawImage(ImageBuffer, 0, 0, this);   //��ͼ�λ��������Ƶ���Ļ��  
	}  
	   
	@Override 
	public void paint(Graphics g)
	{ 
		if(gamemain.isStart)
		{
				//������ͼƬ
				g.drawImage(background.background1, background.x1, background.y1,null);		
				g.drawImage(background.background1, background.x2, background.y2,null);
		        //����ľ��
				for(int i=0;i<6;i++)
		        { 
		        	g.drawImage(board[i].boardimage, board[i].x, board[i].y,null);
		        }
		       //���������ڣ���ô���Ƴ������
		        if(gamemain.rocket.istrue)
		        { 
		        	g.drawImage(gamemain.rocket.imagerocket,gamemain.rocket.x, gamemain.rocket.y,null);
				} 
		        
		        //��������
				g.drawImage(people.playerimage, people.x, people.y,null);
				g.setFont(new Font("΢���ź�",Font.BOLD,20));
				g.setColor(Color.WHITE);   
				g.drawString("���֣�"+ gamemain.grade, 310, 30) ;
				g.drawString("���ʣ�ࣺ" + gamemain.rocket.count, 100,30 );			
		}
		else
		{
			g.drawImage(img,0,0,null);
		}
	}

}
