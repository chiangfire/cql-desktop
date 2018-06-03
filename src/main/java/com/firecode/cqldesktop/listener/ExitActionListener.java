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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 退出应用
 * @author JIANG
 */
public class ExitActionListener extends WindowAdapter implements ActionListener{
	
	private static final ExitActionListener instance = ExitActionListenerInstance.exitActionListener;
	
	private ExitActionListener(){}
	
	@Override
    public void windowClosing(WindowEvent e) {
		exit();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		exit();
	}
	
	private void exit(){
		System.exit(0);
	}
	
	public static final ExitActionListener getInstance(){
		
		return ExitActionListener.instance;
	}
	
	private static class ExitActionListenerInstance{
		private static final ExitActionListener exitActionListener = new ExitActionListener();
	}
	
	
}
