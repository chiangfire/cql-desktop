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
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicProgressBarUI;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BaseProgressBarUI extends BasicProgressBarUI {

    protected PropertyChangeListener propertyChangeListener;

    public static ComponentUI createUI(JComponent c) {
        return new BaseProgressBarUI();
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        c.setBorder(UIManager.getBorder("ProgressBar.border"));
        propertyChangeListener = new PropertyChangeHandler();
        c.addPropertyChangeListener(propertyChangeListener);
    }

    public void uninstallUI(JComponent c) {
        c.removePropertyChangeListener(propertyChangeListener);
        super.uninstallUI(c);
    }

    /*
     * The "selectionForeground" is the color of the text when it is painted over a filled area of the progress bar.
     */
    protected Color getSelectionForeground() {
        Object selectionForeground = progressBar.getClientProperty("selectionForeground");
        if (selectionForeground instanceof Color) {
            return (Color) selectionForeground;
        }
        return super.getSelectionForeground();
    }

    /*
     * The "selectionBackground" is the color of the text when it is painted over an unfilled area of the progress bar.
     */
    protected Color getSelectionBackground() {
        Object selectionBackground = progressBar.getClientProperty("selectionBackground");
        if (selectionBackground instanceof Color) {
            return (Color) selectionBackground;
        }
        return super.getSelectionBackground();
    }

    private void paintString(Graphics g, int x, int y, int width, int height, int fillStart, int amountFull, Insets b) {
        if (!(g instanceof Graphics2D)) {
            return;
        }

        Graphics2D g2D = (Graphics2D) g;
        String progressString = progressBar.getString();
        g2D.setFont(progressBar.getFont());
        Point renderLocation = getStringPlacement(g2D, progressString, x, y, width, height);
        Rectangle savedClip = g2D.getClipBounds();
        
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            g2D.setColor(getSelectionBackground());
            StyleUtils.drawString(progressBar, g2D, progressString, renderLocation.x, renderLocation.y);
            g2D.setColor(getSelectionForeground());
            g2D.clipRect(fillStart, y, amountFull, height);
            StyleUtils.drawString(progressBar, g2D, progressString, renderLocation.x, renderLocation.y);
        } else { // VERTICAL
            g2D.setColor(getSelectionBackground());
            AffineTransform rotate = AffineTransform.getRotateInstance(Math.PI / 2);
            g2D.setFont(progressBar.getFont().deriveFont(rotate));
            renderLocation = getStringPlacement(g2D, progressString, x, y, width, height);
            StyleUtils.drawString(progressBar, g2D, progressString, renderLocation.x, renderLocation.y);
            g2D.setColor(getSelectionForeground());
            g2D.clipRect(x, fillStart, width, amountFull);
            StyleUtils.drawString(progressBar, g2D, progressString, renderLocation.x, renderLocation.y);
        }
        g2D.setClip(savedClip);
    }

    protected void paintString(Graphics g, int x, int y, int width, int height, int amountFull, Insets b) {
        boolean indeterminate = false;
        if (StyleUtils.getJavaVersion() >= 1.6) {
            indeterminate = progressBar.isIndeterminate();
        }
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            if (StyleUtils.isLeftToRight(progressBar)) {
                if (indeterminate) {
                    boxRect = getBox(boxRect);
                    paintString(g, x, y, width, height, boxRect.x, boxRect.width, b);
                } else {
                    paintString(g, x, y, width, height, x, amountFull, b);
                }
            } else {
                paintString(g, x, y, width, height, x + width - amountFull, amountFull, b);
            }
        } else {
            if (indeterminate) {
                boxRect = getBox(boxRect);
                paintString(g, x, y, width, height, boxRect.y, boxRect.height, b);
            } else {
                paintString(g, x, y, width, height, y + height - amountFull,  amountFull, b);
            }
        }
    }

    protected void paintIndeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) {
            return;
        }
        Graphics2D g2D = (Graphics2D) g;

        Insets b = progressBar.getInsets(); // area for border
        int barRectWidth = progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

        Color colors[];
        if (progressBar.getForeground() instanceof UIResource) {
            if (!StyleUtils.isActive(c)) {
                colors = AbstractLookAndFeel.getTheme().getInActiveColors();
            } else if (c.isEnabled()) {
                colors = AbstractLookAndFeel.getTheme().getProgressBarColors();
            } else {
                colors = AbstractLookAndFeel.getTheme().getDisabledColors();
            }
        } else {
            Color hiColor = ColorHelper.brighter(progressBar.getForeground(), 40);
            Color loColor = ColorHelper.darker(progressBar.getForeground(), 20);
            colors = ColorHelper.createColorArr(hiColor, loColor, 20);
        }

        Color cHi = ColorHelper.darker(colors[colors.length - 1], 5);
        Color cLo = ColorHelper.darker(colors[colors.length - 1], 10);

        // Paint the bouncing box.
        Rectangle box = getBox(null);
        if (box != null) {
            g2D.setColor(progressBar.getForeground());
            StyleUtils.draw3DBorder(g, cHi, cLo, box.x + 1, box.y + 1, box.width - 2, box.height - 2);
            if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            	StyleUtils.fillHorGradient(g, colors, box.x + 2, box.y + 2, box.width - 4, box.height - 4);
            } else {
            	StyleUtils.fillVerGradient(g, colors, box.x + 2, box.y + 2, box.width - 4, box.height - 4);
            }

            // Deal with possible text painting
            if (progressBar.isStringPainted()) {
                Object savedRenderingHint = null;
                if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                    savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
                    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
                }
                if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
                    paintString(g2D, b.left, b.top, barRectWidth, barRectHeight, box.width, b);
                } else {
                    paintString(g2D, b.left, b.top, barRectWidth, barRectHeight, box.height, b);
                }
                if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
                }
            }
        }
    }

    protected void paintDeterminate(Graphics g, JComponent c) {
        if (!(g instanceof Graphics2D)) {
            return;
        }

        Graphics2D g2D = (Graphics2D) g;
        Insets b = progressBar.getInsets(); // area for border
        int w = progressBar.getWidth() - (b.right + b.left);
        int h = progressBar.getHeight() - (b.top + b.bottom);

        // amount of progress to draw
        int amountFull = getAmountFull(b, w, h);
        Color colors[];
        if (progressBar.getForeground() instanceof UIResource) {
            if (!StyleUtils.isActive(c)) {
                colors = AbstractLookAndFeel.getTheme().getInActiveColors();
            } else if (c.isEnabled()) {
                colors = AbstractLookAndFeel.getTheme().getProgressBarColors();
            } else {
                colors = AbstractLookAndFeel.getTheme().getDisabledColors();
            }
        } else {
            Color hiColor = ColorHelper.brighter(progressBar.getForeground(), 40);
            Color loColor = ColorHelper.darker(progressBar.getForeground(), 20);
            colors = ColorHelper.createColorArr(hiColor, loColor, 20);
        }
        Color cHi = ColorHelper.darker(colors[colors.length - 1], 5);
        Color cLo = ColorHelper.darker(colors[colors.length - 1], 10);
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            if (StyleUtils.isLeftToRight(progressBar)) {
            	StyleUtils.draw3DBorder(g, cHi, cLo, 1 + b.left, 2, amountFull - 2, h - 2);
            	StyleUtils.fillHorGradient(g, colors, 2 + b.left, 3, amountFull - 4, h - 4);
            } else {
            	StyleUtils.draw3DBorder(g, cHi, cLo, progressBar.getWidth() - amountFull - b.right + 2, 2, amountFull - 2, h - 2);
            	StyleUtils.fillHorGradient(g, colors, progressBar.getWidth() - amountFull - b.right + 3, 3, amountFull - 4, h - 4);
            }
        } else { // VERTICAL
        	StyleUtils.draw3DBorder(g, cHi, cLo, 2, h - amountFull + 2, w - 2, amountFull - 2);
        	StyleUtils.fillVerGradient(g, colors, 3, h - amountFull + 3, w - 4, amountFull - 4);
        }

        // Deal with possible text painting
        if (progressBar.isStringPainted()) {
            Object savedRenderingHint = null;
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
            }
            paintString(g, b.left, b.top, w, h, amountFull, b);
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
            }
        }
    }

    public void paint(Graphics g, JComponent c) {
        if (StyleUtils.getJavaVersion() >= 1.4) {
            if (progressBar.isIndeterminate()) {
                paintIndeterminate(g, c);
            } else {
                paintDeterminate(g, c);
            }
        } else {
            paintDeterminate(g, c);
        }
    }

//-----------------------------------------------------------------------------------------------
    protected class PropertyChangeHandler implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent e) {
            if ("selectionForeground".equals(e.getPropertyName()) && (e.getNewValue() instanceof Color)) {
                progressBar.invalidate();
                progressBar.repaint();
            } else if ("selectionBackground".equals(e.getPropertyName()) && (e.getNewValue() instanceof Color)) {
                progressBar.invalidate();
                progressBar.repaint();
            }
        }
    }
}
