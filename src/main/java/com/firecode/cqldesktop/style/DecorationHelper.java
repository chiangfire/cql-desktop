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

import java.awt.Color;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.Window;
import java.lang.reflect.Method;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class DecorationHelper {

    private DecorationHelper() {
    }

    public static void decorateWindows(Boolean decorate) {
        if (StyleUtils.getJavaVersion() >= 1.4) {
            try {
                Class classParams[] = {Boolean.TYPE};
                Method m = JFrame.class.getMethod("setDefaultLookAndFeelDecorated", classParams);
                Object methodParams[] = {decorate};
                m.invoke(null, methodParams);
                m = JDialog.class.getMethod("setDefaultLookAndFeelDecorated", classParams);
                m.invoke(null, methodParams);
                System.setProperty("sun.awt.noerasebackground", "true");
                System.setProperty("sun.awt.erasebackgroundonresize", "false");
            } catch (Exception ex) {
            }
        }
    }

    public static int getWindowDecorationStyle(JRootPane root) {
        if (StyleUtils.getJavaVersion() >= 1.4) {
            try {
                Class paramTypes[] = null;
                Object args[] = null;
                Method m = root.getClass().getMethod("getWindowDecorationStyle", paramTypes);
                Integer i = (Integer) m.invoke(root, args);
                return i.intValue();
            } catch (Exception ex) {
            }
        }
        return 0;
    }

    public static int getExtendedState(Frame frame) {
        if (StyleUtils.getJavaVersion() >= 1.4) {
            try {
                Class paramTypes[] = null;
                Object args[] = null;
                Method m = frame.getClass().getMethod("getExtendedState", paramTypes);
                Integer i = (Integer) m.invoke(frame, args);
                return i.intValue();
            } catch (Exception ex) {
            }
        }
        return 0;
    }

    public static void setExtendedState(Frame frame, int state) {
        if (StyleUtils.getJavaVersion() >= 1.4) {
            try {
                Class classParams[] = {Integer.TYPE};
                Method m = frame.getClass().getMethod("setExtendedState", classParams);
                Object methodParams[] = {new Integer(state)};
                m.invoke(frame, methodParams);
            } catch (Exception ex) {
            }
        }
    }

    public static boolean isFrameStateSupported(Toolkit tk, int state) {
        if (StyleUtils.getJavaVersion() >= 1.4) {
            try {
                Class classParams[] = {Integer.TYPE};
                Method m = tk.getClass().getMethod("isFrameStateSupported", classParams);
                Object methodParams[] = {new Integer(state)};
                Boolean b = (Boolean) m.invoke(tk, methodParams);
                return b.booleanValue();
            } catch (Exception ex) {
            }
        }
        return false;
    }

    public static boolean isTranslucentWindowSupported() {
        return (StyleUtils.getJavaVersion() >= 1.6010) && (StyleUtils.isMac() || StyleUtils.isWindows());
    }

    public static void setTranslucentWindow(Window wnd, boolean translucent) {
        if (isTranslucentWindowSupported()) {
            if (StyleUtils.getJavaVersion() >= 1.7) {
                if (translucent) {
                    if (wnd.getBackground() == null || !wnd.getBackground().equals(new Color(0, 0, 0, 0))) {
                        wnd.setBackground(new Color(0, 0, 0, 0));
                    }
                } else {
                    if (wnd.getBackground() == null || !wnd.getBackground().equals(new Color(0, 0, 0, 0xff))) {
                        wnd.setBackground(new Color(0, 0, 0, 0xff));
                    }
                }
            } else if (StyleUtils.getJavaVersion() >= 1.6010) {
                try {
                    Class clazz = Class.forName("com.sun.awt.AWTUtilities");
                    Class classParams[] = {Window.class, Boolean.TYPE};
                    Method method = clazz.getMethod("setWindowOpaque", classParams);
                    if (translucent) {
                        Object methodParams[] = {wnd, Boolean.FALSE};
                        method.invoke(wnd, methodParams);
                    } else {
                        Object methodParams[] = {wnd, Boolean.TRUE};
                        method.invoke(wnd, methodParams);
                    }
                } catch (Exception ex) {
                }
            }
        }
    }
    
}
