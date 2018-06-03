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
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.BaseComboBoxUI;
import com.firecode.cqldesktop.style.ColorHelper;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class SimpleComboBoxUI extends BaseComboBoxUI {

    public static ComponentUI createUI(JComponent c) {
        return new SimpleComboBoxUI();
    }

    public JButton createArrowButton() {
        ArrowButton button = new BaseComboBoxUI.ArrowButton();
        Color borderColor = ColorHelper.brighter(AbstractLookAndFeel.getTheme().getFrameColor(), 50);
        if (StyleUtils.isLeftToRight(comboBox)) {
            Border border = BorderFactory.createMatteBorder(0, 1, 0, 0, borderColor);
            button.setBorder(border);
        } else {
            Border border = BorderFactory.createMatteBorder(0, 0, 0, 1, borderColor);
            button.setBorder(border);
        }
        return button;
    }

    protected void setButtonBorder() {
        Color borderColor = ColorHelper.brighter(AbstractLookAndFeel.getTheme().getFrameColor(), 50);
        if (StyleUtils.isLeftToRight(comboBox)) {
            Border border = BorderFactory.createMatteBorder(0, 1, 0, 0, borderColor);
            arrowButton.setBorder(border);
        } else {
            Border border = BorderFactory.createMatteBorder(0, 0, 0, 1, borderColor);
            arrowButton.setBorder(border);
        }
    }
}
