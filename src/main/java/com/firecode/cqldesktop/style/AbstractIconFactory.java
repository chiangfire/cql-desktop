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

import javax.swing.Icon;

/**
 * @author Michael Hagen
 */
public interface AbstractIconFactory {

    public Icon getOptionPaneErrorIcon();

    public Icon getOptionPaneWarningIcon();

    public Icon getOptionPaneInformationIcon();

    public Icon getOptionPaneQuestionIcon();

    public Icon getFileChooserUpFolderIcon();

    public Icon getFileChooserHomeFolderIcon();

    public Icon getFileChooserNewFolderIcon();

    public Icon getFileChooserDetailViewIcon();

    public Icon getFileChooserListViewIcon();
    
    public Icon getFileViewComputerIcon();

    public Icon getFileViewFloppyDriveIcon();

    public Icon getFileViewHardDriveIcon();

    public Icon getMenuIcon();

    public Icon getIconIcon();

    public Icon getMaxIcon();

    public Icon getMinIcon();

    public Icon getCloseIcon();

    public Icon getPaletteCloseIcon();

    public Icon getRadioButtonIcon();

    public Icon getCheckBoxIcon();

    public Icon getComboBoxIcon();

    public Icon getTreeOpenIcon();
    
    public Icon getTreeCloseIcon();

    public Icon getTreeLeafIcon();

    public Icon getTreeCollapsedIcon();

    public Icon getTreeExpandedIcon();

    public Icon getMenuArrowIcon();

    public Icon getMenuCheckBoxIcon();

    public Icon getMenuRadioButtonIcon();

    public Icon getUpArrowIcon();

    public Icon getDownArrowIcon();

    public Icon getLeftArrowIcon();

    public Icon getRightArrowIcon();

    public Icon getSplitterUpArrowIcon();

    public Icon getSplitterDownArrowIcon();

    public Icon getSplitterLeftArrowIcon();

    public Icon getSplitterRightArrowIcon();

    public Icon getSplitterHorBumpIcon();

    public Icon getSplitterVerBumpIcon();

    public Icon getThumbHorIcon();

    public Icon getThumbVerIcon();

    public Icon getThumbHorIconRollover();

    public Icon getThumbVerIconRollover();
}
