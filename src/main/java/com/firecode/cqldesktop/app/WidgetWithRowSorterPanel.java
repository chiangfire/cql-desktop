/*
 * Copyright 2002 and later by MH Software-Entwicklung. All rights reserved.
 * Use is subject to license terms.
 */

package com.firecode.cqldesktop.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import com.firecode.cqldesktop.GridBagHelper;
import com.firecode.cqldesktop.JTBorderFactory;

/**
 *
 * @author  Michael Hagen
 */
public class WidgetWithRowSorterPanel extends JPanel {

    private JPanel widgetPanel = null;
    private JScrollPane tablePanel = null;
    private JSplitPane splitPane = null;
    private JComboBox addressCombo = null;
    private JTextField firstNameField = null;
    private JTextField lastNameField = null;
    private JTextField streetField = null;
    private JTextField cityField = null;
    private ButtonGroup buttonGroup = null;
    private JRadioButton redButton = null;
    private JRadioButton greenButton = null;
    private JRadioButton blueButton = null;
    private JCheckBox bananaButton = null;
    private JCheckBox burgerButton = null;
    private JCheckBox icecreamButton = null;
    private JButton updateButton = null;
    private JButton insertButton = null;
    private JButton deleteButton = null;
    private JScrollPane tableScrollPane = null;
    private JTable table = null;
    private ArrayList colNames = new ArrayList();
    private ArrayList dataList = new ArrayList();
    private boolean initialized = false;

    public WidgetWithRowSorterPanel() {
        super(new BorderLayout());
        init();
        initialized = true;
    }

    public void updateUI() {
        super.updateUI();
        if (initialized) {
            SwingUtilities.invokeLater(new Runnable() {

                public void run() {
                    if (UIManager.getLookAndFeel().getName().equals("Texture")) {
                        tablePanel.setBorder(BorderFactory.createEmptyBorder());
                    } else {
                        Border border = UIManager.getBorder("ScrollPane.border");
                        tablePanel.setBorder(border);
                    }
                }
            });
        }
    }

    private void init() {
        setName("Form");
        initModel();
        initControls();
        initListeners();
    }

    private void initModel() {
        colNames.add("Salutation");
        colNames.add("Firstname");
        colNames.add("Lastname");
        colNames.add("Street");
        colNames.add("City");
        colNames.add("Male");

        ArrayList rowData = new ArrayList();
        rowData.add("Mr.");
        rowData.add("Mad");
        rowData.add("Meier");
        rowData.add("Eastend 17");
        rowData.add("New York");
        rowData.add(Boolean.TRUE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Mrs.");
        rowData.add("Georgia");
        rowData.add("Smith");
        rowData.add("Westend 12");
        rowData.add("New York");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Sir");
        rowData.add("Edward");
        rowData.add("Hillary");
        rowData.add("Parkavenue 1a");
        rowData.add("Los Angeles");
        rowData.add(Boolean.TRUE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Mr.");
        rowData.add("Ronald");
        rowData.add("Mc. Guire");
        rowData.add("13th Street");
        rowData.add("Newmark");
        rowData.add(Boolean.TRUE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Mrs.");
        rowData.add("Martina");
        rowData.add("Diego");
        rowData.add("Block A");
        rowData.add("Westpoint");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Lady");
        rowData.add("Bettina");
        rowData.add("Maradona");
        rowData.add("Eastwoodpark 44");
        rowData.add("Washington");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Herr");
        rowData.add("Albert");
        rowData.add("Dreistein");
        rowData.add("Burgstrasse 22");
        rowData.add("Koeln");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Frau");
        rowData.add("Roberta");
        rowData.add("Robinson");
        rowData.add("Gartenstr. 22");
        rowData.add("Kleinkleckersdorf");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Herr");
        rowData.add("Manfred");
        rowData.add("Mustermann");
        rowData.add("Schlossplatz 22");
        rowData.add("Schwaebisch Gmuend");
        rowData.add(Boolean.TRUE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Herr");
        rowData.add("Norbert");
        rowData.add("Noergler");
        rowData.add("Wallstr 1a");
        rowData.add("Oberammergau");
        rowData.add(Boolean.TRUE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Frau");
        rowData.add("Lischen");
        rowData.add("Mueller");
        rowData.add("Kleintalweg 16");
        rowData.add("Witzenhausen");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);

        rowData = new ArrayList();
        rowData.add("Frau");
        rowData.add("Lotte");
        rowData.add("Lustig");
        rowData.add("Preussenalee 1a");
        rowData.add("Krauthausen");
        rowData.add(Boolean.FALSE);
        dataList.add(rowData);
    }

    private void initControls() {
        widgetPanel = createWidgetPanel();
        tablePanel = createTablePanel();
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, widgetPanel, tablePanel);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(320);
        add(splitPane, BorderLayout.CENTER);
    }

    private void initListeners() {
        addComponentListener(new ComponentAdapter() {

            public void componentShown(ComponentEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        if (getRootPane() != null) {
                            getRootPane().setDefaultButton(updateButton);
                            updateButton.invalidate();
                            updateButton.repaint();
                        }
                    }
                });
            }

        });
    }

    private JPanel createWidgetPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(createFormPanel(), BorderLayout.CENTER);
        panel.add(createButtonPanel(), BorderLayout.EAST);
        return panel;
    }

    private JScrollPane createFormPanel() {
        addressCombo = new JComboBox(new String[]{"Mr.", "Mrs.", "Sir", "Lady", "Herr", "Frau"});
        addressCombo.setToolTipText("Select salutation!");
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        streetField = new JTextField();
        cityField = new JTextField();
        cityField.setToolTipText("<html>This is a long tool tip (also known as bubble help)<br/>for the city text field.<br/><br/>Here comes some more useless text.");

        JPanel radioPanel = new JPanel(new BorderLayout());
        radioPanel.setBorder(JTBorderFactory.createTitleBorder("favorite color"));
        redButton = new JRadioButton("red");
        greenButton = new JRadioButton("green");
        blueButton = new JRadioButton("blue");
        radioPanel.add(redButton, BorderLayout.NORTH);
        radioPanel.add(greenButton, BorderLayout.CENTER);
        radioPanel.add(blueButton, BorderLayout.SOUTH);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(redButton);
        buttonGroup.add(greenButton);
        buttonGroup.add(blueButton);
        redButton.setSelected(true);

        JPanel checkPanel = new JPanel(new BorderLayout());
        checkPanel.setBorder(JTBorderFactory.createTitleBorder("favorite food"));
        bananaButton = new JCheckBox("bananas");
        burgerButton = new JCheckBox("hamburgers");
        icecreamButton = new JCheckBox("icecream");
        checkPanel.add(bananaButton, BorderLayout.NORTH);
        checkPanel.add(burgerButton, BorderLayout.CENTER);
        checkPanel.add(icecreamButton, BorderLayout.SOUTH);

        JPanel panel = new JPanel(new GridBagLayout());
        //panel.setOpaque(false);
        JPanel topDistPanel = new JPanel();
        JPanel bottomDistPanel = new JPanel();
        GridBagHelper.addComponent(panel, topDistPanel, 0, 0, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.NORTHWEST);

        GridBagHelper.addComponent(panel, new JLabel("Salutation"), 0, 1, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, addressCombo, 1, 1, 1, 1, 0, 0, 0.3, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, new JPanel(), 2, 1, 1, 1, 0, 0, 0.7, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagHelper.addComponent(panel, new JLabel("Firstname"), 0, 2, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, firstNameField, 1, 2, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, new JLabel("Lastname"), 0, 3, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, lastNameField, 1, 3, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, new JLabel("Street"), 0, 4, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, streetField, 1, 4, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, new JLabel("City"), 0, 5, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.NONE, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, cityField, 1, 5, 3, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST);

        GridBagHelper.addComponent(panel, radioPanel, 0, 6, 2, 1, 0, 0, 0.0, 0.0, GridBagConstraints.BOTH, GridBagConstraints.WEST);
        GridBagHelper.addComponent(panel, checkPanel, 2, 6, 1, 1, 0, 0, 0.0, 0.0, GridBagConstraints.BOTH, GridBagConstraints.WEST);

        GridBagHelper.addComponent(panel, bottomDistPanel, 0, 10, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.NORTHWEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        return scrollPane;
    }

    private JScrollPane createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        updateButton = new JButton("update");
        updateButton.setDefaultCapable(true);
        updateButton.setMnemonic(KeyEvent.VK_U);

        insertButton = new JButton("insert");
        insertButton.setMnemonic(KeyEvent.VK_I);
        deleteButton = new JButton("delete");
        deleteButton.setMnemonic(KeyEvent.VK_D);

        GridBagHelper.setMinRowHeight(panel, 0, 8);
        GridBagHelper.setMinColWidth(panel, 0, 100);
        GridBagHelper.addComponent(panel, updateButton, 0, 1, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, insertButton, 0, 2, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, deleteButton, 0, 3, 1, 1, 0, 0, 1.0, 0.0, GridBagConstraints.HORIZONTAL, GridBagConstraints.NORTHWEST);
        GridBagHelper.addComponent(panel, new JPanel(), 0, 4, 1, 1, 0, 0, 0.0, 1.0, GridBagConstraints.VERTICAL, GridBagConstraints.NORTHWEST);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        return scrollPane;
    }

    private JScrollPane createTablePanel() {
        MyTableModel model = new MyTableModel();
        table = new JTable(model);
        TableColumn tableCol = table.getColumnModel().getColumn(5);
        tableCol.setCellRenderer(new CheckBoxRenderer());
        tableCol.setPreferredWidth(30);
        //table.getTableHeader().setReorderingAllowed(false);
        table.setRowSorter(new TableRowSorter(model));
        tableScrollPane = new JScrollPane(table);
        //tableScrollPane.getViewport().setBackground(Color.orange);
        return tableScrollPane;
    }

//------------------------------------------------------------------------------
    private class MyTableModel extends AbstractTableModel {

        public int getColumnCount() {
            return colNames.size();
        }

        public String getColumnName(int index) {
            return (String) colNames.get(index);
        }

        public int getRowCount() {
            return dataList.size();
        }

        public Object getValueAt(int rowIndex, int colIndex) {
            if (rowIndex < dataList.size()) {
                ArrayList rowData = (ArrayList) dataList.get(rowIndex);
                if (colIndex < rowData.size()) {
                    return rowData.get(colIndex);
                }
            }
            return "ERROR";
        }
    }

//------------------------------------------------------------------------------
    class CheckBoxRenderer extends DefaultTableCellRenderer {

        public Component getTableCellRendererComponent(JTable jTable, Object obj, boolean isSelected, boolean hasFocus, int row, int col) {
            JCheckBox checkBox = new JCheckBox("");
            checkBox.setOpaque(true);
            checkBox.setForeground(jTable.getForeground());
            checkBox.setBackground(jTable.getBackground());
            checkBox.setHorizontalAlignment(JCheckBox.CENTER);
            if (isSelected) {
                checkBox.setForeground(jTable.getSelectionForeground());
                Color bc = new Color(jTable.getSelectionBackground().getRGB());
                checkBox.setBackground(bc);
            }
            if (obj instanceof Boolean) {
                checkBox.setSelected(((Boolean) obj).booleanValue());
            }
            return checkBox;
        }
    }
}
