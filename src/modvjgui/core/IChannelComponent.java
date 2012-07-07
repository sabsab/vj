/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.core;

import modvjgui.view.SketchComponent;

/**
 *
 * @author me
 */
public interface IChannelComponent {

    public void removeSketch(SketchComponent sketchComponent);
    public void initialize();
    public Channel getChannel();


    public void removeAll();
}
