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
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("images/caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("images/temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
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
    Picture swan = new Picture("images/swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
	  //red pic file, t=run code on it and tun a tester explore on it (like zero blue**
	  
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
    //testKeepOnlyBlue();
    //testNegate();
    //testgrayScale();
    //testpixelate();
    //testBlur();
    //testEnhance();
    //testSwapLeftRight();
    //teststairStep();
    //testLiquify();
    testWavy();
  }
  
  // banan here:
  
  public static void testKeepOnlyBlue(){
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.keepOnlyBlue();
	  beach.explore();
  }
  
  public static void testNegate(){
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.negate();
	  beach.explore();
  } 
  
  public static void testgrayScale(){
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	  beach.grayscale();
	  beach.explore();
  } 
  
  public static void testpixelate(){
	  Picture beach = new Picture("images/swan.jpg");
	  beach.explore();
	  beach.pixelate(11);
	  beach.explore();
  }
  
  public static void testBlur(){
	  Picture beach = new Picture("images/beach.jpg");
	  beach.explore();
	 Picture newPic = beach.blur(11);
	  newPic.explore();
  }
  
  public static void testEnhance(){
	  Picture beach = new Picture("images/water.jpg");
	  beach.explore();
	 Picture newPic = beach.enhance(11);
	  newPic.explore();
  }
  
  public static void testSwapLeftRight(){
	  Picture beach = new Picture("images/redMotorcycle.jpg");
	  beach.explore();
	 Picture newPic = beach.swapLeftRight();
	  newPic.explore();
  }
  
  public static void teststairStep(){
	  Picture beach = new Picture("images/redMotorcycle.jpg");
	  beach.explore();
	  System.out.println("rows:" + beach.getWidth() + "height: " + beach.getHeight());
	 Picture newPic = beach.stairStep(1,400);
	  newPic.explore();
  }
  
  public static void testLiquify(){
	  Picture beach = new Picture("images/swan.jpg");
	  beach.explore();
	 Picture newPic = beach.liquify(100);
	  newPic.explore();
  }
  
  public static void testWavy(){
	  Picture beach = new Picture("images/swan.jpg");
	  beach.explore();
	 Picture newPic = beach.wavy(20);
	 System.out.println("after methd wavy");
	  newPic.explore();
  }
  
}
