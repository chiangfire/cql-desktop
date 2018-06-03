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

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import com.firecode.cqldesktop.app.Main;
import com.firecode.cqldesktop.images.ImageHelper;

/**
 * @author mao
 * 
 * 顶部一排按钮
 */
@SuppressWarnings("serial")
public class TopToolBar extends JToolBar {
    private ImageIcon newImage = null;
    private ImageIcon openImage = null;
    private ImageIcon saveImage = null;
    private ImageIcon cutImage = null;
    private ImageIcon copyImage = null;
    private ImageIcon pasteImage = null;
    private ImageIcon undoImage = null;
    private ImageIcon redoImage = null;
    private ToolButton newButton = null;
    private ToolButton openButton = null;
    private ToolButton saveButton = null;
    private ToolButton cutButton = null;
    /**
     * 复制按钮,会显示两张图片
     */
    private ToogleToolButton copyButton = null;
    private ToolButton pasteButton = null;
    private ToolButton undoButton = null;
    private ToolButton redoButton = null;

//    private JButton defaultBorderButton = null;

    public TopToolBar() {
        super();
        /**
         * 设置 floatable 属性，如果要移动工具栏，此属性必须设置为 true。
         */
        setFloatable(true);
        /**
         * 设置工具栏边框和它的按钮之间的空白
         * 
         * Insets:对象是容器边界的表示形式。它指定容器必须在其各个边缘留出的空间。这个空间可以是边界、空白空间或标题
         */
        setMargin(new Insets(2, 0, 2, 0));
        //加载图片
        newImage = ImageHelper.loadImage("new.png");
        //创建按钮并加入图片
        newButton = new ToolButton(newImage);
        //加入鼠标放置提示
        newButton.setToolTipText("打开文件");
        //----------------------------------------------------------------------------------------------
        
        openImage = ImageHelper.loadImage("open.png");
        openButton = new ToolButton(openImage);
        openButton.setToolTipText("打开文件夹");
        //加入事件
        openButton.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
			public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(Main.app);
            }
        });
        //----------------------------------------------------------------------------------------------
        
        saveImage = ImageHelper.loadImage("save.png");
        saveButton = new ToolButton(saveImage);
        saveButton.setToolTipText("保存");
        //----------------------------------------------------------------------------------------------
        
        cutImage = ImageHelper.loadImage("cut.png");
        cutButton = new ToolButton(cutImage);
        cutButton.setToolTipText("剪切");
        //----------------------------------------------------------------------------------------------
        
        copyImage = ImageHelper.loadImage("copy.png");
        copyButton = new ToogleToolButton(copyImage);
        copyButton.setToolTipText("复制");
        //----------------------------------------------------------------------------------------------
        
        pasteImage = ImageHelper.loadImage("paste.png");
        pasteButton = new ToolButton(pasteImage);
        pasteButton.setToolTipText("禁用");
        //----------------------------------------------------------------------------------------------
        
        //不可点击<禁用>
        pasteButton.setEnabled(false);
        undoImage = ImageHelper.loadImage("undo.png");
        undoButton = new ToolButton(undoImage);
        undoButton.setToolTipText("返回");
        //----------------------------------------------------------------------------------------------
        
        redoImage = ImageHelper.loadImage("redo.png");
        redoButton = new ToolButton(redoImage);
        redoButton.setToolTipText("后退");
        //----------------------------------------------------------------------------------------------

//        defaultBorderButton = new JButton("DefaultBorder");
//        defaultBorderButton.setFocusable(false);
//        defaultBorderButton.putClientProperty("paintToolBarBorder", Boolean.FALSE);

        /**
         * 加入
         */
        add(newButton);
        add(openButton);
        add(saveButton);
        //将默认大小的分隔符添加到工具栏的末尾
        addSeparator();
        add(cutButton);
        add(copyButton);
        add(pasteButton);
        //将默认大小的分隔符添加到工具栏的末尾
        addSeparator();
        add(undoButton);
        add(redoButton);

//        addSeparator();
//        add(defaultBorderButton);

    }

    /**
     * 
     * @author Feeli2010
     * 
     * 按钮组件
     *
     */
    private class ToolButton extends JButton {

        public ToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }

    /**
     * 按钮组件
     * 
     * 区别是如果加入图片会显示两张图片
     * 
     * 具体请看 复制 按钮
     * @author Feeli2010
     *
     */
    private class ToogleToolButton extends JToggleButton {

        public ToogleToolButton(Icon icon) {
            super(icon);
            setMargin(new Insets(4, 4, 4, 4));
        }

        public boolean isFocusTraversable() {
            return false;
        }

        public void requestFocus() {
        }
    }
}
