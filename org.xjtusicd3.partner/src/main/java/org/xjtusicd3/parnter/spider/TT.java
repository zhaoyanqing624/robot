package org.xjtusicd3.parnter.spider;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

public class TT {
	public static void main(String[] args) throws Exception {
	 Robot robot = new Robot();
	 Runtime.getRuntime().exec("dxdiag");
	 robot.delay(2500);
	 keyPressWithCtrl(robot, KeyEvent.VK_S);
	 robot.delay(13000);
	 keyPressWithAlt(robot, KeyEvent.VK_S);
	 keyPressWithCtrl(robot, KeyEvent.VK_X);
	}
	 public static void keyPressWithCtrl(Robot r, int key) {
		 r.keyPress(KeyEvent.VK_CONTROL);
		 r.keyPress(key);
		 r.keyRelease(key);
		 r.keyRelease(KeyEvent.VK_CONTROL);
		 r.delay(100);
	 }
	 
	 public static void keyPressWithAlt(Robot r, int key) {
		 r.keyPress(KeyEvent.VK_ALT);
		 r.keyPress(key);
		 r.keyRelease(key);
		 r.keyRelease(KeyEvent.VK_ALT);
		 r.delay(100);
	}
	 


}
