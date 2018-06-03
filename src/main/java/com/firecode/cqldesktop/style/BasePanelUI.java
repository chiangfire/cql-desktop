/**
 * MIT License
 * Copyright (c) 2018 jiangcihuo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.firecode.cqldesktop.style;

import java.awt.*;
import java.lang.reflect.Field;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPanelUI;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BasePanelUI extends BasicPanelUI {

    private static BasePanelUI panelUI = null;

    public static ComponentUI createUI(JComponent c) {
        if (panelUI == null) {
            panelUI = new BasePanelUI();
        }
        return panelUI;
    }

    protected void installDefaults(JPanel p) {
        super.installDefaults(p);
        p.setFont(AbstractLookAndFeel.getTheme().getControlTextFont());
        
        // We don't want medium weight popups for tool tips, so we try to force heavy weight popups.
        try {
            Field field;
            if (StyleUtils.getJavaVersion() < 1.7) {
                Class clazz = Class.forName("javax.swing.PopupFactory");
                field = clazz.getDeclaredField("forceHeavyWeightPopupKey");
            } else { //1.7.0, 1.8.0
                Class clazz = Class.forName("javax.swing.ClientPropertyKey");
                field = clazz.getDeclaredField("PopupFactory_FORCE_HEAVYWEIGHT_POPUP");
            }
            field.setAccessible(true);
            p.putClientProperty(field.get(null), Boolean.TRUE);
        } catch(Exception ex) {
        }        
    }

    public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            Object backgroundTexture = c.getClientProperty("backgroundTexture");
            if (backgroundTexture instanceof Icon) {
            	StyleUtils.fillComponent(g, c, (Icon)backgroundTexture);
            } else {
                g.setColor(c.getBackground());
                g.fillRect(0, 0, c.getWidth(), c.getHeight());
            }
        }
    }

    public void paint(Graphics g, JComponent c) {
        Graphics2D g2D = (Graphics2D) g;
        Object savedRenderingHint = null;
        if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
            savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
        }
        super.paint(g, c);
        if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
        }
    }
}
