package case01;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
/**��������ʵ���������ࡣ*/

public class GameMain 
{       
	 int grade;  //�ɼ� 
	 boolean isStart = false;//��Ϸ�Ƿ�ʼ��
     boolean isStop = false; //��Ϸֹͣλfalse
     Player player1,player2;   //MP3������    
     People people; //����    
     Board[] board=new Board[6]; //��������
     Rocket rocket;  //��� 
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
		
		 //ľ�����     
		for(int i=0;i<6;i++) 
		{
			board[i]=new Board(people);
		}
		//�������           
		rocket = new Rocket(board[1], people);
				
		GameFrame frame= new GameFrame();
		frame.add(drawer);		
		             
		frame.addKeyListener(new GameControl(this,people));
		//�������̷߳�װ��Ϊһ������
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
	        			if(!isStart)//�����Ϸû��ʼ�� ,ֻ���ֽ���
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
		
		//���� ���߳�  
        new Thread()
        {     	
        	public void run()
        	{
        		while(true)
        		{
        			if(isStart&&!people.isDead)//�����Ϸ��ʼ�� ,���ű�������
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
                   
       
//        ���������λ�õ��߳�
        new Thread(){
			public void run()
			{
				while(true)
				{       //���û����ͣ��Ϸ����������û���������򲻶ϸ��������λ��
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
