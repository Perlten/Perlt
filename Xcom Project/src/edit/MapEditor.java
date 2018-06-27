package edit;

import game.GameObject;
import input.KeyInput;
import input.MouseInput;
import java.awt.Graphics;
import world.World;

public class MapEditor implements GameObject{

    private World world;
    private KeyInput keyInput;
    private MouseInput mouseInput;

    public MapEditor(World world, KeyInput keyInput, MouseInput mouseInput) {
        this.world = world;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        
    }
    
}
