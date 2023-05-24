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
	public void setColor(Color color) {
		this.color = color;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
