package modvjgui.view;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.Box;

import java.awt.Dimension;
import java.awt.event.*;

import modvjgui.core.*;



public class SketchComponent extends JPanel implements ActionListener, IListener{
  
    IChannelComponent channelComp;

    EffectQueueComponent effectQueueComponent;

    protected IFootageComponent sketch;
    JButton removeBtn;


    public SketchComponent(IChannelComponent channelComponent){
        
	channelComp = channelComponent;

	effectQueueComponent = new EffectQueueComponent();


	GuiLocator oLocator = GuiLocator.getInstance();
	sketch = oLocator.getFootageComponent();
	
	// Вещаем события
	Observer.getInstance().addListener(EventMap.EVENT_SKETCH_INIT, this);
	Observer.getInstance().addListener(EventMap.EVENT_SKETCH_SELECTED, this);
	Observer.getInstance().addListener(EventMap.EVENT_SKETCH_DESELECTED, this);


	setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

	add(Box.createRigidArea(new Dimension(0, 4)));


	removeBtn = new JButton("Remoove sketch");
	removeBtn.setEnabled(false);
	removeBtn.addActionListener(this);
	add(removeBtn);

	add(Box.createRigidArea(new Dimension(0, 2)));

	add( (java.awt.Component) sketch);

	add(Box.createRigidArea(new Dimension(0, 2)));

	add(effectQueueComponent);

    }
    @Override
    public void notify ( String eventType, Event event){
        Notificator.invoke(this, eventType, event);
    }
    
    
    public void onSketchInit ( Event event){

        Component oComponent = (Component) event.getSource();
	// Если событие от нашего футажа, обработаем его
        if ( (IFootageComponent) oComponent.getParent()  == sketch ){
	
	    sketch.setEffectQueue(effectQueueComponent.effectQueue);
	    removeBtn.setEnabled(true);
	    effectQueueComponent.addBtn.setEnabled(true); 
	}

    }
    public void onSketchSelected (Event event){
	Component oComponent = (Component) event.getSource();
	// Если событие от нашего футажа, обработаем его
        if ( (IFootageComponent) oComponent.getParent()  == sketch ){
	    removeBtn.setEnabled(false);   
	}
    }
    public void onSketchDeselected (Event event){
	
	Component oComponent = (Component) event.getSource();
	// Если событие от нашего футажа, обработаем его
        if ( (IFootageComponent) oComponent.getParent()  == sketch ){
	    removeBtn.setEnabled(true);   
	}
     
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == removeBtn){
            channelComp.removeSketch(this);
        }
    }
  
}
