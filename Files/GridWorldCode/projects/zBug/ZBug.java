/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int turns;
    private int turnSteps;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        turns = 2;
        turnSteps = 0;
        steps = length;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (turns == 1);
        else if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            if (turnSteps < turns)
            {
				turn();
				turnSteps++;
			}
			else
			{
				steps = 0;
				turnSteps = 0;
				if (turns == 2)
					turns = 3;
				else if (turns == 3)
					turns = 5;
				else if (turns == 5)
					turns = 0;
				else
					turns = 1;
			}
            
        }
        
    }
}
