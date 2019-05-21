package case01;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

//忍者
public class People  
{
	GameMain gamemain;
    int x;//人物的大小
    int y;
    int speedx;//水平的速度
    float acceleration;//上升的加速度
    double  gravity;
    Player player;  
    //人物状态图片,见名知意
    Image playerimage;
    Image jumpleft;
    Image jumpright;
    Image jumpupleft;
    Image jumpupright;
    Image flyleft;
    Image flyright; 
    Image rocket;
    int rockets;  //火箭数，人物触碰火箭后会增加
    boolean direction;
    boolean isDead;
//    int keyspeed = 0;//键盘控制一个加速度

	public  People(GameMain gamemain) throws FileNotFoundException 
	{ 
//		FileInputStream MPT = new FileInputStream("music/Background.mp3");
		this.gamemain=gamemain;
		rockets=0;
		direction=false;
		isDead=true;
	    x=400;
		y=400;
		speedx=0;
		acceleration=-8;
		gravity=0.1;
		//人物状态的七种图片
		jumpleft=Toolkit.getDefaultToolkit().getImage("images/jumpleft.png");
		jumpright=Toolkit.getDefaultToolkit().getImage("images/jumpright.png");
		jumpupleft=Toolkit.getDefaultToolkit().getImage("images/jumpupleft.png");
		jumpupright=Toolkit.getDefaultToolkit().getImage("images/jumpupright.png");
		flyleft=Toolkit.getDefaultToolkit().getImage("images/flyleft.png");
		flyright=Toolkit.getDefaultToolkit().getImage("images/flyright.png");
		rocket=Toolkit.getDefaultToolkit().getImage("images/rocket.png");
		playerimage=flyleft;//初始化的照片
	}
	//重新开始游戏时，初始化信息。
	public void restart()
	{
		direction=false;
		isDead = false;
	    x = 220;
		y = 400;
		speedx = 0;//人物移动的水平速度
		acceleration=-5;
		gravity=0.1;	
	}
	//对人物的更新
	public void update() throws FileNotFoundException
	{		
		//图片更新
		//如果有火箭数
		if(rockets > 0)
		{
			playerimage=rocket;
		}
		//人物向上的时候，判断不同的向上速度。
		//再根据左右方向，获得图片
		else if(acceleration < 0)//负数表示向上
		{
			if(acceleration<-3)
			{
				 if(direction) //右侧表示1
					 playerimage=flyright;
				 else
					 playerimage=flyleft;
			}
			else
			{
		         if(direction)
		        	 playerimage=jumpupright;
		         else
			        playerimage=jumpupleft;
		     }			
		}
		else
		{
			if(direction)
				playerimage=jumpright;
			else
				playerimage=jumpleft;
		}
	
		//位置更新
		x += speedx*2;
		//出了边界的时候设置的值
		if(x < 20)
			x=20;
		if(x > 460)
			x=460;
		if(y < 0)
			y = 0;
//		System.out.println(y);
		//如果火箭数大于0，人物加速度一定，人物以固定速度向上移动，
		//每次更新火箭数减少
		
		if(rockets>0) 
		{
			  rockets--;
			  acceleration=-7;//有火箭的话，速度加快
		}
		else	//没有火箭的时候，就掉落下来，然后要么碰到板子，或者掉下去直接死亡
		{
			y += acceleration+gravity;
			
			if(acceleration < 6)
				acceleration += gravity;
		}
//		System.out.println(acceleration);
		//判断是否死亡
		if(y>630)
		{
			
			//播放死亡音效。
			 new Thread()
			 {
		        	public void run()
		        	{

			            try {
			            	
			            	FileInputStream MPT = new FileInputStream("music/fall.mp3");
			         	    player=new Player(MPT);
							player.play();
							
			            				         							
						} catch (JavaLayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}        	
			        	try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		
			        				        	
		        	}
		        }.start();
		        new GameOver(gamemain);//实例化游戏结束窗体
				isDead=true;
				
			
		}
	}
	//跳跃方法
	public void jumpup()//落到板子之上后就会调用，依据人的加速度来判断它的弹起高度
	 {	
		//根据人物在屏幕的位置，确定人物跳跃的加速度
		acceleration=-(y/80)-1;//忍者从木板上重新跳起来的速度
		//播放跳跃音效		 
		
			new Thread()
			{
		        	public void run()
		        	{
			            try 
			            {
			            	//这里为了避免音效太过枯燥。我根据不同的跳跃加速度，设置了两种不同的跳跃音效，
			            	FileInputStream MPT;
			            	if(acceleration>-4)
			            		MPT = new FileInputStream("music/jump.mp3");
			            	else
			            		MPT = new FileInputStream("music/highjump.mp3");
			         	    player=new Player(MPT);
							player.play();
						}  catch (JavaLayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}        	
			        	try 
			        	{
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		        	}
		        }.start();
		}
	
}
