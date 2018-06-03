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

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

import com.firecode.cqldesktop.style.AbstractLookAndFeel;
import com.firecode.cqldesktop.style.theme.ThemeType;
/**
 * 主题选择
 * @author JIANG
 */
public class ThemeRadioButton extends JRadioButtonMenuItem implements ActionListener{

	private static final long serialVersionUID = 7788264183427546357L;
	//主题类型
	private final ThemeType themeType;
	
	public ThemeRadioButton(ThemeType themeType){
		super(themeType.getName());
		this.themeType = themeType;
		this.addActionListener(this);
	}
	
	protected boolean isDecorated(){
        //获取样式对象
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        if (lookAndFeel instanceof MetalLookAndFeel) {
            return true;
        }
        if (lookAndFeel instanceof AbstractLookAndFeel) {
            return AbstractLookAndFeel.getTheme().isWindowDecorationOn();
        }
        return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		SwingUtilities.invokeLater(()-> {
			try {
				boolean oldDecorated = isDecorated();
				Class<?> lookAndFeel = Class.forName(themeType.getPacket());
				Method setTheme = lookAndFeel.getMethod("setTheme",String.class);
				setTheme.invoke(lookAndFeel, "Default");
				
			    UIManager.setLookAndFeel(themeType.getPacket());
	            boolean newDecorated = isDecorated();
	            if (oldDecorated == newDecorated) {
    				//开始切换主题样式
    				Window windows[] = Window.getWindows();
    				for (Window window : windows) {
    					if (window.isDisplayable()) {
    						SwingUtilities.updateComponentTreeUI(window);
    					}
    				}
	            }
				
			} catch (ClassNotFoundException | NoSuchMethodException |
					SecurityException | IllegalAccessException |
					IllegalArgumentException | InvocationTargetException |
					InstantiationException | UnsupportedLookAndFeelException ex) {
				ex.printStackTrace();
			}
		});
	}

}
