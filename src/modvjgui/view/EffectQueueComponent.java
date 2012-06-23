package modvjgui.view;
import modvjgui.core.Effect;
import modvjgui.core.IEventListener;
import modvjgui.core.Event;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.event.EventListenerList;

import java.awt.Component;
import java.awt.event.*;

public class EffectQueueComponent extends JPanel implements ActionListener
{
  
  protected EventListenerList setQueueListenerList = new EventListenerList();
  
  JButton addBtn;
  
  public Effect[] effectQueue = new Effect[0];
  
  public EffectQueueComponent()
  {
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    addBtn = new JButton("Add effect");
    addBtn.setEnabled(false);
    addBtn.addActionListener(this);
    add(addBtn);
    
  }
  
  
  void addEffect()
  {
    EffectComponent effectComponent = new EffectComponent(this);
    add(effectComponent);
    
    
    Effect[] temp = effectQueue;
    effectQueue = new Effect[effectQueue.length + 1];
    for(int i = 0; i < temp.length; i++)
    {
      effectQueue[i] = temp[i];
    }
    effectQueue[temp.length] = effectComponent.effect;
    
    //System.out.println("effectQueue: --> " + effectQueue.length);
    
    
    
    revalidate();
    
    fireSetQueueEvent(new Event(this));
  }
  
  void remooveEffect(EffectComponent effectComponent)
  {
    
    Effect[] temp = effectQueue;
    effectQueue = new Effect[effectQueue.length - 1];
    int j = 0;
    for(int i = 0; i < temp.length; i++)
    {
      if(temp[i] != effectComponent.effect)
      {
        effectQueue[j++] = temp[i];
      }
    }
    
    //System.out.println("effectQueue: ==> " + effectQueue.length);
    
    
    
    effectComponent.effect.clear();
    effectComponent.removeAll();
    effectComponent.effect = null;
    
    
    remove(effectComponent);
    effectComponent = null;
    revalidate();
    
    fireSetQueueEvent(new Event(this));
  }
  
  public void clear()
  {
    Component[] components = getComponents();
    Component component = null; 
    for (int i = 0; i < components.length; i++)
    {
      if (component instanceof EffectComponent) 
      {
        remooveEffect((EffectComponent) components[i]);
      }
    }
    removeAll();
  }
  
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    
    if(source == addBtn)
    {
      addBtn.setEnabled(false);
      addEffect();
    } 
  }
  
  
  //------------------------------------------------------------------
  public void addSetQueueEventListener(IEventListener listener) 
  {
    setQueueListenerList.add(IEventListener.class, listener);
  }
  
  public void removeSetQueueEventListener(IEventListener listener) 
  {
    setQueueListenerList.remove(IEventListener.class, listener);
  }
  
  void fireSetQueueEvent(Event evt) 
  {
    Object[] listeners = setQueueListenerList.getListenerList();
    for (int i=0; i<listeners.length; i+=2) 
    {
      if (listeners[i] == IEventListener.class) 
      {
        ((IEventListener)listeners[i+1]).eventOccurred(evt);
       }
     }   
  }
  
  
  
}
