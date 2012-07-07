/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.core;

import processing.core.PImage;

/**
 *
 * @author me
 */
public interface IFootageComponent {

    void clear();

    PImage getImage();

    void setEffectQueue(Effect[] effectQueue);
    void initialize();

    void deselect();
}
