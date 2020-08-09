package com.restaurant.app.printer;

import com.restaurant.app.printer.pos.PrinterService;
import de.saxsys.javafx.test.JfxRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JfxRunner.class)
public class FirtsStep {
	
	@Test
	public void myTest() {
		PrinterService printerService = new PrinterService();

		System.out.println(printerService.getPrinters());

		//print some stuff. Change the printer name to your thermal printer name.
		printerService.printString("XP-58", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n");

		// cut that paper!
		byte[] cutP = new byte[] { 0x1d, 'V', 1 };

		printerService.printBytes("XP-58", cutP);
	}
}
