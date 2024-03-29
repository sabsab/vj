/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package modvjgui.resources;

import modvjgui.core.Core;
import modvjgui.core.IEventListener;
import modvjgui.core.Channel;
import modvjgui.core.Event;
import modvjgui.view.*;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.*;
import modvjgui.core.IChannelComponent;
/**
 *
 * @author User
 */
public class MODVJgui extends javax.swing.JFrame implements ActionListener{
    
  Core core;
  JPanel panel;
  JButton addBtn;
    /**
     * Creates new form MODVJgui
     */
    public MODVJgui() {
        
    panel = new JPanel();
    
    addBtn = new JButton("Add channel");
    addBtn.setEnabled(false);
    addBtn.addActionListener(this);
    panel.add(addBtn);
    
    core = new Core();
    panel.add(core);
    core.init();
    core.addInitEventListener(new IEventListener()
    {
      public void eventOccurred(Event evt)
      {
        core.removeInitEventListener(this);
        addBtn.setEnabled(true);
        setEnabled(true);
      }
    });
    
    setLocation(200, 0);
    setSize(1024, 720);
    getContentPane().add(panel);
    setVisible(true);
    setEnabled(false);        
    initComponents();    
    }
  void addChannel(){
      
    GuiLocator oLocator = GuiLocator.getInstance();
    IChannelComponent channelComponent = oLocator.getChannelComponent();
    panel.add( (java.awt.Component) channelComponent);
    panel.revalidate();

    Channel[] temp = core.channels;
    core.channels = new Channel[core.channels.length + 1];
    
    for(int i = 0; i < temp.length; i++){
      core.channels[i] = temp[i];
    }
    core.channels[temp.length] = channelComponent.getChannel();
    
  }
  
  public void removeChannel(IChannelComponent channelComponent)
  {
    
    Channel[] temp = core.channels;
    core.channels = new Channel[core.channels.length - 1];
    int j = 0;
    for(int i = 0; i < temp.length; i++)
    {
      if(temp[i] != channelComponent.getChannel())
      {
        core.channels[j++] = temp[i];
      }
    }
    
    
    javax.swing.JPanel oComponent = (javax.swing.JPanel) channelComponent;
    oComponent.removeAll();
    
    panel.remove(oComponent);
    channelComponent = null;
    panel.repaint();
    panel.revalidate();
    
    
  }
  
  
  public void actionPerformed(ActionEvent e) 
  {
    Object source = e.getSource();
    if(source == addBtn)
    {
      addChannel();
    }
  }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 800, 600));
        setMinimumSize(new java.awt.Dimension(800, 717));

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("New");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Open");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Save");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Save As");
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator2);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Exit");
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem6.setText("Add Channel");
        jMenu2.add(jMenuItem6);

        jMenuItem7.setText("Remove Channel");
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 669, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        try {
          javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MODVJgui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                MODVJgui oFrame = new MODVJgui();
                oFrame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowOpened(WindowEvent e) {
                                // The canvas is ready to load the base document
                                // now, from the AWT thread.
                                //Core oCore  = Core.getInstance();
                               // oCore.init();
                            }
                        });   
                oFrame.setVisible(true);
               // oFrame.oFrame = new AppComponent();
                //oFrame.oFrame.setVisible(true);
                //new MODVJgui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    // End of variables declaration//GEN-END:variables
}
