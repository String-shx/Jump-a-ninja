package case01;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

//����
public class People  
{
	GameMain gamemain;
    int x;//����Ĵ�С
    int y;
    int speedx;//ˮƽ���ٶ�
    float acceleration;//�����ļ��ٶ�
    double  gravity;
    Player player;  
    //����״̬ͼƬ,����֪��
    Image playerimage;
    Image jumpleft;
    Image jumpright;
    Image jumpupleft;
    Image jumpupright;
    Image flyleft;
    Image flyright; 
    Image rocket;
    int rockets;  //����������ﴥ������������
    boolean direction;
    boolean isDead;
//    int keyspeed = 0;//���̿���һ�����ٶ�

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
		//����״̬������ͼƬ
		jumpleft=Toolkit.getDefaultToolkit().getImage("images/jumpleft.png");
		jumpright=Toolkit.getDefaultToolkit().getImage("images/jumpright.png");
		jumpupleft=Toolkit.getDefaultToolkit().getImage("images/jumpupleft.png");
		jumpupright=Toolkit.getDefaultToolkit().getImage("images/jumpupright.png");
		flyleft=Toolkit.getDefaultToolkit().getImage("images/flyleft.png");
		flyright=Toolkit.getDefaultToolkit().getImage("images/flyright.png");
		rocket=Toolkit.getDefaultToolkit().getImage("images/rocket.png");
		playerimage=flyleft;//��ʼ������Ƭ
	}
	//���¿�ʼ��Ϸʱ����ʼ����Ϣ��
	public void restart()
	{
		direction=false;
		isDead = false;
	    x = 220;
		y = 400;
		speedx = 0;//�����ƶ���ˮƽ�ٶ�
		acceleration=-5;
		gravity=0.1;	
	}
	//������ĸ���
	public void update() throws FileNotFoundException
	{		
		//ͼƬ����
		//����л����
		if(rockets > 0)
		{
			playerimage=rocket;
		}
		//�������ϵ�ʱ���жϲ�ͬ�������ٶȡ�
		//�ٸ������ҷ��򣬻��ͼƬ
		else if(acceleration < 0)//������ʾ����
		{
			if(acceleration<-3)
			{
				 if(direction) //�Ҳ��ʾ1
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
	
		//λ�ø���
		x += speedx*2;
		//���˱߽��ʱ�����õ�ֵ
		if(x < 20)
			x=20;
		if(x > 460)
			x=460;
		if(y < 0)
			y = 0;
//		System.out.println(y);
		//������������0��������ٶ�һ���������Թ̶��ٶ������ƶ���
		//ÿ�θ��»��������
		
		if(rockets>0) 
		{
			  rockets--;
			  acceleration=-7;//�л���Ļ����ٶȼӿ�
		}
		else	//û�л����ʱ�򣬾͵���������Ȼ��Ҫô�������ӣ����ߵ���ȥֱ������
		{
			y += acceleration+gravity;
			
			if(acceleration < 6)
				acceleration += gravity;
		}
//		System.out.println(acceleration);
		//�ж��Ƿ�����
		if(y>630)
		{
			
			//����������Ч��
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
		        new GameOver(gamemain);//ʵ������Ϸ��������
				isDead=true;
				
			
		}
	}
	//��Ծ����
	public void jumpup()//�䵽����֮�Ϻ�ͻ���ã������˵ļ��ٶ����ж����ĵ���߶�
	 {	
		//������������Ļ��λ�ã�ȷ��������Ծ�ļ��ٶ�
		acceleration=-(y/80)-1;//���ߴ�ľ�����������������ٶ�
		//������Ծ��Ч		 
		
			new Thread()
			{
		        	public void run()
		        	{
			            try 
			            {
			            	//����Ϊ�˱�����Ч̫������Ҹ��ݲ�ͬ����Ծ���ٶȣ����������ֲ�ͬ����Ծ��Ч��
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
