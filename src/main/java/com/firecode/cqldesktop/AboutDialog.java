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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.firecode.cqldesktop.SplashPanel;


/**
 * @author  Michael Hagen
 */
public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 4631671585397331823L;
	private Component parent = null;

    public AboutDialog(Component aParent) {
        super(JOptionPane.getFrameForComponent(aParent), "About", true);
        parent = aParent;
        init();
    }

    private void init() {
        JPanel contentPanel = new JPanel(new BorderLayout());
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder());
        JPanel aboutPanel = new JPanel();
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(32, 8, 8, 8));
        SplashPanel splashPanel = new SplashPanel();
        aboutPanel.add(splashPanel);
        tabbedPane.add("About", aboutPanel);

        if (!(parent instanceof JApplet)) {
            JPanel propertiesPanel = new JPanel(new BorderLayout());
            JTable propertiesTable = new JTable(new PropertiesTableModel());
            propertiesTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            propertiesTable.setColumnSelectionAllowed(false);
            propertiesTable.setRowSelectionAllowed(false);
            propertiesTable.getColumnModel().getColumn(0).setPreferredWidth(188);
            propertiesTable.getColumnModel().getColumn(1).setPreferredWidth(418);
            propertiesPanel.add(new JScrollPane(propertiesTable), BorderLayout.CENTER);
            tabbedPane.add("Properties", propertiesPanel);
        }
        contentPanel.add(tabbedPane, BorderLayout.CENTER);
        setContentPane(contentPanel);
        showDlg();
    }

    private void showDlg() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dlgSize = new Dimension(640, 480);
        int dlgPosX = (screenSize.width / 2) - (dlgSize.width / 2);
        int dlgPosY = (screenSize.height / 2) - (dlgSize.height / 2);
        setLocation(dlgPosX, dlgPosY);
        setSize(dlgSize);
        setVisible(true);
    }

    private class PropertiesTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 8676514548917221575L;

		public int getColumnCount()
        { return 2; }

        public String getColumnName(int index) {
            if (index == 0)
                return "Property";
            else if (index == 1)
                return "Value";
            return "ERROR";
        }

        public int getRowCount()
        { return System.getProperties().size(); }

        public Object getValueAt(int rowIndex, int colIndex) {
            Iterator<?> iter = System.getProperties().keySet().iterator();
            int i = 0;
            while (iter.hasNext()) {
                Object key = iter.next();
                if (i == rowIndex) {
                    if (colIndex == 0)
                        return key;
                    else
                        return System.getProperties().getProperty(key.toString());
                }
                i++;
            }
            return "ERROR";
        }
    }
}
