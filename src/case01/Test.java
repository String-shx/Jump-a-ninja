package case01;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;

public class Test
{
	public static void main(String[] args)  
	{
	    try
		{  
			new GameMain();
		}  
	    catch (FileNotFoundException | JavaLayerException e)
		{
			e.printStackTrace();
		} 
	}   
}  
  