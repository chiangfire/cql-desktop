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
package com.firecode.cqldesktop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Michael Hagen
 * @author JIANG
 */
public class About extends JDialog {

	private static final long serialVersionUID = -3993616586739920789L;

	public static String CQL_DESKTOP_VERSION = "0.0.1-SNAPSHOT";

	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final Dimension dlgSize = new Dimension(440, 240);
	private static final int dlgPosX = (screenSize.width / 2) - (dlgSize.width / 2);
	private static final int dlgPosY = (screenSize.height / 2) - (dlgSize.height / 2);

	public About() {
		super((JFrame) null, "About CQL-Desktop");
		JPanel contentPanel = new JPanel(null);
		JLabel titleLabel = new JLabel("CQL-Desktop " + CQL_DESKTOP_VERSION);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setBounds(0, 20, dlgSize.width - 8, 36);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		contentPanel.add(titleLabel);

		JLabel copyrightLabel = new JLabel("Copyright (c) 2018 jiangcihuo");
		copyrightLabel.setBounds(0, 80, dlgSize.width - 8, 20);
		copyrightLabel.setHorizontalAlignment(JLabel.CENTER);
		contentPanel.add(copyrightLabel);

		JButton okButton = new JButton("OK");
		okButton.setBounds((dlgSize.width - 80) / 2, 170, 80, 24);
		contentPanel.add(okButton);

		setContentPane(contentPanel);

		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		});

		okButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				System.exit(0);
			}
		});
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.firecode.cqldesktop.style.theme.simple.SimpleLookAndFeel");
			About dlg = new About();
			dlg.setSize(dlgSize);
			dlg.setLocation(dlgPosX, dlgPosY);
			dlg.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
