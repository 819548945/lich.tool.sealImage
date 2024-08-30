package lich.tool.sealImage;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.junit.Test;

import lich.tool.sealImage.SealImageTemplate.CircleTextSetting;
import lich.tool.sealImage.SealImageTemplate.FigureSetting;
import lich.tool.sealImage.SealImageTemplate.ImageSetting;
import lich.tool.sealImage.SealImageTemplate.TextSetting;

public class TestEllipseSealImage {
	@Test
	public void Test() throws SealImageException, Exception{
		String s="北京xx有限责任公司";
		String s1="123456789012345";
		SealImageTemplate sit=	SealImageTemplate.getEllipseDefaultTemplate(s,s1);
		sit.addRectSettings(new FigureSetting().setFrameSize(2).setHeight(28).setWidth(150).setyDeviation(11));
		System.out.println(Base64.getEncoder().encodeToString(TestPersonSealImage.buildBytes(SealImageTool.buildSealImage(sit))));
		}
}
