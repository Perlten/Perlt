package world;

import handler.Handler;
import mapEditor.MapEditor;
import util.Util;

public class World1 extends World {

    

    public World1(Handler handler) {
        super(handler, "resources/textures/background.png");
        tileList = Util.readWorld("resources/worlds/world1/tileFile");
        enemyList = converToEnemyFile("resources/worlds/world1/enemyFile");
        entityList = Util.readWorld("resources/worlds/world1/entityFile");
        mapEditor = new MapEditor(handler, tileList, enemyList, entityList, this, "resources/worlds/world1");
    }

   
}
