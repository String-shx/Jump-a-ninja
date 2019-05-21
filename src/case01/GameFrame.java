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

 //主窗体
//因为我发现JFrame点击关闭窗口后，播放背景音乐的的线程还在继续。所以我重写了一下窗体监听，让它点击关闭后，退出所有进程。
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
		super.setTitle("跳跃忍者");
		super.setResizable(false);//窗体不可以调整大小
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
	/**将Menu这个对象通过方法的形式创造并返回*/
	private JMenuBar getMenu()
	{
		menubar = new JMenuBar(); 
		menu1=new JMenu("菜单");
		item1=new JMenuItem("关于游戏");//item1并没有设置，只是虚构了一下
		item2=new JMenuItem("退出游戏");
		item1.addActionListener(new AboutGame(this));		
		
		menu1.add(item1);
		menu1.add(item2);
		menubar.add(menu1);
		return menubar;
	}	
}
