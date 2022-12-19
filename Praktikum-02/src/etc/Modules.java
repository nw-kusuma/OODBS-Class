package etc;

import static etc.Constants.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * @author N. W. Kusuma
 */
public final class Modules {
    
    private Modules (){}
    
    public static void setComponentSize(Component comp, int w, int h)
    {
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
        comp.setPreferredSize(new Dimension(w, h));
    }
    

    public static Border createBorder(String type, int n, String...title)
    {
        Border newBorder = null;
        if (type.equalsIgnoreCase("titled")){
            newBorder = BorderFactory.createTitledBorder(
                       createBorder("line", 1), 
                        title[0],1, n, FONT_12);
        } else if (type.equalsIgnoreCase("line")){
            newBorder = BorderFactory.createLineBorder(
                        new Color(80, 83, 85), n);
        }
        return newBorder;
    } 


    public static Image getImage(String img, int w, int h)
    {
        String path = IMG_DIR + img;
        int scale_factor = path.toLowerCase().endsWith(".gif") 
                         ? Image.SCALE_DEFAULT : Image.SCALE_SMOOTH;

        return Toolkit.getDefaultToolkit().createImage(path)
                      .getScaledInstance(w, h, scale_factor); 
    }


    public static GridBagConstraints getGBConstraint(int[] gbcs)
    {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gbcs[0];
        gbc.gridy = gbcs[1];
        gbc.gridwidth = gbcs[2];
        gbc.gridheight = gbcs[3];
        gbc.anchor = gbcs[4];
        gbc.insets = new Insets(gbcs[5], gbcs[6], gbcs[7], gbcs[8]);

        return gbc;
    }
}