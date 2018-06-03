/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.firecode.cqldesktop.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import com.firecode.cqldesktop.GridBagHelper;
import com.firecode.cqldesktop.images.ImageHelper;
import com.firecode.cqldesktop.utils.StyleUtils;

/**
 *
 * @author  Michael Hagen
 */
public class DialogPanel extends JPanel {
    private Component parent = null;
    private JButton informationButton = null;
    private JButton confirmationButton = null;
    private JButton warningButton = null;
    private JButton errorButton = null;
    private JButton optionButton = null;
    private JButton nativeFileChooserButton = null;
    private JButton fileChooserButton = null;
    private JButton colorChooserButton = null;
    private JButton customDialogButton = null;
    private FileDialog fileDialog = null;

    public DialogPanel(Component aParent) {
        super(new BorderLayout());
        parent = aParent;
        init();
    }

    private void init() {
        setName("Dialogs");
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        informationButton = new JButton("Information dialog");
        informationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Information dialog", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        confirmationButton = new JButton("Confirmation dialog");
        confirmationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Confirmation dialog", "Confirmation", JOptionPane.QUESTION_MESSAGE);
            }
        });
        warningButton = new JButton("Warning dialog");
        warningButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Warning dialog", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        errorButton = new JButton("Error dialog");
        errorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Error dialog", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        optionButton = new JButton("Option dialog");
        optionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"Yes", "No"};
                int confirm = JOptionPane.showOptionDialog(null, "Please select yes or no using the keyboard (tab/enter) keys\n", "Confirmation Required", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,null, options, options[1]);
                if (confirm == 0 || confirm == 1) {
                    optionButton.setText("Option dialog. Selected: " + options[confirm]);
                } else {
                    optionButton.setText("Option dialog. Selected: nothing ");
                }
            }

        });

        nativeFileChooserButton = new JButton("Native FileChooser dialog");
        nativeFileChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileDialog = new FileDialog(JOptionPane.getFrameForComponent(parent), "Open File");
                fileDialog .setResizable(true);
                fileDialog .setVisible(true);
            }
        });
        fileChooserButton = new JButton("FileChooser dialog");
        fileChooserButton.setEnabled(!(parent instanceof JApplet));
        fileChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(parent);
            }
        });
        colorChooserButton = new JButton("ColorChooser dialog");
        colorChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color c = JColorChooser.showDialog(parent, "JColorChooser", Color.blue);
            }
        });
        customDialogButton = new JButton("Custom dialog");
        customDialogButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                openCustomDialog();
            }
        });

        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagHelper.addComponent(contentPanel, informationButton,         0, 0, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, confirmationButton,        0, 1, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, warningButton,             0, 2, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, errorButton,               0, 3, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, optionButton,              0, 4, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, nativeFileChooserButton,   0, 5, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, fileChooserButton,         0, 6, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, colorChooserButton,        0, 7, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);
        GridBagHelper.addComponent(contentPanel, customDialogButton,        0, 8, 1, 1, 16, 8, 0, 4, 0.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER);

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);
    }

    private void openCustomDialog() {
        JDialog dlg = new JDialog(Main.app, "Custom dialog");
        dlg.setResizable(false);
        CustomDialogPanel panel = new CustomDialogPanel(dlg);
        dlg.setContentPane(panel);
        int x = Main.app.getLocation().x + 200;
        int y = Main.app.getLocation().y + 100;
        dlg.setBounds(x, y, 336, 240);
        if (StyleUtils.getJavaVersion() >= 1.6) {
            dlg.setIconImage(ImageHelper.loadImage("cab_small.gif").getImage());
        }
        dlg.setVisible(true);
    }
}
