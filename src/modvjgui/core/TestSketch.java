package modvjgui.core;
import modvjgui.core.Effect;
import modvjgui.core.IEventListener;
import modvjgui.core.Event;
import processing.core.*;
import processing.opengl.*;
import codeanticode.glgraphics.*;
import codeanticode.gsvideo.*;


import javax.swing.BoxLayout;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import javax.swing.event.EventListenerList;

import java.awt.event.*;

import java.io.File;

import java.util.Timer;
import java.util.TimerTask;

public class TestSketch extends PApplet implements ActionListener
{
  
  PImage img = new PImage();
  PImage buffer = new PImage();
  GLTexture tex;
  GSMovie mov;
  public JPanel controlPanel = new JPanel();
  Timer timer;
  protected EventListenerList initListenerList = new EventListenerList();
  protected EventListenerList selectListenerList = new EventListenerList();
  
  JButton openBtn, playBtn, pauseBtn, selectBtn;
  
  protected JFileChooser fc = new JFileChooser();
  
  
  //boolean selected = false;
  
  boolean buffered;
  
  
  public Effect[] effectQueue;// = new Effect[0];
  
  
  public TestSketch()
  {
  }
  
  public void setup()
  {
    size(160, 90, GLConstants.GLGRAPHICS);
    //frameRate(30);
    noLoop();
    background(0);
    
    
    tex = new GLTexture(this);
    //tex.setPixelBufferSize(10);
    //tex.delPixelsWhenBufferFull(false);
    
    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
    
    
    openBtn = new JButton("Open");
    //openBtn.setEnabled(false);
    openBtn.addActionListener(this);
    controlPanel.add(openBtn);
    
    
    playBtn = new JButton("Play");
    playBtn.setEnabled(false);
    playBtn.addActionListener(this);
    controlPanel.add(playBtn);
    
    
    pauseBtn = new JButton("Pause");
    pauseBtn.setEnabled(false);
    pauseBtn.addActionListener(this);
    controlPanel.add(pauseBtn);
    
    selectBtn = new JButton("Select");
    selectBtn.setEnabled(false);
    selectBtn.addActionListener(this);
    controlPanel.add(selectBtn);
    
    
    fireInitEvent(new Event(this));
  }
  
  
  
  public void draw()
  {
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
    
    //println("---> " + effectQueue.length);
    
    for(int i = 0; i < effectQueue.length; i++)
    {
      //effectQueue[i] = temp[i];
      
      img = effectQueue[i].getFeaturedImage(img);
    }
    
  }
  
  /*
  public void stop() 
  {
    resetMovie();
  }
  */
  
  
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
  
  
  public void clear()
  {
    resetMovie();
    /*
    if(mov != null)
    {
      mov.stop();
      mov.delete();
      mov = null;
    }
    */
    tex = null;
    img = null;
    controlPanel.removeAll();
    controlPanel = null;
  }
  
  
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
    //selected = false;
    //loop();
    //noLoop();
    
    //img = new PImage();
    
    pause();
    mov.jump(0);
    selectBtn.setEnabled(true);
    openBtn.setEnabled(true);
  }
  
  
  public PImage getImage()
  {
    redraw();
    return img;
  }
  
  
  
  
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    
    if(source == openBtn)
    {
      int returnVal = fc.showOpenDialog(this);
      if(returnVal == JFileChooser.APPROVE_OPTION) 
      {
        resetMovie();
        /*
        if(mov != null)
        {
          //pause();
          mov.stop();
          mov.delete();
          mov = null;
        }
        */
        
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
  //------------------------------------------------------------------
}

