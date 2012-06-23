package modvjgui.core;

public class Channel
{
  public float opacity = (float)0.5;
  IFootageComponent sketchSelected;
  
  public Channel()
  {
  }
  
  public void selectSketch(IFootageComponent sketch)
  {
    deselect();
    sketchSelected = sketch;
  }
  
  public IFootageComponent getSketchSelected()
  {
    return sketchSelected;
  }
  
  public void deselect()
  {
    if(sketchSelected != null)
    {
      sketchSelected.deselect();
      sketchSelected = null;
    }
  }
  
}

