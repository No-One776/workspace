package gvprojects.mm.shared;

import gvprojects.mm.view.CardImageFactory;
import gvprojects.mm.view.RecipeImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class SampleGUI {

   private JFrame mainWindow;
   private JPanel toppings, orders;
   private JTextArea recipes;

   /**********************************************************************
    * For your project, you want to place the following class in a separate
    * file under your "view" (sub)package. For this example, I put the class
    * as an inner class of SampleGUI to make the example "self-contained"
    * 
    * When you run this sample but do not see the images of your cards,
    * see the detailed error message in the console output. The common problem
    * is incorrect directory location of the images. The exception error message
    * should give you a hint where to place the images.
    **********************************************************************/
   /*--- BEGIN MyCard.java ---*/
   private class MyCard extends JPanel
   {
      private BufferedImage front, back, current;
      private Dimension dim;
      
      public MyCard(BufferedImage frontImg, BufferedImage backImg)
      {
         front = frontImg;
         back = backImg;
         /* make the card to face up by default */
         current = front;
         dim = new Dimension (front.getWidth(), front.getHeight());
      }
      
      @Override
      protected void paintComponent (Graphics g)
      {
         super.paintComponent(g);
         g.drawImage(current, 
               0, 0, /* coordinate of the upper-left corner */
               Color.WHITE, /* background color */
               null  /* no image observer */);
      }
      
      public void faceUp()
      {
         current = front;
      }
      
      public void faceDown()
      {
         current = back;
      }
      
      @Override
      public Dimension getMinimumSize()
      {
         return dim;
      }
      
      @Override
      public Dimension getPreferredSize()
      {
         return dim;
      }
   }
   /*--- END MyCard.java ---*/
   
   
   
   public SampleGUI()
   {
      mainWindow = new JFrame("BufferedImage Example");
      toppings = new JPanel();
      toppings.setLayout(new FlowLayout());
      toppings.setBorder(BorderFactory.createTitledBorder("Topping Cards"));
      BufferedImage[] tops = CardImageFactory.createToppingCards();
      for (BufferedImage bi : tops)
      {
         /* The backface image is set to null, in your project you
          * want to use your own image.
          * 
          * Look at the code at the beginning ot Topping.java and use
          * ImageIO.read() to load in your own image
          */
         MyCard c = new MyCard (bi, null);
         toppings.add(c);
      }
      
      orders = new JPanel();
      final int N_toppings = Topping.values().length - 1;
      orders.setBorder(BorderFactory.createTitledBorder("Order Cards"));
      orders.setLayout(new GridLayout(N_toppings, 0));
      /* createRecipeCard returns an ARRAY of (LIST of images) */
      List<RecipeImage>[] imageSet = CardImageFactory.createRecipeCards();
      for (List<RecipeImage> oneSet : imageSet) {
         for (RecipeImage img : oneSet)
         {
            /* Replace null with your won image */
            orders.add(new MyCard(img.getImage(), null));
         }
      }
      
      recipes = new JTextArea();
      recipes.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
      for (Topping t : Topping.values())
      {
         Set<String> oneSet = RecipeFactory.generateSet(t);
         recipes.append("Recipe set for " + t.name() + "\n");
         for (String s : oneSet)
            recipes.append(s + "\n");
      }
      
      mainWindow.add(toppings, BorderLayout.NORTH);
      /* place the order cards and recipe set in a scroll pane and
       * set the physical size of the window (instead of calling mainWindow.pack())
       */
      mainWindow.add(new JScrollPane(orders), BorderLayout.CENTER);
      mainWindow.add(new JScrollPane(recipes), BorderLayout.EAST);
      mainWindow.setSize(new Dimension (800,600));
      mainWindow.setVisible(true);
      mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   
   
   /* For your project, the main method will be somewhere else */
   public static void main(String[] args)
   {
      SwingUtilities.invokeLater(new Runnable() {
         
         @Override
         public void run() {
            new SampleGUI();
         }
      });
   }
}
