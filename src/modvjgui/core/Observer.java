/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.core;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author me
 */
public class Observer {
    public static Observer oInstance;
    
    public HashMap <String, ArrayList<IListener>> Listeners= new HashMap <String, ArrayList<IListener>>(); 
    
    public static Observer getInstance (){
        
        if (null == oInstance ){
            oInstance = new Observer();
        }
        
        return oInstance;
    }
    
    public void addListener (String eventType, IListener oListener){
        ArrayList<IListener> aListeners = Listeners.get ( eventType );
        if ( null != aListeners ){
            aListeners.add (oListener);

            Listeners.put (eventType, aListeners);
        }
        else {
            
            aListeners = new ArrayList();
            aListeners.add (oListener);
            Listeners.put (eventType, aListeners);
        }
        
        
    }
    
    public void fireEvent (String eventType, Event event){
         ArrayList<IListener> aListeners = Listeners.get ( eventType );
         for (int i=0; i<aListeners.size(); i++){
             
             IListener oListener = aListeners.get(i);
             
             oListener.notify (eventType, event);
         }
    }
}
