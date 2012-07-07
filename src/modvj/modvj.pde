

import modvjgui.resources.*;
import modvjgui.view.*;
import modvjgui.core.*;
import modvjgui.utils.*;


import processing.opengl.*;
import codeanticode.glgraphics.*;
import codeanticode.gsvideo.*;

void setup()
{
  String args[] = new String [1];
  GuiLocator.getInstance()
    .setFootageComponentClass("modvjgui.resources.FootageComponent");
  MODVJgui.main(args);
}
