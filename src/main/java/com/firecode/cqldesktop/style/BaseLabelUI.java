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

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BaseLabelUI extends BasicLabelUI {

	private static BaseLabelUI baseLabelUI = null;

	public static ComponentUI createUI(JComponent c) {
		if (baseLabelUI == null) {
			baseLabelUI = new BaseLabelUI();
		}
		return baseLabelUI;
	}

	protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY) {
		int mnemIndex;
		if (StyleUtils.getJavaVersion() >= 1.4) {
			mnemIndex = l.getDisplayedMnemonicIndex();
		} else {
			mnemIndex = StyleUtils.findDisplayedMnemonicIndex(l.getText(), l.getDisplayedMnemonic());
		}
		Object sc = l.getClientProperty("shadowColor");
		if (sc instanceof Color) {
			g.setColor((Color) sc);
			StyleUtils.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY + 1);
		}
		g.setColor(l.getForeground());
		StyleUtils.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY);
	}

	protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY) {
		int mnemIndex;
		if (StyleUtils.getJavaVersion() >= 1.4) {
			mnemIndex = l.getDisplayedMnemonicIndex();
		} else {
			mnemIndex = StyleUtils.findDisplayedMnemonicIndex(l.getText(), l.getDisplayedMnemonic());
		}
		g.setColor(Color.white);
		StyleUtils.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX + 1, textY + 1);
		g.setColor(AbstractLookAndFeel.getDisabledForegroundColor());
		StyleUtils.drawStringUnderlineCharAt(l, g, s, mnemIndex, textX, textY);
	}
}
