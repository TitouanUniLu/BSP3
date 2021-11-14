/***************************************************************************
 *   Copyright (C) 2012 by Vincent Raveneau and Giacomo di Tollo           *
 *   giacomodt@gmail.com                                                   *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program; if not, write to the                         *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 ***************************************************************************/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OngletKaleidocycleT extends OngletKaleidocycle {

	JButton boutonTrans;
	JFormattedTextField jtfTrans;
	ArrayList<ArrayList<int[]>> composantesT;
	int transposition;
	int nb_notes;
	
	private static final long serialVersionUID = 1L;
	
	public OngletKaleidocycleT(Cycle c, Kaleidocycle kc, int nb_notes)
	{
		super(c, kc, nb_notes);
		this.nb_notes = nb_notes;
		this.transposition = 0;
		this.composantesT = new ArrayList<ArrayList<int[]>>();
		this.completeHaut();
		this.calculeComposantesT();
	}
	
	void initHaut()
	{
		this.haut = new JPanel();
		this.hautDroit = new JPanel();
		this.zoneErreur = new JLabel();
		this.textHaut = new JLabel("Affichage du kaleidocycle transpose", JLabel.CENTER);
		this.boutonComplet = new JButton("Affichage du kaleidocycle transpose complet");
		this.boutonPartiel = new JButton("Affichage du kaleidocycle transpose partiel");
		this.boutonTrans = new JButton("Affichage d'une autre transposee");
		this.jtfPartiel = new JFormattedTextField("<composantes a afficher, separees par un espace>");
		this.jtfTrans = new JFormattedTextField("<valeur de la transposition souhaitee>");
		
		this.boutonComplet.addActionListener(this);
		this.boutonPartiel.addActionListener(this);
		this.boutonTrans.addActionListener(this);
		
		this.haut.setLayout(new BorderLayout());
		this.hautDroit.setPreferredSize(new Dimension(700,75));
		this.hautDroit.setLayout(new GridLayout(3,2));
		
		this.hautDroit.add(this.jtfPartiel);
		this.hautDroit.add(this.boutonPartiel);
		this.hautDroit.add(this.zoneErreur);
		this.hautDroit.add(this.boutonComplet);
		this.hautDroit.add(this.jtfTrans);
		this.hautDroit.add(this.boutonTrans);
		
		this.haut.add(this.textHaut, BorderLayout.CENTER);
		this.haut.add(this.hautDroit, BorderLayout.EAST);
	}
	
	public void completeHaut()
	{
		this.textHaut.setText(this.textHaut.getText()+" "+this.transposition%12);
		if (this.transposition>=12)
			this.textHaut.setText(this.textHaut.getText()+" (="+this.transposition+"%12)");
		if (this.transposition%12 == 0)
			this.textHaut.setText(this.textHaut.getText()+" (= kaleidocycle)");
	}
	
	public void calculeComposantesT()
	{
		int i,j;
		
		for (i=0;i<this.kc.composantes.size();i++)
		{
			this.composantesT.add(new ArrayList<int[]>());
			for (j=0;j<this.kc.composantes.get(0).size();j++)
			{
				this.composantesT.get(i).add(new int[2]);
				this.composantesT.get(i).get(j)[0] = (this.kc.composantes.get(i).get(j)[0]+this.transposition)%12;
				this.composantesT.get(i).get(j)[1] = j;
			}
		}
	}
	
	public void affComplet()
	{
		int i,j,t;
		boolean rempli;
		JPanel casePleine;
		
		for (i=11;i>=-1;i--)
		{// lignes
			for (j=-1;j<=this.kc.taille-1;j++)
			{// colonnes
				//si coin inferieur gauche
				if (i == -1 && j == -1)
					this.zoneAffichage.add(new JLabel());
				//si axe des ordonnees
				else if (j == -1)
					this.zoneAffichage.add(new JLabel(Integer.toString(i), JLabel.CENTER));
				//si axe des abscisses
				else if (i == -1)
					this.zoneAffichage.add(new JLabel(Integer.toString(this.kc.cycle.get(j)), JLabel.CENTER));
				//si case du graph
				else
				{
					rempli = false;
					for (t=0;t<this.composantesT.get(0).size() && !rempli;t++)
					{
						if (this.composantesT.get(j).get(t)[0] == i)
						{
							casePleine = new JPanel();
							casePleine.setBackground(this.couleurs[this.composantesT.get(j).get(t)[1]].getBackground());
							this.zoneAffichage.add(casePleine);
							rempli = true;
						}
					}
					if (!rempli)
						this.zoneAffichage.add(new JLabel());
				}
			}
		}
	}
	
	public void affPartiel(int[] tab)
	{
		int i,j,t;
		boolean rempli;
		JPanel casePleine;
		
		for (i=11;i>=-1;i--)
		{// lignes
			for (j=-1;j<=tab.length-1;j++)
			{// colonnes
				//si coin inferieur gauche
				if (i == -1 && j == -1)
					this.zoneAffichage.add(new JLabel());
				//si axe des ordonnees
				else if (j == -1)
					this.zoneAffichage.add(new JLabel(Integer.toString(i), JLabel.CENTER));
				//si axe des abscisses
				else if (i == -1)
					this.zoneAffichage.add(new JLabel(Integer.toString(this.kc.cycle.get(tab[j])), JLabel.CENTER));
				//si case du graph
				else
				{
					rempli = false;
					for (t=0;t<this.composantesT.get(0).size() && !rempli;t++)
					{
						if (this.composantesT.get(tab[j]).get(t)[0] == i)
						{
							casePleine = new JPanel();
							casePleine.setBackground(this.couleurs[this.composantesT.get(tab[j]).get(t)[1]].getBackground());
							this.zoneAffichage.add(casePleine);
							rempli = true;
						}
					}
					if (!rempli)
						this.zoneAffichage.add(new JLabel());
				}
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object src = e.getSource();
		if (src == this.boutonComplet)
		{
			this.textHaut.setText("Affichage du kaleidocycle transpose");
			this.completeHaut();
			this.zoneAffichage.removeAll();
			this.affComplet();
			this.zoneAffichage.repaint();
			this.jtfPartiel.setText("<composantes a afficher, separees par un espace>");
			this.zoneErreur.setText("");
		}
		else if (src == this.boutonPartiel)
		{
			if (this.jtfPartiel.getText().matches("^([0-9]|10|11)( ([0-9]|10|11))*$")) // si le format de l'entree est bon
			{
				int i;
				int[] tab;
				
				String str = this.jtfPartiel.getText();
				String[] tabStr = str.split(" ");
				
				tab = new int[tabStr.length];
				for (i=0;i<tabStr.length;i++)
				{
					tab[i] = Integer.parseInt(tabStr[i]);
				}
				
				this.zoneAffichage.removeAll();
				this.affPartiel(tab);
				this.zoneAffichage.repaint();
				this.textHaut.setText("Affichage partiel du kaleidocycle transpose");
				this.completeHaut();
				this.zoneErreur.setText("Affichage actuel : "+this.jtfPartiel.getText());
				this.jtfPartiel.setText("<composantes a afficher, separees par un espace>");
			}
			else // si le format de l'entree est incorrect
			{
				this.jtfPartiel.setText("<composantes a afficher, separees par un espace>");
				this.zoneErreur.setText("Erreur, format de saisie incorrect (affichage partiel)");
			}
		}
		else if (src == this.boutonTrans)
		{
			if (this.jtfTrans.getText().matches("^[0-9]+$")) // si le format d'entree est valide
			{
				this.transposition = Integer.parseInt(this.jtfTrans.getText());
				this.textHaut.setText("Affichage du kaleidocycle transpose");
				this.completeHaut();
				this.zoneAffichage.removeAll();
				this.composantesT.clear();
				this.calculeComposantesT();
				this.affComplet();
				this.zoneAffichage.repaint();
				this.jtfTrans.setText("<valeur de la transposition souhaitee>");
				this.zoneErreur.setText("");
			}
			else
			{
				this.jtfTrans.setText("<valeur de la transposition souhaitee>");
				this.zoneErreur.setText("Erreur, format de saisie incorrect (transposition)");
			}
		}
		else
			System.out.println("Erreur d'ecouteur ...");
	}
}
