package lich.tool.sealImage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.junit.Test;

import lich.tool.sealImage.SealImageTemplate.ImageSetting;


public class TestSealImage {
	@Test
	public void Test() throws SealImageException, Exception {
		String img="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";
		String s="南点聚科技合伙企业有限合伙";
		String code="A8457878SDFHSD432WQE";
		
		
		String s1="46010600046365";
		String s2="ᠮᠣᠩᠭᠣᠯ ᠪᠢᠴᠢᠭ ᠦᠨ ᠤᠯᠤᠰ ᠤᠨ ᠪᠠᠷᠢᠮᠵᠢᠶ᠎ᠠ ᠺᠣᠳ᠋";
		SealImageTemplate sit=	SealImageTemplate.getDefaultTemplate(s,s1);
		sit.getTextSettings().clear();
		 BufferedImage image = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(img)));
		sit.addImageSettings(new ImageSetting(image,100,100,0,0));
		//List circleTextSettings=	sit.getCircleTextSettings();
	//	circleTextSettings.clear();
	//	circleTextSettings.add(new CircleTextSetting(s).setFont(new Font("CESI_FS_GB18030", Font.BOLD, 40)).setFontSpace(20));
	//	circleTextSettings.add(new CircleTextSetting(s2).setFont(new Font("", Font.BOLD, 30)).setFontSpace(7).setWidth(100).setHeight(100));
	//	circleTextSettings.add(new CircleTextSetting(s1).setTopWinding(false).setFontSpace(5).setFont(new Font("CESI_SS_GB18030", Font.BOLD, 20)).setFontSpace(5));
		
		//sit.addRectSettings(new FigureSetting(1,150,50,0,0));
		
		//SealImageTemplate sit=	SealImageTemplate.getEllipseDefaultTemplate(s,code);
		System.out.println(Base64.getEncoder().encodeToString(TestPersonSealImage.buildBytes(SealImageTool.buildSealImage(sit))));
	}



}