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
package com.firecode.cqldesktop.listener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.firecode.cqldesktop.app.Main;

/**
 * 新建连接监听器
 * @author JIANG
 */
public class NewConnectActionListener implements ActionListener{

	@Override
    public void actionPerformed(ActionEvent e) {
        final JDialog dlg = new JDialog(Main.app, "Modeless-Demo-Dialog", false);
        dlg.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dlg.setSize(400, 300);
        dlg.setLocationRelativeTo(Main.app);
        JButton changeTitleButton = new JButton("changeTitle");
        changeTitleButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dlg.setTitle("New Title");
            }
        });
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dlg.dispose();
            }
        });
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        buttonPanel.add(changeTitleButton, BorderLayout.NORTH);
        buttonPanel.add(Box.createRigidArea(new Dimension(120, 20)), BorderLayout.WEST);
        buttonPanel.add(closeButton, BorderLayout.CENTER);
        buttonPanel.add(Box.createRigidArea(new Dimension(120, 20)), BorderLayout.EAST);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        dlg.setContentPane(contentPanel);
        dlg.setVisible(true);
    }

}
