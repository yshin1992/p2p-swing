package org.ysh.p2p.view.foreground;

import java.io.IOException;

import javax.swing.JPanel;

import org.ysh.p2p.util.ViewUtil;

public class BigDataPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2000977553482269549L;

	public BigDataPanel() throws IOException{
		this.add(ViewUtil.makeImageLabel("/img/bigdata.jpg"));
	}
	
}
