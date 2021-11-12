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


import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Onglet extends JPanel{
	
	JLabel textHaut;
	JPanel zoneAffichage;
	JPanel bas;
	JLabel basM;
	JLabel basC;
	JLabel basP;
	
	private static final long serialVersionUID = 1L;
	
	public void initBas(Cycle c)
	{
		int i;
		
		this.bas = new JPanel();
		this.basM = new JLabel("", JLabel.CENTER);
		this.basC = new JLabel("", JLabel.CENTER);
		this.basP = new JLabel("", JLabel.CENTER);
		
		this.basM.setText("Module : ["); //! Display the module at the bottom of the screen
		for (i=0;i<c.metre-1;i++)
			this.basM.setText(this.basM.getText()+" "+c.module.get(i)+" ,");
		this.basM.setText(this.basM.getText()+" "+c.module.get(i)+" ]");
		
		this.basC.setText("Composante : ["); //! Display the composante at the bottom of the screen
		for (i=0;i<c.metre-1;i++)
			this.basC.setText(this.basC.getText()+" "+c.cycle.get(0)[i]+" ,");
		this.basC.setText(this.basC.getText()+" "+c.cycle.get(0)[i]+" ]");
		
		this.basP.setText("Phases : "+Integer.toString(c.phases)); //! Display the phases at the bottom of the screen
		
		this.bas.setLayout(new GridLayout(1,3));
		this.bas.add(this.basM);
		this.bas.add(this.basC);
		this.bas.add(this.basP);
	}
	
	abstract void initHaut();
	abstract void initZoneAff();
}
