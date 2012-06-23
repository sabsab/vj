package modvjgui.view;
import modvjgui.core.Effect;
import modvjgui.core.IEventListener;
import modvjgui.core.Event;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.event.*;

public class EffectComponent extends JPanel implements ActionListener
{
  
  EffectQueueComponent effectQueueComp;
  
  Effect effect;
  
  JButton removeBtn;
  
  public EffectComponent(EffectQueueComponent effectQueueComponent)
  {
    
    effectQueueComp = effectQueueComponent;
    
    removeBtn = new JButton("Remove effect");
    removeBtn.setEnabled(false);
    removeBtn.addActionListener(this);
    
    
    
    //TODO: дописать
    effect = new Effect();
    
    effect.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        effect.removeInitEventListener(this);
        
        removeBtn.setEnabled(true);
        effectQueueComp.addBtn.setEnabled(true);
        
      }
    });
    
    
    
    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    
    add(Box.createRigidArea(new Dimension(0, 5)));
    
    add(removeBtn);
    add(Box.createRigidArea(new Dimension(0, 2)));
    add(effect);
    
    
    
    //revalidate();
    
    
  }
  
  /*
  void addEffect()
  {
    System.out.println("Add Effect");
  }
  */
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    
    if(source == removeBtn)
    {
      effectQueueComp.remooveEffect(this);
    }
    
  }
}
