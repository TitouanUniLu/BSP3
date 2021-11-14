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

public class Cycle {
	
	int metre; //! nombre d'entiers
	ArrayList<Integer> module; //! liste des entiers du module
	ArrayList<int[]> cycle; //! liste des composantes[col][lignes] obtenues, formant le cycle
	int phases; //! nbr de composantes pour avoir un cycle
	int base; //! le %12 de la somme des elements du module
	int nb_notes;

	public Cycle(String[] entiers, int nb_notes)
	{
		int i;
		this.metre = entiers.length;
		this.nb_notes = nb_notes;
		
		this.module = new ArrayList<Integer>();
		this.base = 0;
		for (i=0; i<this.metre;i++)
		{//! conversion au fur et a mesure String -> Integer
			this.module.add(Integer.parseInt(entiers[i])); //! enregistrement du module
			this.base += Integer.parseInt(entiers[i]); //! calcul de la somme des elements du module
		}
		this.base = this.base%nb_notes; //! mise au %12 pour obtenir la base

		this.calculCycle();

		this.calculPhases();
	}

	public void calculComposante(int x)
	{
		int i;
		int j;
		
		this.cycle.add(new int[this.metre]); //! ajout d'une colonne a la liste
		
		if (x == 0) //! si on a affaire a la 1ere composante, composante_0
		{
			for (i=0; i<this.metre; i++)
			{
				this.cycle.get(x)[i] = this.module.get(0);
				for (j=1; j<=i; j++)
					this.cycle.get(x)[i] += this.module.get(j); //! calcul de la somme cumulative
				this.cycle.get(x)[i] = this.cycle.get(x)[i]%nb_notes; //! calcul du %12 de la somme
			}
		}
		else //! si on a affaire a composante_x , x>=1
		{ //! calculs identiques, en ajoutant a chaque terme la base le composante_(x-1)
			for (i=0; i<this.metre; i++)
			{
				this.cycle.get(x)[i] = this.module.get(0) + this.cycle.get(x-1)[this.metre-1]; //! ajout de la nouvelle base
				for (j=1; j<=i; j++)
					this.cycle.get(x)[i] += this.module.get(j); //! calcul de la somme cumulative
				this.cycle.get(x)[i] = this.cycle.get(x)[i]%nb_notes; //! calcul du %12 de la somme
			}
		}
	}
	
	public void calculPhases()
	{
		this.phases = this.cycle.size();
	}
	
	public void calculCycle()
	{
		int i = 0;
		this.cycle = new ArrayList<int[]>();
		
		calculComposante(i); //! calcul de la composante_0
		while (this.cycle.get(i)[this.metre-1] != 0) //! on recommence tant qu'aucune composante ne finit par 0
		{
			i++;
			calculComposante(i);
		}
	}
	public int getBase() {
		return this.base;
	}
	public int getNotes() {
		return this.nb_notes;
	}
}
