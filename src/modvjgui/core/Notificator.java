/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.core;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author me
 */
public class Notificator {
    /**
     * Вызывает метод у обуъекта в соответствии с типом события
     * @param oSource
     * @param eventType
     * @param event 
     */
    public static void invoke( Object oSource, String eventType, Event event ){
        
        java.lang.reflect.Method method;
        try {
            System.out.print("on" + eventType);
            method = oSource.getClass().getMethod("on" + eventType, event.getClass());
            method.invoke(oSource, event);

        } 
        catch (SecurityException e) {
          System.out.print("Security" + e.getMessage());
        } 
        catch (NoSuchMethodException e) {
          System.out.print("NoSuchMethod" +e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.out.print("IllegalArgument" +e.getMessage());
        } 
        catch (IllegalAccessException e) {
            System.out.print("IllegalAccess" +e.getMessage());
        } 
        catch (InvocationTargetException e) {
            System.out.print("Invocation target" +e.getMessage());

        }        
    }
    
}
