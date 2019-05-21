package case01;
import java.awt.Image;
import java.awt.Toolkit;

//��������塣
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
		//�����ʼ����ʱ�����������λ��	
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

	//���¿�ʼ��Ϸʱ���³�ʼ�������λ����Ϣ
	public void restart()
	{
		x=(int)((Math.random()*400)+20);
		y=(int)((Math.random()*300)+200);
		boardimage = Toolkit.getDefaultToolkit().getImage("images/board.png");
		acceleration = 3;
	}
	
	//�����������Ϣ������������Ծʱ�����������ƶ��������Ӿ����˸о�������һֱ������ʱ���ƺ���һ����ͷ�ƶ���
	public void update()
	{
		//�ж�������Ծ������1.���������½���ġ�2.����İ�������
	    if(people.acceleration>0)//���������ʱ�򣬰��Ӳ���
	    {
	    	//������������ӵ�����
	    	if(people.y+45<y&&people.y+60>y&&people.x-60<x&&people.x+20>x)
	    		people.jumpup();
	    }
		
		if(people.acceleration<0)//	����������ʱ�򣬰����½�
		{
			acceleration =(int)(-people.acceleration*3);//�����½����ٶ������ߵ������ٶ��й�
		}   
		else 
		{
			acceleration = 0;
		}
		
		y += acceleration;
		
		if(!people.gamemain.isStop&&people.gamemain.isStart)//�����Ϸû����ͣ����ô���������ƶ����ɼ������ӡ���������Ǿ�ͷ���ƣ��ɼ��ͻ����ӡ�
		{
			sum += acceleration;	
			people.gamemain.grade = sum/100; 
			if(people.isDead)
			{
				people.gamemain.grade = sum/100;
				sum = 0;
			}			
		}
		//������λ�ó�����Ļʱ�����»��һ�����ꡣ
		if(y>610)
		{
//			
			y=(int)(-Math.random()*400)+150;
			x=(int)((Math.random()*400)+20);
		}		
	}
}