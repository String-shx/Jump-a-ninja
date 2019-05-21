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
  //��Ϸ��������
@SuppressWarnings("serial")
public class GameOver extends JFrame implements ActionListener
{
	GameMain gamemain;
	//����ʱ���һЩ��������
	JLabel gradelabel;//����
	JLabel againlabel;//����һ��
	JButton yesbutton;//yes��ť 
	JButton nobutton;//no��ť
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
		
		gradelabel.setFont(new Font("΢���ź�",Font.BOLD,15));
		againlabel=new JLabel("����һ�Σ�");
		yesbutton=new JButton("YES");
		nobutton=new JButton("NO");
		gradelabel.setText("��Ϸ����,��ĳɼ�Ϊ��"+gamemain.grade);
		
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
			public void windowClosing(WindowEvent e) //����ر�ʱ��������ϷҲ�ͽ�����
			{
				System.exit(0);			
			}
		});	
		yesbutton.addActionListener(this);
		
		nobutton.addActionListener(this);
	}
	

	//�Դ�������ļ���
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.setVisible(false);
		
		//������YES��ť����ʼ��������Ϸ���ݣ����¿�ʼ����
		if(e.getSource()== yesbutton)
		{
		    gamemain.people.restart();
		    gamemain.rocket.count = 10;
		    for(int i=0;i<6;i++)
		    {
		    	gamemain.board[i].restart();
		    }	
		}		    
		//���NO��ť���˳�����
		if(e.getSource()== nobutton)
		{
			System.exit(0);
		}
	}
}
