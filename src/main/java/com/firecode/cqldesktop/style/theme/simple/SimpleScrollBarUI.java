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
import javax.swing.*;
import javax.swing.plaf.ComponentUI;

import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.BaseScrollBarUI;
import com.firecode.cqldesktop.style.ColorHelper;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 *
 * @author  Michael Hagen
 */
public class SimpleScrollBarUI extends BaseScrollBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new SimpleScrollBarUI();
    }

    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (!c.isEnabled()) {
            return;
        }

        g.translate(thumbBounds.x, thumbBounds.y);

        Color colors[] = getThumbColors();
        Color frameColor = AbstractLookAndFeel.getFrameColor();
        if (!StyleUtils.isActive(scrollbar)) {
            frameColor = ColorHelper.brighter(frameColor, 80);
        }
        if (isRollover || isDragging) {
            frameColor = AbstractLookAndFeel.getTheme().getRolloverColorDark();
        }

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
        	StyleUtils.fillVerGradient(g, colors, 1, 1, thumbBounds.width - 1, thumbBounds.height - 1);

        	StyleUtils.draw3DBorder(g, frameColor, ColorHelper.darker(frameColor, 15), 0, 0, thumbBounds.width, thumbBounds.height);

            Graphics2D g2D = (Graphics2D) g;
            Composite composite = g2D.getComposite();
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
            g2D.setComposite(alpha);

            if (!AbstractLookAndFeel.getTheme().isMacStyleScrollBarOn()) {
                int dx = 5;
                int dy = thumbBounds.height / 2 - 3;
                int dw = thumbBounds.width - 11;

                Color c1 = Color.white;
                Color c2 = Color.darkGray;

                for (int i = 0; i < 4; i++) {
                    g.setColor(c1);
                    g.drawLine(dx, dy, dx + dw, dy);
                    dy++;
                    g.setColor(c2);
                    g.drawLine(dx, dy, dx + dw, dy);
                    dy++;
                }
            }
            g2D.setComposite(composite);
        } else { // HORIZONTAL
        	StyleUtils.fillHorGradient(g, colors, 1, 1, thumbBounds.width - 1, thumbBounds.height - 1);
        	StyleUtils.draw3DBorder(g, frameColor, ColorHelper.darker(frameColor, 10), 0, 0, thumbBounds.width, thumbBounds.height);

            int dx = thumbBounds.width / 2 - 3;
            int dy = 5;
            int dh = thumbBounds.height - 11;

            Graphics2D g2D = (Graphics2D) g;
            Composite composite = g2D.getComposite();
            AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
            g2D.setComposite(alpha);

            if (!AbstractLookAndFeel.getTheme().isMacStyleScrollBarOn()) {
                Color c1 = Color.white;
                Color c2 = Color.darkGray;

                for (int i = 0; i < 4; i++) {
                    g.setColor(c1);
                    g.drawLine(dx, dy, dx, dy + dh);
                    dx++;
                    g.setColor(c2);
                    g.drawLine(dx, dy, dx, dy + dh);
                    dx++;
                }
            }
            g2D.setComposite(composite);
        }

        g.translate(-thumbBounds.x, -thumbBounds.y);
    }
}
