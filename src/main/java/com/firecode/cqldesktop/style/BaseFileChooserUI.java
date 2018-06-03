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

import java.awt.Dimension;
import java.awt.Window;
import java.io.File;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalFileChooserUI;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BaseFileChooserUI extends MetalFileChooserUI {

    private FileView fileView = null;

    // Preferred and Minimum sizes for the dialog box
    private static final int PREF_WIDTH = 580;
    private static final int PREF_HEIGHT = 340;
    private static final Dimension PREF_SIZE = new Dimension(PREF_WIDTH, PREF_HEIGHT);
    
    private AncestorListener ancestorListener = null;

    public BaseFileChooserUI(JFileChooser fileChooser) {
        super(fileChooser);
        fileView = new BaseFileView();
    }

    public static ComponentUI createUI(JComponent c) {
        return new BaseFileChooserUI((JFileChooser) c);
    }

    protected void installListeners(JFileChooser fc) {
        super.installListeners(fc);
        ancestorListener = new AncestorListener() {

            public void ancestorAdded(AncestorEvent event) {
                Window w = SwingUtilities.getWindowAncestor(getFileChooser());
                if (w != null) {
                    w.setMinimumSize(getPreferredSize(getFileChooser()));
                }
            }

            public void ancestorRemoved(AncestorEvent event) {
            }

            public void ancestorMoved(AncestorEvent event) {
            }
        };
                
        fc.addAncestorListener(ancestorListener);
    }

    protected void uninstallListeners(JFileChooser fc) {
        super.uninstallListeners(fc); 
        fc.removeAncestorListener(ancestorListener);
    }

    /**
     * Returns the preferred size of the specified
     * <code>JFileChooser</code>.
     * The preferred size is at least as large,
     * in both height and width,
     * as the preferred size recommended
     * by the file chooser's layout manager.
     *
     * @param c  a <code>JFileChooser</code>
     * @return   a <code>Dimension</code> specifying the preferred
     *           width and height of the file chooser
     */
    public Dimension getPreferredSize(JComponent c) {
        int prefWidth = PREF_SIZE.width;
        Dimension d = c.getLayout().preferredLayoutSize(c);
        if (d != null) {
            return new Dimension(d.width < prefWidth ? prefWidth : d.width,
                    d.height < PREF_SIZE.height ? PREF_SIZE.height : d.height);
        } else {
            return new Dimension(prefWidth, PREF_SIZE.height);
        }
    }

    public FileView getFileView(JFileChooser fc) {
        if (StyleUtils.getJavaVersion() < 1.4) {
            return super.getFileView(fc);
        } else {
            return fileView;
        }
    }

//------------------------------------------------------------------------------    
    protected class BaseFileView extends BasicFileView {

        public Icon getIcon(File f) {
            Icon icon = getCachedIcon(f);
            if (icon != null) {
                return icon;
            }
            if (f != null) {
                icon = getFileChooser().getFileSystemView().getSystemIcon(f);
            }
            if (icon == null) {
                icon = super.getIcon(f);
            }
            cacheIcon(f, icon);
            return icon;
        }
    }
}
