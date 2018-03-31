/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package world;

import handler.Handler;
import java.awt.Graphics;

public class TestWorld extends World {

    public TestWorld(Handler handler) {
        super(handler, "resources/textures/background.png");
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(100, 100, 50, 50);
    }

}
