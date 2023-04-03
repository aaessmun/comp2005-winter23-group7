import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TitleScreen extends JFrame {
    
    String st="";
    private JLabel titleLabel;
    private JComboBox<Integer> playerCountComboBox;
    private JButton helpButton, startButton;
    
    public TitleScreen() {
        this.setSize(800, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Can't Stop");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        // Set up the title label
        titleLabel = new JLabel("CAN'T STOP", SwingConstants.CENTER);
        titleLabel.setPreferredSize(new Dimension(400, 200));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 72));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        
        // Set up the player count combo box
        JPanel newGamePanel = new JPanel(new BorderLayout());
        newGamePanel.setBackground(Color.WHITE);
        newGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newGamePanel.setPreferredSize(new Dimension(350, 300));
        JLabel newGameLabel = new JLabel("New Game", SwingConstants.CENTER);
        newGameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        newGameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        newGamePanel.add(newGameLabel, BorderLayout.NORTH);
        JPanel newGameContentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        newGameContentPanel.setBackground(Color.WHITE);
        JLabel playerCountLabel = new JLabel("Number of Players:", SwingConstants.RIGHT);
        playerCountLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        playerCountLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));
        newGameContentPanel.add(playerCountLabel);
        playerCountComboBox = new JComboBox<>(new Integer[] {1, 2, 3, 4});
        playerCountComboBox.setFont(new Font("Arial", Font.PLAIN, 24));
        newGameContentPanel.add(playerCountComboBox);
        newGamePanel.add(newGameContentPanel, BorderLayout.CENTER);
        
        // Set up the saved game panel
        JPanel savedGamePanel = new JPanel(new BorderLayout());
        savedGamePanel.setBackground(Color.WHITE);
        savedGamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        savedGamePanel.setPreferredSize(new Dimension(350, 300));
        JLabel savedGameLabel = new JLabel("Load Game", SwingConstants.CENTER);
        savedGameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        savedGameLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        savedGamePanel.add(savedGameLabel, BorderLayout.NORTH);
        JPanel savedGameContentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        savedGameContentPanel.setBackground(Color.WHITE);
        JButton loadSavedGameButton = new JButton("Load a saved game");
        loadSavedGameButton.setFont(new Font("Arial", Font.PLAIN, 24));
        loadSavedGameButton.addActionListener(i -> {
            savedGame();
        });
        savedGameContentPanel.add(loadSavedGameButton);
        savedGamePanel.add(savedGameContentPanel, BorderLayout.CENTER);
        
        // Set up the center panel
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.add(newGamePanel);
        centerPanel.add(savedGamePanel);
        
        // Set up the options panel
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        optionsPanel.setBackground(Color.WHITE);
        helpButton = new JButton("HELP");
        helpButton.setFont(new Font("Arial", Font.BOLD, 24));
        helpButton.addActionListener(i -> {
            // show help
        });
        optionsPanel.add(helpButton);
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.addActionListener(i -> {
            SetupScreen setupScreen = new SetupScreen(this, getPlayerCount());
            this.dispose();
        });
        optionsPanel.add(startButton);
        
        // Add everything to the frame
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(optionsPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    public int getPlayerCount() {
        return (int) playerCountComboBox.getSelectedItem();
    }

    private void savedGame() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                FileReader reader = new FileReader(selectedFile);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    st +="\n"+line;
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getLoadGame() {
        return st;
    }
}
