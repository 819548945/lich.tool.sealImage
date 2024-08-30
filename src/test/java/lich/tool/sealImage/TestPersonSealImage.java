package lich.tool.sealImage;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.junit.Test;


public class TestPersonSealImage {
	@Test
	public void Test() throws SealImageException, Exception {
		String s="张三三三三三三三印";
		PersonSealImageTool.setDefaultFont(new Font("CESI_XBS_GB18030", Font.BOLD, 300));
		for(int i=0;i<s.length();i++) {
			System.out.println("测试"+(i+1)+"字章:"+Base64.getEncoder().encodeToString(buildBytes(PersonSealImageTool.buildPersonSealImage(s.substring(i)))));
		}
	}


    /**
     * 生成印章图片的byte数组
     *
     * @param image BufferedImage对象
     * @return byte数组
     * @throws IOException 异常
     */
    public static byte[] buildBytes(BufferedImage image) throws Exception {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //bufferedImage转为byte数组
        ImageIO.write(image, "png", outStream);
        return outStream.toByteArray();
        
    }
}
