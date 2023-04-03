import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JButton;

public class HardComp extends Comp{
    


    private StopBoard board;
    private Timer timer;
    private ArrayList<Integer> runnerIndices;
    private int rerollChance = 2;
    private Random random;
    

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
            // determine whether to roll again
            Boolean roll = false;
            if(runnerIndices.size() < 3){
                roll = true;
            }
            else if(runnerIndices.contains(5)){
                  roll = true;
            }
            else if(true){
                for(StopColumn c : board.boardColumns){
                    if(c.getRunnerIdx() < 3){
                        roll = true;
                    }
                }
            }
            
            if(random.nextInt(rerollChance) == 1){
                roll = true;
            }
            if(board.bust) roll = false;
            if(roll){
                timer.removeActionListener(rollOrEnd);
                rerollChance += 1;
                timer.addActionListener(diceRoller); 
            }
            else{
                stopTimer();
                rerollChance = 2;
                timer.removeActionListener(rollOrEnd);
                timer.addActionListener(diceRoller);
                runnerIndices.clear();
                board.endButton.doClick();
            }        
        
    }};


    public HardComp(StopBoard parent){
        super(parent);
        this.board = parent;
        runnerIndices = new ArrayList<>();
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
        DiceCombo sel = null;
        for(DiceCombo c : comb){
            int min = 10;
            int idx = board.boardColumns.get(c.sum() - 2).getRunnerIdx();
            if(idx < min){
                min = idx;
                sel = c;
            }
        }
        board.selectColumns(sel);
        
    }



    public void advanceRunners(){
        ArrayList<StopColumn> active = new ArrayList<>();
        while(!board.noColumnActive()){
            active.clear();
            for(StopColumn col : board.boardColumns){
                if(col.columnActive){
                    active.add(col);
                }
            }
        
            //board.columnSelected(active.get(random.nextInt(active.size())));
            int min = 20;
            StopColumn selected = null;
            for(StopColumn col : active){
                if(col.getRunnerIdx() < min){
                    min = col.getRunnerIdx();
                    selected = col;
                } 
            }
            board.columnSelected(selected);
            runnerIndices.add(board.boardColumns.indexOf(selected));
        }
    }


    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
    }





