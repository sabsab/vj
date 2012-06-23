package modvjgui.core;
import processing.core.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.event.EventListenerList;


public class Effect extends JPanel
{
  //TestSketch testSketch;
  
  TestEffect testEffect;
  
  protected EventListenerList initListenerList;//, selectListenerList, deselectListenerList;
  
  public Effect()
  {
    
    initListenerList = new EventListenerList();
    
    //System.out.println("EFFECT");
    
    /*
    selectListenerList = new EventListenerList();
    deselectListenerList = new EventListenerList();
    */
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    
    //TODO: дописать
    
    testEffect = new TestEffect();
    add(testEffect);
    testEffect.init();
    
    
    testEffect.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        testEffect.removeInitEventListener(this);
        add(testEffect.controlPanel);
        revalidate();
        fireInitEvent(new Event(this));
      }
    });
    
    
    /*
    testSketch = new TestSketch();
    add(testSketch);
    testSketch.init();
    
    testSketch.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        testSketch.removeInitEventListener(this);
        add(testSketch.controlPanel);
        revalidate();
        fireInitEvent(new Event(this));
      }
    });
    
    testSketch.addSelectEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        fireSelectEvent(new Event(this));
      }
    });
    */
  }
  
  
  public void clear()
  {
    
    if(testEffect != null)
    {
      
      testEffect.clear();
      testEffect.stop();
      testEffect.dispose();
      testEffect = null;
      removeAll();
      revalidate();
      
    }
    
  }
  
  /*
  public void deselect()
  {
    testSketch.deselect();
    fireDeselectEvent(new Event(this));
  }
  */
  
  
  
  public PImage getFeaturedImage(PImage img)
  {
    return testEffect.getFeaturedImage(img);
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
  
  //------------------------------------------------------------------
  public void addDeselectEventListener(IEventListener listener) 
  {
    deselectListenerList.add(IEventListener.class, listener);
  }
  
  public void removeDeselectEventListener(IEventListener listener) 
  {
    deselectListenerList.remove(IEventListener.class, listener);
  }
  
  void fireDeselectEvent(Event evt) 
  {
    Object[] listeners = deselectListenerList.getListenerList();
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
