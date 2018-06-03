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

import javax.swing.Icon;
import javax.swing.JButton;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author  Michael Hagen
 */
public class NoFocusButton extends JButton {

    public NoFocusButton() {
        super();
        init();
    }

    public NoFocusButton(Icon ico) {
        super(ico);
        init();
    }

    private void init() {
        setFocusPainted(false);
        setRolloverEnabled(true);
        if (StyleUtils.getJavaVersion() >= 1.4) {
            setFocusable(false);
        }
    }

    public boolean isFocusTraversable() {
        return false;
    }

    public void requestFocus() {
    }
}
