import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

public class Game extends JFrame {
    
    public HashMap<Player, String> playerColours;
    public static void main(String args[]) {
        /*HashMap<Player, ArrayList<String>> testmap = new HashMap<>();
        ArrayList<String> p1 = new ArrayList<>();
        p1.add("Slick");
        p1.add("Blue");
        p1.add("false");
        p1.add("Level");
        p1.add("8");

        ArrayList<String> p2 = new ArrayList<>();
        p2.add("Rick");
        p2.add("Yellow");
        p2.add("true");
        p2.add("Hard");
        p2.add("8");

        testmap.put(Player.player1, p1);
        testmap.put(Player.player2, p2);
        */
        TitleScreen select = new TitleScreen();
        //StopBoard test = new StopBoard(2, testmap);
    }
}
