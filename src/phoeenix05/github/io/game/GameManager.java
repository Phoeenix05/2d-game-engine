package phoeenix05.github.io.game;

import java.awt.event.KeyEvent;

import phoeenix05.github.io.AbstractGame;
import phoeenix05.github.io.GameContainer;
import phoeenix05.github.io.Renderer;
import phoeenix05.github.io.audio.SoundClip;
import phoeenix05.github.io.gfx.Image;
import phoeenix05.github.io.gfx.ImageTile;

public class GameManager extends AbstractGame {

  public GameManager() {
    
  }

  @Override
  public void update(GameContainer gc, float dt) {
    // Animation speed
    // temp += dt * 5;
    // if (temp > 4) temp = 0;


  }

  // float temp = 0;

  @Override
  public void render(GameContainer gc, Renderer r) {
    r.drawFillRect(gc.getInput().getMouseX() - 16, gc.getInput().getMouseY() - 16, 32, 32, 0xffffccff);
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
}
