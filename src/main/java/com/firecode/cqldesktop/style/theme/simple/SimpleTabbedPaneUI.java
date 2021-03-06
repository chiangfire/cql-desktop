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
import javax.swing.JComponent;
import javax.swing.plaf.*;
import javax.swing.text.View;

import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.BaseTabbedPaneUI;
import com.firecode.cqldesktop.style.ColorHelper;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * author Michael Hagen
 */
public class SimpleTabbedPaneUI extends BaseTabbedPaneUI {

    public static ComponentUI createUI(JComponent c) {
        return new SimpleTabbedPaneUI();
    }

    public void installDefaults() {
        super.installDefaults();
        tabAreaInsets.bottom = 5;
    }

    protected Color[] getTabColors(int tabIndex, boolean isSelected, boolean isRollover) {
        if ((tabIndex >= 0) && (tabIndex < tabPane.getTabCount())) {
            boolean isEnabled = tabPane.isEnabledAt(tabIndex);
            Color backColor = tabPane.getBackgroundAt(tabIndex);
            Color colorArr[];
            if ((backColor instanceof UIResource)) {
                if (isSelected) {
                    colorArr = AbstractLookAndFeel.getTheme().getDefaultColors();
                } else if (isRollover && isEnabled) {
                    colorArr = AbstractLookAndFeel.getTheme().getRolloverColors();
                } else {
                    colorArr = AbstractLookAndFeel.getTheme().getTabColors();
                }
            } else {
                if (isSelected) {
                    colorArr = ColorHelper.createColorArr(ColorHelper.brighter(backColor, 60), backColor, 20);
                } else if (isRollover && isEnabled) {
                    colorArr = ColorHelper.createColorArr(ColorHelper.brighter(backColor, 80), ColorHelper.brighter(backColor, 20), 20);
                } else {
                    colorArr = ColorHelper.createColorArr(ColorHelper.brighter(backColor, 40), ColorHelper.darker(backColor, 10), 20);
                }
            }
            return colorArr;
        }
        return AbstractLookAndFeel.getTheme().getTabColors();
    }

    protected Color[] getContentBorderColors(int tabPlacement) {
        Color SEP_COLORS[] = {
            ColorHelper.brighter(AbstractLookAndFeel.getControlColorLight(), 20),
            AbstractLookAndFeel.getControlColorLight(),
            ColorHelper.brighter(AbstractLookAndFeel.getControlColorDark(), 20),
            AbstractLookAndFeel.getControlColorDark(),
            ColorHelper.darker(AbstractLookAndFeel.getControlColorDark(), 20)
        };
        return SEP_COLORS;
    }

    protected Color getContentBorderColor() {
        return ColorHelper.brighter(AbstractLookAndFeel.getTheme().getFrameColor(), 50);
    }

    protected Color getLoBorderColor(int tabIndex) {
        if (tabIndex == tabPane.getSelectedIndex() && tabPane.getBackgroundAt(tabIndex) instanceof ColorUIResource) {
            return ColorHelper.brighter(AbstractLookAndFeel.getFrameColor(), 10);
        }
        return super.getLoBorderColor(tabIndex);
    }
    
    protected Font getTabFont(boolean isSelected) {
        if (isSelected) {
            return super.getTabFont(isSelected).deriveFont(Font.BOLD);
        } else {
            return super.getTabFont(isSelected);
        }
    }

    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        Color backColor = tabPane.getBackgroundAt(tabIndex);
        if (!(backColor instanceof UIResource)) {
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected);
            return;
        }
        g.setFont(font);
        View v = getTextViewForTab(tabIndex);
        if (v != null) {
            // html
            Graphics2D g2D = (Graphics2D) g;
            Object savedRenderingHint = null;
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, AbstractLookAndFeel.getTheme().getTextAntiAliasingHint());
            }
            v.paint(g, textRect);
            if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
            }
        } else {
            // plain text
            int mnemIndex = -1;
            if (StyleUtils.getJavaVersion() >= 1.4) {
                mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
            }

            if (tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex)) {
                if (isSelected) {
                    Color shadowColor = ColorHelper.darker(AbstractLookAndFeel.getWindowTitleColorDark(), 30);
                    g.setColor(shadowColor);
                    StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x - 1, textRect.y - 1 + metrics.getAscent());
                    StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x - 1, textRect.y + 1 + metrics.getAscent());
                    StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x + 1, textRect.y - 1 + metrics.getAscent());
                    StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x + 1, textRect.y + 1 + metrics.getAscent());
                    g.setColor(AbstractLookAndFeel.getTheme().getTabSelectionForegroundColor());
                } else {
                    g.setColor(tabPane.getForegroundAt(tabIndex));
                }
                StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());

            } else { // tab disabled
                g.setColor(tabPane.getBackgroundAt(tabIndex).brighter());
                StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x, textRect.y + metrics.getAscent());
                g.setColor(tabPane.getBackgroundAt(tabIndex).darker());
                StyleUtils.drawStringUnderlineCharAt(tabPane, g, title, mnemIndex, textRect.x - 1, textRect.y + metrics.getAscent() - 1);
            }
        }
    }
}
