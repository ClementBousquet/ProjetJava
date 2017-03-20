package Vue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.CORBA.portable.InputStream;

import Modele.CSV;
import Modele.CSVModele;
import Modele.Personne;

public class VueCompetences extends JPanel implements Serializable {
	final int MAX = 100;
	JTable tableview=new JTable();
	public VueCompetences() {

		this.setLayout(new BorderLayout());

		String Directory = System.getProperty("user.dir");
		Directory += "\\src\\Bd\\liste_competences.csv";
		File file = new File(Directory);

		CSV csv = new CSV();
		CSVModele modele = new CSVModele();

		ArrayList<String[]> donneeCSV = csv.ReadCSVfile(file);

		JPanel Westpannel = new JPanel();
		Westpannel.setLayout(new BorderLayout());
		Westpannel.add(tableview, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane(tableview);
		modele.ajouterDonnee(donneeCSV);
		tableview.setModel(modele);
		Westpannel.add(scrollPane);
		this.add(Westpannel, "West");

		JPanel eastPanel = new JPanel();
		JButton boutonAjouter = new JButton("Ajouter compétence");
		boutonAjouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boutonAjouterActionPerformed(e);
			};
		});
		eastPanel.add(boutonAjouter);
		
		JButton boutonSupprimer = new JButton("Supprimer compétence");
		boutonSupprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					supprimerComp(e);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			};
		});
		eastPanel.add(boutonSupprimer);

		this.add(eastPanel);

	}

	public void boutonAjouterActionPerformed(ActionEvent e) {
		FormAddCompetence form = new FormAddCompetence(this);
		form.initialize();
		form.run();

	}
	
	protected void supprimerComp(ActionEvent e) throws IOException {
		int dialogButton = JOptionPane.YES_NO_OPTION;
		int dialogResult = JOptionPane.showConfirmDialog (null, "Etes vous sur de vouloir supprimer cette compétence ?","Warning",dialogButton);
	 	if(dialogResult == JOptionPane.YES_OPTION){

			CSVModele modeleC = (CSVModele) tableview.getModel();
			int row = tableview.getSelectedRow();
			if (row != -1)
				modeleC.deleteRow(row);
	
			String Directory = System.getProperty("user.dir");
			String pathC = Directory + "\\src\\Bd\\liste_competences.csv";
			File fileC = new File(pathC);
	
			JTable nouveauT = new JTable();
			nouveauT.setModel(modeleC);
			FileWriter writerF = new FileWriter(fileC.getAbsoluteFile());
			BufferedWriter writerB = new BufferedWriter(writerF);
			writerB.write("Code;Traduction;Intitule\n");
			// Boucle ligne
			for (int i = 0; i < nouveauT.getRowCount(); i++) {
				// Boucle colonne
				for (int j = 0; j < nouveauT.getColumnCount(); j++) {
					if (!(nouveauT.getModel().getValueAt(i, j).equals(""))) {
						writerB.write(nouveauT.getModel().getValueAt(i, j) + ";");
					}
				}
				writerB.write("\r\n");
			}
			writerB.close();// On ferme le BufferedWriter
			writerF.close();// On ferme le FileWriter
     	}
	}

}
