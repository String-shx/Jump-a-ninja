package case01;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javazoom.jl.decoder.JavaLayerException;

 //������
//��Ϊ�ҷ���JFrame����رմ��ں󣬲��ű������ֵĵ��̻߳��ڼ�������������д��һ�´����������������رպ��˳����н��̡�
public class GameFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Image icon;
	private JMenuBar menubar;
	private JMenu menu1;	
//	private GameMain gamemain;
	JMenuItem item1;
	JMenuItem item2;
	public GameFrame() throws FileNotFoundException, JavaLayerException
	{
//		this.gamemain = gamemain;
		icon = new ImageIcon("images/icon.jpg").getImage();
		getMenu();		
		super.setTitle("��Ծ����");
		super.setResizable(false);//���岻���Ե�����С
		super.setIconImage(icon);
		super.setBounds(200, 200, 500, 650);
		super.setLocationRelativeTo(null);
		this.add(menubar,"North");
		this.setVisible(true);
		addEventHandler();		
	}
	    
	private void addEventHandler()
	{
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);			
			}
		});
		item2.addActionListener(new ActionListener()
		{			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);				
			}
		});	
	}
	/**��Menu�������ͨ����������ʽ���첢����*/
	private JMenuBar getMenu()
	{
		menubar = new JMenuBar(); 
		menu1=new JMenu("�˵�");
		item1=new JMenuItem("������Ϸ");//item1��û�����ã�ֻ���鹹��һ��
		item2=new JMenuItem("�˳���Ϸ");
		item1.addActionListener(new AboutGame(this));		
		
		menu1.add(item1);
		menu1.add(item2);
		menubar.add(menu1);
		return menubar;
	}	
}
