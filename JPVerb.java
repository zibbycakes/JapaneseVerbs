import java.util.*;
import java.io.*;

public class JPVerb
{
	private int type;
	private String polite;
	//use this teMap for type 1 verbs to convert to te form
	private TreeMap<String[],String> teMap;

	public JPVerb()
	{
		type = 0;
		polite = "";
		initTeMap();

	}

	//assumes all input will be of polite, non-past, affirmative form
	//ex - ikimasu, not iku
	//ex - shimasu, not suru
	public JPVerb(int t, String b)
	{
		type = t;
		polite = b;
		initTeMap();
	}

	public void initTeMap()
	{
		teMap = new TreeMap<String[],String>();
		
		System.out.println("Made it inside initTe");

		String[] test = new String[1];

		test[0] = "test1";

		System.out.println("Inb4 fail");

		teMap.put(test, "test2");

		System.out.println("Made it past the test");

		teMap.put(new String[] {"u","tsu","ru"}, "tte");

		System.out.println("Made it past first put in initTe");

		teMap.put(new String[] {"mu","bu","ru"}, "nde");

		teMap.put(new String[] {"ku"}, "ite");

		teMap.put(new String[] {"gu"}, "ide");

		teMap.put(new String[] {"su"}, "shite");

		teMap.put(new String[] {"ku"}, "ite");
	}

	//returns the polite, non-past, affirmative form of this verb
	public String poNoAf() 
	{
		return polite;
	}

	//returns the polite, non-past, negative form of this verb
	public String poNoNeg() 
	{
		//change su to sen
		return polite.substring(0,polite.length()-2) + "sen";
	}

	//returns the polite, past, affirmative form of this verb
	public String poPaAf()
	{
		//change su to shita
		return polite.substring(0,polite.length()-2) + "shita";
	}

	//returns the polite, past, negative form of this verb
	public String poPaNeg()
	{
		//take polite, non-past, negative form, and add deshita
		return poNoNeg() + "deshita";
	}

	//return the plain, non-past, affirmative form of this verb
	public String plNoAf()
	{
		//for u verbs (type 1), drop masu. Change last syllable to u column. Special care for chi -> tsu
		//for ru verbs (type 2), change masu to ru
		//for irregular (type 3), hardcode (only 2)

		//remove masu
		String changed = polite.substring(0,polite.length()-4);
		switch(type)
		{
			case 1:
			{
				if(changed.indexOf("chi") > 0)
				{
					changed = changed.substring(0, changed.length()-3) + "tsu";
				}
				else
				{
					changed = changed.substring(0, changed.length()-1) + "u";
				}
				break;
			}
			case 2:
				changed += "ru"; break;
			case 3:
			{
				if(polite.equals("shimasu"))
					changed = "suru";
				if(polite.equals("kimasu"))
					changed = "kuru";
				break;
			}
		}
		return changed;
	}

	//returns the plain, non-past, negative form of this verb
	public String plNoNeg()
	{
		//for u verbs (type 1). Change last syallable to a column. Special care for tsu -> ta, and for u -> wa. Add nai.
		//for ru verbs (type 2), drop ru from plNoAf(). Add nai.
		//for irregular (type 3), hardcode (only 2 of them)

		//remove ru
		String plnoaf = plNoAf();
		String changed = plnoaf.substring(0,plnoaf.length()-2);
		switch(type)
		{
			case 1:
			{
				if(plnoaf.indexOf("tsu")>0)
				{
					changed = plnoaf.substring(0,plnoaf.length()-3) + "ta";
				}
				else if(plnoaf.equals("au"))
				{
					changed = "awa";
				}
				else
				{
					changed = plnoaf.substring(0, plnoaf.length()-1) + "a";
				}
				changed += "nai";
				break;
			}
			case 2:
				changed += "nai"; break;
			case 3:
			{
				if(plnoaf.equals("suru"))
					changed = "shinai";
				if(plnoaf.equals("kuru"))
					changed = "konai";
				break;
			}
		}
		return changed;
	}

	//returns the te form of the verb
	public String te()
	{
		//refer to map
		//u, tsu, ru (class 1) - tte
		//mu, bu, nu (class 1) - nde
		//ku (class 1) - ite
		//gu (class 1) - ide
		//su (class 1) - shite

		//kuru (class 3) - kite
		//suru (class 3) - shite
		//iku (class 1) - itte
		//iru (class 2) - ite
		//eru (class 2) - ete
		return "";
	}

	//returns the plain, past, affirmative form of this verb
	public String plPaAf()
	{
		//take te form and change te -> ta and de -> da
		return "";
	}

	public String toString()
	{
		return "Class " + type + " verb, " + polite;
	}

	public TreeMap<String[], String> getMap()
	{
		return teMap;
	}
}
