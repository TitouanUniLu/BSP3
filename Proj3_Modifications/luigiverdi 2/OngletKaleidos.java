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
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

public class OngletKaleidos extends OngletGraph implements ActionListener{

	Kaleidos k;

	private static final long serialVersionUID = 1L;
	
	public OngletKaleidos(Cycle c)
	{
		this.setLayout(new BorderLayout());
		
		this.initHaut();
		this.initBas(c);
		this.initCouleurs();
		this.initZoneAff();
		
		this.k = new Kaleidos(c);
		
		this.add(this.haut, BorderLayout.NORTH);
		this.add(this.zoneAffichage, BorderLayout.CENTER);
		this.add(this.bas, BorderLayout.SOUTH);
	}

	void initHaut() {
		this.haut = new JPanel();
		this.hautDroit = new JPanel();
		this.zoneErreur = new JLabel();
		this.textHaut = new JLabel("Affichage du kaleidos", JLabel.CENTER);
		this.boutonComplet = new JButton("Affichage du kaleidos complet");
		this.boutonPartiel = new JButton("Affichage du kaleidos partiel");
		this.jtfPartiel = new JFormattedTextField("<composantes a afficher, separees par un espace>");
		
		this.boutonComplet.addActionListener(this);
		this.boutonPartiel.addActionListener(this);
		
		this.haut.setLayout(new BorderLayout());
		this.hautDroit.setPreferredSize(new Dimension(700,50));
		this.hautDroit.setLayout(new GridLayout(2,2));
		
		this.hautDroit.add(this.jtfPartiel);
		this.hautDroit.add(this.boutonPartiel);
		this.hautDroit.add(this.zoneErreur);
		this.hautDroit.add(this.boutonComplet);
		
		this.haut.add(this.textHaut, BorderLayout.CENTER);
		this.haut.add(this.hautDroit, BorderLayout.EAST);
	}

	public void initZoneAff()
	{
		this.zoneAffichage = new JPanel();
		this.zoneAffichage.setLayout(new GridLayout(13,13));
	}

	public void affComplet()
	{
		int i,j,t;
		boolean rempli;
		JPanel casePleine;
		
		for (i=9;i>=-1;i--) //! rows
		{
			for (j=-1;j<=9;j++) //! columns
			{
				//!si coin inferieur gauche
				if (i == -1 && j == -1)
					this.zoneAffichage.add(new JLabel());
				//!si axe des ordonnees
				else if (j == -1)
					this.zoneAffichage.add(new JLabel(Integer.toString(i), JLabel.CENTER));
				//!si axe des abscisses
				else if (i == -1)
					this.zoneAffichage.add(new JLabel(Integer.toString(j), JLabel.CENTER));
				//!si case du graph
				else
				{
					rempli = false;
					for (t=0;t<this.k.structVerticale.size() && !rempli;t++) //! add colored square for each value in the composante
					{
						if (this.k.composantes.get(j).get(t)[0] == i)
						{
							casePleine = new JPanel();
							casePleine.setBackground(this.couleurs[this.k.composantes.get(j).get(t)[1]].getBackground());
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

	public void affPartiel(int[] tab) //! same logique as above but by selecting the column
	{
		int i,j,t;
		boolean rempli;
		JPanel casePleine;
		
		for (i=9;i>=-1;i--)
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
					this.zoneAffichage.add(new JLabel(Integer.toString(tab[j]), JLabel.CENTER));
				//si case du graph
				else
				{
					rempli = false;
					for (t=0;t<this.k.structVerticale.size() && !rempli;t++)
					{
						if (this.k.composantes.get(tab[j]).get(t)[0] == i)
						{
							casePleine = new JPanel();
							casePleine.setBackground(this.couleurs[this.k.composantes.get(tab[j]).get(t)[1]].getBackground());
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
			this.textHaut.setText("Affichage du kaleidos");
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
				String str;
				String[] tabStr;

				str = this.jtfPartiel.getText();
				tabStr = str.split(" ");
				
				tab = new int[tabStr.length];
				for (i=0;i<tabStr.length;i++)
				{
					tab[i] = Integer.parseInt(tabStr[i]);
				}
				
				this.zoneAffichage.removeAll();
				this.affPartiel(tab);
				this.zoneAffichage.repaint();
				this.textHaut.setText("Affichage partiel du kaleidos");
				this.zoneErreur.setText("Affichage actuel : "+this.jtfPartiel.getText());
				this.jtfPartiel.setText("<composantes a afficher, separees par un espace>");
			}
			else // si le format de l'entree est incorrect
			{
				this.jtfPartiel.setText("<composantes a afficher, separees par un espace>");
				this.zoneErreur.setText("Erreur, format de saisie incorrect (transposition)");
			}
		}
		else
			System.out.println("Erreur d'ecouteur ...");
	}
}
