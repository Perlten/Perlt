package display;

/**
 *
 * @author Perlt
 */
public class FpsLock {
    
    private double timePerUpdate;
    private double delta = 0;
    private long now;
    private long last = System.nanoTime();

    public FpsLock(int fps) {
        this.timePerUpdate = 1e9 / fps;
    }
    
    public boolean check(){
        now = System.nanoTime();
        delta += (now - last) / timePerUpdate;
        last = now;
        
        if(delta >= 1){
            delta--;
            return true;
        }
        return false;
    }
    
    
    
    
}
