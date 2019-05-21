package case01;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Rocket 
{
	 GameMain gamemain;
     Image imagerocket;//火箭的图片
     People people;
     Board board;
     Player player;
     int x;
     int y;
     boolean istrue;//火箭是否存在。
     boolean isChoose;//是否选中火箭
     int count;//用于总共能够得到的火箭数
     //火箭时一直都有的，在JunmpGame里面实例化了一个火箭。然而我通过istrue这个判断的是是否绘制火箭图片和是否让火箭与人物碰撞
     //火箭依赖于一个跳板，因为火箭是在跳板上，火箭位置也是跟着跳板改变的
	 public Rocket(Board board,People people)  
	 {
		imagerocket=Toolkit.getDefaultToolkit().getImage("images/flyup.png");
	    istrue=true;  //初始化时出现一个火箭
	    isChoose = false;
		this.board=board;
		this.people=people;
		x=board.x+10; //火箭是存在于木板之上的
		y=board.y-70;
		count = 10;
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
						
						try {
							sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					}
				}
				
			}.start();		
	}
	
	//对火箭位置的更新
	public void update()
	{
		x = board.x+10;
		y = board.y-70;
		//当火箭存在，并且人物碰到火箭时，获得火箭数
		if(isChoose&&istrue&&people.x-40<x&&people.x+50>x
				&&people.y-50<y&&people.y+70>y&&people.rockets<=0)
		{
			count --;
			people.rockets=(int)((Math.random()+1)*200);
		    //当人物触碰到火箭时，播放火箭音效，   
				new Thread()
				{
		        	public void run()
		        	{  		
		        		 try 
		        		 {	 	        				 
	        				 FileInputStream MPT = new FileInputStream("music/rocket.mp3");
	        				 player=new Player(MPT);
	        				 if(people.gamemain.isStart)
	        					 	player.play();	 
						 } catch (JavaLayerException e1)
		        		 {
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
			   istrue=false;
		//当“虚拟”火箭位置大于屏幕位置时，随机火箭是否存在			
		}
		
		if(count > 0)
		{
			if(people.rockets == 0 )
			{				
				istrue = true;
			}
			else
			{
				istrue = false;
			}
		}			
	}
	
}
