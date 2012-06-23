package modvjgui.view;
import modvjgui.core.IFootageComponent;
import modvjgui.core.TestSketch;
import modvjgui.core.Effect;
import modvjgui.core.IEventListener;
import modvjgui.core.Event;
import processing.core.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.event.EventListenerList;


public class SketchPanel extends JPanel implements IFootageComponent
{
  TestSketch testSketch;
  
  protected EventListenerList initListenerList, selectListenerList, deselectListenerList;
  
  //Effect[] effectQueue;
  
  public void initialize(){
      
  }
  public SketchPanel()
  {
    initListenerList = new EventListenerList();
    selectListenerList = new EventListenerList();
    deselectListenerList = new EventListenerList();
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    
    //TODO: дописать
    testSketch = new TestSketch();
    add(testSketch);
    testSketch.init();
    
    testSketch.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        
        //System.out.println("Sketch: --> " + effectQueue);
        
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
  }
  
  
    @Override
  public void clear()
  {
    if(testSketch != null)
    {
      testSketch.clear();
      testSketch.stop();
      testSketch.dispose();
      testSketch = null;
      removeAll();
      revalidate();
    }
  }
  
  public void deselect()
  {
    testSketch.deselect();
    fireDeselectEvent(new Event(this));
  }
  
  
    @Override
  public void setEffectQueue(Effect[] effectQueue)
  {
    //System.out.println("Sketch: --> " + effectQueue);
    testSketch.effectQueue = effectQueue;
  }
  
  
    @Override
  public PImage getImage()
  {
    return testSketch.getImage();
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
  
  //------------------------------------------------------------------
}
