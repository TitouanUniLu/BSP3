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

public class Kaleidos {

	ArrayList<Integer> accessoires;
	ArrayList<Integer> structVerticale;
	ArrayList<ArrayList<int[]>> composantes;
	int nb_notes;
	
	public Kaleidos(Cycle c, int nb_notes)
	{
		int i;
		this.nb_notes = nb_notes;
		
		this.accessoires = new ArrayList<Integer>(); 
		this.structVerticale = new ArrayList<Integer>();
		
		for (i=0;i<c.metre;i++)
			this.accessoires.add(c.module.get(i)); //! add to the accessory list each value of the module
		this.structVerticale.add(0);  //! add to the vertical structure an entry
		for (i=0;i<c.metre;i++)
			this.structVerticale.add(c.cycle.get(0)[i]); //! add to the vertical structure the module i to the entry 0
		
		this.calculeComposantes(c); //! calculate the values of the composantes of the module
	}
	


	public void calculeComposantes(Cycle c)
	{
		int i,j;
		this.composantes = new ArrayList<ArrayList<int[]>>();
		
		for (i=0;i<nb_notes;i++)
		{
			this.composantes.add(new ArrayList<int[]>());
			for (j=0;j<this.structVerticale.size();j++)  //loop increments each value of the module by 1 and always modulo 12
			{
				this.composantes.get(i).add(new int[2]);
				this.composantes.get(i).get(j)[0] = (this.structVerticale.get(j)+i)%nb_notes;
				this.composantes.get(i).get(j)[1] = j;
			}
		}
	}
}
