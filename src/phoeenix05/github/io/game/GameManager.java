package phoeenix05.github.io.game;

import java.awt.event.KeyEvent;

import phoeenix05.github.io.AbstractGame;
import phoeenix05.github.io.GameContainer;
import phoeenix05.github.io.Renderer;
import phoeenix05.github.io.gfx.Image;
import phoeenix05.github.io.gfx.ImageTile;

public class GameManager extends AbstractGame {

  // private ImageTile tilemap;
  private Image arrow;

  public GameManager() {
    // tilemap = new ImageTile("/res/textures/tilemap.png", 16, 16);
    arrow = new Image("/res/textures/arrow.png");
  }

  @Override
  public void update(GameContainer gc, float dt) {
    temp += dt * 4;

    if (temp > 4) temp = 0;
  }

  float temp = 0;

  @Override
  public void render(GameContainer gc, Renderer r) {
    // r.drawImageTile(tilemap, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8, (int) temp, 0);
    r.drawImage(arrow, gc.getInput().getMouseX() - 8, gc.getInput().getMouseY() - 8);
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
}
