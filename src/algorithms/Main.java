package algorithms;

import supportGUI.FramedDiamRace;
import supportGUI.Variables;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by nizar on 03/03/15.
 */
public class Main {
    private static int width = 0;
    private static int height = 0;
    private static String title = "Diameter Racer";
    private static String filename = "input.points";
    private static FramedDiamRace framedGUI;
    private static int nbPoints = 100;


    public static void main(String[] args) {

        int i = 0;

        label50:
        while (true) {
            if (i >= args.length) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        framedGUI = new FramedDiamRace(width, height, title, nbPoints);
                    }
                });
                Object var7 = Variables.lock;
                synchronized (Variables.lock) {
                    try {
                        Variables.lock.wait();
                    } catch (InterruptedException var4) {
                        var4.printStackTrace();
                    }
                }

                readFile();
                return;
            }

            if (args[i].charAt(0) == 45) {
                if (args[i + 1].charAt(0) == 45) {
                    System.err.println("Option " + args[i] + " expects an argument but received none");
                    return;
                }

                String e1;
                switch ((e1 = args[i]).hashCode()) {
                    case -2118335772:
                        if (e1.equals("-nbPoints")) {
                            try {
                                nbPoints = Integer.parseInt(args[i + 1]);
                            } catch (Exception var6) {
                                System.err.println("Invalid argument for option " + args[i] + ": Integer type expected");
                                return;
                            }

                            ++i;
                            break;
                        }
                    default:
                        break label50;
                }
            }

            ++i;
        }

        System.err.println("Unknown option " + args[i]);
    }

    public static void readFile() {
        ArrayList points = new ArrayList();

        try {
            BufferedReader e = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

            try {
                String line;
                while ((line = e.readLine()) != null) {
                    String[] coordinates = line.split("\\s+");
                    points.add(new Point(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
                }

                framedGUI.drawPoints(points);
                Object e1 = Variables.lock2;
                synchronized (Variables.lock2) {
                    Variables.lock2.notify();
                }
            } catch (IOException var16) {
                System.err.println("Exception: interrupted I/O.");
            } finally {
                try {
                    e.close();
                } catch (IOException var14) {
                    System.err.println("I/O exception: unable to close " + filename);
                }

            }
        } catch (FileNotFoundException var18) {
            System.err.println("Input file not found.");
        }

    }
}

