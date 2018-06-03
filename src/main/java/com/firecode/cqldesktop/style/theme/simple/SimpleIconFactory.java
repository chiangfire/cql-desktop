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
package com.firecode.cqldesktop.style.theme.simple;

import javax.swing.Icon;

import com.firecode.cqldesktop.style.AbstractIconFactory;

/**
 * @author Michael Hagen
 */
public class SimpleIconFactory implements AbstractIconFactory {

    private static SimpleIconFactory instance = null;

    private SimpleIconFactory() {
    }

    public static synchronized SimpleIconFactory getInstance() {
        if (instance == null) {
            instance = new SimpleIconFactory();
        }
        return instance;
    }

    public Icon getOptionPaneErrorIcon() {
        return SimpleIcons.getOptionPaneErrorIcon();
    }

    public Icon getOptionPaneWarningIcon() {
        return SimpleIcons.getOptionPaneWarningIcon();
    }

    public Icon getOptionPaneInformationIcon() {
        return SimpleIcons.getOptionPaneInformationIcon();
    }

    public Icon getOptionPaneQuestionIcon() {
        return SimpleIcons.getOptionPaneQuestionIcon();
    }

    public Icon getFileChooserUpFolderIcon() {
        return SimpleIcons.getFileChooserUpFolderIcon();
    }

    public Icon getFileChooserHomeFolderIcon() {
        return SimpleIcons.getFileChooserHomeFolderIcon();
    }

    public Icon getFileChooserNewFolderIcon() {
        return SimpleIcons.getFileChooserNewFolderIcon();
    }

    public Icon getFileChooserListViewIcon() {
        return SimpleIcons.getFileChooserListViewIcon();
    }

    public Icon getFileChooserDetailViewIcon() {
        return SimpleIcons.getFileChooserDetailViewIcon();
    }

    public Icon getFileViewComputerIcon() {
        return SimpleIcons.getFileViewComputerIcon();
    }

    public Icon getFileViewFloppyDriveIcon() {
        return SimpleIcons.getFileViewFloppyDriveIcon();
    }

    public Icon getFileViewHardDriveIcon() {
        return SimpleIcons.getFileViewHardDriveIcon();
    }

    public Icon getMenuIcon() {
        return SimpleIcons.getMenuIcon();
    }

    public Icon getIconIcon() {
        return SimpleIcons.getIconIcon();
    }

    public Icon getMaxIcon() {
        return SimpleIcons.getMaxIcon();
    }

    public Icon getMinIcon() {
        return SimpleIcons.getMinIcon();
    }

    public Icon getCloseIcon() {
        return SimpleIcons.getCloseIcon();
    }

    public Icon getPaletteCloseIcon() {
        return SimpleIcons.getPaletteCloseIcon();
    }

    public Icon getRadioButtonIcon() {
        return SimpleIcons.getRadioButtonIcon();
    }

    public Icon getCheckBoxIcon() {
        return SimpleIcons.getCheckBoxIcon();
    }

    public Icon getComboBoxIcon() {
        return SimpleIcons.getComboBoxIcon();
    }

    public Icon getTreeOpenIcon() {
        return SimpleIcons.getTreeOpenedIcon();
    }

    public Icon getTreeCloseIcon() {
        return SimpleIcons.getTreeClosedIcon();
    }
    
    public Icon getTreeLeafIcon() {
        return SimpleIcons.getTreeLeafIcon();
    }

    public Icon getTreeCollapsedIcon() {
        return SimpleIcons.getTreeCollapsedIcon();
    }

    public Icon getTreeExpandedIcon() {
        return SimpleIcons.getTreeExpandedIcon();
    }

    public Icon getMenuArrowIcon() {
        return SimpleIcons.getMenuArrowIcon();
    }

    public Icon getMenuCheckBoxIcon() {
        return SimpleIcons.getMenuCheckBoxIcon();
    }

    public Icon getMenuRadioButtonIcon() {
        return SimpleIcons.getMenuRadioButtonIcon();
    }

    public Icon getUpArrowIcon() {
        return SimpleIcons.getUpArrowIcon();
    }

    public Icon getDownArrowIcon() {
        return SimpleIcons.getDownArrowIcon();
    }

    public Icon getLeftArrowIcon() {
        return SimpleIcons.getLeftArrowIcon();
    }

    public Icon getRightArrowIcon() {
        return SimpleIcons.getRightArrowIcon();
    }

    public Icon getSplitterDownArrowIcon() {
        return SimpleIcons.getSplitterDownArrowIcon();
    }

    public Icon getSplitterHorBumpIcon() {
        return SimpleIcons.getSplitterHorBumpIcon();
    }

    public Icon getSplitterLeftArrowIcon() {
        return SimpleIcons.getSplitterLeftArrowIcon();
    }

    public Icon getSplitterRightArrowIcon() {
        return SimpleIcons.getSplitterRightArrowIcon();
    }

    public Icon getSplitterUpArrowIcon() {
        return SimpleIcons.getSplitterUpArrowIcon();
    }

    public Icon getSplitterVerBumpIcon() {
        return SimpleIcons.getSplitterVerBumpIcon();
    }

    public Icon getThumbHorIcon() {
        return SimpleIcons.getThumbHorIcon();
    }

    public Icon getThumbVerIcon() {
        return SimpleIcons.getThumbVerIcon();
    }

    public Icon getThumbHorIconRollover() {
        return SimpleIcons.getThumbHorIconRollover();
    }

    public Icon getThumbVerIconRollover() {
        return SimpleIcons.getThumbVerIconRollover();
    }
}
