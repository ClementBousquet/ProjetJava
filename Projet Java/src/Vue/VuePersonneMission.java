package Vue;

import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import Modele.CSV;
import Modele.CSVModele;

public class VuePersonneMission extends JFrame {
	public JTable tableview=new JTable();
	private int numMission;
	
	public VuePersonneMission(int numMission) {
		this.numMission = numMission;
		ArrayList<String> personnes = new ArrayList<String>();
		ArrayList<String[]> personnesAssigne = new ArrayList<String []>();
		
		//Ajouter les donn�es CSV	
		String Directory = System.getProperty("user.dir");
		String pathMP = Directory + "\\src\\Bd\\missions_personnel.csv";
     	File fileMP = new File(pathMP);
							
		CSV csvMP = new CSV();
		
		ArrayList<String[]> donneeCSVMP = csvMP.ReadCSVfile(fileMP);
		for(String[] mission : donneeCSVMP) {
			if (mission[0].equals(Integer.toString(this.numMission))) {
				for(int j=1; j<mission.length;j++) {
					personnes.add(mission[j]);
				}
			}
		}
		
		System.out.println(personnes);
		
		String pathP = Directory + "\\src\\Bd\\liste_personnel.csv";
		File fileP = new File(pathP);
		CSV csvP = new CSV();
		
		ArrayList<String[]> donneeCSVP = csvP.ReadCSVfile(fileP);
		for(String[] all : donneeCSVP) {
			for (String p : personnes) {
				if (all[0].equals(p)) {
					personnesAssigne.add(all);
				}
			}
		}
		
		personnesAssigne.add(0,donneeCSVP.get(0));

		CSVModele modele = new CSVModele();
		modele.ajouterDonnee(personnesAssigne);
		tableview.setModel(modele);
		JPanel panel= new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		panel.add(tableview, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane(tableview);
		panel.add(scrollPane);
		this.add(panel, "West");
		this.add(panel);
		this.pack();
		this.setVisible(true);
	}
}