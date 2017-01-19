package GUI;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;

interface Isymbol {
	  
	void SetImage();
	ImageIcon getImage(String location,String name);
	void setValue(int v);
	int getValue();

}
