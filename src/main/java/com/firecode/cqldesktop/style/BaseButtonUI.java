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
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.*;
import javax.swing.text.View;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BaseButtonUI extends BasicButtonUI {

    protected static Rectangle viewRect = new Rectangle();
    protected static Rectangle textRect = new Rectangle();
    protected static Rectangle iconRect = new Rectangle();
    protected static Color[] defaultColors = null;

    public static ComponentUI createUI(JComponent c) {
        return new BaseButtonUI();
    }

    protected void installKeyboardActions(AbstractButton b) {
        super.installKeyboardActions(b);
        InputMap im = (InputMap) UIManager.get("Button.focusInputMap");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "pressed");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "released");
        Color cArr[] = AbstractLookAndFeel.getTheme().getButtonColors();
        defaultColors = new Color[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            defaultColors[i] = ColorHelper.brighter(cArr[i], 20);
        }
    }

    public void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setOpaque(false);
        b.setRolloverEnabled(true);
    }

    protected void uninstallDefaults(AbstractButton b) {
        super.uninstallDefaults(b);
        b.setOpaque(true);
        b.setRolloverEnabled(false);
    }
    

    protected BasicButtonListener createButtonListener(AbstractButton b) {
        return new BaseButtonListener(b);
    }

    protected void paintBackground(Graphics g, AbstractButton b) {
        if (!b.isContentAreaFilled() || (b.getParent() instanceof JMenuBar)) {
            return;
        }

        int width = b.getWidth();
        int height = b.getHeight();
        
        ButtonModel model = b.getModel();
        Color colors[] = AbstractLookAndFeel.getTheme().getButtonColors();
        if (b.isEnabled()) {
            Color background = b.getBackground();
            if (background instanceof ColorUIResource) {
                if (model.isPressed() && model.isArmed()) {
                    colors = AbstractLookAndFeel.getTheme().getPressedColors();
                } else if (b.isRolloverEnabled() && model.isRollover()) {
                    colors = AbstractLookAndFeel.getTheme().getRolloverColors();
                } else if (AbstractLookAndFeel.getTheme().doShowFocusFrame() && b.hasFocus()) {
                    colors = AbstractLookAndFeel.getTheme().getFocusColors();
                } else if (StyleUtils.isFrameActive(b) 
                        && (b.getRootPane() != null) 
                        && (b.equals(b.getRootPane().getDefaultButton()))) {
                    colors = defaultColors;
                }
            } else {
                if (model.isPressed() && model.isArmed()) {
                    colors = ColorHelper.createColorArr(ColorHelper.darker(background, 30), ColorHelper.darker(background, 10), 20);
                } else {
                    if (b.isRolloverEnabled() && model.isRollover()) {
                        colors = ColorHelper.createColorArr(ColorHelper.brighter(background, 50), ColorHelper.brighter(background, 10), 20);
                    } else {
                        colors = ColorHelper.createColorArr(ColorHelper.brighter(background, 30), ColorHelper.darker(background, 10), 20);
                    }
                }
            }
        } else { // disabled
            colors = AbstractLookAndFeel.getTheme().getDisabledColors();
        }
        
        if (b.isBorderPainted() && (b.getBorder() != null) && !(b.getBorder() instanceof EmptyBorder)) {
            Insets insets = b.getBorder().getBorderInsets(b);
            int x = insets.left > 0 ? 1 : 0;
            int y = insets.top > 0 ? 1 : 0;
            int w = insets.right > 0 ? width - 1 : width;
            int h = insets.bottom > 0 ? height - 1 : height;
            StyleUtils.fillHorGradient(g, colors, x, y, w - x, h - y);
        } else {
        	StyleUtils.fillHorGradient(g, colors, 0, 0, width, height);
        }
    }

    protected void paintText(Graphics g, AbstractButton b, Rectangle textRect, String text) {
        ButtonModel model = b.getModel();
        FontMetrics fm = StyleUtils.getFontMetrics(b, g, b.getFont());
        int mnemIndex;
        if (StyleUtils.getJavaVersion() >= 1.4) {
            mnemIndex = b.getDisplayedMnemonicIndex();
        } else {
            mnemIndex = StyleUtils.findDisplayedMnemonicIndex(b.getText(), model.getMnemonic());
        }

        if (model.isEnabled()) {
            Color foreground = b.getForeground();
            Color background = b.getBackground();
            int offs = 0;
            if (model.isArmed() && model.isPressed()) {
                offs = 1;
            }
            if (!(model.isPressed() && model.isArmed())) {
                Object sc = b.getClientProperty("shadowColor");
                if (sc instanceof Color) {
                    g.setColor((Color)sc);
                    StyleUtils.drawStringUnderlineCharAt(b, g, text, mnemIndex, textRect.x + 1, textRect.y + 1 + fm.getAscent());
                }
            }
            if (background instanceof ColorUIResource) {
                if (model.isPressed() && model.isArmed()) {
                    g.setColor(AbstractLookAndFeel.getTheme().getPressedForegroundColor());
                } else if (model.isRollover()) {
                    g.setColor(AbstractLookAndFeel.getTheme().getRolloverForegroundColor());
                } else {
                    g.setColor(foreground);
                }
            } else {
                g.setColor(foreground);
            }
            StyleUtils.drawStringUnderlineCharAt(b, g, text, mnemIndex, textRect.x + offs, textRect.y + offs + fm.getAscent());
        } else {
            if (ColorHelper.getGrayValue(b.getForeground()) < 128) {
                g.setColor(Color.white);
                StyleUtils.drawStringUnderlineCharAt(b, g, text, mnemIndex, textRect.x + 1, textRect.y + 1 + fm.getAscent());
            }
            g.setColor(AbstractLookAndFeel.getDisabledForegroundColor());
            StyleUtils.drawStringUnderlineCharAt(b, g, text, mnemIndex, textRect.x, textRect.y + fm.getAscent());
        }
    }

    protected void paintFocus(Graphics g, AbstractButton b, Rectangle viewRect, Rectangle textRect, Rectangle iconRect) {
        g.setColor(AbstractLookAndFeel.getFocusColor());
        BasicGraphicsUtils.drawDashedRect(g, 4, 3, b.getWidth() - 8, b.getHeight() - 6);
    }

    public void paint(Graphics g, JComponent c) {
        Graphics2D g2D = (Graphics2D) g;

        AbstractButton b = (AbstractButton) c;
        Font f = c.getFont();
        g.setFont(f);
        FontMetrics fm = StyleUtils.getFontMetrics(b, g, b.getFont());
        Insets insets = c.getInsets();

        viewRect.x = insets.left;
        viewRect.y = insets.top;
        viewRect.width = b.getWidth() - (insets.right + viewRect.x);
        viewRect.height = b.getHeight() - (insets.bottom + viewRect.y);

        textRect.x = textRect.y = textRect.width = textRect.height = 0;
        iconRect.x = iconRect.y = iconRect.width = iconRect.height = 0;

        int iconTextGap = defaultTextIconGap;
        if (StyleUtils.getJavaVersion() >= 1.4) {
            iconTextGap = b.getIconTextGap();
        }
        String text = SwingUtilities.layoutCompoundLabel(
                c, fm, b.getText(), b.getIcon(),
                b.getVerticalAlignment(), b.getHorizontalAlignment(),
                b.getVerticalTextPosition(), b.getHorizontalTextPosition(),
                viewRect, iconRect, textRect,
                b.getText() == null ? 0 : iconTextGap);

        paintBackground(g, b);

        if (b.getIcon() != null) {
            if (!b.isEnabled()) {
                Composite savedComposite = g2D.getComposite();
                AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
                g2D.setComposite(alpha);
                paintIcon(g, c, iconRect);
                g2D.setComposite(savedComposite);
            } else {
                if (b.getModel().isPressed() && b.getModel().isRollover()) {
                    iconRect.x++;
                    iconRect.y++;
                }
                paintIcon(g, c, iconRect);
            }
        }

        if (text != null && !text.equals("")) {
            View v = (View) c.getClientProperty(BasicHTML.propertyKey);
            if (v != null) {
                Object savedRenderingHint = null;
                if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                    savedRenderingHint = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
                    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                }
                v.paint(g, textRect);
                if (AbstractLookAndFeel.getTheme().isTextAntiAliasingOn()) {
                    g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, savedRenderingHint);
                }
            } else {
                paintText(g, b, textRect, text);
            }
        }

        if (b.isFocusPainted() && b.hasFocus()) {
            paintFocus(g, b, viewRect, textRect, iconRect);
        }
    }
}
