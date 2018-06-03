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
package com.firecode.cqldesktop.style.theme.simple;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;

import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.AbstractToolBarUI;
import com.firecode.cqldesktop.style.ColorHelper;
import com.firecode.cqldesktop.utils.StyleUtils;

public class SimpleToolBarUI extends AbstractToolBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new SimpleToolBarUI();
    }

    public Border getRolloverBorder() {
        return SimpleBorders.getRolloverToolButtonBorder();
    }

    public Border getNonRolloverBorder() {
        return SimpleBorders.getToolButtonBorder();
    }

    public boolean isButtonOpaque() {
        return false;
    }

    public void paint(Graphics g, JComponent c) {
        int w = c.getWidth();
        int h = c.getHeight();
        StyleUtils.fillHorGradient(g, AbstractLookAndFeel.getTheme().getToolBarColors(), 0, 0, w, h - 2);
        if ((toolBar.getOrientation() == JToolBar.HORIZONTAL) && isToolbarDecorated() && isToolBarUnderMenubar()) {
            g.setColor(Color.white);
            g.drawLine(0, 0, w, 0);
            g.drawLine(0, h - 2, w, h - 2);
            g.setColor(ColorHelper.darker(AbstractLookAndFeel.getToolbarColorDark(), 10));
            g.drawLine(0, 1, w, 1);
            g.drawLine(0, h - 1, w, h - 1);
        }
    }
}
