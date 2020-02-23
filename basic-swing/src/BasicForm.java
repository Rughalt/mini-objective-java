import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class BasicForm {
    private final DefaultListModel listModel;
    private JCheckBox checkCheckBox;
    private JButton pushButton;
    private JLabel selectedLabel;
    private JPanel mainPanel;
    private JList list1;
    private JTextField textField1;
    private JRadioButton radioButton1;
    private JButton button1;

    public BasicForm() {
        listModel = new DefaultListModel();
        list1.setModel(listModel);
        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLabel.setText(String.format("Selected = %s", checkCheckBox.isSelected()));
                listModel.addElement(String.format("%s: %s, %s", LocalDateTime.now(), textField1.getText(), checkCheckBox.isSelected()));
            }
        });
        checkCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.addElement(String.format("%s: checkbox status changed to  %s", LocalDateTime.now(),  checkCheckBox.isSelected()));

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("App");
        jFrame.setContentPane(new BasicForm().mainPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

}
