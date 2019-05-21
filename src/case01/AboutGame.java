package case01;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
//关于游戏的窗体
//点击菜单栏的关于游戏选项的时候，会弹出的窗口

@SuppressWarnings("serial")
public class AboutGame extends JFrame implements ActionListener
{
    private GameFrame frame;
    private Image icon;
	public AboutGame(GameFrame frame) //传入一个frame作为参数，为了在监听方法里面进行判断
	{	
		this.frame=frame;
		super.setBounds(300, 300, 300, 300);
		super.setTitle("关于游戏");
		super.setLocationRelativeTo(frame);
		super.setResizable(false);
		icon = new ImageIcon("images/icon.jpg").getImage();
		JTextArea jt=new JTextArea();
		jt.setSize(300, 300);
		super.setFont(new Font("微软雅黑",Font.BOLD,10));
		super.setIconImage(icon);
		String text=new String(
				"这是一款趣味游戏\n通过操作键盘左右键控制人物。\n通过操作shift键选中火箭。\n使用火箭的次数只有10次。"
				+ "\n按下空格会停止游戏。        \n希望你能玩得愉快    \n   制作团队——Java Go   ");
		jt.setText(text);
	
		Font font=new Font(text, Font.BOLD, 20);
		jt.setFont(font);
		super.add(jt);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) //在判断点击关于游戏后，将窗体位可见
	{
		if(e.getSource()==frame.item1)
		{
			this.setVisible(true);
		}
	}
}
