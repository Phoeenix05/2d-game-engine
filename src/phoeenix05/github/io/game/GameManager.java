package phoeenix05.github.io.game;

import java.awt.event.KeyEvent;

import phoeenix05.github.io.AbstractGame;
import phoeenix05.github.io.GameContainer;
import phoeenix05.github.io.Renderer;
import phoeenix05.github.io.gfx.Image;

public class GameManager extends AbstractGame {

  private Image image;

  public GameManager() {
    image = new Image("/res/barrier.png");
  }

  @Override
  public void update(GameContainer gc, float dt) {
    if (gc.getInput().isKeyDown(KeyEvent.VK_A)) {
      System.out.println("A was pressed");
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
    r.drawImage(image, gc.getInput().getMouseX(), gc.getInput().getMouseY());
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
}
