/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.core;


import javax.swing.JButton;
import modvjgui.view.SketchComponent;

/**
 *
 * @author me
 */
public interface IChannelComponent {

    public void removeSketch(SketchComponent sketchComponent);
    public void initialize();
    public Channel getChannel();
    public JButton getAddButton();
    public JButton getClearButton();
    public JButton getRemoveButton();
    public void removeAll();
}
