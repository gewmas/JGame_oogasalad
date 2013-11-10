package gameAuthoring;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class JDialogTest extends JFrame {
    AddressDialog dialog = new AddressDialog(this, false);

    public JDialogTest(String title) {
      super(title);
      init();
    }

    public JDialogTest() {
      super();
      init();

    }

    private void init() {
      this.getContentPane().setLayout(new FlowLayout());
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      final AddressDialog dialog = new AddressDialog(this, false);
      JButton button = new JButton("Show Dialog");
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
          dialog.setSize(250, 120);
          dialog.setVisible(true);
        }
      });
      this.getContentPane().add(button);
    }

    public static void main(String[] args) {
      JFrame.setDefaultLookAndFeelDecorated(true);
      JDialog.setDefaultLookAndFeelDecorated(true);
      JDialogTest frame = new JDialogTest();
      frame.pack();
      frame.setVisible(true);
    }
  }