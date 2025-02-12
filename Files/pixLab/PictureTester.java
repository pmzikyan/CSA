/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  private static String getRandomImage() {
	  String[] images = {"images/arch.jpg", "images/beach.jpg", 
		  "images/butterfly1.jpg", "images/gorge.jpg", "images/redMotorcycle.jpg",
		  "images/seagull.jpg", "images/swan.jpg", "images/temple.jpg", "images/water.jpg"};
	  return images[(int)(Math.random() * images.length)];
  }

  /** Method to test liquify */
  public static void testLiquify(int maxHeight)
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    Picture newImage = image.liquify(maxHeight);
    newImage.explore();
  }

  /** Method to test stairStep */
  public static void testStairStep(int shiftCount, int steps)
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    Picture newImage = image.stairStep(shiftCount, steps);
    newImage.explore();
  }

  /** Method to test swapLeftRight */
  public static void testSwapLeftRight()
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    Picture newImage = image.swapLeftRight();
    newImage.explore();
  }
  
  /** Method to test enhance */
  public static void testEnhance(int size)
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    Picture newImage = image.enhance(size);
    newImage.explore();
  }
  
  /** Method to test blur */
  public static void testBlur(int size)
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    Picture newImage = image.blur(size);
    newImage.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate(int size)
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    image.pixelate(size);
    image.explore();
  }
  
  /** Method to test grayscale */
  public static void testGrayscale()
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    image.grayscale();
    image.explore();
  }
  
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    image.zeroBlue();
    image.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    image.keepOnlyBlue();
    image.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    image.mirrorVertical();
    image.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture image = new Picture(getRandomImage());
    image.explore();
    image.mirrorTemple();
    image.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("images/640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture image = new Picture(getRandomImage());
    image.edgeDetection(10);
    image.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
    
    //testPixelate(57); testPixelate(75); testPixelate(99);  
    //testBlur(7); testBlur(21); testBlur(45);
    //testEnhance(21);
    //testSwapLeftRight();
    //testStairStep(30, 3); testStairStep(60, 10);
    
    /*Picture image = new Picture("images/beach.jpg");
    image.explore();
    Picture newImage = image.stairStep(1, 480);
    newImage.explore();*/
    
    testLiquify(100);
  }
}
