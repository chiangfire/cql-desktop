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

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;

import com.firecode.cqldesktop.utils.ImageUtils;

import lombok.Getter;

/**
 * @author JIANG
 */
public class KeySpacesTree extends JScrollPane{
	
	private static final long serialVersionUID = 6171016420103858825L;
	
	static {
		UIManager.put("Tree.expandedIcon", KeySpacesTreeNodeIcon.ARROW.standby());
		UIManager.put("Tree.collapsedIcon", KeySpacesTreeNodeIcon.ARROW.active());
		/*BasicTreeUI ui=(BasicTreeUI)(this.getUI());
		ui.setCollapsedIcon(OpenedIcon);
		ui.setExpandedIcon(OpenedIcon);*/
	}
	
	public KeySpacesTree(){
		super(new KeySpacesTreeContent());
	}
	
	private static class KeySpacesTreeContent extends JTree{
		
		private static final long serialVersionUID = -8668651381958250347L;
		private TreeCellRenderer cellRenderer = new KeySpacesTreeCellRenderer();

		public KeySpacesTreeContent(){
			super(getDefaultTreeModel());
			this.setRootVisible(false);
			this.setCellRenderer(cellRenderer);
			this.setRowHeight(22);
		}
		
	    protected static TreeModel getDefaultTreeModel() {
	    	KeySpacesTreeNode root = new KeySpacesTreeNode(null,null);
	    	KeySpacesTreeNode locaConnect = new KeySpacesTreeNode("本地连接",KeySpacesTreeNodeIcon.SERVER);
	        locaConnect.add(new KeySpacesTreeNode("f_morganna2",KeySpacesTreeNodeIcon.DATABASE));
	        locaConnect.add(new KeySpacesTreeNode("f_morganna3",KeySpacesTreeNodeIcon.DATABASE));
	        root.add(locaConnect);
	        root.add(locaConnect);
	        KeySpacesTreeNode testConnect = new KeySpacesTreeNode("测试连接",KeySpacesTreeNodeIcon.SERVER);
	        testConnect.add(new KeySpacesTreeNode("f_morganna4",KeySpacesTreeNodeIcon.DATABASE));
	        testConnect.add(new KeySpacesTreeNode("f_morganna5",KeySpacesTreeNodeIcon.DATABASE));
	        testConnect.add(new KeySpacesTreeNode("f_morganna6",KeySpacesTreeNodeIcon.DATABASE));
	        root.add(testConnect);
	        return new DefaultTreeModel(root);
	    }
	}
	
	/**
	 * @author JIANG
	 */
	@Getter
	public static class KeySpacesTreeNode extends DefaultMutableTreeNode{
		
		private static final long serialVersionUID = -3625726786752120063L;
		private String value;
		private KeySpacesTreeNodeIcon icon;
		
		public KeySpacesTreeNode(String value,KeySpacesTreeNodeIcon icon){
			super(value);
			this.value = value;
			this.icon = icon;
		}
	}
	
	/**
	 * @author JIANG
	 */
	private static enum KeySpacesTreeNodeIcon {
		
		ARROW(ImageUtils.loadImage("images/tree/arrow_bottom.png"),ImageUtils.loadImage("images/tree/arrow_right.png")),
		DATABASE(ImageUtils.loadImage("images/tree/database_active.png"),ImageUtils.loadImage("images/tree/database_standby.png")),
		SERVER(ImageUtils.loadImage("images/tree/server_active.png"),ImageUtils.loadImage("images/tree/server_standby.png")),
		TABLE(ImageUtils.loadImage("images/tree/table.png"),ImageUtils.loadImage("images/tree/table.png"));
		
		private ImageIcon active,standby;
		
		KeySpacesTreeNodeIcon(ImageIcon active,ImageIcon standby){
			this.active = active;
			this.standby = standby;
		}
		
		public ImageIcon active(){
			
			return this.active;
		}
		
		public ImageIcon standby(){
			
			return this.standby;
		}
	}
	
	/**
	 * @author JIANG
	 */
	private static class KeySpacesTreeCellRenderer extends DefaultTreeCellRenderer {

		private static final long serialVersionUID = -1499172875593454935L;

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,int row, boolean hasFocus) {
			super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			if(value instanceof KeySpacesTreeNode){
				KeySpacesTreeNode node = (KeySpacesTreeNode)value;
				setText(node.getValue());
				if (sel) {
					setForeground(getTextSelectionColor());
				} else {
					setForeground(getTextNonSelectionColor());
				}
				if(expanded){
					this.setOpenIcon(node.getIcon().active());
				}else{
					this.setIcon(node.getIcon().standby());
				}
			}
			return this;
		}
	}
}
