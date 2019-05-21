package case01;
import java.awt.Image;
import java.awt.Toolkit;

//这个是跳板。
public class Board  
{
   int x;
   int y;
   int acceleration;
   Rocket rocket;
   People people;
   Image boardimage;
   int sum = 0; 
   public Board(People people) 
	{
		// TODO Auto-generated constructor stub
		this.people=people;	
		//这里初始化的时候随机出他的位置	
		x=(int)((Math.random()*400)+20);
		y=(int)((Math.random()*300)+200);
		boardimage=Toolkit.getDefaultToolkit().getImage("images/board.png");
		acceleration = 3;
		action();
	}
	
	private void action()
	{
		new Thread()
		{
			public void run()
			{
				while(true)
					{
						update();
						try 
						{
							sleep(10);
						}
						catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				
			}
		}.start();	
	}

	//重新开始游戏时重新初始化跳板的位置信息
	public void restart()
	{
		x=(int)((Math.random()*400)+20);
		y=(int)((Math.random()*300)+200);
		boardimage = Toolkit.getDefaultToolkit().getImage("images/board.png");
		acceleration = 3;
	}
	
	//跟新跳板的信息。人物向上跳跃时，跳板往下移动，利用视觉让人感觉人物是一直往上跳时，似乎有一个镜头移动，
	public void update()
	{
		//判断人物跳跃条件：1.人物是往下降落的。2.人物的挨着跳板
	    if(people.acceleration>0)//忍者下落的时候，板子不动
	    {
	    	//这就是碰到板子的条件
	    	if(people.y+45<y&&people.y+60>y&&people.x-60<x&&people.x+20>x)
	    		people.jumpup();
	    }
		
		if(people.acceleration<0)//	忍者上升的时候，板子下降
		{
			acceleration =(int)(-people.acceleration*3);//板子下降的速度与忍者的上升速度有关
		}   
		else 
		{
			acceleration = 0;
		}
		
		y += acceleration;
		
		if(!people.gamemain.isStop&&people.gamemain.isStart)//如果游戏没有暂停，那么跳板向下移动，成绩就增加。看起就像是镜头下移，成绩就会增加。
		{
			sum += acceleration;	
			people.gamemain.grade = sum/100; 
			if(people.isDead)
			{
				people.gamemain.grade = sum/100;
				sum = 0;
			}			
		}
		//当跳板位置超过屏幕时，重新获得一个坐标。
		if(y>610)
		{
//			
			y=(int)(-Math.random()*400)+150;
			x=(int)((Math.random()*400)+20);
		}		
	}
}