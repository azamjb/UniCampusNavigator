import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// https://www.youtube.com/watch?v=qPMesvqZsmA&t=393s
public class Search extends JFrame {
    
    ArrayList<String[]> listPOIs;
    DefaultListModel<String> defaultListModel;
    JList<String> myJList = new JList<>();
    JTextField searchField = new JTextField();
    String userName;

    public Search(ArrayList<String[]> POIs, String floor, String userName) {
        listPOIs = POIs;
        this.userName = userName;
        searchFilter("", floor);
        // Create a new JFrame for the search panel which will contain the textField and List of POIs the User is searching for.
        JFrame searchFrame = new JFrame("Search for a POI");

        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setSize(350, 650);
        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    
        searchField.setPreferredSize(new Dimension(200, 20));
        searchField.setBackground(Color.lightGray);
        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchQuery = searchField.getText();
                searchFilter(searchQuery, floor);
            }
        });
        JPanel textArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
        textArea.add(searchField);
        searchFrame.getContentPane().add(textArea, BorderLayout.NORTH);
        searchFrame.add(myJList);

        ArrayList<String[]> floorPOIs = new ArrayList<>();
        for (int i = 0; i < listPOIs.size(); i++) {
            if (listPOIs.get(i)[0].equals(floor)) {
                floorPOIs.add(listPOIs.get(i));
            }
        }

        // https://stackoverflow.com/questions/4344682/double-click-event-on-jlist-element
        // Handle users clicking on a POI in the list
        myJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList myJList = (JList)evt.getSource();
                if (evt.getClickCount() > 0) {
                    int poiIndex = myJList.locationToIndex(evt.getPoint());
                    if (poiIndex >= 0) {
                        Object o = myJList.getModel().getElementAt(poiIndex);
                        
                        // Pop-up box opens asking whether the User would like to Warp to their selected POI
                        int q = JOptionPane.showConfirmDialog(myJList, "Warp to this POI?", o.toString(),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                        
                        // If they select yes, warp to POI
                        if (q == JOptionPane.YES_OPTION) {
                            searchFrame.dispose();
                            String floor = floorPOIs.get(poiIndex)[0];
                            int xVal = Integer.valueOf(floorPOIs.get(poiIndex)[1]);
                            int yVal = Integer.valueOf(floorPOIs.get(poiIndex)[2]);
                            String name = floorPOIs.get(poiIndex)[6];
                            String desc = floorPOIs.get(poiIndex)[7];
                            String num = floorPOIs.get(poiIndex)[8];
                            new Maps(floor, userName, xVal, yVal);
                            showPopup(name, desc, num);
                        }
                        else {
                            searchFrame.dispose();
                            new Maps(floor, userName);
                        }
                    }
                }
            } 
        });
    }

    public void searchFilter(String query, String floor) {
        
        DefaultListModel<String> filteredList = new DefaultListModel<>();
        
        listPOIs.stream().forEach((POI) -> {
            if (floor.equals(POI[0])) {
                String POIname = POI[6].toLowerCase();
                String POIdescription = POI[7].toLowerCase();
                String POIroomnum = POI[8].toLowerCase();
                if(POIname.contains(query.toLowerCase()) || POIdescription.contains(query.toLowerCase()) || POIroomnum.contains(query.toLowerCase())) {
                    filteredList.addElement(POI[6]);
                }
            }
        });
        defaultListModel = filteredList;
        myJList.setModel(defaultListModel);
    }

    private void showPopup(String name, String desc, String num) {
        JFrame popupFrame = new JFrame(name);
    
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        popupFrame.setSize(575, 350);
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setVisible(true);
    
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        // Create a panel for the title and room number labels
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel titleLabel = new JLabel("Name: " + name);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 18));
        topPanel.add(titleLabel);
    
        JLabel roomnumLabel = new JLabel("Room number: " + num);
        roomnumLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        roomnumLabel.setFont(roomnumLabel.getFont().deriveFont(18));
        topPanel.add(roomnumLabel);
    
        // Create a panel for the description JTextArea
        JPanel descPanel = new JPanel(new BorderLayout());
        JTextArea description = new JTextArea(desc);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setEditable(false);
        descPanel.add(description, BorderLayout.CENTER);
    
        // Add the panels to the parent panel
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(descPanel, BorderLayout.CENTER);
    
        popupFrame.add(panel);
    }
}

