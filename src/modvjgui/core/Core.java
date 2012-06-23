
package modvjgui.core;
import processing.core.*;
import codeanticode.glgraphics.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.swing.event.EventListenerList;



public class Core extends PApplet
{
  
  protected EventListenerList initListenerList = new EventListenerList();
  GLTexture baseTexture, blendTexture, resultTexture;
  PImage bg;
  public Channel[] channels = new Channel[0];
  GLTextureFilter textureFilter;
  
  public Core()
  {
  }
  
  public void setup()
  {
    //size(1024, 576, GLConstants.GLGRAPHICS);
    //size(640, 360, GLConstants.GLGRAPHICS);
    //size(480, 270, GLConstants.GLGRAPHICS);
    size(320, 180, GLConstants.GLGRAPHICS);
    
    frameRate(30);
    
    baseTexture = new GLTexture(this);
    blendTexture = new GLTexture(this);
    resultTexture = new GLTexture(this, width, height);
    
    bg = createImage(width, height, RGB);
    bg.loadPixels();
    for (int i = 0; i < bg.pixels.length; i++) {
      bg.pixels[i] = color(0); 
    }
    bg.updatePixels();
    
    URL realPath = getClass().getResource("/blend/BlendPremultiplied.xml");
    URL realPathGlSl = getClass().getResource("/blend/BlendPremultiplied.glsl");
    String tmpDir = System.getProperty("java.io.tmpdir");
    try {
        File oFile = new File (realPath.toURI());
        File oDest = new File (tmpDir + "BlendPremultiplied.xml");
        //oDest.createNewFile();
       // System.out.println(oDest.toPath());
        if (!oDest.exists()){
            copyfile(oFile.getAbsolutePath(), oDest.getAbsolutePath());
            copyfile(
                new File (realPathGlSl.toURI()).getAbsolutePath(), 
                new File (tmpDir + "BlendPremultiplied.glsl").getAbsolutePath()
            );
           // Files. (oFile.getAbsolutePath(), oDest.getAbsolutePath());
           // Files.copy (new File (realPathGlSl.toURI()).toPath(), new File (tmpDir + "BlendPremultiplied.glsl").toPath());
        }
        
        
    }
    catch (Exception oException){
         System.out.println(oException.getMessage());
    }
    //realPath.
    //new GLTextureFilter(this, )
    textureFilter = new GLTextureFilter(this, tmpDir + "BlendPremultiplied.xml");

    fireInitEvent(new Event(this));
 

  }
  private  void copyfile(String srFile, String dtFile){
  try{
  File f1 = new File(srFile);
  File f2 = new File(dtFile);
  InputStream in = new FileInputStream(f1);
  
  //For Append the file.
//  OutputStream out = new FileOutputStream(f2,true);

  //For Overwrite the file.
  OutputStream out = new FileOutputStream(f2);

  byte[] buf = new byte[1024];
  int len;
  while ((len = in.read(buf)) > 0){
  out.write(buf, 0, len);
  }
  in.close();
  out.close();
  System.out.println("File copied.");
  }
  catch(FileNotFoundException ex){
  System.out.println(ex.getMessage() + " in the specified directory.");
  System.exit(0);
  }
  catch(IOException e){
  System.out.println(e.getMessage());  
  }
  }
  public void draw()
  {
    background(0);
    
    //println(channels.length);
    
    for(int i = 0; i < channels.length; i++)
    {
      if(i == 0)
      {
        baseTexture.putPixelsIntoTexture(bg);
      }
      else
      {
        baseTexture.copy(resultTexture);
      }
      
      IFootageComponent sketchBlend = channels[i].getSketchSelected();
      
      if(sketchBlend != null)
      {
        blendTexture.putPixelsIntoTexture(sketchBlend.getImage());
        textureFilter.setParameterValue("Opacity", channels[i].opacity);
        textureFilter.apply(new GLTexture[]{baseTexture, blendTexture}, resultTexture);
      }
      else
      {
        resultTexture.copy(baseTexture);
      }
    }
    resultTexture.render(0, 0);
    
  }
  
  //------------------------------------------------------------------
  public void addInitEventListener(IEventListener listener) 
  {
    initListenerList.add(IEventListener.class, listener);
  }
  
  public void removeInitEventListener(IEventListener listener) 
  {
    initListenerList.remove(IEventListener.class, listener);
  }
  
  void fireInitEvent(Event evt) 
  {
    Object[] listeners = initListenerList.getListenerList();
    for (int i=0; i<listeners.length; i+=2) 
    {
      if (listeners[i] == IEventListener.class) 
      {
        ((IEventListener)listeners[i+1]).eventOccurred(evt);
       }
     }   
  }
}
