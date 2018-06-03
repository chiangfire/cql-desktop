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
import javax.swing.Action;
import javax.swing.Icon;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author  Michael Hagen
 */
public class BaseTitleButton extends NoFocusButton {

    private float alpha = 1.0f;

    public BaseTitleButton(Action action, String accessibleName, Icon icon, float alpha) {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setAction(action);
        setText(null);
        setIcon(icon);
        putClientProperty("paintActive", Boolean.TRUE);
        getAccessibleContext().setAccessibleName(accessibleName);
        this.alpha = Math.max(0.2f, alpha);
    }

    public void paint(Graphics g) {
        if (StyleUtils.isActive(this) || (alpha >= 1.0)) {
            super.paint(g);
        } else {
            Graphics2D g2D = (Graphics2D) g;
            Composite savedComposite = g2D.getComposite();
            AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2D.setComposite(alphaComposite);
            super.paint(g);
            g2D.setComposite(savedComposite);
        }
    }

}
