package phoeenix05.github.io.game;

import java.awt.event.KeyEvent;

import phoeenix05.github.io.AbstractGame;
import phoeenix05.github.io.GameContainer;
import phoeenix05.github.io.Renderer;

public class GameManager extends AbstractGame {

  public GameManager() {

  }

  @Override
  public void update(GameContainer gc, float dt) {
    if (gc.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
      System.out.println("A was pressed");
    }
  }

  @Override
  public void render(GameContainer gc, Renderer r) {
   
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
}
