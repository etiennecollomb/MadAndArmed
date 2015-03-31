package com.geekmecrazy.madandarmed.IA;

import com.geekmecrazy.madandarmed.Game.Element.GameElement;
import com.geekmecrazy.madandarmed.Game.Element.Vehicle;


public abstract class PathFinding extends GameElement {

	public abstract void calculate(Vehicle v);
		
}
