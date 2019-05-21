package case01;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//��Ϸ����
//��������������ң�������ҵ������ٶȷֱ�Ϊ-1��1
//�����������ո���Ϸ��ͣΪTRUE
public class GameControl implements KeyListener
{
    GameMain gamemain;
	People player;
	public GameControl(GameMain panel, People player) 
	{
		this.gamemain=panel;
		this.player=player;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		//���¼�����������ı��˵�����ķ�������ƽ��
		if(e.getKeyCode()==37)
		{
			player.direction=false;
			player.speedx=-1;
		}
		//���¼����ҷ�������ı��˵����ҵķ�������ƽ��
		if(e.getKeyCode()==39)
		{
			player.direction=true;
			player.speedx=1;
		}
		//��Ϸ��ʼ�˲��Ұ��¿ո���Ļ�
		if(e.getKeyCode()==32&&gamemain.isStart)
		{
			gamemain.isStop = !gamemain.isStop;//�ո����ʾ������ͣ���߻ظ���ͣ
		}
		//����shift�����Ի��
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			gamemain.rocket.isChoose = true;
		}
		//����F2������Ϸ��ʼ
		if(e.getKeyCode() == KeyEvent.VK_F2)
		{
			gamemain.isStart = true;
			gamemain.people.isDead = false;
		}		
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		player.speedx = 0;//���̻���֮��ˮƽ������ٶ���������Ϊ0
		gamemain.rocket.isChoose = false;//�ͷż���֮�󣬻����û��ѡ��
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
}
