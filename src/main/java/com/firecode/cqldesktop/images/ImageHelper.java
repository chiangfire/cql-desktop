
package com.firecode.cqldesktop.images;

import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author mao
 * 
 * 加载图片
 */

public class ImageHelper {

    private ImageHelper() {
    }

    public static ImageIcon loadImage(String name) {
        ImageIcon image = null;
        try {
            URL url = ImageHelper.class.getResource(name);
            if (url != null) {
                java.awt.Image img = Toolkit.getDefaultToolkit().createImage(url);
                if (img != null) {
                    image = new ImageIcon(img);
                }
            }
        } catch (Throwable ex) {
            System.out.println("ERROR: loading image " + name + " failed. Exception: " + ex.getMessage());
        }
        return image;
    }
}
