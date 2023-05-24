package lich.tool.sealImage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.junit.Test;


public class TestPersonSealImage {
	@Test
	public void Test() throws SealImageException, Exception {
		String s="测试印章测试印章侧";
		for(int i=0;i<s.length();i++) {
			System.out.println("测试"+(i+1)+"字章:"+Base64.getEncoder().encodeToString(buildBytes(PersonSealImage.buildPersonSealImage(s.substring(i)))));
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
