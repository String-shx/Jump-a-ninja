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
     Image imagerocket;//�����ͼƬ
     People people;
     Board board;
     Player player;
     int x;
     int y;
     boolean istrue;//����Ƿ���ڡ�
     boolean isChoose;//�Ƿ�ѡ�л��
     int count;//�����ܹ��ܹ��õ��Ļ����
     //���ʱһֱ���еģ���JunmpGame����ʵ������һ�������Ȼ����ͨ��istrue����жϵ����Ƿ���ƻ��ͼƬ���Ƿ��û����������ײ
     //���������һ�����壬��Ϊ������������ϣ����λ��Ҳ�Ǹ�������ı��
	 public Rocket(Board board,People people)  
	 {
		imagerocket=Toolkit.getDefaultToolkit().getImage("images/flyup.png");
	    istrue=true;  //��ʼ��ʱ����һ�����
	    isChoose = false;
		this.board=board;
		this.people=people;
		x=board.x+10; //����Ǵ�����ľ��֮�ϵ�
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
	
	//�Ի��λ�õĸ���
	public void update()
	{
		x = board.x+10;
		y = board.y-70;
		//��������ڣ����������������ʱ����û����
		if(isChoose&&istrue&&people.x-40<x&&people.x+50>x
				&&people.y-50<y&&people.y+70>y&&people.rockets<=0)
		{
			count --;
			people.rockets=(int)((Math.random()+1)*200);
		    //�����ﴥ�������ʱ�����Ż����Ч��   
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
		//�������⡱���λ�ô�����Ļλ��ʱ���������Ƿ����			
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
