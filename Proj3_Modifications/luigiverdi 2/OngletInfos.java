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

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class OngletInfos extends Onglet {

	JLabel module;
	JLabel metre;
	JLabel composante;
	JLabel base;
	JLabel cycle;
	JLabel kcycle;
	JLabel phases;
	
	private static final long serialVersionUID = 1L;
	
	public OngletInfos()
	{
		this.setLayout(new BorderLayout());
		
		this.initHaut();
		this.initZoneAff();
		
		this.add(this.textHaut, BorderLayout.NORTH);
		this.add(this.zoneAffichage, BorderLayout.CENTER);
	}
	
	public void initHaut()
	{
		this.textHaut = new JLabel("Informations sur le module saisi", JLabel.CENTER);
		this.textHaut.setPreferredSize(new Dimension(0,50));
	}

	public void initZoneAff()
	{		
		this.module = new JLabel("     Module :");
		this.metre = new JLabel("     Metre :");
		this.composante = new JLabel("     Composante :");
		this.base = new JLabel("     Base :");
		this.cycle = new JLabel("     Cycle :");
		this.kcycle = new JLabel("    Kaleidocycle :");
		this.phases = new JLabel("     Phases :");
		
		this.zoneAffichage = new JPanel();
		this.zoneAffichage.setLayout(new GridLayout(6,1));
		
		this.zoneAffichage.add(this.module);
		this.zoneAffichage.add(this.metre);
		this.zoneAffichage.add(this.composante);
		this.zoneAffichage.add(this.base);
		this.zoneAffichage.add(this.cycle);
		this.zoneAffichage.add(this.kcycle);
		this.zoneAffichage.add(this.phases);
	}
	
	public void rempliZoneAff(Cycle c, Kaleidocycle kc) //! add the values to the JLabel attributes
	{
		int i,j;
		
		this.module.setText(this.module.getText()+" [ ");
		for (i=0;i<c.metre-1;i++)
			this.module.setText(this.module.getText()+c.module.get(i)+" , ");
		this.module.setText(this.module.getText()+c.module.get(i)+" ]");
		
		this.metre.setText(this.metre.getText()+" "+c.metre);
		
		this.composante.setText(this.composante.getText()+" [ ");
		for (i=0;i<c.metre-1;i++)
			this.composante.setText(this.composante.getText()+c.cycle.get(0)[i]+" , ");
		this.composante.setText(this.composante.getText()+c.cycle.get(0)[i]+" ]");
		
		this.base.setText(this.base.getText()+" "+c.base);
		
		this.cycle.setText(this.cycle.getText()+" 0 -");
		for (i=0;i<c.cycle.size();i++)
		{
			this.cycle.setText(this.cycle.getText()+" [ ");
			for (j=0;j<c.metre-1;j++)
				this.cycle.setText(this.cycle.getText()+c.cycle.get(i)[j]+" , ");
			this.cycle.setText(this.cycle.getText()+c.cycle.get(i)[j]+" ]");
		}

		this.kcycle.setText(this.kcycle.getText());
		System.out.println(Arrays.deepToString(kc.composantes.stream().map(u ->  u.toArray(int[][]::new)).toArray(int[][][]::new)));
		System.out.println(kc.composantes.get(0));
		for (i=0; i<kc.composantes.size(); i++)
		{
			this.kcycle.setText((this.kcycle.getText())+" [ ");
			for (j=0; j<kc.composantes.get(i).size()-1; j++)
				this.kcycle.setText(this.kcycle.getText()+kc.composantes.get(i).get(j)+" ,");
			this.kcycle.setText(this.kcycle.getText()+kc.composantes.get(i).get(j)+" ]");
		}
		
		this.phases.setText(this.phases.getText()+" "+c.phases);
	}
}
