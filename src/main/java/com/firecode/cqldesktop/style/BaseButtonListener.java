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

import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractButton;
import javax.swing.plaf.basic.BasicButtonListener;

public class BaseButtonListener extends BasicButtonListener {

    public BaseButtonListener(AbstractButton b) {
        super(b);
    }

    public void focusGained(FocusEvent e) {
        AbstractButton b = (AbstractButton) e.getSource();
        b.repaint();
    }

    public void focusLost(FocusEvent e) {
        AbstractButton b = (AbstractButton) e.getSource();
        b.repaint();
    }

    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);
        AbstractButton button = (AbstractButton) e.getSource();
        button.getModel().setRollover(true);
    }

    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        AbstractButton button = (AbstractButton) e.getSource();
        button.getModel().setRollover(false);
    }

    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
    }
}
