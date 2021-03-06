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
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicMenuItemUI;

/**
 * @author Michael Hagen
 */
public class BaseMenuItemUI extends BasicMenuItemUI {

    public static ComponentUI createUI(JComponent c) {
        return new BaseMenuItemUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        c.setOpaque(false);
    }

    public void uninstallUI(JComponent c) {
        c.setOpaque(true);
        super.uninstallUI(c);
    }

    public void update(Graphics g, JComponent c) {
        paintBackground(g, c, 0, 0, c.getWidth(), c.getHeight());
        paint(g, c);
    }

    protected void paintBackground(Graphics g, JMenuItem menuItem, Color bgColor) {
        if (menuItem.isOpaque()) {
            int w = menuItem.getWidth();
            int h = menuItem.getHeight();
            paintBackground(g, menuItem, 0, 0, w, h);
        }
    }

    protected void paintBackground(Graphics g, JComponent c, int x, int y, int w, int h) {
        JMenuItem mi = (JMenuItem) c;
        Color backColor = mi.getBackground();
        if (backColor == null || backColor instanceof UIResource) {
            backColor = AbstractLookAndFeel.getMenuBackgroundColor();
        }
        
        ButtonModel model = mi.getModel();
        if (model.isArmed() || model.isRollover() || (c instanceof JMenu && model.isSelected())) {
            g.setColor(AbstractLookAndFeel.getMenuSelectionBackgroundColor());
            g.fillRect(x, y, w, h);
            g.setColor(AbstractLookAndFeel.getMenuSelectionForegroundColor());
        } else if (!AbstractLookAndFeel.getTheme().isMenuOpaque()) {
            Graphics2D g2D = (Graphics2D) g;
            Composite savedComposite = g2D.getComposite();
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, AbstractLookAndFeel.getTheme().getMenuAlpha());
            g2D.setComposite(alpha);
            g2D.setColor(backColor);
            g2D.fillRect(x, y, w, h);
            g2D.setComposite(savedComposite);
            g.setColor(AbstractLookAndFeel.getMenuForegroundColor());
        } else {
            g.setColor(backColor);
            g.fillRect(x, y, w, h);
            g.setColor(AbstractLookAndFeel.getMenuForegroundColor());
        }
    }

    protected void paintText(Graphics g, JMenuItem menuItem, Rectangle textRect, String text) {
        ButtonModel model = menuItem.getModel();
        Color foreColor = menuItem.getForeground();
        if (model.isArmed() || model.isRollover()) {
            foreColor = AbstractLookAndFeel.getMenuSelectionForegroundColor();
        } else if (foreColor == null || foreColor instanceof UIResource) {
            foreColor = AbstractLookAndFeel.getMenuForegroundColor();
        }
        Graphics2D g2D = (Graphics2D) g;
        Object savedRenderingHint = null;
        if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
            savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
        }
        g2D.setColor(foreColor);
        super.paintText(g, menuItem, textRect, text);
        if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
            g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
        }
    }
}
