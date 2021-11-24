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


import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame {

	JTabbedPane onglets;
	OngletInfos ongletInfos;
	OngletKaleidos ongletKaleidos;
	OngletKaleidocycle ongletKaleidocycle;
	OngletKaleidosT ongletKaleidosT;
	OngletKaleidocycleT ongletKaleidocycleT;
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame(Cycle c, Kaleidocycle kc, int nb_notes)
	{	//! parametres generaux
		super("Projet de java - L2 MPCIE");
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(1100,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//! creation des onglets
		this.onglets = new JTabbedPane();
		
		this.ongletInfos = new OngletInfos();
		this.ongletKaleidos = new OngletKaleidos(c, nb_notes);
		this.ongletKaleidocycle = new OngletKaleidocycle(c,kc,nb_notes);
		this.ongletKaleidosT = new OngletKaleidosT(c, nb_notes);
		this.ongletKaleidocycleT = new OngletKaleidocycleT(c,kc,nb_notes);
		
		this.ongletInfos.rempliZoneAff(c);		
		this.ongletKaleidos.affComplet();		
		this.ongletKaleidocycle.affComplet();
		this.ongletKaleidosT.affComplet();	
		this.ongletKaleidocycleT.affComplet();
		
		this.onglets.addTab("Informations", null, this.ongletInfos, "Afficher les infos");
		this.onglets.addTab("Kaleidos", null, this.ongletKaleidos, "Afficher le kaleidos");
		this.onglets.addTab("Kaleidocycle", null, this.ongletKaleidocycle, "Afficher le kaleidocycle");
		this.onglets.addTab("Kaleidos transpose", null, this.ongletKaleidosT, "Afficher le kaleidos transpose");
		this.onglets.addTab("Kaleidocycle transpose", null, this.ongletKaleidocycleT, "Afficher le kaleidocycle transpose");
		
		this.getContentPane().add(this.onglets);
	}
}
