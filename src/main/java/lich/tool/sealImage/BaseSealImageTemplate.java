package lich.tool.sealImage;

import java.awt.Color;

/**
 * Base印章模板
 * @author lich
 *
 */
public class BaseSealImageTemplate {
	/**
	 * 书写模式
	 */
	protected WritingMode writingMode=WritingMode.RIGHT_TOP_VERTICAL;
	/**
	 * 字体颜色
	 */
	protected Color color=Color.RED;
	/**
	 * 宽度
	 */
	protected int width=300;
	/**
	 * 高度
	 */
	protected int height=300;
	public WritingMode getWritingMode() {
		return writingMode;
	}
	
	public Color getColor() {
		return color;
	}
	public BaseSealImageTemplate setColor(Color color) {
		this.color = color;
		return this;
	}
	public int getWidth() {
		return width;
	}
	public BaseSealImageTemplate setWidth(int width) {
		this.width = width;
		return this;
	}
	public int getHeight() {
		return height;
	}
	public BaseSealImageTemplate setHeight(int height) {
		this.height = height;
		return this;
	}
	
	
}
