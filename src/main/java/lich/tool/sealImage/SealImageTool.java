package lich.tool.sealImage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import lich.tool.sealImage.SealImageTemplate.CircleTextSetting;
import lich.tool.sealImage.SealImageTemplate.FigureSetting;
import lich.tool.sealImage.SealImageTemplate.ImageSetting;
import lich.tool.sealImage.SealImageTemplate.TextSetting;

public class SealImageTool {
	public static BufferedImage	buildSealImage(SealImageTemplate p) throws SealImageException{
        BufferedImage bi = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g2d = bi.createGraphics();
       
        RenderingHints hints = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        hints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(hints);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 0));
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1));
        g2d.setPaint(p.getColor());
       // g2d.drawRect(0, 0, p.getWidth()-1, p.getHeight()-1);
        for(FigureSetting circleSetting:p.getCircleSettings()) drawCicle(g2d,circleSetting,p);
        for(FigureSetting rectSetting:p.getRectSettings())  drawRect(g2d,rectSetting,p);
        for(ImageSetting imageSetting:p.getImageSettings()) drawImage( g2d,  imageSetting,p);
        for(CircleTextSetting circleTextSetting:p.getCircleTextSettings())drawArcFont4Oval( g2d,  circleTextSetting,p);
        for(TextSetting textSetting:p.getTextSettings()) drawFont( g2d,  textSetting,p);
        
        g2d.dispose();
        return bi;
	}
	
    private static void drawImage(Graphics2D g2d, ImageSetting imageSettings,SealImageTemplate sit) {
    	
        int x1=	sit.getWidth()/2-imageSettings.getWidth()/2+imageSettings.getxDeviation();
        int y1=	sit.getHeight()/2-imageSettings.getHeight()/2+imageSettings.getyDeviation();
        int x2=	sit.getWidth()/2+imageSettings.getWidth()/2+imageSettings.getxDeviation();
        int y2=	sit.getHeight()/2+imageSettings.getHeight()/2+imageSettings.getyDeviation();
        g2d.drawImage(imageSettings.getImg(), x1, y1, x2, y2, 0, 0,imageSettings.getImg().getWidth(null), imageSettings.getImg().getHeight(null), null);
      
    }
	/**
     * 画长方型
     *
     * @param g2d    画笔
     * @param circle 长方型配置对象
     */
    private static void drawRect(Graphics2D g2d, FigureSetting circleSettings,SealImageTemplate sit) {
    	
        int x1=	sit.getWidth()/2-circleSettings.getWidth()/2+circleSettings.getxDeviation();
        int y1=	sit.getHeight()/2-circleSettings.getHeight()/2+circleSettings.getyDeviation();
        g2d.setStroke(new BasicStroke(circleSettings.getFrameSize()));
        g2d.drawRect(x1, y1, circleSettings.getWidth(), circleSettings.getHeight());
    }
	 /**
     * 画圆
     *
     * @param g2d    画笔
     * @param circle 圆配置对象
     */
    private static void drawCicle(Graphics2D g2d, FigureSetting circleSettings,SealImageTemplate sit) {
    	
        int x=	sit.getWidth()/2-circleSettings.getWidth()+circleSettings.getxDeviation();;
        int y=	sit.getHeight()/2-circleSettings.getHeight()+circleSettings.getyDeviation();
        g2d.setStroke(new BasicStroke(circleSettings.getFrameSize()));
        g2d.drawOval(x, y, circleSettings.getWidth() * 2, circleSettings.getHeight() * 2);
    }
    
   
    
    private static void drawArcFont4Oval(Graphics2D g2d, CircleTextSetting circleTextSetting,SealImageTemplate sit) {
        
        float radiusX = circleTextSetting.getWidth();
        float radiusY = circleTextSetting.getHeight();
        float radiusWidth =   sit.getWidth()/2+circleTextSetting.getxDeviation();
        float radiusHeight = sit.getHeight()/2+circleTextSetting.getyDeviation();;
        boolean   isTop=circleTextSetting.isTopWinding();
        int fontTextLen = circleTextSetting.getText().length();
        Font f = circleTextSetting.getFont();
        float totalArcAng = (float) (circleTextSetting.getFontSpace() * fontTextLen);
        float minRat = 0.90f;
        double startAngle = isTop ? -90f - totalArcAng / 2f : 90f - totalArcAng / 2f;
        double step = 0.5;
        int alCount = (int) Math.ceil(totalArcAng / step) + 1;
        double[] angleArr = new double[alCount];
        double[] arcLenArr = new double[alCount];
        int num = 0;
        double accArcLen = 0.0;
        angleArr[num] = startAngle;
        arcLenArr[num] = accArcLen;
        num++;
        double angR = startAngle * Math.PI / 180.0;
        double lastX = radiusX * Math.cos(angR) + radiusWidth;
        double lastY = radiusY * Math.sin(angR) + radiusHeight;
        for (double i = startAngle + step; num < alCount; i += step) {
            angR = i * Math.PI / 180.0;
            double x = radiusX * Math.cos(angR) + radiusWidth, y = radiusY * Math.sin(angR) + radiusHeight;
            accArcLen += Math.sqrt((lastX - x) * (lastX - x) + (lastY - y) * (lastY - y));
            angleArr[num] = i;
            arcLenArr[num] = accArcLen;
            lastX = x;
            lastY = y;
            num++;
        }
        double arcPer = accArcLen / fontTextLen;
        for (int i = 0; i < fontTextLen; i++) {
            double arcL = i * arcPer + arcPer / 2.0;
            double ang = 0.0;
            for (int p = 0; p < arcLenArr.length - 1; p++) {
                if (arcLenArr[p] <= arcL && arcL <= arcLenArr[p + 1]) {
                    ang = (arcL >= ((arcLenArr[p] + arcLenArr[p + 1]) / 2.0)) ? angleArr[p + 1] : angleArr[p];
                    break;
                }
            }
            angR = (ang * Math.PI / 180f);
            Float x = radiusX * (float) Math.cos(angR) + radiusWidth;
            Float y = radiusY * (float) Math.sin(angR) + radiusHeight;
            double qxang = Math.atan2(radiusY * Math.cos(angR), -radiusX * Math.sin(angR));
            double fxang = qxang + Math.PI / 2.0;
            int subIndex = isTop ? i : fontTextLen - 1 - i;
            String c =circleTextSetting.getText().substring(subIndex, subIndex + 1);
            FontMetrics fm = new JLabel().getFontMetrics(f);
            int w = fm.stringWidth(c), h = fm.getHeight();
            if (isTop) {
                x += h * minRat * (float) Math.cos(fxang);
                y += h * minRat * (float) Math.sin(fxang);
                x += -w / 2f * (float) Math.cos(qxang);
                y += -w / 2f * (float) Math.sin(qxang);
            } else {
                x += (h * minRat) * (float) Math.cos(fxang);
                y += (h * minRat) * (float) Math.sin(fxang);
                x += w / 2f * (float) Math.cos(qxang);
                y += w / 2f * (float) Math.sin(qxang);
            }
            AffineTransform transform;
            if (!isTop) {
                transform = AffineTransform.getRotateInstance(Math.PI + Math.PI / 2 );
            } else {
                transform = AffineTransform.getRotateInstance(Math.PI / 2  + Math.toRadians(8));
            }
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.scale(0.8, 1);
            if (isTop) {
                affineTransform.rotate(Math.toRadians((fxang * 180.0 / Math.PI - 90)), 0, 0);
            } else {
                affineTransform.rotate(Math.toRadians((fxang * 180.0 / Math.PI + 180 - 90)), 0, 0);
            }
            Font f2 = f.deriveFont(affineTransform);
            g2d.setFont(f2);
            g2d.drawString(c, x.intValue() , y.intValue() );
        }
    }
    
    
    private static void drawFont(Graphics2D g2d, TextSetting textSetting,SealImageTemplate sit) {
        Font f = textSetting.getFont();
        g2d.setFont(f);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D rectangle2D = f.getStringBounds(textSetting.getText(), context);
        g2d.drawString(textSetting.getText(), (int)(sit.getWidth()/2 - rectangle2D.getCenterX()+textSetting.getxDeviation()) , (int)(sit.getHeight()/2 - rectangle2D.getCenterY()+textSetting.getyDeviation()));
    }
    
    
}
