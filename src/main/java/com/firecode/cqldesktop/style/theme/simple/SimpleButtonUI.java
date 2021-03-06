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

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.BaseButtonUI;

/**
 * @author Michael Hagen
 */
public class SimpleButtonUI extends BaseButtonUI {

    public static ComponentUI createUI(JComponent c) {
        return new SimpleButtonUI();
    }

    protected void paintBackground(Graphics g, AbstractButton b) {
        if (AbstractLookAndFeel.getTheme().doDrawSquareButtons()) {
            super.paintBackground(g, b);
        } else {
            int w = b.getWidth();
            int h = b.getHeight();
            Graphics2D g2D = (Graphics2D) g;
            Shape savedClip = g.getClip();
            if ((b.getBorder() != null) && b.isBorderPainted() && (b.getBorder() instanceof UIResource)) {
                Area clipArea = new Area(new RoundRectangle2D.Double(0, 0, w -1, h - 1, 6, 6));
                if (savedClip != null) {
                    clipArea.intersect(new Area(savedClip));
                }
                g2D.setClip(clipArea);
            }
            super.paintBackground(g, b);
            g2D.setClip(savedClip);
        }
    }
}
