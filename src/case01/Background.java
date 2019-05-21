package case01;
import java.awt.Image;
import java.awt.Toolkit;


public class Background
{
    Image background1;
    Image background2;
    People people;
    
    int x1;//���鱳��������   
    int y1;
    int x2;
    int y2;
    
	public Background(People people)   
	{
		// TODO Auto-generated constructor stub
		x1=0;
		y1=0;
		x2=0;
		y2=-645;
		this.people=people;
		background1 =
				Toolkit.getDefaultToolkit().getImage("images/back.png");
		background2 = 
				Toolkit.getDefaultToolkit().getImage("images/back2.png");
		action();
	}
	
	private void action()
	{
		new Thread() //����λ�ø����߳�
		{
			public void run()
			{
				while(true)
				{
					updateBackground();
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();		
	}

	public void updateBackground()//�������·���
	{
		if(people.acceleration < 0)//ֻ��������������Ծ��ʱ�򣬱����Ż�ƽ�ƣ������ж�������ٶȷ���
		{
			y1-=(int)(0.5*people.acceleration);
			y2-=(int)(0.5*people.acceleration);
		}
		if(y1>650)	
			y1=650-640*2;
		if(y2>650)
			y2=650-640*2;
	}
}
