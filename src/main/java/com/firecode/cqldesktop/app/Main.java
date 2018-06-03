
package com.firecode.cqldesktop.app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;

import com.firecode.cqldesktop.GUIProperties;
import com.firecode.cqldesktop.SplashPanel;
import com.firecode.cqldesktop.components.KeySpacesTree;
import com.firecode.cqldesktop.components.RightPanel;
import com.firecode.cqldesktop.components.TopMenuBar;
import com.firecode.cqldesktop.components.TopToolBar;
import com.firecode.cqldesktop.images.ImageHelper;
import com.firecode.cqldesktop.listener.ExitActionListener;

/**
 * @author mao
 */
@SuppressWarnings("serial")
public class Main extends JFrame {

	public static Main app = null;
	/**
	 * 默认大小
	 */
	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	/**
	 * 主窗口大小
	 */
	private static final Dimension appSize = new Dimension(880, 660);
	/**
	 * 主窗口位置
	 */
	private static final int appPosX = (screenSize.width / 2) - (appSize.width / 2);
	private static final int appPosY = (screenSize.height / 2) - (appSize.height / 2);

	/**
	 * Rectangle:JFrame启动时图片显示的位置
	 * 
	 * 移动组件并调整其大小，使其符合新的有界矩形 r。由 r.x 和 r.y 指定组件的新位置，由 r.width 和 r.height
	 * 指定组件的新大小 如果 r.width 值或 r.height 值小于之前调用 setMinimumSize 指定的最小大小，则它的值将自动增加
	 */
	private static Rectangle appBounds = new Rectangle(appPosX, appPosY, appSize.width, appSize.height);
	/**
	 * 抬头
	 */
	private static final String appTitle = "CQL-Desktop";
	/**
	 * JWindow 是一个容器，可以显示在用户桌面上的任何位置。它没有标题栏、窗口管理按钮或者其他与 JFrame
	 * 关联的修饰，但它仍然是用户桌面的“一类居民”，可以存在于桌面上的任何位置
	 */
	private JWindow splashScreen = null;
	/**
	 * JFrame启动时显示面板
	 */
	private SplashPanel splashPanel = null;
	/**
	 * 顶部 menu
	 */
	private TopMenuBar menuBar = null;
	/**
	 * 顶部一排按钮
	 */
	private TopToolBar toolBar = null;
	/**
	 * 主面板
	 */
	private JPanel contentPanel = null;
	/**
	 * 左边面板
	 */
	private KeySpacesTree leftPanel = null;
	/**
	 * 右边面板
	 */
	private RightPanel rightPanel = null;
	/**
	 * 装左右两边面板的视图容器组件
	 */
	private JSplitPane splitPane = null;

	/**
	 * 右边Tabs
	 */
	// private JTabbedPane mainTabbedPane = null;

	public Main() {
		super(appTitle);
		init();
	}

	/**
	 * 构造器
	 * 
	 * @param bounds
	 *            启动时位置
	 */
	public Main(Rectangle bounds) {
		super(appTitle);
		appBounds = bounds;
		// 初始化
		init();
	}

	private void init() {
		// 关闭按钮监听
		addWindowListener(ExitActionListener.getInstance());
		// 创建JFrame启动时显示面板
		splashPanel = new SplashPanel();
		// 创建容器
		splashScreen = new JWindow();
		// 将JFrame启动时显示面板加入容器
		splashScreen.getContentPane().add(splashPanel);
		// 调整此窗口的大小，以适合其子组件的首选大小和布局。如果该窗口和/或其所有者还不可显示，则在计算首选大小之前都将变得可显示。在计算首选大小之后，将会验证该窗口
		splashScreen.pack();
		// 获取JFrame启动时显示面板 大小
		Dimension size = splashScreen.getSize();
		// 设置JFrame启动时显示面板 位置
		splashScreen.setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height / 2);
		// 导致 doRun.run() 在 AWT 事件指派线程上异步执行。 显示JFrame启动时面板
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				splashScreen.setVisible(true);
			}
		});
		// 初始化 menu
		initMenuBar();
		// 初始化顶部一排按钮
		initToolBar();
		// 初始化主面板
		initContentPane();

		// 导致 doRun.run() 在 AWT 事件指派线程上异步执行。 显示 主面板
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// 设置JFrame抬头图片
				showApp();
				if (splashScreen != null) {
					splashScreen.setVisible(false);
				}
			}
		});
	}

	/**
	 * 初始化 顶部 menu
	 */
	private void initMenuBar() {
		menuBar = new TopMenuBar(this);
		// 装配 menu
		setJMenuBar(menuBar);
	}

	/**
	 * 创建顶部一排按钮
	 */
	private void initToolBar() {
		// 创建顶部一排按钮
		toolBar = new TopToolBar();
		/**
		 * 向此组件添加任意的键/值"客户端属性" 可以通过key取得该组件
		 */
		toolBar.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
	}

	/**
	 * 初始化主面板
	 */
	private void initContentPane() {
		/**
		 * 创建主面板
		 */
		contentPanel = new JPanel(new BorderLayout());
		/**
		 * 创建左边面板
		 */
		leftPanel = new KeySpacesTree();
		/**
		 * 创建右边面板
		 */
		rightPanel = new RightPanel(this);
		/**
		 * 将左右两边面板放入视图组件容器中
		 * 
		 * 使用 JSplitPane.HORIZONTAL_SPLIT 可让分隔窗格中的两个 Component 从左到右排列，
		 * 
		 * 或者使用 JSplitPane.VERTICAL_SPLIT 使其从上到下排列。
		 * 
		 */
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, leftPanel, rightPanel);
		/**
		 * 改变 Component 大小的首选方式是调用 setDividerLocation，
		 * 
		 * 其中 location 是新的 x 或 y 位置，具体取决于 JSplitPane 的方向
		 * 
		 * 设置分隔条的位置为 JSplitPane 大小的一个百分比
		 */
		splitPane.setDividerLocation(180);

		/**
		 * 设置 oneTouchExpandable 属性的值，要使 JSplitPane 在分隔条上提供一个 UI
		 * 小部件来快速展开/折叠分隔条，此属性必须为 true。
		 */
		splitPane.setOneTouchExpandable(true);
		/**
		 * 向此组件添加任意的键/值"客户端属性"
		 */
		splitPane.putClientProperty("textureType", GUIProperties.TEXTURE_TYPE);
		/**
		 * 将顶部一排按钮加入到顶部
		 */
		contentPanel.add(toolBar, BorderLayout.NORTH);
		/**
		 * 将左右两边父容器加入到中间
		 */
		contentPanel.add(splitPane, BorderLayout.CENTER);
		/**
		 * 将主面板设置到窗口
		 * 
		 * 一般的 Abstract Window Toolkit(AWT) 容器对象是一个可包含其他 AWT 组件的组件
		 */
		setContentPane(contentPanel);
	}

	/**
	 * 设置JFrame抬头图片
	 */
	private void showApp() {
		// JFrame抬头图片
		setIconImage(ImageHelper.loadImage("home.gif").getImage());
		// 设置图片大小
		setBounds(appBounds);
		// 默认启动最大化
		// setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		// 可见
		setVisible(true);
	}

	public static void main(String args[]) {
		try {
			ToolTipManager.sharedInstance().setInitialDelay(500);
			ToolTipManager.sharedInstance().setDismissDelay(60000);
			ToolTipManager.sharedInstance().setReshowDelay(0);
			ToolTipManager.sharedInstance().setLightWeightPopupEnabled(false);
			// UIManager.put("ScrollBar.squareButtons", Boolean.TRUE);
			// UIManager.put("ScrollBar.incrementButtonGap", new Integer(-1));
			// UIManager.put("ScrollBar.decrementButtonGap", new Integer(-1));

			// com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme("Default");
			UIManager.setLookAndFeel("com.firecode.cqldesktop.style.theme.simple.SimpleLookAndFeel");
			app = new Main();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
