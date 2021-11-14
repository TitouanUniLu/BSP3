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


import java.util.ArrayList;

public class Kaleidocycle {
	
	int taille;
	ArrayList<ArrayList<int[]>> composantes;
	ArrayList<Integer> cycle;
	int nb_notes;

	public Kaleidocycle(Cycle c, Kaleidos k, int nb_notes)
	{
		int i,j;
		this.nb_notes = nb_notes;
		this.taille = c.metre*c.phases + 1; //! size of the cycle (unique ones before it starts being repeated again)
		
		this.cycle = new ArrayList<Integer>();
		this.cycle.add(0);
		for (i=0;i<c.cycle.size();i++)  //! add cycles to the arrayList
		{
			for (j=0;j<c.cycle.get(0).length;j++)
			this.cycle.add(c.cycle.get(i)[j]);
		}
		
		this.composantes = new ArrayList<ArrayList<int[]>>();
		j=0;
		for (i=0;i<this.taille;i++) //!add the composantes to the list (calculates in the Kaleidos.java)
		{
			this.composantes.add(k.composantes.get(this.cycle.get(j++)));
		}
	}
}
