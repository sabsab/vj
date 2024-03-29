package modvjgui.view;

import modvjgui.core.Channel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.event.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.*;
import modvjgui.core.IChannelComponent;
import modvjgui.resources.MODVJgui;



public class ChannelComponent extends JPanel implements ChangeListener, ActionListener, IChannelComponent
{
  //MODVJgui appComp;
  
  public Channel channel;
  public JPanel panel;
  public JSlider opacitySlider;
  public JButton removeBtn, addBtn, clearBtn;
  
  public ChannelComponent()
  {
    
    //appComp = appComponent;

    channel = new Channel();
    
    
    
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    
    add(Box.createRigidArea(new Dimension(0, 10)));
    
    removeBtn = new JButton("Remove channel");
    //removeBtn.setEnabled(false);
    removeBtn.addActionListener(this);
    add(removeBtn);
    
    addBtn = new JButton("Add sketch");
    //addBtn.setEnabled(false);
    addBtn.addActionListener(this);
    add(addBtn);
    
    add(Box.createRigidArea(new Dimension(0, 30)));
    
    panel = new JPanel();
    add(panel);
    
    add(Box.createRigidArea(new Dimension(0, 30)));
    
    clearBtn = new JButton("Clear channel");
    clearBtn.setEnabled(false);
    clearBtn.addActionListener(this);
    add(clearBtn);
    
    add(Box.createRigidArea(new Dimension(0, 5)));
    
    opacitySlider = new JSlider (JSlider.VERTICAL, 0, 100, 50);
    opacitySlider.addChangeListener(this);
    add(opacitySlider);
  }
  public void initialize(){
      
  }
  public Channel getChannel(){
      return channel;
  }
  
  public JButton getAddButton (){
      return addBtn;
  }
  
  public JButton getRemoveButton (){
      return removeBtn;
  }
  
  public JButton getClearButton (){
      return clearBtn;
  }
  
  void addSketch()
  {
    SketchComponent sketchComponent = new SketchComponent(this);
    panel.add(sketchComponent);
    
    panel.add(sketchComponent.effectQueueComponent);
  }
  
  public void removeSketch(SketchComponent sketchComponent)
  {
    sketchComponent.effectQueueComponent.clear();
    panel.remove(sketchComponent.effectQueueComponent);
    sketchComponent.effectQueueComponent = null;
    
    sketchComponent.sketch.clear();
    sketchComponent.removeAll();
    sketchComponent.sketch = null;
    
    panel.remove(sketchComponent);
    sketchComponent = null;
    panel.revalidate();
    
  }
  
  void remooveChannel()
  {
    Component[] components = panel.getComponents(); 
    Component component = null; 
    
    for (int i = 0; i < components.length; i++)
    {
      if (component instanceof SketchComponent) 
      {
        removeSketch((SketchComponent) components[i]);
      }
    } 
    //appComp.removeChannel(this); @todo реализовать
  }
  
  
  
  public void stateChanged (ChangeEvent e) 
  {
    channel.opacity = (float)(opacitySlider.getValue()) / 100;
  }
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    
    if(source == removeBtn)
    {
      remooveChannel();
    }
    else if(source == addBtn)
    {
      //addBtn.setEnabled(false);
      addSketch();
    }
    else if(source == clearBtn)
    {
      channel.deselect();
      clearBtn.setEnabled(false);
    }
  } 
}
