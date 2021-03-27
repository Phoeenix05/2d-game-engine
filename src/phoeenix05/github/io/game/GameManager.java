package phoeenix05.github.io.game;

import java.awt.event.KeyEvent;

import phoeenix05.github.io.AbstractGame;
import phoeenix05.github.io.GameContainer;
import phoeenix05.github.io.Renderer;
import phoeenix05.github.io.audio.SoundClip;
import phoeenix05.github.io.gfx.Image;
import phoeenix05.github.io.gfx.ImageTile;
import phoeenix05.github.io.noise.NoiseGenerator;

public class GameManager extends AbstractGame {

  private Image arrow;
  private NoiseGenerator ns;

  public GameManager() {
    arrow = new Image("/res/textures/arrow.png");
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
    
  }

  public static void main(String[] args) {
    GameContainer gc = new GameContainer(new GameManager());
    gc.start();
  }
  
}
