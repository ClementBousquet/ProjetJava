
package Vue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.portable.InputStream;

import Modele.CSV;
import Modele.Personne;
import TestCSV.AppTest;

public class VueMissions extends JPanel implements Serializable {
	final int  MAX=100;
	
	public VueMissions(){
	
		this.setLayout(new BorderLayout());
		
		String[] columnNames = {"Code","Date d�but","dur�e(mois)"};
		
		//Ajouter les données CSV	
		String Directory = System.getProperty("user.dir");
		Directory+="\\src\\Bd\\liste_mission.csv";
     	File file = new File(Directory);
							
		CSV csv = new CSV();
		
		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(file);
		
		String[][] array = new String[donneeCSV.size()][0];
		donneeCSV.toArray(array);
		
		
		JTable tableview=new JTable(array,columnNames);
		tableview.setFillsViewportHeight(true);
		 JPanel Westpannel= new JPanel();
		 Westpannel.setLayout(new BorderLayout());
		 Westpannel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		 Westpannel.add(tableview, BorderLayout.WEST);
		 JScrollPane scrollPane = new JScrollPane(tableview);
		 Westpannel.add(scrollPane);
		 this.add(Westpannel, "West");
		
	
		
	}
	
	
}