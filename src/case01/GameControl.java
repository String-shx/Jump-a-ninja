package case01;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//游戏控制
//如果键盘输入左右，设置玩家的左右速度分别为-1和1
//如果键盘输入空格，游戏暂停为TRUE
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
		//按下键盘左方向键，改变人的向左的方向，向左平移
		if(e.getKeyCode()==37)
		{
			player.direction=false;
			player.speedx=-1;
		}
		//按下键盘右方向键，改变人的向右的方向，向右平移
		if(e.getKeyCode()==39)
		{
			player.direction=true;
			player.speedx=1;
		}
		//游戏开始了并且按下空格键的话
		if(e.getKeyCode()==32&&gamemain.isStart)
		{
			gamemain.isStop = !gamemain.isStop;//空格键表示的是暂停或者回复暂停
		}
		//按下shift键，吃火箭
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			gamemain.rocket.isChoose = true;
		}
		//按下F2键，游戏开始
		if(e.getKeyCode() == KeyEvent.VK_F2)
		{
			gamemain.isStart = true;
			gamemain.people.isDead = false;
		}		
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		player.speedx = 0;//键盘回来之后，水平方向的速度重新设置为0
		gamemain.rocket.isChoose = false;//释放键盘之后，火箭就没有选中
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}
}
