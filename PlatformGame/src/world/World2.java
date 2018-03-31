package world;

import handler.Handler;
import java.awt.Graphics;
import mapEditor.MapEditor;
import util.Util;

public class World2 extends World {

    public World2(Handler handler) {
        super(handler, "resources/textures/background.png");
        tileList = Util.readWorld("resources/worlds/world2/tileFile");
        enemyList = converToEnemyFile("resources/worlds/world2/enemyFile");
        entityList = Util.readWorld("resources/worlds/world2/entityFile");
        mapEditor = new MapEditor(handler, tileList, enemyList, entityList, this, "resources/worlds/world2");
    }
}
