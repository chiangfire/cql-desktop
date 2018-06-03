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
import javax.swing.JRootPane;

import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.BaseRootPaneUI;
import com.firecode.cqldesktop.style.BaseTitlePane;
import com.firecode.cqldesktop.style.ColorHelper;
import com.firecode.cqldesktop.style.DecorationHelper;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author  Michael Hagen
 */
public class SimpleTitlePane extends BaseTitlePane {

    public SimpleTitlePane(JRootPane root, BaseRootPaneUI ui) {
        super(root, ui);
    }

    public LayoutManager createLayout() {
        return new TitlePaneLayout();
    }

    protected int getHorSpacing() {
        return 1;
    }

    protected int getVerSpacing() {
        return 3;
    }

    public void paintBorder(Graphics g) {
        if (isActive()) {
            g.setColor(AbstractLookAndFeel.getWindowBorderColor());
        } else {
            g.setColor(AbstractLookAndFeel.getWindowInactiveBorderColor());
        }
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    public void paintText(Graphics g, int x, int y, String title) {
        Color shadowColor = AbstractLookAndFeel.getWindowTitleColorDark();
        if (isActive()) {
            shadowColor = ColorHelper.darker(shadowColor, 30);
        }
        g.setColor(shadowColor);
        StyleUtils.drawString(rootPane, g, title, x - 1, y - 1);
        StyleUtils.drawString(rootPane, g, title, x - 1, y + 1);
        StyleUtils.drawString(rootPane, g, title, x + 1, y - 1);
        StyleUtils.drawString(rootPane, g, title, x + 1, y + 1);
        if (isActive()) {
            g.setColor(AbstractLookAndFeel.getWindowTitleForegroundColor());
        } else {
            g.setColor(AbstractLookAndFeel.getWindowInactiveTitleForegroundColor());
        }
        StyleUtils.drawString(rootPane, g, title, x, y);
    }
//-----------------------------------------------------------------------------------------------
    protected class TitlePaneLayout implements LayoutManager {

        public void addLayoutComponent(String name, Component c) {
        }

        public void removeLayoutComponent(Component c) {
        }

        public Dimension preferredLayoutSize(Container c) {
            int height = computeHeight();
            return new Dimension(height, height);
        }

        public Dimension minimumLayoutSize(Container c) {
            return preferredLayoutSize(c);
        }

        protected int computeHeight() {
            FontMetrics fm = StyleUtils.getFontMetrics(SimpleTitlePane.this, null, getFont());
            return fm.getHeight() + 5;
        }

        public void layoutContainer(Container c) {
            if (AbstractLookAndFeel.getTheme().isMacStyleWindowDecorationOn()) {
                layoutMacStyle(c);
            } else {
                layoutDefault(c);
            }
        }

        public void layoutDefault(Container c) {
            boolean leftToRight = isLeftToRight();

            int spacing = getHorSpacing();
            int w = getWidth();
            int h = getHeight();

            // assumes all buttons have the same dimensions these dimensions include the borders
            int btnHeight = h - getVerSpacing();
            int btnWidth = btnHeight + 10;

            if (menuBar != null) {
                int mw = menuBar.getPreferredSize().width;
                int mh = menuBar.getPreferredSize().height;
                if (leftToRight) {
                    menuBar.setBounds(2, (h - mh) / 2, mw, mh);
                } else {
                    menuBar.setBounds(getWidth() - mw, (h - mh) / 2, mw, mh);
                }
            }

            int x = leftToRight ? w - spacing : 0;
            int y = Math.max(0, ((h - btnHeight) / 2) - 1);
            
            if (closeButton != null) {
                x += leftToRight ? -btnWidth : spacing;
                closeButton.setBounds(x, y, btnWidth, btnHeight);
                if (!leftToRight) {
                    x += btnWidth;
                }
            }

            if ((maxButton != null) && (maxButton.getParent() != null)) {
                if (DecorationHelper.isFrameStateSupported(Toolkit.getDefaultToolkit(), BaseRootPaneUI.MAXIMIZED_BOTH)) {
                    x += leftToRight ? -spacing - btnWidth : spacing;
                    maxButton.setBounds(x, y, btnWidth, btnHeight);
                    if (!leftToRight) {
                        x += btnWidth;
                    }
                }
            }

            if ((iconifyButton != null) && (iconifyButton.getParent() != null)) {
                x += leftToRight ? -spacing - btnWidth : spacing;
                iconifyButton.setBounds(x, y, btnWidth, btnHeight);
                if (!leftToRight) {
                    x += btnWidth;
                }
            }
            
            buttonsWidth = leftToRight ? w - x : x;
            
            if (customTitlePanel != null) {
                int maxWidth = w - buttonsWidth - spacing - 20;
                if (menuBar != null) {
                    maxWidth -= menuBar.getPreferredSize().width;
                    maxWidth -= spacing;
                }
                int cpw = Math.min(maxWidth, customTitlePanel.getPreferredSize().width);
                int cph = h;
                int cpx = leftToRight ? w - buttonsWidth - cpw : buttonsWidth;
                int cpy = 0;
                customTitlePanel.setBounds(cpx, cpy, cpw, cph);
                buttonsWidth += customTitlePanel.getPreferredSize().width;
            }
        }

        private void layoutMacStyle(Container c) {
            int spacing = getHorSpacing();
            int h = getHeight();

            // assumes all buttons have the same dimensions these dimensions include the borders
            int btnHeight = h - getVerSpacing();
            int btnWidth = btnHeight;

            int x = 0;
            int y = 0;

            if (closeButton != null) {
                closeButton.setBounds(x, y, btnWidth, btnHeight);
                x += btnWidth + spacing;
            }
            if ((iconifyButton != null) && (iconifyButton.getParent() != null)) {
                iconifyButton.setBounds(x, y, btnWidth, btnHeight);
                x += btnWidth + spacing;
            }
            if ((maxButton != null) && (maxButton.getParent() != null)) {
                if (DecorationHelper.isFrameStateSupported(Toolkit.getDefaultToolkit(), BaseRootPaneUI.MAXIMIZED_BOTH)) {
                    maxButton.setBounds(x, y, btnWidth, btnHeight);
                    x += btnWidth + spacing;
                }
            }

            buttonsWidth = x;
           
            if (customTitlePanel != null) {
                int cpx = buttonsWidth + 5;
                int cpy = 0;
                int cpw = customTitlePanel.getPreferredSize().width;
                int cph = h;
                customTitlePanel.setBounds(cpx, cpy, cpw, cph);
                buttonsWidth += cpw + 5;
            }
        }
        
    }
}
