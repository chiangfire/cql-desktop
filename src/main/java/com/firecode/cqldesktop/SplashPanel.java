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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.firecode.cqldesktop.About;
import com.firecode.cqldesktop.utils.ImageUtils;

/**
 * @author JIANG
 * 
 * JFrame启动时显示面板
 */
public class SplashPanel extends JPanel {
	private static final long serialVersionUID = -6996687484154376922L;
	/**
	 * 图片对象
	 */
    private ImageIcon splashImage = ImageUtils.loadImage("images/cassandra_logo.png");
    /**
     * 类封装单个对象中组件的宽度和高度（精确到整数）
     */
    private Dimension size = new Dimension(splashImage.getIconWidth(), splashImage.getIconHeight());

    /**
     * 构造器
     */
    public SplashPanel() {
        super();
        //设置此面板的前景色
        setForeground(new Color(233, 115, 103));
        //设置此面板的字体
        setFont(new Font("Serif", Font.PLAIN, 28));
    }

    /**
     * 获得面板的大小
     */
    public Dimension getPreferredSize() {
        return size;
    }

    /**
     * 画笔
     */
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;
        splashImage.paintIcon(this, g, 0, 0);
        int x = 316;
        int y = 172;
        Object rh = g2D.getRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(getFont());
        g.setColor(Color.black);
        g.drawString(About.CQL_DESKTOP_VERSION, x + 3, y + 2);
        g.setColor(getForeground());
        g.drawString(About.CQL_DESKTOP_VERSION, x, y);
        g.drawString(About.CQL_DESKTOP_VERSION, x + 1, y);
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, rh);
    }

}
