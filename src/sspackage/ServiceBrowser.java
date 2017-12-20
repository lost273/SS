
package sspackage;
import java.awt.*;
import javax.swing.*;
import java.rmi.*;
import java.awt.event.*;

public class ServiceBrowser {
    JPanel mainPanel;
    JComboBox serviceList;
    ServiceServer server;
    
    public void buildGUI(){
        JFrame frame = new JFrame("RMI Browser");
        mainPanel = new JPanel();
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        Object[] services = getServicesList();
        serviceList = new JComboBox(services);
        
        frame.getContentPane().add(BorderLayout.NORTH, serviceList);
        serviceList.addActionListener(new MyListListener());
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    void loadService(Object serviceSelection){
        try{
            Service svc = server.getService(serviceSelection);
            mainPanel.removeAll();
            mainPanel.add(svc.getGuiPanel());
            mainPanel.validate();
            mainPanel.repaint();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
