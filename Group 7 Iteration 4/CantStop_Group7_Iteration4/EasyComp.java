import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JButton;

public class EasyComp extends Comp{
    
    private StopBoard board;
    private Random random;
    private Timer timer;
    

    ActionListener diceRoller = new ActionListener() {
        public void actionPerformed(ActionEvent event){
                rollDice();
                timer.removeActionListener(diceRoller);
                timer.addActionListener(columnSelecter);
        }
    };

    ActionListener columnSelecter = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            selectColumns();
            timer.removeActionListener(columnSelecter);
            timer.addActionListener(advance);
        }
    };

    ActionListener advance = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            advanceRunners();
            timer.removeActionListener(advance);
            timer.addActionListener(rollOrEnd);
        }
    };

    ActionListener rollOrEnd = new ActionListener() {
        public void actionPerformed(ActionEvent event){
            if(board.placedRunners.size() < 3){
                timer.removeActionListener(rollOrEnd);
                timer.addActionListener(diceRoller);
            }
            else{
                timer.removeActionListener(rollOrEnd);
                stopTimer();
                timer.addActionListener(diceRoller);
                board.endButton.doClick();
            }
        }
    };




    public EasyComp(StopBoard parent){
        super(parent);
        this.board = parent;
        random = new Random();
        timer = new Timer(2000, diceRoller);
        timer.setRepeats(true);

    
        
    }

    //take the turn
    public void rollDice(){
        board.rollButton.doClick();
        System.out.println("Click");
        
    }
    public void selectColumns(){
        ArrayList<DiceCombo> comb = board.getCombinations();
        System.out.println(comb);
        int rand = random.nextInt(6);
        board.selectColumns(comb.get(rand));
    }
    public void advanceRunners(){
        while(!board.noColumnActive()){
            ArrayList<StopColumn> active = new ArrayList<>();
            for(StopColumn col : board.boardColumns){
                if(col.columnActive){
                    active.add(col);
                }
            }
            board.columnSelected(active.get(random.nextInt(active.size())));
            
        }
    }


    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
}



