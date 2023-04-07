/**
 * @author Shadi Seaidoun
 * Functionality for different layers of POI visibility
 * Hides and displays certain POIs based on user selection
 */


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Layers {

    ArrayList<POIMarker> list;
    
    public Layers(ArrayList<POIMarker> newList){
        this.list = newList;
    }

    public void changeLayer() {
        /**
         Toggles layers to show based on user input
        */
        JFrame layerFrame = new JFrame();
        layerFrame.setTitle("Change Layers");
        layerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        layerFrame.setSize(new Dimension(250, 125));
        layerFrame.setLocationRelativeTo(null); // Center popup frame on screen
        
        JPanel layerPanel = new JPanel();
        layerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel layerLabel = new JLabel("Select layer:");
        JComboBox<String> layerComboBox = new JComboBox<String>(new String[] {"All", "Favourites", "Food", "Classroom", "Lab room", "Accessibility", "Washroom"});
        JButton layerButton = new JButton("OK");
        layerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the selected layer from the JComboBox and do something with it
                String selectedLayer = (String)layerComboBox.getSelectedItem();
                
                // displaying all pois
               if (selectedLayer.equals("All")){
                for (int i=0; i < list.size(); i++){
                    list.get(i).setVisible(true);
                }
               }

               // displaying Favourites
               if (selectedLayer.equals("Favourites")){
                for (int i=0; i < list.size(); i++){
                    if(!list.get(i).isFavourite()){
                        list.get(i).setVisible(false);
                    }
                    else{
                        list.get(i).setVisible(true);
                    }
                }
               }

               // displaying food pois
               if (selectedLayer.equals("Food")){
                for (int i=0; i < list.size(); i++){
                    if(!list.get(i).getType().toLowerCase().equals("food")){
                        list.get(i).setVisible(false);
                    }
                    else{
                        list.get(i).setVisible(true);
                    }
                }
               }

               // displaying classroom pois
               if (selectedLayer.equals("Classroom")){
                for (int i=0; i < list.size(); i++){
                    if(!list.get(i).getType().toLowerCase().equals("classroom")){
                        list.get(i).setVisible(false);
                    }
                    else{
                        list.get(i).setVisible(true);
                    }
                }
               }

               // displaying lab room pois
               if (selectedLayer.equals("Lab room")){
                for (int i=0; i < list.size(); i++){
                    if(!list.get(i).getType().toLowerCase().equals("lab room")){
                        list.get(i).setVisible(false);
                    }
                    else{
                        list.get(i).setVisible(true);
                    }
                }
               }

               // displaying accessibility pois
               if (selectedLayer.equals("Accessibility")){
                for (int i=0; i < list.size(); i++){
                    if(!list.get(i).getType().toLowerCase().equals("accessibility")){
                        list.get(i).setVisible(false);
                    }
                    else{
                        list.get(i).setVisible(true);
                    }
                }
               }

               // displaying washroom pois
               if (selectedLayer.equals("Washroom")){
                for (int i=0; i < list.size(); i++){
                    if(!list.get(i).getType().toLowerCase().equals("washroom")){
                        list.get(i).setVisible(false);
                    }
                    else{
                        list.get(i).setVisible(true);
                    }
                }
               }
 
                
                // close the layerFrame
                layerFrame.dispose();
            }
        });
        
        layerPanel.add(layerLabel);
        layerPanel.add(layerComboBox);
        layerPanel.add(layerButton);
        layerFrame.add(layerPanel);
        
        layerFrame.setVisible(true);
    }

}
