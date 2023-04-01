import java.awt.Component;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
public class Comp {
    
    private StopBoard parent;
    private Random random;
    Timer timer;

    public Comp(StopBoard parent){
        this.parent = parent;
        random = new Random();
        timer = new Timer(0, null);
                

    }

    public void takeTurn() throws InterruptedException{
        ;
    }
    public void startTimer(){
        timer.start();
    }
    
    public void stopTimer(){
        timer.stop();
    }
}
