import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.*;

public class SetupScreen extends JFrame implements ActionListener, MouseListener {
    HashMap<Player, ArrayList<String>> playerDetailsMap;
    ArrayList<PlayerCard> playerCards;
    // Top level elements
    private JLabel title;
    private JPanel titlePanel;
    private JPanel playerPanel1;
    private JPanel playerPanel2;
    private JPanel optionsPanel;
    private JPanel player;
    private JButton help;
    private JButton start;
    private JButton exit;
    private JButton settings;
    private JButton back;
    private  JComponent popup;
    private Popup p;
    private  JButton popUpExit;
    private int PlayerCount;
    private ArrayList<String> colours;
    private String[] HColour = {"Color", "Black", "Gold","Cyan","Magenta"};
    private String[] CColour = {"Red", "Blue", "Yellow", "Green"};
    private String[] colour;
    private TitleScreen select;

    private Player[] players = {Player.player1, Player.player2, Player.player3, Player.player4};

    public SetupScreen(TitleScreen select, int num_players) {
        
        colours = new ArrayList<>();
        this.PlayerCount = num_players;
        colour = CColour;
        for (String col : colour) {
            colours.add(col);
        }
        this.select=select;//iter 4 update
        this.setSize(800, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout());
        HashMap<Player, ArrayList<String>> playerDetailsMap = new HashMap<>();
        playerCards = new ArrayList<>();
        title = new JLabel("CAN'T STOP");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        titlePanel = new JPanel();
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setPreferredSize(new Dimension(800, 100));
        titlePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        titlePanel.add(title, c);

        playerPanel1 = new JPanel();
        playerPanel1.setBackground(Color.WHITE);
        playerPanel2 = new JPanel();
        playerPanel2.setBackground(Color.WHITE);
        optionsPanel = new JPanel();
        optionsPanel.setBackground(Color.WHITE);
        optionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        player = new JPanel(new BorderLayout());
        player.setBackground(Color.WHITE);

        help = new JButton("HELP");
        help.setFont(new Font("Arial", Font.BOLD, 18));
        start = new JButton("START");
        start.setFont(new Font("Arial", Font.BOLD, 18));
        exit = new JButton("EXIT");
        exit.setFont(new Font("Arial", Font.BOLD, 18));
        settings = new JButton("SETTINGS");
        settings.setFont(new Font("Arial", Font.BOLD, 18));
        back = new JButton("BACK");
        back.setFont(new Font("Arial", Font.BOLD, 18));
        addPlayers(select);
        optionsPanel.add(settings);
        optionsPanel.add(help);
        optionsPanel.add(start);
        optionsPanel.add(exit);
        optionsPanel.add(back);
        player.add(playerPanel1, BorderLayout.NORTH);
        player.add(playerPanel2, BorderLayout.CENTER);
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(player, BorderLayout.CENTER);
        this.add(optionsPanel, BorderLayout.SOUTH);

        // Create a scroll pane and add the player panel to it
        JScrollPane playerScrollPane = new JScrollPane(player);
        playerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        playerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add the components to the frame
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(playerScrollPane, BorderLayout.CENTER);
        this.add(optionsPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);

        
        settings.addActionListener(i->{
            showPopupSettings();
        });

        start.addActionListener(i->{
            if(p!=null)
                p.hide();
            
            HashMap<Player, ArrayList<String>> playerMap = generatePlayerDetails();
            Boolean ready = true;
            for(Player p : playerMap.keySet()){
                if(playerMap.get(p).contains("Enter Your Name!") || playerMap.get(p).isEmpty()){
                    JOptionPane.showMessageDialog(this, p.name() + ", Enter a name!");
                    ready = false;
                }   
            }
            if(ready){
                playerMap = generatePlayerDetails();
                TurnOrder turn = new TurnOrder(this, PlayerCount, playerMap);
                this.setVisible(false);
            }
            
        });

        exit.addActionListener(i->{
            if(p!=null)
            p.hide();
            System.exit(0);
        });

        help.addActionListener(i->{
            showPopup();
        });

        back.addActionListener(i->{
            this.setVisible(false);
            select.setVisible(true);;
        });

    }

    void showPopupSettings() {
        popup = new JPanel();
        popup.setPreferredSize(new Dimension(300, 300));
        JLabel label = new JLabel("SETTINGS!!");
    
        String[] difficulty = {"Plain Colored Marker", "Textured marker"};
        
        JComboBox<String> mark = new JComboBox<>(difficulty);

        popUpExit = new JButton("EXIT");
        popup.add(popUpExit);

        popup.add(label,BorderLayout.NORTH);
        popup.add(label,BorderLayout.NORTH);
        popup.add(mark,BorderLayout.SOUTH);

        p = PopupFactory.getSharedInstance().getPopup(this, popup, 250,150);
        p.show();
        
        popUpExit.addActionListener(i->{
            p.hide();
            changeColourSettings();
        });
    }

    void showPopup() {
        popup = new JPanel();
        popup.setPreferredSize(new Dimension(300, 300));
        JLabel label=new JLabel();
        label.setText("HELP!!");
        popUpExit = new JButton("EXIT");

        popup.add(popUpExit);
        popup.add(label);
        
        p = PopupFactory.getSharedInstance().getPopup(this, popup, 250,150);
        p.show();
        
        popUpExit.addActionListener(i->{
            p.hide();
        });
    }


    public void actionPerformed(ActionEvent e){
        System.out.println("Implement me!");
    }

    public void close(){
        this.removeAll();
    }

    public void addPlayers(TitleScreen select){
        PlayerCard nextPlayer = new PlayerCard(null, colour);
        Set<String> AlreadySelectedcolor = new HashSet<>();

        for (int i = 1; i <=select.getPlayerCount(); i++) {
            if(i==1 || i==2){
                nextPlayer = new PlayerCard(AlreadySelectedcolor, colour);
                playerCards.add(nextPlayer);
                playerPanel1.add(nextPlayer);
            }
            else{
                nextPlayer = new PlayerCard(AlreadySelectedcolor, colour);
                playerCards.add(nextPlayer);
                playerPanel2.add(nextPlayer);
            
            }
        }
        colours.remove(nextPlayer.getPlayerColor());
    }

    public void changeColourSettings(){
        if(colour.equals(CColour)) colour = HColour;
        else colour = CColour;
        playerPanel1.removeAll();
        playerPanel2.removeAll();
        playerCards.clear();
        addPlayers(select);
        this.repaint();
        this.revalidate();
    }

    public HashMap<Player, ArrayList<String>> generatePlayerDetails(){
        // init map
        HashMap<Player, ArrayList<String>> playerDetails = new HashMap<>();
        //loop through players
        
        for(int i = 0; i < PlayerCount; i++){
            PlayerCard p = playerCards.get(i);
            ArrayList<String> details = new ArrayList<>();
            details.add(p.getPlayerName());
            details.add(p.getPlayerColor());
            details.add(Boolean.toString(p.isComputerPlayer()));
            details.add(p.getPlayerLevel());
            playerDetails.put(players[i], details);
        }
        return playerDetails;
    }

    public String getLoadGame(){ //iter 4 update
        return select.getLoadGame();
    }

    public ArrayList<PlayerCard> getPlayer(){

        return null;
    }

    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    public void mouseClicked(MouseEvent arg0) {}
}