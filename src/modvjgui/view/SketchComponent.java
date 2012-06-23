package modvjgui.view;
import modvjgui.core.IEventListener;
import modvjgui.core.Event;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.event.*;
import modvjgui.core.IChannelComponent;
import modvjgui.core.IFootageComponent;


public class SketchComponent extends JPanel implements ActionListener 
{
  
  IChannelComponent channelComp;
  
  EffectQueueComponent effectQueueComponent;
  
  protected IFootageComponent sketch;
  JButton removeBtn;
  
  
  public SketchComponent(IChannelComponent channelComponent)
  {
    channelComp = channelComponent;
    
    effectQueueComponent = new EffectQueueComponent();
    
    
    effectQueueComponent.addSetQueueEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        //System.out.println("sketch = " + sketch);
        sketch.setEffectQueue(effectQueueComponent.effectQueue);
      }
    });
    
    
    GuiLocator oLocator = GuiLocator.getInstance();
    sketch = oLocator.getFootageComponent();
    System.out.println(sketch);
    //TODO: дописать
    //sketch = new FootageComponent();
    
    
    sketch.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        sketch.removeInitEventListener(this);
        
        //System.out.println("SketchComponent: --> " + effectQueueComponent.effectQueue);
        
        sketch.setEffectQueue(effectQueueComponent.effectQueue);
        
        
        removeBtn.setEnabled(true);
        effectQueueComponent.addBtn.setEnabled(true);
      }
    });
    
    
    sketch.addSelectEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        channelComp.getChannel().selectSketch(sketch);
        channelComp.getRemoveButton().setEnabled(false);
        channelComp.getClearButton().setEnabled(true);
        removeBtn.setEnabled(false);
      }
    });
    
    
    sketch.addDeselectEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        channelComp.getRemoveButton().setEnabled(true);
        removeBtn.setEnabled(true);
      }
    });
    
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    add(Box.createRigidArea(new Dimension(0, 4)));
    
    
    removeBtn = new JButton("Remoove sketch");
    removeBtn.setEnabled(false);
    removeBtn.addActionListener(this);
    add(removeBtn);
    
    add(Box.createRigidArea(new Dimension(0, 2)));
    
    add( (java.awt.Component) sketch);
    
    add(Box.createRigidArea(new Dimension(0, 2)));
    
    //add(effectQueueComponent);
    
  }
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    
    if(source == removeBtn)
    {
      channelComp.removeSketch(this);
    }
  }
  
}
