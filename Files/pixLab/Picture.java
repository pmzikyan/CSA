import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author	Barbara Ericson ericson@cc.gatech.edu
 * 			and Petros Mzikyan (with some methods)
 * 
 * @since	2/3/2025
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }


    /** Uses a sinusodial function to shift an image to the left and right
     * @param amplitude The maximum shift of pixels
     * @return Wavy picture
     */
    public Picture wavy(int amplitude) {
        Pixel[][] pixels = this.getPixels2D();
        Picture result = new Picture(pixels.length, pixels[0].length);
        Pixel[][] resultPixels = result.getPixels2D();

        int height = pixels.length;
        int bellWidth = pixels.length / 6;

        for (int i = 0; i < pixels.length; i++) {
            double angle = 2 * Math.PI * 4.5 * i / height;
            int shift = (int) (25 * Math.sin(angle));
            for (int j = 0; j < pixels[0].length; j++) {
                resultPixels[i][(j + shift + pixels[0].length) % pixels[0].length].setColor
                        (pixels[i][j].getColor());
            }
        }

        return result;
    }

    /**	Uses a horizontal Gaussian curve to shift an image to the right
	 *	@param maxFactor Max height (shift) of curve in pixels
	 *	@return Liquified picture
	 */	
	public Picture liquify(int maxHeight) {
		Pixel[][] pixels = this.getPixels2D();
        Picture result = new Picture(pixels.length, pixels[0].length);
        Pixel[][] resultPixels = result.getPixels2D();
        
        int height = pixels.length;
        int bellWidth = pixels.length/6;

        for (int i = 0; i < pixels.length; i++)
        {
            double exponent = Math.pow(i - height / 2.0, 2) / (2.0 * Math.pow(bellWidth, 2));
            int rightShift = (int)(maxHeight * Math.exp(- exponent));
            for (int j = 0; j < pixels[0].length; j++)
            {
                resultPixels[i][(j + rightShift) % pixels[0].length].setColor
												(pixels[i][j].getColor());
			}
        }

        return result;
    }
    
    
    
    /** Method that shifts the pixels of an image shiftCount amount og pixels
     *  for steps amount of times (each step is around the same height)
     *
     * @param shiftCount The number of pixels to shift to the right
     * @param steps The number of steps
     * @return The picture with pixels shifted in stair steps
     */
    public Picture stairStep(int shiftCount, int steps)
    {
        Pixel[][] pixels = this.getPixels2D();
        Picture result = new Picture(pixels.length, pixels[0].length);
        Pixel[][] resultPixels = result.getPixels2D();

        int shift = 0;
        int stepSize = pixels.length/steps;
        int nextStep = stepSize;

//        int numRemainders = pixels.length%steps;
        int addRemainderInterval = pixels.length/pixels.length%steps;
        int remainderIndex = addRemainderInterval;
        int extra = 0;

        if (nextStep - stepSize <= remainderIndex && remainderIndex < nextStep) {
            extra = 1;
            remainderIndex += addRemainderInterval;
        }

        for (int i = 0; i < pixels.length; i++)
        {
            if (i == nextStep + extra) {
                shift += shiftCount;
                nextStep += stepSize;
                if (nextStep - stepSize <= remainderIndex && remainderIndex < nextStep) {
                    extra = 1;
                    remainderIndex += addRemainderInterval;
                }
                else
                    extra = 0;
            }
            for (int j = 0; j < pixels[0].length; j++) {
                resultPixels[i][(j + shift) % pixels[0].length].setColor
												(pixels[i][j].getColor());
			}
        }

        return result;
    }

    /** Method that swaps the left and right side of a picture
	* 
	* @return left-right swapped picture
	*/
	public Picture swapLeftRight()
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		for (int i = 0; i < pixels.length; i++)
		{
			for (int j = 0; j < pixels[0].length; j++)
			{
				resultPixels[i][(j + pixels[0].length/2) % pixels[0].length]
					= pixels[i][j];
			}
		}
		return result;
	}
	
	/** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
	*/
	public Picture enhance(int size)
	{
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		
		for (int i = 0; i < pixels.length; i++)
		{
			for (int j = 0; j < pixels[0].length; j++)
			{
				int pixelCount, redTotal, greenTotal, blueTotal;
				pixelCount = redTotal = greenTotal = blueTotal = 0;
				
				for (int a = i - size/2; a < i + size/2 && a < 
												pixels.length; a++)
				{
					if (a < 0)
						a = 0;
					for (int b = j - size/2; b < j + size/2 && b < 
												pixels[0].length; b++)
					{
						if (b < 0)
							b = 0;

						redTotal += pixels[a][b].getRed();
						greenTotal += pixels[a][b].getGreen();
						blueTotal += pixels[a][b].getBlue();
						pixelCount++;
					}
				}
				int red = 2 * pixels[i][j].getRed() - redTotal/pixelCount;
				int green = 2 * pixels[i][j].getGreen() - greenTotal/pixelCount;
				int blue = 2 * pixels[i][j].getBlue() - blueTotal/pixelCount;
				
				if (red < 0)
					red = 0;
				else if (red > 255)
					red = 255;
				
				if (green < 0)
					green = 0;
				else if (green > 255)
					green = 255;
				
				if (blue < 0)
					blue = 0;
				else if (blue > 255)
					blue = 255;
				
				resultPixels[i][j].setColor(new Color(red, green, blue));
			}
		}
		
		return result;
	}
	
	
	/** Method that blurs the picture
	 * Precondition: size has to be an odd int that is bigger than 0
	 * 
	 * @param size Blur size, greater is more blur
     * @return Blurred picture
	 */
	public Picture blur(int size) {
		Pixel[][] pixels = this.getPixels2D();
		Picture result = new Picture(pixels.length, pixels[0].length);
		Pixel[][] resultPixels = result.getPixels2D();
		
		
		for (int i = 0; i < pixels.length; i++)
		{
			for (int j = 0; j < pixels[0].length; j++)
			{
				int pixelCount, redTotal, greenTotal, blueTotal;
				pixelCount = redTotal = greenTotal = blueTotal = 0;
				
				for (int a = i - size/2; a < i + size/2 && a < 
												pixels.length; a++)
				{
					if (a < 0)
						a = 0;
					for (int b = j - size/2; b < j + size/2 && b < 
												pixels[0].length; b++)
					{
						if (b < 0)
							b = 0;

						redTotal += pixels[a][b].getRed();
						greenTotal += pixels[a][b].getGreen();
						blueTotal += pixels[a][b].getBlue();
						pixelCount++;
					}
				}
				resultPixels[i][j].setColor(new Color(redTotal/pixelCount,
							greenTotal/pixelCount, blueTotal/pixelCount));
			}
		}
		
		return result;
	}
  
	/** To pixelate by dividing area into size x size.
	 * Precondition: size has to be an odd int that is bigger than 0
	 * @param size Side length of square area to pixelate.
	 */
	public void pixelate(int size) {
		Pixel[][] pixels = this.getPixels2D();
		
		for (int i = size/2; i < pixels.length + size/2; i += size - 1)
		{
			for (int j = size/2; j < pixels[0].length + size/2; j += size - 1)
			{
				int pixelCount, redTotal, greenTotal, blueTotal;
				pixelCount = redTotal = greenTotal = blueTotal = 0;
				
				for (int a = i - size/2; a < i + size/2 && a < 
												pixels.length; a++)
				{
					for (int b = j - size/2; b < b + size/2 && b < 
												pixels[0].length; b++)
					{
						

						redTotal += pixels[a][b].getRed();
						greenTotal += pixels[a][b].getGreen();
						blueTotal += pixels[a][b].getBlue();
						pixelCount++;
					}
				}
				
				Color averageColor = new Color(redTotal/pixelCount, 
							greenTotal/pixelCount, blueTotal/pixelCount);
				
				for (int a = i - size/2; a < i + size/2 && a < 
												pixels.length; a++)
				{
					if (a < 0)
						a = 0;
					for (int b = j - size/2; b < j + size/2 && b < 
												pixels[0].length; b++)
					{
						if (b < 0)
							b = 0;
						
						pixels[a][b].setColor(averageColor);
					}
				}
			}
		}
	}
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method to set everything besides blue to 0 */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to invert the colors */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
        pixelObj.setBlue(255 - pixelObj.getBlue());
      }
    }
  }
  
  /** Method to set make an image grayscale by averaging the red, green, and blue */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int value = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3;
        pixelObj.setRed(value);
        pixelObj.setGreen(value);
        pixelObj.setBlue(value);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
