package case01;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
//������Ϸ�Ĵ���
//����˵����Ĺ�����Ϸѡ���ʱ�򣬻ᵯ���Ĵ���

@SuppressWarnings("serial")
public class AboutGame extends JFrame implements ActionListener
{
    private GameFrame frame;
    private Image icon;
	public AboutGame(GameFrame frame) //����һ��frame��Ϊ������Ϊ���ڼ���������������ж�
	{	
		this.frame=frame;
		super.setBounds(300, 300, 300, 300);
		super.setTitle("������Ϸ");
		super.setLocationRelativeTo(frame);
		super.setResizable(false);
		icon = new ImageIcon("images/icon.jpg").getImage();
		JTextArea jt=new JTextArea();
		jt.setSize(300, 300);
		super.setFont(new Font("΢���ź�",Font.BOLD,10));
		super.setIconImage(icon);
		String text=new String(
				"����һ��Ȥζ��Ϸ\nͨ�������������Ҽ��������\nͨ������shift��ѡ�л����\nʹ�û���Ĵ���ֻ��10�Ρ�"
				+ "\n���¿ո��ֹͣ��Ϸ��        \nϣ������������    \n   �����Ŷӡ���Java Go   ");
		jt.setText(text);
	
		Font font=new Font(text, Font.BOLD, 20);
		jt.setFont(font);
		super.add(jt);
	}
 
	@Override
	public void actionPerformed(ActionEvent e) //���жϵ��������Ϸ�󣬽�����λ�ɼ�
	{
		if(e.getSource()==frame.item1)
		{
			this.setVisible(true);
		}
	}
}
