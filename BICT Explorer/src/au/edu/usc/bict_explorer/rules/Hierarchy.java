package au.edu.usc.bict_explorer.rules;

import java.util.ArrayList;

import javafx.scene.control.CheckBox;

class Hierarchy {

	private ArrayList<Hierarchy>  childs=new ArrayList<>();
	CheckBox checkbox=null;
	 
	public ArrayList<Hierarchy> getChild()
	{
		return childs;
	}

	public Hierarchy(CheckBox checkbox) {
		this.checkbox = checkbox;
	}
	
	public CheckBox getCheckBox()
	{
		return checkbox;
	}
}
