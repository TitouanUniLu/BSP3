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


public class main {

	public static void main(String[] args)
	{
		if (args.length != 0)
		{
      int new_base = 12;

			Cycle c = new Cycle(args, new_base);
			Kaleidos k = new Kaleidos(c, new_base);
			Kaleidocycle kc = new Kaleidocycle(c, k, new_base);
      System.out.println(c.getBase());
			
			MainFrame fenetre = new MainFrame(c, kc, new_base);
		}
		else
			System.out.println("Pas d'arguments!");
	}
}
