import java.util.*;
import java.io.*;

public class JPVerbRunner
{
	public static void main(String[] args)
	{
		System.out.println("Hello World");
		JPVerb test = new JPVerb(1, "aimasu");
		System.out.println(test);
		System.out.println(test.poNoAf());
		System.out.println(test.poNoNeg());
		System.out.println(test.poPaAf());
		System.out.println(test.poPaNeg());

		System.out.println(test.plNoAf());
		System.out.println(test.plNoNeg());
		//System.out.println(test.plPaAf());
		//System.out.println(test.plPaNeg());

		System.out.println(test.getMap());
	}
}
