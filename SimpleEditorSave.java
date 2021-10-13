import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleEditorSave extends JFrame {
    
    public SimpleEditorSave(){

        initComponents();

    }


    public void initComponents(){

        this.setTitle("SimpleEditorSave");
        this.setBounds(300,300,300,200);
        this.setJMenuBar(barMenu);

        JMenu menuFile =  barMenu.add(new JMenu("File"));

        Action actionSave = new ActionSave("Save");

        JMenuItem newFileMenu = menuFile.add(new JMenuItem("New"));
        newFileMenu.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               // adding new file code
                
            }

        });
        menuFile.addSeparator();
        final JMenuItem saveFileMenu = menuFile.add(actionSave);
        saveButton = new JButton(actionSave);

        actionSave.setEnabled(false);
        
        saveFileMenu.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                saveFileMenu.setEnabled(textAreaflag = false);
                
            }

        });


        menuFile.add(new JMenuItem("Read"));
        menuFile.addSeparator();
           
        menuFile.setMnemonic('F');

        JMenu optionsMenu = new JMenu("Options");
        menuFile.add(optionsMenu);

        optionsMenu.add(new JMenuItem("Option 1"));
        optionsMenu.add(new JMenuItem("Option 2"));
        optionsMenu.add(checkReadOnly);

        JMenu helpMenu = barMenu.add(new JMenu("Help"));
        helpMenu.add(new JMenuItem("FAQ"));

        textArea.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {

                if(checkReadOnly.isSelected())
                e.consume();

            }

    
            public void keyPressed(KeyEvent e) {
                if(checkReadOnly.isSelected())
                e.consume();
                
                else if(!(textArea.getText() + e.getKeyChar()).equals(beforeUpdateTextArea)&& isAscii(e.getKeyChar()))
                beforeUpdateTextArea = textArea.getText() + e.getKeyChar();

                actionSave.setEnabled(textAreaflag=true);
            }

        });

        saveFileMenu.setToolTipText("Saving file on disk");
        saveFileMenu.setMnemonic('S');
        saveFileMenu.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));


        checkReadOnly.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(checkReadOnly.isSelected()){
                    actionSave.setEnabled(false) ;
                }
                else 
                actionSave.setEnabled(textAreaflag);
            }

        });
        this.getContentPane().setLayout(new GridLayout(2,1));
        this.getContentPane().add(textArea);
        this.getContentPane().add(saveButton);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private JMenuBar barMenu = new JMenuBar();
    private JCheckBoxMenuItem checkReadOnly = new JCheckBoxMenuItem("Read only");
    private JTextArea textArea = new JTextArea();
    private boolean textAreaflag = false;
    private String beforeUpdateTextArea = "";
    private JButton saveButton;


    private class ActionSave extends AbstractAction{

        public ActionSave(String nazwa){
        
            this.putValue(Action.NAME, nazwa);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
        
            this.setEnabled(textAreaflag = false);
            
        }

    }
     
    private boolean isAscii(char znak){

        for(int i = 0; i < 255; i++){
            if(znak ==i)
            return true;
        }

        return false;

    }

    public static void main(String[] args) {
    
        new SimpleEditorSave().setVisible(true);
    }

}
