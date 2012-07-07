package modvjgui.view;
import modvjgui.core.*;

import processing.core.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;


public class SketchPanel extends JPanel implements IFootageComponent, IListener
{
    TestSketch testSketch;


    //Effect[] effectQueue;
    @Override
    public void initialize(){

    }
    
    public SketchPanel(){
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        //TODO: дописать
        testSketch = new TestSketch( );
        add(testSketch);
        testSketch.init();
        Observer.getInstance().addListener(EventMap.EVENT_SKETCH_INIT, this);
        Observer.getInstance().addListener(EventMap.EVENT_EFFECT_CHANGED,  this);

    }
  
    public void notify ( String eventType, Event event){

        Notificator.invoke(this, eventType, event);
    }
    public void onEffectChanged ( Event event){
        
        EffectQueueComponent oComponent  = (EffectQueueComponent) event.getSource();
        this.setEffectQueue(oComponent.effectQueue);

    } 
    public void onSketchInit ( Event event){
    // testSketch.removeInitEventListener(this);
        add(testSketch.controlPanel);
        revalidate();

    }
    @Override
    public void clear(){
        if(testSketch != null){
            testSketch.clear();
            testSketch.stop();
            testSketch.dispose();
            testSketch = null;
            removeAll();
            revalidate();
        }
    }

    public void deselect(){
        testSketch.deselect();

    }


    @Override
    public void setEffectQueue(Effect[] effectQueue){
    //System.out.println("Sketch: --> " + effectQueue);
        testSketch.effectQueue = effectQueue;
    }


    @Override
    public PImage getImage(){
    return testSketch.getImage();
    }

 }
