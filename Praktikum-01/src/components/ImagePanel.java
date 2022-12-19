package components;

import static etc.Constants.*;

import java.awt.*;
import javax.swing.*;

/**
 * @author N. W. Kusuma
 */
public class ImagePanel extends JPanel {
//    private static final String IMG_DIR  = "src/images/";
    
    private Image  image;
    private String path;
    //--- Class constructors
    public ImagePanel () {}
    public ImagePanel(Image img) { this.image = img; }
    public ImagePanel(String img) {
        this.path  = IMG_DIR + img;
        this.image = Toolkit.getDefaultToolkit().createImage(path);
    }
    public ImagePanel(String img, int w, int h) {
        this.path  = IMG_DIR + img;
        this.image = Toolkit.getDefaultToolkit().createImage(path)
                            .getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }

    //--- Attribut getter method
    public Image getImage(){ return this.image; }

    //--- Attribut setter methods
    public void setImage(Image img){ this.image = img; }
    public void setImage(String img){
        this.path  = IMG_DIR + img;
        this.image = Toolkit.getDefaultToolkit().createImage(path);
    }
    public void setImage(String img, int w, int h){
        this.path  = IMG_DIR + img;
        this.image = Toolkit.getDefaultToolkit().createImage(path)
                            .getScaledInstance(w, h, Image.SCALE_SMOOTH);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int x = 0, y = 0;
            if (image.getWidth(this) < this.getWidth()){
               x = (this.getWidth()/2) - (image.getWidth(this)/2);
            }
            if (image.getHeight(this) < this.getHeight()){
               y = (this.getHeight()/2) - (image.getHeight(this)/2);
            } 
            g.drawImage(image, x, y, this);
            g.dispose();
        }
    }
}