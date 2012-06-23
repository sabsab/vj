/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modvjgui.utils;

/**
 *
 * @author me
 */
import java.util.jar.*;
import java.util.*;
import java.io.*;

public class PackageUtils {

 private static boolean debug = true;

 public static List getClasseNamesInPackage
     (String jarName, String packageName){
   ArrayList classes = new ArrayList ();

   packageName = packageName.replaceAll("\\." , "/");
   if (debug) System.out.println
        ("Jar " + jarName + " looking for " + packageName);
   try{
     JarInputStream jarFile = new JarInputStream
        (new FileInputStream (jarName));
     JarEntry jarEntry;

     while(true) {
       jarEntry=jarFile.getNextJarEntry ();
       if(jarEntry == null){
         break;
       }
       if((jarEntry.getName ().startsWith (packageName)) &&
            (jarEntry.getName ().endsWith (".class")) ) {
         if (debug) System.out.println 
           ("Found " + jarEntry.getName().replaceAll("/", "\\."));
         classes.add (jarEntry.getName().replaceAll("/", "\\."));
       }
     }
   }
   catch( Exception e){
     e.printStackTrace ();
   }
   return classes;
}
}