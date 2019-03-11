package org.ysh.p2p.util;

import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	
	public static Frame getRootFrame(JPanel panel){
		return (Frame)SwingUtilities.getAncestorOfClass(Frame.class, panel);
	}
}
