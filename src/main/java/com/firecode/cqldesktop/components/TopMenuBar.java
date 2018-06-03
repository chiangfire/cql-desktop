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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.firecode.cqldesktop.AboutDialog;
import com.firecode.cqldesktop.images.ImageHelper;
import com.firecode.cqldesktop.listener.ExitActionListener;
import com.firecode.cqldesktop.listener.NewConnectActionListener;
import com.firecode.cqldesktop.style.theme.ThemeType;
/**
 * 顶部Menu组
 * @author JIANG
 */
public class TopMenuBar extends JMenuBar{

	private static final long serialVersionUID = 8369009398783475719L;
	
    private static final ImageIcon newIcon = ImageHelper.loadImage("new.png");
    private static final ImageIcon openIcon = ImageHelper.loadImage("open.png");
    private static final ImageIcon saveIcon = ImageHelper.loadImage("save.png");
    
    private ButtonGroup themeButtonGroup = new ButtonGroup();
    
    public TopMenuBar(Component parent) {
        JMenu fileMenuBar = new JMenu("文件");
        fileMenuBar.setMnemonic('F');
        //-----------------------------文件-----------------------------------------
        JMenu newConnectMenu = new JMenu("新建链接");
        newConnectMenu.setIcon(newIcon);
        JMenuItem cassandraItem = new JMenuItem("Cassandra...");
        cassandraItem.setAccelerator(KeyStroke.getKeyStroke('N', KeyEvent.CTRL_MASK));
        cassandraItem.addActionListener(new NewConnectActionListener());
        newConnectMenu.add(cassandraItem);
        fileMenuBar.add(newConnectMenu);
        //分割线
        fileMenuBar.addSeparator();
        JMenuItem exportConnectMenu = new JMenuItem("导出连接...", openIcon);
        exportConnectMenu.setMnemonic('O');
        exportConnectMenu.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));
        fileMenuBar.add(exportConnectMenu);
        JMenuItem importConnectMenu = new JMenuItem("导入连接...", saveIcon);
        importConnectMenu.setMnemonic('S');
        importConnectMenu.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        fileMenuBar.add(importConnectMenu);
        //分割线
        fileMenuBar.addSeparator();
        JMenuItem exitMenu = new JMenuItem("退出");
        exitMenu.setMnemonic('x');
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        exitMenu.addActionListener(ExitActionListener.getInstance());
        fileMenuBar.add(exitMenu);
        add(fileMenuBar);
        
        //-----------------------------帮助-----------------------------------------
        JMenu helpMenuBar = new JMenu("帮助");
        helpMenuBar.setMnemonic('H');
        JMenu themeMenu = new JMenu("主题");
        themeMenu.setMnemonic('L');
        
        themeButtonGroup.add(themeMenu.add(new ThemeRadioButton(ThemeType.SIMPLE)));

        helpMenuBar.add(themeMenu);
        //分割线
        helpMenuBar.addSeparator();
        JMenuItem aboutMenu = new JMenuItem("关于...",openIcon);
        aboutMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AboutDialog(parent);
            }
        });
        aboutMenu.setMnemonic('A');
        helpMenuBar.add(aboutMenu);
        add(helpMenuBar);
        
    }

}
