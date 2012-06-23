
package modvjgui.view;

import modvjgui.core.Core;
import modvjgui.core.IEventListener;
import modvjgui.core.Channel;
import modvjgui.core.Event;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.*;
import modvjgui.core.IChannelComponent;

public class AppComponent extends JFrame implements ActionListener
{
  Core core;
  JPanel panel;
  JButton addBtn;
  
  public AppComponent()
  {
    panel = new JPanel();
    
    addBtn = new JButton("Add channel");
    addBtn.setEnabled(false);
    addBtn.addActionListener(this);
    panel.add(addBtn);
    
    core = new Core();
    panel.add(core);
    core.init();
    core.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        core.removeInitEventListener(this);
        addBtn.setEnabled(true);
        setEnabled(true);
      }
    });
    
    setLocation(200, 0);
    setSize(1024, 720);
    getContentPane().add(panel);
    setVisible(true);
    setEnabled(false);
    
  }
  
  void addChannel()
  {
    GuiLocator oLocator = GuiLocator.getInstance();
    IChannelComponent channelComponent = oLocator.getChannelComponent();

    panel.add( (javax.swing.JPanel )channelComponent);
    panel.revalidate();
    
    Channel[] temp = core.channels;
    core.channels = new Channel[core.channels.length + 1];
    for(int i = 0; i < temp.length; i++)
    {
      core.channels[i] = temp[i];
    }
    core.channels[temp.length] = channelComponent.getChannel() ;
    
  }
  
  public void remooveChannel(IChannelComponent channelComponent)
  {
    
    Channel[] temp = core.channels;
    core.channels = new Channel[core.channels.length - 1];
    int j = 0;
    for(int i = 0; i < temp.length; i++)
    {
      if(temp[i] != channelComponent.getChannel())
      {
        core.channels[j++] = temp[i];
      }
    }
    
    
    channelComponent.removeAll();
    panel.remove( (javax.swing.JPanel ) channelComponent);
    channelComponent = null;
    panel.repaint();
    panel.revalidate();
    
    
  }
  
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    if(source == addBtn)
    {
      addChannel();
    }
  }
}
