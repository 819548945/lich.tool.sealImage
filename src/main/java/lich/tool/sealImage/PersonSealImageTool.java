package lich.tool.sealImage;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import lich.tool.sealImage.PersonAlignMode;
import lich.tool.sealImage.PersonSealImageTemplate;
import lich.tool.sealImage.SealImageException;
import lich.tool.sealImage.WritingMode;
/**
 * 个人章（方章生成工具）
 * @author liuch
 *
 */
public class PersonSealImageTool{
	/**
	 * 个人章默认模板列表
	 */
	private static List<PersonSealImageTemplate> personSealImageTemplateList=new ArrayList<PersonSealImageTemplate>();
	static {
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_1X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_1X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_1X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_2X2).setExtTextMode(2, PersonAlignMode.P_1X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_2X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_2X3).setExtTextMode(4, PersonAlignMode.P_1X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_2X3));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_3X3).setExtTextMode(6, PersonAlignMode.P_1X3));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_3X3).setExtTextMode(7, PersonAlignMode.P_1X2));
		personSealImageTemplateList.add(new PersonSealImageTemplate().setPersonAlignMode(PersonAlignMode.P_3X3));
	}	
	/**
	 * 设置默认书写模式
	 * @param wm 书写模式
	 */
	public static void setDefaultWritingMode(WritingMode wm){
		 for(PersonSealImageTemplate personSealImageTemplate:personSealImageTemplateList)personSealImageTemplate.setWritingMode(wm);
	}
	
	/**
	 * 设置默认内边距
	 * @param size 内边距
	 */
	public static void setDefaultPadding(int size){
		 for(PersonSealImageTemplate personSealImageTemplate:personSealImageTemplateList)personSealImageTemplate.setPadding(size);
	}
	/**
	 * 设置默认字体
	 * @param font 字体
	 */
	public static void setDefaultFont(Font font){
		 for(PersonSealImageTemplate personSealImageTemplate:personSealImageTemplateList)personSealImageTemplate.setFont(font);
	}
	/**
	 * 设置默认外框大小
	 * @param size 外框大小
	 */
	public static void setDefaultFrameSize(int size){
		 for(PersonSealImageTemplate personSealImageTemplate:personSealImageTemplateList)personSealImageTemplate.setFrameSize(size);
	}
	/**
	 * 设置默认外边距
	 * @param size 外边距
	 */
	public static void setDefaultMargin(int size){
		 for(PersonSealImageTemplate personSealImageTemplate:personSealImageTemplateList)personSealImageTemplate.setMargin(size);
	}
	
	private static Graphics2D setConfig(Graphics2D g2d, PersonSealImageTemplate p){
		g2d.setPaint(p.getColor());   
	    g2d.setFont(p.getFont());
	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    return g2d;
	}
	/**
	 * 使用默认配置生成方章
	 * @param str印章文字
	 * @return BufferedImage 对象
	 * @throws SealImageException
	 */
	public static BufferedImage	buildPersonSealImage(String str) throws SealImageException{
		return PersonSealImageTool.buildPersonSealImage(null, str);
	}
	/**
	 * 生成方章
	 * @param p 章配置
	 * @param str 印章文字
	 * @return BufferedImage 对象
	 * @throws SealImageException
	 */
	public static BufferedImage	buildPersonSealImage(PersonSealImageTemplate p,String str) throws SealImageException{
		int strLength=str.length();	
		if(p==null) {
			if(personSealImageTemplateList.size()-1<strLength)throw new SealImageException("未定义文字长度为"+strLength+"的默认规则");
			p=personSealImageTemplateList.get(str.length());
		}
	    BufferedImage bi = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);
	    Graphics2D g2d = setConfig( bi.createGraphics(),p);
	    FontRenderContext context = g2d.getFontRenderContext();
	    Rectangle2D rectangle = p.getFont().getStringBounds(str.substring(0, 1), context);
	   //LineMetrics metrics=p.getFont().getLineMetrics(str.substring(0, 1),context);
	    double fX= Math.abs(rectangle.getWidth());
	    double fY= Math.abs(rectangle.getHeight());
	    double  ascent =Math.abs(rectangle.getY());
	    double mX=p.getPersonAlignMode().getxLength()*fX+(p.getPersonAlignMode().getxLength()-1)*p.getPadding();
	    double mY=p.getPersonAlignMode().getyLength()*fY+(p.getPersonAlignMode().getyLength()-1)*p.getPadding();
	    bi = new BufferedImage( (int)(mY*2),(int)(mX*2), bi.getType());
	    g2d =setConfig( bi.createGraphics(),p);
	    double currentX=0;
	    double currentY=0;
	    int i=0,j=0,k=0,jMax=0,kMax=0,jAdd=0,kAdd=0;
		if(p.getWritingMode().isxPrior()) {
			jAdd=p.getWritingMode().getIvY();
			kAdd=p.getWritingMode().getIvX();
			jMax=p.getPersonAlignMode().getyLength();
			kMax=p.getPersonAlignMode().getxLength();
			j=p.getWritingMode().getIvY()<0?p.getPersonAlignMode().getyLength()-1:0;
			k=p.getWritingMode().getIvX()<0?p.getPersonAlignMode().getxLength()-1:0;
		}else {
			jAdd=p.getWritingMode().getIvX();
			kAdd=p.getWritingMode().getIvY();
			jMax=p.getPersonAlignMode().getxLength();
			kMax=p.getPersonAlignMode().getyLength();
			j=p.getWritingMode().getIvX()<0?p.getPersonAlignMode().getxLength()-1:0;
			k=p.getWritingMode().getIvY()<0?p.getPersonAlignMode().getyLength()-1:0;
		}
		for(;j>=0&&j<jMax;j=j+jAdd) {
			for(int kx=k;kx>=0&&kx<kMax;kx=kx+kAdd) {
				if(p.getWritingMode().isxPrior()) {
					currentX=kx*fX+j*p.getPadding();
					currentY=j*fX+kx*p.getPadding()+ascent;
				}else {
					currentX=j*fX+j*p.getPadding();
					currentY=kx*fX+kx*p.getPadding()+ascent;
				}
				if(i<str.length()) {
					PersonAlignMode pam;
					if((pam=p.getExtTextMode(i))!=null) {
						BufferedImage bi2=getImages(str.charAt(i), rectangle, p );
						g2d.drawImage(bi2,(int) currentX,(int)(currentY-pam.getyLength()*fY*0.4),(int)(pam.getyLength()*(fX)),(int)( pam.getxLength()*(fX*0.9)) , null);	
					}else {
						g2d.drawString( str.charAt(i)+"", (int)currentX,(int)currentY);
					}		
				}
				i++;
	    	}
		}	
		bi=PersonSealImageTool.subImage(bi);
		//设置边框
		BufferedImage tag = new BufferedImage(p.getWidth(), p.getHeight(), bi.getType());
	    tag.getGraphics().drawImage(bi,p.getMargin()+p.getFrameSize(), p.getMargin()+p.getFrameSize(),p.getWidth()-2*(p.getMargin()+p.getFrameSize()), p.getHeight()-2*(p.getMargin()+p.getFrameSize()), null);
	    g2d = setConfig(tag.createGraphics(),p);
	    g2d.setStroke(new BasicStroke(p.getFrameSize()));
		g2d.drawRect(0, 0, p.getWidth(), p.getHeight());
		g2d.dispose();
		bi=tag;
		return bi;
	}
	private static BufferedImage getImages(char c,Rectangle2D rectangle,PersonSealImageTemplate p ) {
		double fX= Math.abs(rectangle.getWidth());
	    double fY= Math.abs(rectangle.getHeight());
	    double  ascent =Math.abs(rectangle.getY());
	    BufferedImage bi=new BufferedImage( (int)(fX*2),(int)(fY*2), BufferedImage.TYPE_4BYTE_ABGR);
	    Graphics2D  g2d =setConfig( bi.createGraphics(),p);
	    g2d.drawString(c+"",(int) fX,(int)(fX+ascent));
	    return subImage( bi);
	}
	private static BufferedImage subImage(BufferedImage bi){
		int bx=bi.getWidth(),by=bi.getHeight(),ex=0,ey=0;
		for(int m=0;m<bi.getHeight() ;m++)
			for(int l=0;l<bi.getWidth();l++) {
				int rgb=bi.getRGB(l, m);
				if(rgb!=0) {
					if(l<bx) bx=l;
					if(m<by)by=m;
					if(l>ex)ex=l;
					if(m>ey)ey=m;
					
				}	
			}
		return bi.getSubimage(bx, by, ex-bx, ey-by);
	}
}
