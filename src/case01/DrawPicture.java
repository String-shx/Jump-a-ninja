package case01;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

//绘制图片方法
@SuppressWarnings("serial")
public class DrawPicture extends JPanel
{ 
	boolean istrue = true;
	Image img;
	People people;
	Background background;
	Board[] board;
	GameMain gamemain;
	//方法的参数传入绘制的内容。人物，跳板和背景。
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
					                       
	    			if(!gamemain.isStop) //如果没有暂停游戏，就更新绘图
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

//	双缓存,这是最难的一个点，百度了很多才知道
	public void update(Graphics g)
	{     //覆盖update方法，截取默认的调用过程
	
	    Image ImageBuffer = createImage(this.getWidth(), this.getHeight());   //创建图形缓冲区  
	    Graphics GraImage = ImageBuffer.getGraphics();       //获取图形缓冲区的图形上下文  
	    paint(GraImage);        //用paint方法中编写的绘图过程对图形缓冲区绘图  
	    GraImage.dispose();     //释放图形上下文资源  
	    g.drawImage(ImageBuffer, 0, 0, this);   //将图形缓冲区绘制到屏幕上  
	}  
	   
	@Override 
	public void paint(Graphics g)
	{ 
		if(gamemain.isStart)
		{
				//画背景图片
				g.drawImage(background.background1, background.x1, background.y1,null);		
				g.drawImage(background.background1, background.x2, background.y2,null);
		        //绘制木板
				for(int i=0;i<6;i++)
		        { 
		        	g.drawImage(board[i].boardimage, board[i].x, board[i].y,null);
		        }
		       //如果火箭存在，那么绘制出火箭。
		        if(gamemain.rocket.istrue)
		        { 
		        	g.drawImage(gamemain.rocket.imagerocket,gamemain.rocket.x, gamemain.rocket.y,null);
				} 
		        
		        //绘制人物
				g.drawImage(people.playerimage, people.x, people.y,null);
				g.setFont(new Font("微软雅黑",Font.BOLD,20));
				g.setColor(Color.WHITE);   
				g.drawString("积分："+ gamemain.grade, 310, 30) ;
				g.drawString("火箭剩余：" + gamemain.rocket.count, 100,30 );			
		}
		else
		{
			g.drawImage(img,0,0,null);
		}
	}

}
