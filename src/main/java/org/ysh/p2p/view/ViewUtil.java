package org.ysh.p2p.view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Swing视图工具类
 * @author yshin1992
 *
 */
public class ViewUtil {

	public static JLabel makeImageLabel(String imgPath) throws IOException{
		BufferedImage read = ImageIO.read(ViewUtil.class.getClass().getResourceAsStream(imgPath));
		return new JLabel(new ImageIcon(read));
	}
	
	public static BufferedImage getImage(String imgPath) throws IOException{
		BufferedImage read = ImageIO.read(ViewUtil.class.getClass().getResourceAsStream(imgPath));
		return read;
	}
}
