package etc;

import static etc.Constants.*;

import java.awt.*;
import javax.swing.*;

/**
 * @author N. W. Kusuma
 */
public final class Routines {
//    private static final String IMG_DIR  = "src/images/";
    
    private Routines (){}
    
    public static void setComponentSize(Component comp, int w, int h)
    {
        comp.setMaximumSize(new Dimension(w, h));
        comp.setMinimumSize(new Dimension(w, h));
        comp.setPreferredSize(new Dimension(w, h));
    }
    
    public static void setComponentProp(JComponent comp, Font font, Color[] colors, int...args)
    {
        comp.setFont(font);
        comp.setForeground(colors[0]);
        comp.setBackground(colors[1]);
        comp.setFocusable(false);

        if (comp instanceof JTextArea jta){
            jta.setWrapStyleWord(true);
            jta.setLineWrap(true);
            jta.setEditable(false);
        } else if (comp instanceof JTextField jtf){
            jtf.setHorizontalAlignment(args[0]);
            jtf.setEditable(false);
            jtf.setBorder(null);
        } else if (comp instanceof JButton jbt){
            jbt.setIconTextGap(0);
            jbt.setHorizontalAlignment(0);
            jbt.setMargin(new Insets(2, 5, 2, 5));
        } else if (comp instanceof JComboBox jcb){
            jcb.setEditable(false);
            jcb.setBorder(null);
        }
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