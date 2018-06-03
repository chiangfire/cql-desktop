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
import javax.swing.Icon;
import javax.swing.plaf.basic.BasicArrowButton;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BaseScrollButton extends BasicArrowButton {

    protected int buttonWidth = 24;

    public BaseScrollButton(int direction, int width) {
        super(direction);
        buttonWidth = width;
    }

    public void paint(Graphics g) {
        boolean isPressed = getModel().isPressed();
        boolean isRollover = getModel().isRollover();

        int width = getWidth();
        int height = getHeight();

        Color colors[];
        if (isPressed) {
            colors = AbstractLookAndFeel.getTheme().getPressedColors();
        } else if (isRollover) {
            colors = AbstractLookAndFeel.getTheme().getRolloverColors();
        } else {
            colors = AbstractLookAndFeel.getTheme().getButtonColors();
        }

        boolean inverse = ColorHelper.getGrayValue(colors) < 128;
        
        Color frameColorHi = ColorHelper.brighter(colors[0], 20);
        Color frameColorLo = ColorHelper.darker(colors[colors.length - 1], 20);

        Graphics2D g2D = (Graphics2D) g;
        Composite savedComposite = g2D.getComposite();
        if ((getDirection() == NORTH) || (getDirection() == SOUTH)) {
            StyleUtils.fillVerGradient(g2D, colors, 0, 0, width, height);
        } else {
        	StyleUtils.fillHorGradient(g2D, colors, 0, 0, width, height);
        }
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
        if (getDirection() == NORTH) {
            Icon upArrow = inverse ? BaseIcons.getUpArrowInverseIcon() : BaseIcons.getUpArrowIcon();
            int x = (width / 2) - (upArrow.getIconWidth() / 2);
            int y = (height / 2) - (upArrow.getIconHeight() / 2) - 1;
            upArrow.paintIcon(this, g2D, x, y);
        } else if (getDirection() == SOUTH) {
            Icon downArrow = inverse ? BaseIcons.getDownArrowInverseIcon() : BaseIcons.getDownArrowIcon();
            int x = (width / 2) - (downArrow.getIconWidth() / 2);
            int y = (height / 2) - (downArrow.getIconHeight() / 2);
            downArrow.paintIcon(this, g2D, x, y);
        } else if (getDirection() == WEST) {
            Icon leftArrow = inverse ? BaseIcons.getLeftArrowInverseIcon() : BaseIcons.getLeftArrowIcon();
            int x = (width / 2) - (leftArrow.getIconWidth() / 2) - 1;
            int y = (height / 2) - (leftArrow.getIconHeight() / 2);
            leftArrow.paintIcon(this, g2D, x, y);
        } else {
            Icon rightArrow = inverse ? BaseIcons.getRightArrowInverseIcon() : BaseIcons.getRightArrowIcon();
            int x = (width / 2) - (rightArrow.getIconWidth() / 2);
            int y = (height / 2) - (rightArrow.getIconHeight() / 2);
            rightArrow.paintIcon(this, g2D, x, y);
        }
        StyleUtils.draw3DBorder(g2D, frameColorLo, ColorHelper.darker(frameColorLo, 10), 0, 0, width, height);
        g2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
        g2D.setColor(frameColorHi);
        g2D.drawLine(1, 1, width - 2, 1);
        g2D.drawLine(1, 1, 1, height - 2);
        
        g2D.setComposite(savedComposite);
    }

    public Dimension getPreferredSize() {
        if (getDirection() == NORTH) {
            return new Dimension(buttonWidth, buttonWidth + 1);
        } else if (getDirection() == SOUTH) {
            return new Dimension(buttonWidth, buttonWidth + 1);
        } else if (getDirection() == EAST) {
            return new Dimension(buttonWidth + 1, buttonWidth);
        } else if (getDirection() == WEST) {
            return new Dimension(buttonWidth + 1, buttonWidth);
        } else {
            return new Dimension(0, 0);
        }
    }

    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    public Dimension getMaximumSize() {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public int getButtonWidth() {
        return buttonWidth;
    }
}
