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

import javax.swing.border.Border;

import com.firecode.cqldesktop.style.AbstractBorderFactory;

/**
 * @author Michael Hagen
 */
public class SimpleBorderFactory implements AbstractBorderFactory {

    private static SimpleBorderFactory instance = null;

    private SimpleBorderFactory() {
    }

    public static synchronized SimpleBorderFactory getInstance() {
        if (instance == null) {
            instance = new SimpleBorderFactory();
        }
        return instance;
    }

    public Border getFocusFrameBorder() {
        return SimpleBorders.getFocusFrameBorder();
    }

    public Border getButtonBorder() {
        return SimpleBorders.getButtonBorder();
    }

    public Border getToggleButtonBorder() {
        return SimpleBorders.getToggleButtonBorder();
    }

    public Border getTextBorder() {
        return SimpleBorders.getTextBorder();
    }

    public Border getSpinnerBorder() {
        return SimpleBorders.getSpinnerBorder();
    }

    public Border getTextFieldBorder() {
        return SimpleBorders.getTextFieldBorder();
    }

    public Border getComboBoxBorder() {
        return SimpleBorders.getComboBoxBorder();
    }

    public Border getTableHeaderBorder() {
        return SimpleBorders.getTableHeaderBorder();
    }

    public Border getTableScrollPaneBorder() {
        return SimpleBorders.getTableScrollPaneBorder();
    }

    public Border getScrollPaneBorder() {
        return SimpleBorders.getScrollPaneBorder();
    }

    public Border getTabbedPaneBorder() {
        return SimpleBorders.getTabbedPaneBorder();
    }

    public Border getMenuBarBorder() {
        return SimpleBorders.getMenuBarBorder();
    }

    public Border getMenuItemBorder() {
        return SimpleBorders.getMenuItemBorder();
    }

    public Border getPopupMenuBorder() {
        return SimpleBorders.getPopupMenuBorder();
    }

    public Border getInternalFrameBorder() {
        return SimpleBorders.getInternalFrameBorder();
    }

    public Border getPaletteBorder() {
        return SimpleBorders.getPaletteBorder();
    }

    public Border getToolBarBorder() {
        return SimpleBorders.getToolBarBorder();
    }

    public Border getProgressBarBorder() {
        return SimpleBorders.getProgressBarBorder();
    }

    public Border getDesktopIconBorder() {
        return SimpleBorders.getDesktopIconBorder();
    }
}
