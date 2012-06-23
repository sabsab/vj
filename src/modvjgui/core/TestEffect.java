package modvjgui.core;
import modvjgui.core.IEventListener;
import modvjgui.core.Event;
import processing.core.*;
/*
import processing.opengl.*;
import codeanticode.glgraphics.*;
//import codeanticode.gsvideo.*;

/*
import javax.swing.Box;
*/
import javax.swing.BoxLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
/*
import javax.swing.JButton;
import javax.swing.JFileChooser;
*/
import javax.swing.event.EventListenerList;

import javax.swing.event.*;

/*
import java.awt.Dimension;
/*
import java.awt.event.*;

import java.io.File;

import java.util.Timer;
import java.util.TimerTask;
*/
public class TestEffect extends PApplet implements ChangeListener /*ActionListener*/
{
  
  
  PImage featuredImage = new PImage();
  
  /*
  PImage img = new PImage();
  PImage buffer = new PImage();
  */
  //GLTexture tex;
  /*
  GSMovie mov;
  */
  public JPanel controlPanel = new JPanel();
  /*
  Timer timer;
  */
  protected EventListenerList initListenerList = new EventListenerList();
  
  
  JSlider thresholdSlider;//, yPosSlider;
  
  float value = 50;
  
  
  /*
  protected EventListenerList selectListenerList = new EventListenerList();
  
  JButton openBtn, playBtn, pauseBtn, selectBtn;
  
  JFileChooser fc = new JFileChooser();
  
  
  //boolean selected = false;
  
  boolean buffered;
  */
  
  public TestEffect()
  {
  }
  
  public void setup()
  {
    size(160, 90);
    //size(160, 90, GLConstants.GLGRAPHICS);
    
    //size(160, 90, OPENGL);
    
    
    //frameRate(30);
    //noLoop();
    background(0);
    
    
    noLoop();
    
    
    //println("Test Effect!!!");
    
    
    //tex = new GLTexture(this, width, height);
    /*
    //tex.setPixelBufferSize(10);
    //tex.delPixelsWhenBufferFull(false);
    */
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
    
    
    
    
    thresholdSlider = new JSlider (JSlider.HORIZONTAL, 30, 70, 50);
    thresholdSlider.addChangeListener(this);
    controlPanel.add(thresholdSlider);
    /*
    yPosSlider = new JSlider (JSlider.HORIZONTAL, -100, 100, 0);
    yPosSlider.addChangeListener(this);
    controlPanel.add(yPosSlider);
    */
    fireInitEvent(new Event(this));
    
  }
  
  
  
  public void draw()
  {
    /*
    buffered = false;
    try 
    {
      // TODO: Don't have pixel data to copy to texture
      if(tex.putPixelsIntoTexture())
      {
        buffered = true;
        tex.getImage(buffer);
        
        background(0);
        tex.render(0, 0, width, height);
      }
    }
    catch (Exception e)
    {
      System.out.println(e);
    }
    
    if(buffered)
    {
      img = buffer.get(0, 0, buffer.width, buffer.height); 
    }
    */
    
    //println(featuredImage);
    
    //image(featuredImage, 0, 0);
    
    //tex.putPixelsIntoTexture(featuredImage);
    //tex.render(0, 0, width, height);
    
    
    //tex.putPixelsIntoTexture(featuredImage);
    //tex.render(0, 0, width, height);
    
    image(featuredImage, 0, 0, width, height);
    
  }
  
  
  /*
  public void movieEvent(GSMovie m) 
  {
    m.read();
    redraw();
  }
  
  void play()
  {
    //loop();
    mov.play();
    playBtn.setEnabled(false);
    pauseBtn.setEnabled(true);
  }
  
  void pause()
  {
    //noLoop();
    mov.pause();
    playBtn.setEnabled(true);
    pauseBtn.setEnabled(false);
  }
  
  
  void resetMovie()
  {
    if(mov != null)
    {
      mov.stop();
      mov.delete();
      mov = null;
    }
  }
  */
  
  public void clear()
  {
    /*
    resetMovie();
    tex = null;
    img = null;
    controlPanel.removeAll();
    controlPanel = null;
    */
  }
  
  /*
  void select()
  {
    //selected = true;
    //noLoop();
    //loop();
    
    play();
    selectBtn.setEnabled(false);
    openBtn.setEnabled(false);
    fireSelectEvent(new Event(this));
  }
  
  
  public void deselect()
  {
    pause();
    mov.jump(0);
    selectBtn.setEnabled(true);
    openBtn.setEnabled(true);
  }
  */
  
  public PImage getFeaturedImage(PImage img)
  {
    /*
    redraw();
    return img;
    */
    
    //println("img: " + img);
    /*
    featuredImage = createImage(width, height, RGB);
    featuredImage.loadPixels();
    for (int i = 0; i < featuredImage.pixels.length; i++) {
      featuredImage.pixels[i] = color(0); 
    }
    featuredImage.updatePixels();
    */
    
    //featuredImage = img.get(100, 50, img.width - 100, img.height - 50);
    //featuredImage = img.get(-50, -50, img.width, img.height);
    
    featuredImage = img;
    
    featuredImage.filter(THRESHOLD, value/100);
    
    
    
    redraw();
    
    return img;
    
  }
  
  
  
  
  /*
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    
    if(source == openBtn)
    {
      int returnVal = fc.showOpenDialog(this);
      if(returnVal == JFileChooser.APPROVE_OPTION) 
      {
        resetMovie();
        
        openBtn.setEnabled(false);
        playBtn.setEnabled(false);
        pauseBtn.setEnabled(false);
        selectBtn.setEnabled(false);
        
        File file = fc.getSelectedFile();
        mov = new GSMovie(this, file.getAbsolutePath());
        mov.setPixelDest(tex);
        mov.loop();
        mov.volume(0);
        //mov.frameRate(30);
        
        timer = new Timer();
        timer.schedule(new FootageReadyWait(), 1);
      }
    }
    else if(source == playBtn)
    {
      play();
    }
    else if(source == pauseBtn)
    {
      pause();
    }
    else if(source == selectBtn)
    {
      select();
    }
  }
  
  class FootageReadyWait extends TimerTask  
  {
    public void run()
    {
      timer.cancel();
      
      if(mov.ready())
      {
        mov.jump(0);
        pause();
        openBtn.setEnabled(true);
        playBtn.setEnabled(true);
        pauseBtn.setEnabled(false);
        selectBtn.setEnabled(true);
      }
      else
      {
        timer = new Timer();
        timer.schedule(new FootageReadyWait(), 1);
      }
    }
  }
  */
  
  
  public void stateChanged (ChangeEvent e) 
  {
    //channel.opacity = (float)(opacitySlider.getValue()) / 100;
    
    Object source = e.getSource();
    
    if(source == thresholdSlider)
    {
      value = (float)(thresholdSlider.getValue());
    }
    
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
  
  
  /*
  //------------------------------------------------------------------
  public void addSelectEventListener(IEventListener listener) 
  {
    selectListenerList.add(IEventListener.class, listener);
  }
  
  public void removeSelectEventListener(IEventListener listener) 
  {
    selectListenerList.remove(IEventListener.class, listener);
  }
  
  void fireSelectEvent(Event evt) 
  {
    Object[] listeners = selectListenerList.getListenerList();
    for (int i=0; i<listeners.length; i+=2) 
    {
      if (listeners[i] == IEventListener.class) 
      {
        ((IEventListener)listeners[i+1]).eventOccurred(evt);
       }
     }   
  }
  */
  //------------------------------------------------------------------
}

