package org.example.maus;

import javax.swing.*;
import java.awt.AWTException;
import java.awt.Container;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

public class MausWerk {

    static final int COLUMN_NEW_LINE = 8;

    int moveTimes = 3;
    int currentMove = 0;
    Robot robo;
    JFrame frm = null;
    JTextArea txt = null;

    int counter = 0;

    public MausWerk(String[] args) {
        System.out.printf("%s ---- Start MAIN\n", this.getClass().getSimpleName());

        int mx = 892;
        int mi = 888;
        try {
            robo = new Robot();
        } catch (AWTException e) {
            // TODO Auto-generated catch block
            System.err.println("Unable to create and retrieve AWT Robot - something is wrong here!");
            e.printStackTrace(System.err);
            throw new RuntimeException(e);
        }

        System.out.println("Mx " + mx + "  Min " + mi);
//		for (int i = mi; i <= mx; i++) {
//			int j = intRotate(i, mx, mi, 1);
//			System.out.println("i " + i + "  j " + j);
//		}
        frm = new JFrame ("Mouse move close to stop 1.2");
        frm.setSize(650,600);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        txt = new JTextArea("Loading ...", 3, 20);
        Container cnt = frm.getContentPane();
        cnt.setLayout(null);
        cnt.add(txt);
        txt.setBounds(20,25,200, 40);
        PointerInfo pointerInfo = MouseInfo.getPointerInfo();
        Point coordinate = null;

        robo.mouseMove(0, 0);
        nodOff(1000);
        int x, y;
        int delta = 1;
        while (true) {
            delta = (int)(1 + Math.random() * 5);
            pointerInfo = MouseInfo.getPointerInfo();

            if ( pointerInfo == null) {
                txt.setText("*POINTER?*");
                System.out.print("*POINTER?*");
                nodOff((int) (2000 + Math.random() * 10000));
            }
            else {
                coordinate = pointerInfo.getLocation();

                x = (int) coordinate.getX();
                y = (int) coordinate.getY();
                currentMove = intRotate(currentMove, moveTimes, 0, 1);
                if (currentMove % 4 == 0) {
                    x = (int) (coordinate.getX() + delta);
                } else if (currentMove % 4 == 1) {
                    x = (int) (coordinate.getX() - delta);
                } else if (currentMove % 4 == 2) {
                    y = (int) (coordinate.getY() + delta);
                } else if (currentMove % 4 == 3) {
                    y = (int) (coordinate.getY() - delta);
                }

                final String dumpFormat = String.format("x %-4d, y %-4d", x, y );
                System.out.print(dumpFormat);
                counter++;
                if ( counter % 8 == 0 ) {
                    System.out.println();
                }
                else {
                    System.out.print("; ");
                }
                txt.setText(dumpFormat);
                robo.mouseMove(x, y);
                // SoundUtils.toneit((int)(1500 + Math.random() * 1500), 50);
                nodOff((int) (4000 + Math.random() * 5000));

            }
        }

    }

    int intRotate(int i, int max, int min, int by) {
        int j = i + by;
        if (j < min)
            j = min;// edge case to check
        if (j > max) {
            // TODO other cases like negative and non 1 increment
            j = min;
        }
        return j;
    }

    public static void nodOff(int s) {
        try {
            Thread.sleep(s);
        } catch (Exception ignore) {
        }
    }

    public static void main(String[] args) {
        // SoundUtils.toneit(900, 140);
        new MausWerk(args);
    }
}
