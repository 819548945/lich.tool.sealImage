package lich.tool.sealImage;

/**
 * 个人章排列方式
 * @author lich
 *
 */
public enum PersonAlignMode {
	/**
	 * 两行一列
	 */
	P_2X1(2,1),
	/**
	 * 两行两列
	 */
	P_2X2(2,2),
	/**
	 * 两行三列
	 */
	P_2X3(2,3),
	/**
	 * 一行两列
	 */
	P_1X2(1,2),
	/**
	 * 一行三列
	 */
	P_1X3(1,3),
	/**
	 * 三行两列
	 */
	P_3X2(3,2),
	/**
	 * 三行三列
	 */
	P_3X3(3,3);
	/**
	 * x文字长度
	 */
	private int xLength;
	/**
	 * y文字长度
	 */
	private int yLength;
	PersonAlignMode(int y,int x){
		this.xLength=x;
		this.yLength=y;
	}
	public int getxLength() {
		return xLength;
	}
	public void setxLength(int xLength) {
		this.xLength = xLength;
	}
	public int getyLength() {
		return yLength;
	}
	public void setyLength(int yLength) {
		this.yLength = yLength;
	}
	
	
}
