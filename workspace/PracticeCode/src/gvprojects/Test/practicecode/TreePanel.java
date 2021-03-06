package gvprojects.Test.practicecode;

import javax.swing.*;
import java.awt.*;

/**
 *  Example of how to draw recursively on a JPanel.
 * @author Zachary Kurmas
 */
// Created  3/8/13 at 11:35 AM
// (C) Zachary Kurmas 2013

public class TreePanel extends JPanel {

   @Override
   public void paintComponent(Graphics g) {
       drawTree(g, this, 9, getWidth()/2, 10, getWidth()/2, 75);
   }

   public static void drawTree(Graphics g, JPanel p, int depth, int x, int y, int width, int height) {
      if (depth == 0) {
         return;
      }

      int half_width = width /2 ;
      g.drawLine(x, y, x - half_width, y + height);
      drawTree(g, p, depth - 1, x - half_width, y + height, half_width, height);

      g.drawLine(x, y, x + half_width, y + height);
      drawTree(g, p, depth - 1, x + half_width, y + height, half_width, height);
   }
}
