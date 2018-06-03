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

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicSliderUI;

import com.firecode.cqldesktop.utils.StyleUtils;

/**
 * @author Michael Hagen
 */
public class BaseSliderUI extends BasicSliderUI {

	protected boolean isRollover = false;

	public BaseSliderUI(JSlider slider) {
		super(slider);
	}

	public static ComponentUI createUI(JComponent c) {
		return new BaseSliderUI((JSlider) c);
	}

	public TrackListener createTrackListener(JSlider slider) {
		return new MyTrackListener();
	}

	public Icon getThumbHorIcon() {
		if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
			return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbHorIcon();
		}
		return null;
	}

	public Icon getThumbHorIconRollover() {
		if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
			return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbHorIconRollover();
		}
		return null;
	}

	public Icon getThumbVerIcon() {
		if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
			return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbVerIcon();
		}
		return null;
	}

	public Icon getThumbVerIconRollover() {
		if (UIManager.getLookAndFeel() instanceof AbstractLookAndFeel) {
			return ((AbstractLookAndFeel) UIManager.getLookAndFeel()).getIconFactory().getThumbVerIconRollover();
		}
		return null;
	}

	protected int getTrackWidth() {
		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			return (thumbRect.height - 9);
		} else {
			return (thumbRect.width - 9);
		}
	}

	protected Dimension getThumbSize() {
		Dimension size = super.getThumbSize();
		if ((getThumbHorIcon() != null) && (getThumbVerIcon() != null)) {
			if (slider.getOrientation() == JSlider.HORIZONTAL) {
				size.width = getThumbHorIcon().getIconWidth();
				size.height = getThumbHorIcon().getIconHeight();
			} else {
				size.width = getThumbVerIcon().getIconWidth();
				size.height = getThumbVerIcon().getIconHeight();
			}
		}
		return size;
	}

	public void paint(Graphics g, JComponent c) {
		paintBackground(g, c);
		recalculateIfInsetsChanged();
		recalculateIfOrientationChanged();
		Rectangle clip = g.getClipBounds();

		if (!clip.intersects(trackRect) && slider.getPaintTrack()) {
			calculateGeometry();
		}

		if (slider.getPaintTrack() && clip.intersects(trackRect)) {
			paintTrack(g);
		}
		if (slider.getPaintTicks() && clip.intersects(tickRect)) {
			paintTicks(g);
		}
		if (slider.getPaintLabels() && clip.intersects(labelRect)) {
			paintLabels(g);
		}
		if (slider.hasFocus() && clip.intersects(focusRect)) {
			paintFocus(g);
		}
		if (clip.intersects(thumbRect)) {
			paintThumb(g);
		}
	}

	public void paintBackground(Graphics g, JComponent c) {
		if (c.isOpaque()) {
			if (c.getBackground() instanceof ColorUIResource) {
				g.setColor(AbstractLookAndFeel.getBackgroundColor());
			} else {
				g.setColor(c.getBackground());
			}
			g.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
	}

	public void paintTrack(Graphics g) {
		boolean leftToRight = StyleUtils.isLeftToRight(slider);

		g.translate(trackRect.x, trackRect.y);
		int overhang = 4;
		int trackLeft = 0;
		int trackTop = 0;
		int trackRight;
		int trackBottom;

		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			trackBottom = (trackRect.height - 1) - overhang;
			trackTop = trackBottom - (getTrackWidth() - 1);
			trackRight = trackRect.width - 1;
		} else {
			if (leftToRight) {
				trackLeft = (trackRect.width - overhang) - getTrackWidth();
				trackRight = (trackRect.width - overhang) - 1;
			} else {
				trackLeft = overhang;
				trackRight = overhang + getTrackWidth() - 1;
			}
			trackBottom = trackRect.height - 1;
		}

		g.setColor(AbstractLookAndFeel.getFrameColor());
		g.drawRect(trackLeft, trackTop, (trackRight - trackLeft) - 1, (trackBottom - trackTop) - 1);

		int middleOfThumb;
		int fillTop;
		int fillLeft;
		int fillBottom;
		int fillRight;

		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			middleOfThumb = thumbRect.x + (thumbRect.width / 2);
			middleOfThumb -= trackRect.x;
			fillTop = trackTop + 1;
			fillBottom = trackBottom - 2;

			if (!drawInverted()) {
				fillLeft = trackLeft + 1;
				fillRight = middleOfThumb;
			} else {
				fillLeft = middleOfThumb;
				fillRight = trackRight - 2;
			}
			Color colors[];
			if (!StyleUtils.isActive(slider)) {
				colors = AbstractLookAndFeel.getTheme().getInActiveColors();
			} else {
				if (slider.isEnabled()) {
					colors = AbstractLookAndFeel.getTheme().getSliderColors();
				} else {
					colors = AbstractLookAndFeel.getTheme().getDisabledColors();
				}
			}
			StyleUtils.fillHorGradient(g, colors, fillLeft + 2, fillTop + 2, fillRight - fillLeft - 2,
					fillBottom - fillTop - 2);
			Color cHi = ColorHelper.darker(colors[colors.length - 1], 5);
			Color cLo = ColorHelper.darker(colors[colors.length - 1], 10);
			StyleUtils.draw3DBorder(g, cHi, cLo, fillLeft + 1, fillTop + 1, fillRight - fillLeft - 1,
					fillBottom - fillTop - 1);
		} else {
			middleOfThumb = thumbRect.y + (thumbRect.height / 2);
			middleOfThumb -= trackRect.y;
			fillLeft = trackLeft + 1;
			fillRight = trackRight - 2;

			if (!drawInverted()) {
				fillTop = middleOfThumb;
				fillBottom = trackBottom - 2;
			} else {
				fillTop = trackTop + 1;
				fillBottom = middleOfThumb;
			}
			Color colors[];
			if (!StyleUtils.isActive(slider)) {
				colors = AbstractLookAndFeel.getTheme().getInActiveColors();
			} else {
				if (slider.isEnabled()) {
					colors = AbstractLookAndFeel.getTheme().getSliderColors();
				} else {
					colors = AbstractLookAndFeel.getTheme().getDisabledColors();
				}
			}
			StyleUtils.fillVerGradient(g, colors, fillLeft + 2, fillTop + 2, fillRight - fillLeft - 2,
					fillBottom - fillTop - 2);
			Color cHi = ColorHelper.darker(colors[colors.length - 1], 5);
			Color cLo = ColorHelper.darker(colors[colors.length - 1], 10);
			StyleUtils.draw3DBorder(g, cHi, cLo, fillLeft + 1, fillTop + 1, fillRight - fillLeft - 1,
					fillBottom - fillTop - 1);
		}
		g.translate(-trackRect.x, -trackRect.y);
	}

	public void paintTicks(Graphics g) {
		boolean leftToRight = StyleUtils.isLeftToRight(slider);
		Rectangle tickBounds = tickRect;
		g.setColor(slider.getForeground());
		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			g.translate(0, tickBounds.y);

			int value = slider.getMinimum();
			int xPos;

			if (slider.getMinorTickSpacing() > 0) {
				while (value <= slider.getMaximum()) {
					xPos = xPositionForValue(value);
					paintMinorTickForHorizSlider(g, tickBounds, xPos);
					value += slider.getMinorTickSpacing();
				}
			}

			if (slider.getMajorTickSpacing() > 0) {
				value = slider.getMinimum();
				while (value <= slider.getMaximum()) {
					xPos = xPositionForValue(value);
					paintMajorTickForHorizSlider(g, tickBounds, xPos);
					value += slider.getMajorTickSpacing();
				}
			}

			g.translate(0, -tickBounds.y);
		} else {
			g.translate(tickBounds.x, 0);

			int value = slider.getMinimum();
			int yPos;

			if (slider.getMinorTickSpacing() > 0) {
				int offset = 0;
				if (!leftToRight) {
					offset = tickBounds.width - tickBounds.width / 2;
					g.translate(offset, 0);
				}

				while (value <= slider.getMaximum()) {
					yPos = yPositionForValue(value);
					paintMinorTickForVertSlider(g, tickBounds, yPos);
					value += slider.getMinorTickSpacing();
				}
				if (!leftToRight) {
					g.translate(-offset, 0);
				}
			}

			if (slider.getMajorTickSpacing() > 0) {
				value = slider.getMinimum();
				if (!leftToRight) {
					g.translate(2, 0);
				}

				while (value <= slider.getMaximum()) {
					yPos = yPositionForValue(value);
					paintMajorTickForVertSlider(g, tickBounds, yPos);
					value += slider.getMajorTickSpacing();
				}

				if (!leftToRight) {
					g.translate(-2, 0);
				}
			}
			g.translate(-tickBounds.x, 0);
		}
	}

	protected boolean isDragging() {
		if (StyleUtils.getJavaVersion() >= 1.5) {
			return super.isDragging();
		} else {
			return false;
		}
	}

	public void paintThumb(Graphics g) {
		Icon icon;
		if (slider.getOrientation() == JSlider.HORIZONTAL) {
			if ((isRollover || isDragging()) && slider.isEnabled()) {
				icon = getThumbHorIconRollover();
			} else {
				icon = getThumbHorIcon();
			}
		} else {
			if ((isRollover || isDragging()) && slider.isEnabled()) {
				icon = getThumbVerIconRollover();
			} else {
				icon = getThumbVerIcon();
			}
		}
		Graphics2D g2D = (Graphics2D) g;
		Composite savedComposite = g2D.getComposite();
		if (!slider.isEnabled()) {
			g.setColor(AbstractLookAndFeel.getBackgroundColor());
			g.fillRect(thumbRect.x + 1, thumbRect.y + 1, thumbRect.width - 2, thumbRect.height - 2);
			AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f);
			g2D.setComposite(alpha);
		}
		icon.paintIcon(null, g, thumbRect.x, thumbRect.y);
		g2D.setComposite(savedComposite);
	}

	protected class MyTrackListener extends TrackListener {

		public void mouseEntered(MouseEvent e) {
			super.mouseEntered(e);
			if (slider.isEnabled()) {
				isRollover = thumbRect.contains(e.getPoint());
				slider.repaint();
			}
		}

		public void mouseMoved(MouseEvent e) {
			super.mouseMoved(e);
			if (slider.isEnabled()) {
				boolean rollover = thumbRect.contains(e.getPoint());
				if (rollover != isRollover) {
					isRollover = rollover;
					slider.repaint();
				}
			}
		}

		public void mouseExited(MouseEvent e) {
			super.mouseExited(e);
			if (slider.isEnabled()) {
				isRollover = false;
				slider.repaint();
			}
		}

	}
}
