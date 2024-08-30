package lich.tool.sealImage;

import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class SealImageTemplate extends BaseSealImageTemplate{
	/**
	 * 圆弧配置
	 */
	private List<FigureSetting> circleSettings=new ArrayList<FigureSetting>();
	/**
	 * 长方形配置
	 */
	private List<FigureSetting> rectSettings=new ArrayList<FigureSetting>();
	/**
	 * 圆弧文字配置
	 */
	private List<CircleTextSetting> circleTextSettings=new ArrayList<CircleTextSetting>();
	/**
	 * 横向文字配置
	 */
	private List<TextSetting> textSettings=new ArrayList<TextSetting>();
	/**
	 * 横向文字配置
	 */
	private List<ImageSetting> imageSettings=new ArrayList<ImageSetting>();
	
	public static SealImageTemplate getDefaultTemplate(String topWinding,String bottomWinding) {
		SealImageTemplate sit=	new SealImageTemplate();
		sit.addCircleSettings(new FigureSetting());
		sit.addCircleTextSettings(new CircleTextSetting(topWinding));
		sit.addCircleTextSettings(new CircleTextSetting(bottomWinding).setTopWinding(false).setFontSpace(5).setFont(new Font("CESI_SS_GB18030", Font.BOLD, 20)));
		sit.addTextSettings(new TextSetting("★"));
		
		return sit;
	}
	public static SealImageTemplate getEllipseDefaultTemplate(String topWinding,String code) {
		SealImageTemplate sit=(SealImageTemplate)new SealImageTemplate().setHeight(150).setWidth(300);
		sit.addCircleSettings(new FigureSetting().setHeight(70).setWidth(145));
		sit.addCircleSettings(new FigureSetting().setHeight(65).setWidth(140).setFrameSize(1));
		sit.addCircleTextSettings(new CircleTextSetting(topWinding).setFont(new Font("CESI_FS_GB18030", Font.BOLD, 23)).setHeight(65).setFontSpace(10).setWidth(140));
		sit.addTextSettings(new TextSetting(code).setFont(new Font("CESI_FS_GB18030", Font.BOLD, 20)).setyDeviation(10));
		sit.addTextSettings(new TextSetting("发票专用章").setFont(new Font("CESI_FS_GB18030", Font.BOLD, 20)).setyDeviation(40));
		
		return sit;
	}
	
	public void addImageSettings(ImageSetting ... imageSettings) {
		for(ImageSetting imageSetting: imageSettings)this.imageSettings.add(imageSetting);
	}
	public List<ImageSetting> getImageSettings() {
		return imageSettings;
	}
	public void setImageSettings(List<ImageSetting> imageSettings) {
		this.imageSettings = imageSettings;
	}
	public void addRectSettings(FigureSetting ... rectSettings) {
		for(FigureSetting rectSetting: rectSettings)this.rectSettings .add(rectSetting);
	}
	public List<FigureSetting> getRectSettings() {
		return rectSettings;
	}
	public void setRectSettings(List<FigureSetting> rectSettings) {
		this.rectSettings = rectSettings;
	}
	public void addTextSettings(TextSetting ... textSettings) {
		for(TextSetting textSetting: textSettings)this.textSettings .add(textSetting);
	}
	
	public List<TextSetting> getTextSettings() {
		return textSettings;
	}

	public void setTextSettings(List<TextSetting> textSettings) {
		this.textSettings = textSettings;
	}

	public List<FigureSetting> getCircleSettings() {
		return circleSettings;
	}
	public void addCircleSettings(FigureSetting ... circleSettings) {
		for(FigureSetting circleSetting: circleSettings)this.circleSettings .add(circleSetting);
	}
	
	public void setCircleSettings(List<FigureSetting> circleSettings) {
		this.circleSettings = circleSettings;
	}

	public List<CircleTextSetting> getCircleTextSettings() {
		return circleTextSettings;
	}
	public void addCircleTextSettings(CircleTextSetting ... circleTextSettings) {
		for(CircleTextSetting circleTextSetting: circleTextSettings)this.circleTextSettings .add(circleTextSetting);
	}
	public void setCircleTextSettings(List<CircleTextSetting> circleTextSettings) {
		this.circleTextSettings = circleTextSettings;
	}

	public static class TextSetting{
		private int yDeviation=10;
		private int xDeviation=0;
		/**
		 * 字体
		 */
		private	Font font=new Font("CESI_SS_GB18030", Font.BOLD, 130);
		/**
		 * 文字
		 */
		private String text="";
		
		public TextSetting(String text) {
			super();
			this.text = text;
		}
		public int getyDeviation() {
			return yDeviation;
		}
		public TextSetting setyDeviation(int yDeviation) {
			this.yDeviation = yDeviation;
			return this;
		}
		public int getxDeviation() {
			return xDeviation;
		}
		public TextSetting setxDeviation(int xDeviation) {
			this.xDeviation = xDeviation;
			return this;
		}
		public Font getFont() {
			return font;
		}
		public TextSetting setFont(Font font) {
			this.font = font;
			return this;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		
	}
	
	public static class CircleTextSetting{
		private int yDeviation=6;
		private int xDeviation=0;
		/**
		 * 上下弦
		 */
		private boolean	isTopWinding=true;
		/**
		 * 字体
		 */
		private	Font font=new Font("CESI_SS_GB18030", Font.BOLD, 40);
		/**
		 * 椭圆x半径
		 */
		private int width=150;
		/**
		 * 椭圆y半径
		 */
		private int height=150;
		/**
		 * 字间距
		 */
		private int fontSpace=20;
		/**
		 * 文字
		 */
		private String text="";
		
		public CircleTextSetting(String text) {
			this.text=text;
		}
		
		public Font getFont() {
			return font;
		}

		public CircleTextSetting setFont(Font font) {
			this.font = font;
			return this;
		}

		public int getyDeviation() {
			return yDeviation;
		}

		public CircleTextSetting setyDeviation(int yDeviation) {
			this.yDeviation = yDeviation;
			return this;
		}

		public int getxDeviation() {
			return xDeviation;
		}

		public CircleTextSetting setxDeviation(int xDeviation) {
			this.xDeviation = xDeviation;
			return this;
		}

		public int getWidth() {
			return width;
		}

		public CircleTextSetting setWidth(int width) {
			this.width = width;
			return this;
		}

		public int getHeight() {
			return height;
		}

		public CircleTextSetting setHeight(int height) {
			this.height = height;
			return this;
		}

		public int getFontSpace() {
			return fontSpace;
		}

		public CircleTextSetting setFontSpace(int fontSpace) {
			this.fontSpace = fontSpace;
			return this;
		}

		public String getText() {
			return text;
		}

		public CircleTextSetting setText(String text) {
			this.text = text;
			return this;
		}

		public boolean isTopWinding() {
			return isTopWinding;
		}

		public CircleTextSetting setTopWinding(boolean isTopWinding) {
			this.isTopWinding = isTopWinding;
			return this;
		}
		
	}
	
public static class FigureSetting{	
		
		/**
		 * 边框宽度
		 */
		private int frameSize=5;
		/**
		 * 椭圆x半径
		 */
		private int width=145;
		/**
		 * 椭圆y半径
		 */
		private int height=145;
		
		private int yDeviation=0;
		private int xDeviation=0;
				
		
		public FigureSetting() {
			super();
		}
		/**
		 * 
		 * @param frameSize 线条宽度
		 * @param width 图形宽 椭圆为X半径
		 * @param height 图形高 椭圆为Y半径
		 * @param yDeviation y轴偏移量
		 * @param xDeviation x轴偏移量
		 */
		public FigureSetting(int frameSize, int width, int height, int yDeviation, int xDeviation) {
			super();
			this.frameSize = frameSize;
			this.width = width;
			this.height = height;
			this.yDeviation = yDeviation;
			this.xDeviation = xDeviation;
		}
		public int getFrameSize() {
			return frameSize;
		}
		public FigureSetting setFrameSize(int frameSize) {
			this.frameSize = frameSize;
			return this;
		}

		public int getWidth() {
			return width;
		}
		public FigureSetting setWidth(int width) {
			this.width = width;
			return this;
		}
		public int getHeight() {
			return height;
		}
		public FigureSetting setHeight(int height) {
			this.height = height;
			return this;
		}
		public int getyDeviation() {
			return yDeviation;
		}
		public FigureSetting setyDeviation(int yDeviation) {
			this.yDeviation = yDeviation;
			return this;
		}
		public int getxDeviation() {
			return xDeviation;
		}
		public FigureSetting setxDeviation(int xDeviation) {
			this.xDeviation = xDeviation;
			return this;
		}
	}
public static class ImageSetting{	
	private int width=0;
	private int height=0;
	private int yDeviation=0;
	private int xDeviation=0;
	private	Image img;
	
	public ImageSetting() {
		super();
	}
	/**
	 * 
	 * @param img
	 * @param width
	 * @param height
	 * @param yDeviation
	 * @param xDeviation
	 */
	public ImageSetting(Image img,int width, int height, int yDeviation, int xDeviation) {
		super();
		this.img=img;
		this.width = width;
		this.height = height;
		this.yDeviation = yDeviation;
		this.xDeviation = xDeviation;
	}
	

	public Image getImg() {
		return img;
	}
	public ImageSetting setImg(Image img) {
		this.img = img;
		return this;
	}
	public int getWidth() {
		return width;
	}
	public ImageSetting setWidth(int width) {
		this.width = width;
		return this;
	}
	public int getHeight() {
		return height;
	}
	public ImageSetting setHeight(int height) {
		this.height = height;
		return this;
	}
	public int getyDeviation() {
		return yDeviation;
	}
	public ImageSetting setyDeviation(int yDeviation) {
		this.yDeviation = yDeviation;
		return this;
	}
	public int getxDeviation() {
		return xDeviation;
	}
	public ImageSetting setxDeviation(int xDeviation) {
		this.xDeviation = xDeviation;
		return this;
	}
	
}
}
