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

import java.awt.Container;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class BaseInternalFrameUI extends BasicInternalFrameUI {

    private static final PropertyChangeListener MY_PROPERTY_CHANGE_HANDLER = new MyPropertyChangeHandler();
    private static final WindowAdapter MY_WINDOW_HANDLER = new MyWindowHandler();

    private static final Border HANDY_EMPTY_BORDER = new EmptyBorder(0, 0, 0, 0);
    
    private static final String IS_PALETTE = "JInternalFrame.isPalette";
    private static final String FRAME_TYPE = "JInternalFrame.frameType";
    private static final String FRAME_BORDER = "InternalFrame.border";
    private static final String FRAME_PALETTE_BORDER = "InternalFrame.paletteBorder";
    private static final String PALETTE_FRAME = "palette";

    public BaseInternalFrameUI(JInternalFrame b) {
        super(b);
    }

    public static ComponentUI createUI(JComponent c) {
        return new BaseInternalFrameUI((JInternalFrame) c);
    }

    public void installUI(JComponent c) {
        super.installUI(c);
        Object paletteProp = c.getClientProperty(IS_PALETTE);
        if (paletteProp != null) {
            setPalette(((Boolean) paletteProp).booleanValue());
        }
        stripContentBorder();
    }

    public void uninstallUI(JComponent c) {
        Container cp = frame.getContentPane();
        if (cp instanceof JComponent) {
            JComponent contentPane = (JComponent) cp;
            if (contentPane.getBorder() == HANDY_EMPTY_BORDER) {
                contentPane.setBorder(null);
            }
        }
        super.uninstallUI(c);
    }

    protected void installDefaults() {
        super.installDefaults();
        Icon frameIcon = frame.getFrameIcon();
        if (frameIcon == null || frameIcon instanceof LazyImageIcon) {
            frame.setFrameIcon(UIManager.getIcon("InternalFrame.icon"));
        }
    }
    
    protected void installListeners() {
        super.installListeners();
        frame.addPropertyChangeListener(MY_PROPERTY_CHANGE_HANDLER);
    }

    protected void uninstallListeners() {
        frame.removePropertyChangeListener(MY_PROPERTY_CHANGE_HANDLER);
        super.uninstallListeners();
    }

    protected void uninstallComponents() {
        titlePane = null;
        super.uninstallComponents();
    }

    public void stripContentBorder() {
        Container cp = frame.getContentPane();
        if (cp instanceof JComponent) {
            JComponent contentPane = (JComponent) cp;
            Border contentBorder = contentPane.getBorder();
            if (contentBorder == null || contentBorder instanceof UIResource) {
                contentPane.setBorder(HANDY_EMPTY_BORDER);
            }
        }
    }

    protected JComponent createNorthPane(JInternalFrame w) {
        return new BaseInternalFrameTitlePane(w);
    }

    public BaseInternalFrameTitlePane getTitlePane() {
        return (BaseInternalFrameTitlePane) titlePane;
    }

    public void setPalette(boolean isPalette) {
        if (isPalette) {
            frame.setBorder(UIManager.getBorder(FRAME_PALETTE_BORDER));
        } else {
            frame.setBorder(UIManager.getBorder(FRAME_BORDER));
        }
        getTitlePane().setPalette(isPalette);
    }

//-----------------------------------------------------------------------------
// inner classes    
//-----------------------------------------------------------------------------
    private static class MyPropertyChangeHandler implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent e) {
            JInternalFrame jif = (JInternalFrame) e.getSource();
            if (!(jif.getUI() instanceof BaseInternalFrameUI)) {
                return;
            }

            BaseInternalFrameUI ui = (BaseInternalFrameUI) jif.getUI();
            String name = e.getPropertyName();
            if (name.equals(FRAME_TYPE)) {
                if (e.getNewValue() instanceof String) {
                    if (PALETTE_FRAME.equals(e.getNewValue())) {
                        LookAndFeel.installBorder(ui.frame, FRAME_PALETTE_BORDER);
                        ui.setPalette(true);
                    } else {
                        LookAndFeel.installBorder(ui.frame, FRAME_BORDER);
                        ui.setPalette(false);
                    }
                }
            } else if (name.equals(IS_PALETTE)) {
                if (e.getNewValue() != null) {
                    ui.setPalette(((Boolean) e.getNewValue()).booleanValue());
                } else {
                    ui.setPalette(false);
                }
            } else if (name.equals(JInternalFrame.CONTENT_PANE_PROPERTY)) {
                ui.stripContentBorder();
            } else if (name.equals("ancestor") && !AbstractLookAndFeel.isWindowDecorationOn()) {
                if (e.getNewValue() instanceof JDesktopPane) {
                    JDesktopPane jp = (JDesktopPane)e.getNewValue();
                    Window window = SwingUtilities.getWindowAncestor(jp);
                    if (window != null) {
                        WindowListener wl[] = window.getWindowListeners();
                        boolean doAdd = true;
                        for (int i = 0; i < wl.length; i++) {
                            if (wl[i].equals(MY_WINDOW_HANDLER)) {
                                doAdd = false;
                                break;
                            }
                        }
                        if (doAdd) {
                            window.addWindowListener(MY_WINDOW_HANDLER);
                        }
                    }
                } else if (e.getOldValue() instanceof JDesktopPane) {
                    JDesktopPane jp = (JDesktopPane)e.getOldValue();
                    Window window = SwingUtilities.getWindowAncestor(jp);
                    if (window != null) {
                        window.removeWindowListener(MY_WINDOW_HANDLER);
                    }
                }
            }
        }
    } // end class MyPropertyChangeHandler
    
//-----------------------------------------------------------------------------
    private static class MyWindowHandler extends WindowAdapter {

        public void windowActivated(WindowEvent e) {
            e.getWindow().invalidate();
            e.getWindow().repaint();
        }
        
        public void windowDeactivated(WindowEvent e) {
            e.getWindow().invalidate();
            e.getWindow().repaint();
        }
    } // end class MyWindowHandler
}
