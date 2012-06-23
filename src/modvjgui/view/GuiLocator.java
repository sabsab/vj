/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.view;

import modvjgui.core.IChannelComponent;
import modvjgui.core.IFootageComponent;

/**
 *
 * @author me
 */
public class GuiLocator {

    private String  footageComponentClass="modvjgui.view.SketchPanel";
    
    private String  channelComponentClass="modvjgui.view.ChannelComponent";
    private static GuiLocator oInstance;
    public static GuiLocator getInstance (){
        if (null == oInstance ){
            oInstance = new GuiLocator();
        }
        return oInstance;
    }
    /**
     * @return the channelComponent
     */
    public IChannelComponent getChannelComponent() {

        IChannelComponent channelComponent =null;
        try {
            String channelClassName = getChannelComponentClass();
            channelComponent =  (IChannelComponent) Class.forName(channelClassName).newInstance();
            channelComponent.initialize();
        }
        catch (Exception oException){
            System.out.println (oException.getMessage());
        }

        return channelComponent;

    }



    /**
     * @return the footageComponent
     */
    public IFootageComponent getFootageComponent() {
        IFootageComponent footageComponent =  null;

        try {
            String footageClassName = getFootageComponentClass();
            footageComponent =  (IFootageComponent) Class.forName(footageClassName).newInstance();
            footageComponent.initialize();
        }
        catch (Exception oException){
            System.out.println ( oException.getStackTrace() );
        }
            

        return footageComponent;
    }


    /**
     * @return the footageComponentClass
     */
    public String getFootageComponentClass() {
        return footageComponentClass;
    }

    /**
     * @param footageComponentClass the footageComponentClass to set
     */
    public void setFootageComponentClass(String footageComponentClass) {
        this.footageComponentClass = footageComponentClass;
    }

    /**
     * @return the channelComponentClass
     */
    public String getChannelComponentClass() {
        return channelComponentClass;
    }

    /**
     * @param channelComponentClass the channelComponentClass to set
     */
    public void setChannelComponentClass(String channelComponentClass) {
        this.channelComponentClass = channelComponentClass;
    }
    

    
}
