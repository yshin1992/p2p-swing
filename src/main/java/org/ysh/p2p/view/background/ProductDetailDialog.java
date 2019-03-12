package org.ysh.p2p.view.background;

import java.awt.Frame;

import javax.swing.JDialog;

import org.ysh.p2p.model.Product;

public class ProductDetailDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5172460550833290402L;

	private Product product;
	
	public ProductDetailDialog(Product product,Frame owner){
		super(owner,true);
		this.product = product;
	}
	
}
