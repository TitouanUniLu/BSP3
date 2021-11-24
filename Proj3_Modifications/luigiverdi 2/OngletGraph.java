/***************************************************************************
 *   Copyright (C) 2021-22 by Titouan Guerin and Giacomo di Tollo           *
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


import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class OngletGraph extends Onglet implements ActionListener{

	JPanel[] couleurs;
	JPanel haut;
	JPanel hautDroit;
	JButton boutonPartiel;
	JButton boutonComplet;
	JLabel zoneErreur;
	JFormattedTextField jtfPartiel;
	
	private static final long serialVersionUID = 1L;
	
	public void initCouleurs() //! Initialise le tableau de couleurs
	{
		this.couleurs = new JPanel[12]; //! amount of rows
		this.couleurs[0] = new JPanel();
		this.couleurs[0].setBackground(Color.red);
		this.couleurs[1] = new JPanel();
		this.couleurs[1].setBackground(Color.green);
		this.couleurs[2] = new JPanel();
		this.couleurs[2].setBackground(Color.blue);
		this.couleurs[3] = new JPanel();
		this.couleurs[3].setBackground(Color.orange);
		this.couleurs[4] = new JPanel();
		this.couleurs[4].setBackground(Color.cyan);
		this.couleurs[5] = new JPanel();
		this.couleurs[5].setBackground(Color.gray);
		this.couleurs[6] = new JPanel();
		this.couleurs[6].setBackground(Color.magenta);
		this.couleurs[7] = new JPanel();
		this.couleurs[7].setBackground(Color.darkGray);
		this.couleurs[8] = new JPanel();
		this.couleurs[8].setBackground(Color.pink);
		this.couleurs[9] = new JPanel();
		this.couleurs[9].setBackground(Color.black);
		this.couleurs[10] = new JPanel();
		this.couleurs[10].setBackground(Color.lightGray);
		this.couleurs[11] = new JPanel();
		this.couleurs[11].setBackground(Color.yellow);
	}

	abstract void initZoneAff();
	abstract void initHaut();
	abstract void affComplet();
	abstract void affPartiel(int[] tab);

}
