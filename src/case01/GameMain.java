package case01;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**主方法。实例化各种类。*/

public class GameMain 
{       
	 int grade;  //成绩 
	 boolean isStart = false;//游戏是否开始了
     boolean isStop = false; //游戏停止位false
     Player player1,player2;   //MP3播放器    
     People people; //人物    
     Board[] board=new Board[6]; //六个跳板
     Rocket rocket;  //火箭 
     Background background;

	public GameMain() throws FileNotFoundException, JavaLayerException
	{       
		grade = 0;	
		people=new People(this);
		background = new Background(people);
		DrawPicture drawer = new DrawPicture(people,this,background);
		
		FileInputStream MPT1 = new FileInputStream("music/main.mp3");
		FileInputStream MPT2= new FileInputStream("music/background.mp3");
		
		player1 =new Player(MPT1);
		player2 = new Player(MPT2);
		
		 //木板对象     
		for(int i=0;i<6;i++) 
		{
			board[i]=new Board(people);
		}
		//火箭对象           
		rocket = new Rocket(board[1], people);
				
		GameFrame frame= new GameFrame();
		frame.add(drawer);		
		             
		frame.addKeyListener(new GameControl(this,people));
		//将三个线程封装成为一个方法
		action(drawer);	
	}  
        	      
	private void action(final DrawPicture drawer)
	{			
	     new Thread()
	     {     	
	        	public void run()
	        	{
	        		while(true)
	        		{
	        			if(!isStart)//如果游戏没开始了 ,只出现界面
	        			{
	        				try
							{
								player1.play();
							} catch (JavaLayerException e)
							{
								// TODO Auto-generated catch block
								e.printStackTrace();
							} 
	        			}      			
	                    try {
	        				sleep(10);
	        			} catch (InterruptedException e) {
	        				// TODO Auto-generated catch block
	        				e.printStackTrace();
	        			}
	                }
	        	}
	        }.start();		
		
		//音乐 的线程  
        new Thread()
        {     	
        	public void run()
        	{
        		while(true)
        		{
        			if(isStart&&!people.isDead)//如果游戏开始了 ,播放背景音乐
        			{
        				
        				try
						{      				
        					player1.close();      					      					
        					player2.play();
						} catch (JavaLayerException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
        			}      			
                    try {
        				sleep(10);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
                }
        	}
        }.start();
                   
       
//        更新人物的位置的线程
        new Thread(){
			public void run()
			{
				while(true)
				{       //如果没有暂停游戏，或者人物没有死亡，则不断跟新人物的位置
					if(isStart&&!isStop&&!people.isDead)
					{
						try
						{
							people.update();
						} catch (FileNotFoundException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try 
					{
						sleep(10);	
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
			}				 
		}.start();	
	}
}
