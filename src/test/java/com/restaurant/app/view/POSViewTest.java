package com.restaurant.app.view;

import javax.swing.JFrame;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.restaurant.app.utils.UtilView;
import com.restaurant.app.view.caja.POSView;

import de.saxsys.javafx.test.JfxRunner;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import junit.framework.TestCase;

@RunWith(JfxRunner.class)
public class POSViewTest{
	
	@Test
	public void myTest() {
		System.out.println("sadasda");
		POSView pos = new POSView();
		
		UtilView.openWindows(getClass(), "POS", "algo");
		
	}
}
