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
package com.firecode.cqldesktop.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import com.firecode.cqldesktop.GUIProperties;
import com.firecode.cqldesktop.app.DialogPanel;
import com.firecode.cqldesktop.app.WidgetWithRowSorterPanel;
import com.firecode.cqldesktop.images.ImageHelper;

/**
 * @author mao 右边面板
 */
@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	private Component parent = null;
	private JTabbedPane tabbedPane = null;
	private JPanel widgetPanel = null;
	private DialogPanel dialogPanel = null;

	public RightPanel(Component aParent) {
		super(new BorderLayout());
		parent = aParent;
		init();
	}

	private void init() {
	    widgetPanel = new WidgetWithRowSorterPanel();
		dialogPanel = new DialogPanel(parent);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);

		tabbedPane.add(widgetPanel);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(widgetPanel),
				new CloseableTabComponent(tabbedPane, "测试关闭     "));

		tabbedPane.add(dialogPanel.getName(), dialogPanel);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(dialogPanel),
				new CloseableTabComponent(tabbedPane, "测试关闭     "));

		tabbedPane.setTabPlacement(JTabbedPane.TOP);
		tabbedPane.setSelectedIndex(1);

		add(tabbedPane, BorderLayout.CENTER);
	}

	private static class CloseableTabComponent extends JPanel {

		private JTabbedPane tabbedPane = null;
		private JLabel titleLabel = null;
		private JButton closeButton = null;
		private Font defaultFont = null;
		private Font selectedFont = null;
		private Color selectedColor = null;

		public CloseableTabComponent(JTabbedPane aTabbedPane, String title) {
			super(new BorderLayout());
			tabbedPane = aTabbedPane;
			setOpaque(false);

			titleLabel = new JLabel(title);
			titleLabel.setOpaque(false);
			defaultFont = titleLabel.getFont().deriveFont(~Font.BOLD);
			selectedFont = titleLabel.getFont().deriveFont(Font.BOLD);
			selectedColor = UIManager.getColor("TabbedPane.selectedForeground");
			if (selectedColor == null) {
				selectedColor = tabbedPane.getForeground();
			}
			closeButton = new CloseButton();
			add(titleLabel, BorderLayout.CENTER);
			add(closeButton, BorderLayout.EAST);

			closeButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int tabIndex = getTabIndex();
					if (tabIndex >= 0) {
						tabbedPane.removeTabAt(tabIndex);
					}
					if ((tabbedPane.getTabCount() > 1)
							&& (tabbedPane.getSelectedIndex() == tabbedPane.getTabCount() - 1)) {
						tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 2);
					}
				}
			});

		}

		private int getTabIndex() {
			for (int i = 0; i < tabbedPane.getTabCount(); i++) {
				if (this.equals(tabbedPane.getTabComponentAt(i))) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public void updateUI() {
			super.updateUI();
			if (titleLabel != null) {
				defaultFont = titleLabel.getFont().deriveFont(~Font.BOLD);
				selectedFont = titleLabel.getFont().deriveFont(Font.BOLD);
				selectedColor = UIManager.getColor("TabbedPane.selectedForeground");
				if (selectedColor == null) {
					selectedColor = tabbedPane.getForeground();
				}
			}
		}

		@Override
		public void paint(Graphics g) {
			int tabIndex = getTabIndex();
			if (tabIndex >= 0) {
				if (tabIndex == tabbedPane.getSelectedIndex()) {
					titleLabel.setFont(selectedFont);
					if (tabbedPane.getForegroundAt(tabIndex) instanceof ColorUIResource) {
						titleLabel.setForeground(selectedColor);
					} else {
						titleLabel.setForeground(tabbedPane.getForegroundAt(tabIndex));
					}
				} else {
					titleLabel.setFont(defaultFont);
					titleLabel.setForeground(tabbedPane.getForegroundAt(tabIndex));
				}
			}

			super.paint(g);
		}

	}

	private static class CloseButton extends JButton {

		private static final ImageIcon CLOSER_ICON = ImageHelper.loadImage("closer.gif");
		private static final ImageIcon CLOSER_ROLLOVER_ICON = ImageHelper.loadImage("closer_rollover.gif");
		private static final ImageIcon CLOSER_PRESSED_ICON = ImageHelper.loadImage("closer_pressed.gif");

		private static Dimension prefSize = new Dimension(16, 16);

		public CloseButton() {
			super("");
			setIcon(CLOSER_ICON);
			setRolloverIcon(CLOSER_ROLLOVER_ICON);
			setPressedIcon(CLOSER_PRESSED_ICON);
			setContentAreaFilled(false);
			setBorder(BorderFactory.createEmptyBorder());
			setFocusable(false);
			prefSize = new Dimension(CLOSER_ICON.getIconWidth(), CLOSER_ICON.getIconHeight());
		}

		@Override
		public Dimension getPreferredSize() {
			return prefSize;
		}
	}

}
