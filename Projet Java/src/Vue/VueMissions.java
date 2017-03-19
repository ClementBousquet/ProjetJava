
package Vue;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.TableView;

import org.omg.CORBA.portable.InputStream;

import Modele.CSV;
import Modele.CSVModele;
import Modele.Competence;
import Modele.MissionEnPreparation;
import Modele.Personne;
import TestCSV.AppTest;

public class VueMissions extends JPanel implements Serializable {
	public JTable tableview=new JTable();
	
	public VueMissions(){
	
		this.setLayout(new BorderLayout());
		
		//Ajouter les donn�es CSV	
		String Directory = System.getProperty("user.dir");
		Directory+="\\src\\Bd\\liste_mission.csv";
     	File file = new File(Directory);
							
		CSV csv = new CSV();
		CSVModele modele = new CSVModele();
		
		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(file);
		
		modele.ajouterDonnee(donneeCSV);
		tableview.setModel(modele);
		
		JPanel westPanel= new JPanel();
		westPanel.setLayout(new BorderLayout());
		westPanel.add(tableview.getTableHeader(), BorderLayout.PAGE_START);
		westPanel.add(tableview, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane(tableview);
		westPanel.add(scrollPane);
		this.add(westPanel, "West");
		
		JPanel eastPanel = new JPanel();
		JButton boutonAjouter = new JButton("Ajouter");
		boutonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boutonAjouterActionPerformed(e);
			};
		});
		eastPanel.add(boutonAjouter);
		
		JButton boutonDetail = new JButton("Details mission");
		boutonDetail.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boutonDetailActionPerformed(e);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		});
		eastPanel.add(boutonDetail);
		
		JButton competMission = new JButton("Comp�tences mission");
		competMission.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					competMissionActionPerformed(e);
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			};
		});
		eastPanel.add(competMission);
		
		this.add(eastPanel);
	}
	
	public void boutonAjouterActionPerformed(ActionEvent e) {
		FormAddMission form = new FormAddMission(this);
	}
	
	public void boutonDetailActionPerformed(ActionEvent e) throws NumberFormatException, IOException {
		VuePersonneMission persMiss = new VuePersonneMission(Integer.parseInt(this.tableview.getValueAt(this.tableview.getSelectedRow(),0).toString()));
	}
	
	public void competMissionActionPerformed(ActionEvent e)throws NumberFormatException, IOException {
		//en 1er lieu on concid�re la mission "en pr�paration"
		int codeMission =  Integer.parseInt(this.tableview.getValueAt(this.tableview.getSelectedRow(),0).toString());
		Date dateDebut = new Date(this.tableview.getValueAt(this.tableview.getSelectedRow(),1).toString());
		int duree = Integer.parseInt(this.tableview.getValueAt(this.tableview.getSelectedRow(),2).toString());
		MissionEnPreparation m1= new MissionEnPreparation();
		m1.setCodeMission(codeMission);
		m1.setDateDebut(dateDebut);
		m1.setDuree(duree);
		
		//d�finir la liste des competences necessaires pour la mission
		
		//d�finir le nb de personnes par cometences
		
		
		
	}
	
}
