package case01;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javazoom.jl.player.Player;
  //游戏结束窗体
@SuppressWarnings("serial")
public class GameOver extends JFrame implements ActionListener
{
	GameMain gamemain;
	//结束时候的一些界面设置
	JLabel gradelabel;//分数
	JLabel againlabel;//再来一次
	JButton yesbutton;//yes按钮 
	JButton nobutton;//no按钮
	private Image icon;
	public GameOver(GameMain gamemain) 
	{
		icon = new ImageIcon("images/icon.jpg").getImage();
		// TODO Auto-generated constructor stub
		this.gamemain=gamemain;
		super.setLayout(null);
		super.setBounds(300, 300, 300, 180);
		super.setTitle("Game Over!");
		super.setIconImage(icon);
		super.setResizable(false);
		gradelabel=new JLabel();
		
		gradelabel.setFont(new Font("微软雅黑",Font.BOLD,15));
		againlabel=new JLabel("再玩一次？");
		yesbutton=new JButton("YES");
		nobutton=new JButton("NO");
		gradelabel.setText("游戏结束,你的成绩为："+gamemain.grade);
		
		gradelabel.setBounds(10, 0, 300, 50);
		againlabel.setBounds(10, 50, 300, 50);
		nobutton.setBounds(150, 100, 150, 50);
		yesbutton.setBounds(0, 100, 150, 50);
		
		this.add(gradelabel);
		this.add(yesbutton);
		this.add(nobutton);
		this.add(againlabel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		addEventHandler();
	}
	
	
	private void addEventHandler()
	{
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) //点击关闭时，整个游戏也就结束了
			{
				System.exit(0);			
			}
		});	
		yesbutton.addActionListener(this);
		
		nobutton.addActionListener(this);
	}
	

	//对窗体操作的监听
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.setVisible(false);
		
		//如果点击YES按钮，初始化部分游戏数据，重新开始比赛
		if(e.getSource()== yesbutton)
		{
		    gamemain.people.restart();
		    gamemain.rocket.count = 10;
		    for(int i=0;i<6;i++)
		    {
		    	gamemain.board[i].restart();
		    }	
		}		    
		//点击NO按钮，退出程序。
		if(e.getSource()== nobutton)
		{
			System.exit(0);
		}
	}
}
