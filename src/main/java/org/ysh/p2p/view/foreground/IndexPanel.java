package org.ysh.p2p.view.foreground;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.ysh.p2p.util.ViewUtil;

public class IndexPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2174389360873618264L;

	public IndexPanel() throws IOException{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		//中间部分，主要是图片
		JLabel imgLabel = ViewUtil.makeImageLabel("/img/banner.jpg");
		imgLabel.setAlignmentX(LEFT_ALIGNMENT);
		this.add(imgLabel);
		
		
		//大数据部分
		JPanel dataPart = new JPanel();
		JPanel dataPart_left = new JPanel(new GridLayout(1,1));
		JLabel dataImgLab1= ViewUtil.makeImageLabel("/img/dashuju.png");
		dataPart_left.add(dataImgLab1);
		dataPart.add(dataPart_left);
		
		JPanel dataPart_right = new JPanel(new GridLayout(1,1));
		dataPart_right.add(ViewUtil.makeImageLabel("/img/shouyi.png"));
		dataPart.add(dataPart_right);
		
		dataPart.setAlignmentX(LEFT_ALIGNMENT);
		this.add(dataPart);
		
		JPanel dataPart2 = new JPanel();
		dataPart2.setLayout(new GridLayout(2,2));
		JLabel jLabel1 = new JLabel("客观信用，改变中国",JLabel.CENTER);
		dataPart2.add(jLabel1);
		
		JLabel jLabel3 = new JLabel("融资莫愁，投资莫愁",JLabel.CENTER);
		dataPart2.add(jLabel3);
		
		JLabel jLabel2 = new JLabel("国内领先的大数据综合服务提供商",JLabel.CENTER);
		dataPart2.add(jLabel2);
		
		JLabel jLabel4 = new JLabel("国资文化金融互联网借贷平台",JLabel.CENTER);
		dataPart2.add(jLabel4);
		
		dataPart2.setAlignmentX(LEFT_ALIGNMENT);
		this.add(dataPart2);
		
		
		//事件部分
		JPanel eventPart = new JPanel(new GridLayout(1,3));
		JPanel eventPart1 = new JPanel(new GridLayout(2,1));
		eventPart1.add(ViewUtil.makeImageLabel("/img/event1.png"));
		eventPart1.add(new JLabel("关于我们",JLabel.CENTER));
		eventPart.add(eventPart1);
		
		JPanel eventPart2 = new JPanel(new GridLayout(2,1));
		eventPart2.add(ViewUtil.makeImageLabel("/img/event2.png"));
		eventPart2.add(new JLabel("媒体报道",JLabel.CENTER));
		eventPart.add(eventPart2);
		
		JPanel eventPart3 = new JPanel(new GridLayout(2,1));
		eventPart3.add(ViewUtil.makeImageLabel("/img/event3.png"));
		eventPart3.add(new JLabel("行业新闻",JLabel.CENTER));
		eventPart.add(eventPart3);
		eventPart.setAlignmentX(LEFT_ALIGNMENT);
		this.add(eventPart);
	}
}
