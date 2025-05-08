import javax.swing.*;
import java.awt.*;//for color
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class MyFrame extends JFrame implements ActionListener{
  
    JButton button;
    MyFrame()
    {
        
        // this.setTitle("Jthis title goes here");//set title of the this
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when you hit x button will close and this is setting by default
        // // this.setResizable(false);
        // ImageIcon image = new ImageIcon("1.png");//create image icon
        // this.setIconImage(image.getImage());//add it to my this
        // this.setSize(900,900);//set x-dimention and y-demantion
        // this.setVisible(true);//make this visible
        // this.getContentPane().setBackground(new Color(0x123456));//set color of the background
    
        button=new JButton();
        button.setBounds(100,100,250,100);

        button.setText("I'm a button");
        button.setFocusable(false);
        // button.setIcon(icon);
        // button.setHorizontalTextPosition(JButton.CENTER);
        // button.setVerticalTextPosition(JButton.BOTTOM);
        button.setFont(new Font("Comic Sans",Font.BOLD,25));
        // button.setIconTextGap();
        button.setForeground(Color.cyan);//color of text at button
        button.setBackground(Color.lightGray);
        button.setBorder(BorderFactory.createEtchedBorder());
        button.addActionListener(this);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(button);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("POO");
        }
    }
}