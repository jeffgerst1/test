package Shapes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class JGSHAPE extends Shape { // Changed to concrete class
    private Color color; // Color of the hexagon
    private int side;    // Side length of the hexagon

    // Constructor to accept the side length
    public JGSHAPE(int side) {
        this.side = side; // Store the side length of the hexagon
        this.color = Color.BLUE; // Default color
    }

    @Override
    public double getPerimiter() {
        return 6 * side; // Perimeter of the hexagon
    }

    @Override
    public double getArea() {
        return (3 * Math.sqrt(3) / 2) * side * side; // Area of the hexagon
    }

    // Method to get the shape's color
    public Color getColor() {
        return color;
    }

    // Method to set the shape's color
    public void setColor(Color color) {
        this.color = color; // Set the color
    }

    @Override
    public String getAuthor() {
        return "Jeff"; // Author name
    }

    protected void addAuthorText(Graphics2D g2d, int width, int height) throws Exception {
        String text = getAuthor(); // The text to be centered

        // Set font and size
        Font font = new Font("Arial", Font.BOLD, 24);
        g2d.setColor(Color.black);
        g2d.setFont(font);

        // Get the FontMetrics object to calculate the position
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        // Calculate the coordinates to center the text
        int x = (width - textWidth) / 2;
        int y = (height - textHeight) / 2 + fm.getAscent();

        // Draw the text centered
        g2d.drawString(text, x, y);
    }

    @Override
    public void renderAsJpeg(File fileToJpeg) throws Exception {
        int width = 256;  // Image width
        int height = 256; // Image height

        // Create a buffered image with dimensions 256x256
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Get the graphics object
        Graphics2D g2d = image.createGraphics();

        // Set background color (white)
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        // Set color for the hexagon using the instance's color
        g2d.setColor(this.getColor()); // Use the instance's color

        // Define the coordinates for the hexagon
        int[] xPoints = new int[6];
        int[] yPoints = new int[6];

        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (width / 2 + side * Math.cos(2 * Math.PI * i / 6));
            yPoints[i] = (int) (height / 2 + side * Math.sin(2 * Math.PI * i / 6));
        }

        // Draw the hexagon
        g2d.fillPolygon(xPoints, yPoints, 6);

        // Set font and color for the author's name
        g2d.setColor(Color.BLACK); // Set color for the text
        g2d.setFont(new Font("Arial", Font.BOLD, 24)); // Set font style and size

        // Call the method to add the author's name after drawing the hexagon
        addAuthorText(g2d, width, height);

        // Dispose the graphics object
        g2d.dispose();

        // Save the image as a JPEG file
        ImageIO.write(image, "jpeg", fileToJpeg);
        System.out.println("Hexagon image saved as " + fileToJpeg.getName());
    }
}
