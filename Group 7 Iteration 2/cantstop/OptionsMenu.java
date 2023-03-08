import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;


// popup options menu
public class OptionsMenu extends JFrame{
    
    private JButton saveGame, exit;
    private JPanel optionsPanel;
    JFileChooser fileChooser;
    StopBoard parent;

    public OptionsMenu(StopBoard parent){
        this.parent = parent;
        this.setSize(600,400);
        optionsPanel = new JPanel();
        this.add(optionsPanel);
        optionsPanel.setLayout(new FlowLayout());
        saveGame = new JButton("Save Game");
        saveGame.addActionListener(e -> {
            saveGame();
        });
        optionsPanel.add(saveGame);
		setResizable(true);
		setVisible(true);
        exit = new JButton("Exit");
        exit.addActionListener(e -> {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        optionsPanel.add(exit);


    }

    public void saveGame(){
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("~/Documents"));
        if(fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
            try{
                FileWriter writer = new FileWriter(fileChooser.getSelectedFile() + ".txt");
                String gameData = generateGameData();
                writer.write(gameData.toCharArray());
                JOptionPane success = new JOptionPane("File creation successful!");
            }
            catch(Exception e){
                JOptionPane error = new JOptionPane("There was an error saving the file");
            }
        }
    }
    public String generateGameData(){
        String data = "";
        //parent.generateGameData();
        return data;
    }

    
}
