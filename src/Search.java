import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

// https://www.youtube.com/watch?v=qPMesvqZsmA&t=393s
public class Search extends JFrame {
    
    ArrayList<String[]> listPOIs;
    DefaultListModel<String> defaultListModel;
    JList<String> myJList = new JList<>();
    JTextField searchField = new JTextField();

    public Search(ArrayList<String[]> POIs, String floor) {
        listPOIs = POIs;
        // Create a new JFrame for the search panel which will contain the textField and List of POIs the User is searching for.
        JFrame searchFrame = new JFrame("Search for a POI");

        searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchFrame.setSize(400, 800);
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
}

