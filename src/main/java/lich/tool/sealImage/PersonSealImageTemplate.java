package lich.tool.sealImage;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
/**
 * 个人章印章模板
 * @author lich
 *
 */
public class PersonSealImageTemplate extends BaseSealImageTemplate{
	/**
	 * 字体
	 */
	private	Font font=new Font("CESI_SS_GB18030", Font.BOLD, 300);
	/**
	 * 边框宽度
	 */
	private int frameSize=15;
	/**
	 * 内边距
	 */
	private int padding=20;
	/**
	 * 外边距
	 */
	private int margin=10;
	/**
	 * 扩展文字模式
	 */
	private Map<Integer,PersonAlignMode> extTextMode=new HashMap<Integer,PersonAlignMode>();
	
	/**
	 * 个人章排列模式
	 */
	PersonAlignMode personAlignMode=PersonAlignMode.P_1X2;
	public Font getFont() {
		return font;
	}
	/**
	 * 设置字体
	 * @param font 字体
	 * @return this
	 */
	public PersonSealImageTemplate setFont(Font font) {
		this.font = font;
		return this;
	}
	public int getFrameSize() {
		return frameSize;
	}
	
	/**
	 * 设置边框宽度
	 * @param frameSize 
	 * @return
	 */
	public PersonSealImageTemplate setFrameSize(int frameSize) {
		this.frameSize = frameSize;
		return this;
	}
	/**
	 * 获取内边距
	 * @return 内边距
	 */
	public int getPadding() {
		return padding;
	}
	/**
	 * 设置内边距
	 * @param padding 内边距
	 * @return this
	 */
	public PersonSealImageTemplate setPadding(int padding) {
		this.padding = padding;
		return this;
	}
	/**
	 * 获取书写方向
	 */
	public WritingMode getWritingMode() {
		return writingMode;
	}
	/**
	 * 设置书写方向
	 * @param writingMode 书写方向
	 * @return this
	 */
	public PersonSealImageTemplate setWritingMode(WritingMode writingMode) {
		this.writingMode = writingMode;
		return this;
	}
	/**
	 * 获取排列规则
	 * @return 排列规则
	 */
	public PersonAlignMode getPersonAlignMode() {
		return personAlignMode;
	}
	/**
	 * 设置排列规则
	 * @param personAlignMode 排列规则
	 * @return this
	 */
	public PersonSealImageTemplate setPersonAlignMode(PersonAlignMode personAlignMode) {
		this.personAlignMode = personAlignMode;
		return this;
	}
	/**
	 * 获取外边距
	 * @return 外边距
	 */
	public int getMargin() {
		return margin;
	}
	/**
	 * 设置外边距
	 * @param margin 外边距
	 * @return this
	 */
	public PersonSealImageTemplate setMargin(int margin) {
		this.margin = margin;
		return this;
	}
	/**
	 * 设置第x个字扩展规则
	 * @param x 文字序号 1开始
	 * @param p 模式
	 * @return this
	 */
	public PersonSealImageTemplate setExtTextMode(int x,PersonAlignMode p){
		extTextMode.put(x, p);
		return this;
	}
	/**
	 * 设置第x个字扩展规则
	 * @param index 第index个字
	 * @return 书写规则
	 */
	public  PersonAlignMode getExtTextMode(int index) {
		return extTextMode.get(index);
	}
	
}
