package phoeenix05.github.io;

import java.awt.image.DataBufferInt;

import phoeenix05.github.io.gfx.Font;
import phoeenix05.github.io.gfx.Image;
import phoeenix05.github.io.gfx.ImageTile;

public class Renderer {
  
  private int pW, pH;
  private int[] p;

  private Font font = Font.STANDARD;

  public Renderer(GameContainer gc) {
    pW = gc.getwidth();
    pH = gc.getHeight();
    p = ((DataBufferInt)gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
  }

  public void clear() {
    for (int i = 0; i < p.length; i++) {
      p[i] = 0; // p[i] = Hex Value for other color
    }
  }

  public void setPixel(int x, int y, int value) {
    if ((x < 0 || x >= pW || y < 0 || y >= pH) || ((value >> 24) & 0xff) == 0) {
      return;
    }

    p[x + y * pW] = value; 
  }

  public boolean getPixel(int x, int y) {
    // If there is not pixel at given position
    if (p[x + y * pW] == 0) return false;

    // If there is pixel at given position
    return true;
  }



  public void drawText(String text, int offX, int offY, int color) {  
    text = text.toUpperCase();
    int offset = 0;

    for (int i = 0; i < text.length(); i++) {
      int unicode = text.codePointAt(i) - 32;

      for (int y = 0; y < font.getFontImage().getH(); y++) {
        for (int x = 0; x < font.getWidths()[unicode]; x++) {
          if (font.getFontImage().getP()[(x + font.getOffsets()[unicode]) + y * font.getFontImage().getW()] == 0xffffffff) {
            setPixel(x + offX + offset, y + offY, color);
          }
        }
      }

      offset += font.getWidths()[unicode];
    }
  }



  public void drawImage(Image image, int offX, int offY) {
    //Don't render
    // if (offX < -image.getW() / 4) return;
    // if (offY < -image.getH() / 4) return;
    // if (offX >= pW) return;
    // if (offY >= pH) return;

    if (offX <= -image.getW() / 3) return;
    if (offY <= -image.getH() / 3) return;
    if (offX + image.getW() >= pW + image.getW() / 3) return;
    if (offY + image.getH() >= pH + image.getH() / 3) return;
    
    int newX = 0;
    int newY = 0;
    int newWidth = image.getW();
    int newHeight = image.getH();

    // Clipping code
    if (offX < 0) {newX -= offX;}
    if (offY < 0) {newY -= offY;}
    if (newWidth + offX > pW) {newWidth -= (newWidth + offX - pW);}
    if (newHeight + offY > pH) {newHeight -= (newHeight + offY - pH);}
    
    for (int y = newY; y < newHeight; y++) {
      for (int x = newX; x < newWidth; x++) {
        setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
      } 
    }
  }

  public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {
    // Don't render
    // if (offX < -image.getTileW() / 4) return;
    // if (offY < -image.getTileH() / 4) return;
    // if (offX >= pW) return;
    // if (offY >= pH) return;
    
    if (offX <= -image.getTileW() / 3) return;
    if (offY <= -image.getTileH() / 3) return;
    if (offX + image.getTileW() >= pW + image.getTileW() / 3) return;
    if (offY + image.getTileH() >= pH + image.getTileH() / 3) return;
    
    int newX = 0;
    int newY = 0;
    int newWidth = image.getTileW(); 
    int newHeight = image.getTileH();

    // Clipping code
    if (offX < 0) {newX -= offX;}
    if (offY < 0) {newY -= offY;}
    if (newWidth + offX > pW) {newWidth -= (newWidth + offX - pW);}
    if (newHeight + offY > pH) {newHeight -= (newHeight + offY - pH);}
    
    for (int y = newY; y < newHeight; y++) {
      for (int x = newX; x < newWidth; x++) {
        setPixel(x + offX, y + offY, image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);
      } 
    }
  }



  public void drawRect(int offX, int offY, int width, int height, int color) {
    for (int y = 0; y <= height; y++) {
      setPixel(offX, y + offY, color);
      setPixel(offX + width, y + offY, color);
    }

    for (int x = 0; x <= width; x++) {
      setPixel(x + offX, offY, color);
      setPixel(x + offX, offY + height, color);
    }
  }

  public void drawFillRect(int offX, int offY, int width, int height, int color) {
    // Don't render
    // if (offX < -width / 3) return;
    // if (offY < -height / 3) return;
    // if (offX >= pW) return;
    // if (offY >= pH) return;
    
    if (offX <= -width / 3) return;
    if (offY <= -height / 3) return;
    if (offX + width >= pW + width / 3) return;
    if (offY + width >= pH + height / 3) return;
     
    int newX = 0;
    int newY = 0;
    int newWidth = width;
    int newHeight = height;

    // Clipping code
    if (offX < 0) {newX -= offX;}
    if (offY < 0) {newY -= offY;}
    if (newWidth + offX > pW) {newWidth -= (newWidth + offX - pW);}
    if (newHeight + offY > pH) {newHeight -= (newHeight + offY - pH);}
    
    for (int y = newY; y <= newHeight; y++) {
      for (int x = newX; x <= newWidth; x++) {
        setPixel(x + offX, y + offY, color);
      }
    }
  }



  public void drawCircle(int offX, int offY, int r, int color) {
    double angle, x1, y1;

    for (double i = 0; i < 360; i++) {
      angle = i;
      x1 = r * Math.cos(angle * Math.PI / 180);
      y1 = r * Math.sin(angle * Math.PI / 180);

      int ElX = (int) Math.round(offX + x1);
      int ElY = (int) Math.round(offY + y1);
      setPixel(ElX, ElY, color);
    }
  }



  public void drawLine(int x0, int y0, int x1, int y1, int color) {
    if (Math.abs(y1 - y0) < Math.abs(x1 - x0)) {
      if (x0 > x1) drawLineLow(x1, y1, x0, y0, color);
      else drawLineLow(x0, y0, x1, y1, color);
    } else {
      if (y0 > y1) drawLineHigh(x1, y1, x0, y0, color);
      else drawLineHigh(x0, y0, x1, y1, color);
    }
  }

  private void drawLineLow(int x0, int y0, int x1, int y1, int color) {
    int dx = x1 - x0;
    int dy = y1 - y0;
    int yi = 1;

    if (dy < 0) {
      yi = -1;
      dy = -dy;
    }

    int D = (2 * dy) - dx;
    int y = y0;

    for (int x = x0; x < x1; x++) {
      setPixel(x, y, color);

      if (D > 0) {
        y += yi;
        D += 2 * (dy - dx);
      } else {
        D += 2 * dy;
      }
    }
  }

  private void drawLineHigh(int x0, int y0, int x1, int y1, int color) {
    int dx = x1 - x0;
    int dy = y1 - y0;
    int xi = 1;

    if (dx < 0) {
      xi = -1;
      dx = -dx;
    }

    int D = (2 * dx) - dy;
    int x = x0;

    for (int y = y0; y < y1; y++) {
      setPixel(x, y, color);

      if (D > 0) {
        x += xi;
        D += 2 * (dx - dy);
      } else {
        D += 2 * dx;
      }
    }
  }

}
