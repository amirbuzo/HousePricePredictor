package com.price.predict.predict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProccessing {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws FileNotFoundException, IOException {
		String files = "C:\\Users\\abuzo\\git\\HousePricePredictor\\src\\main\\resources\\originalsource\\data.txt";
		File file = new File(files);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			int i = 0;
			String text = "";
			int j = 0;
			Set<String> set = new HashSet<>();

			while ((line = br.readLine()) != null) {

				if (line.contains("Tirane")) {
					i++;
					// System.out.println(line);
					if (findPrice(line) != null && findAddress(line) != null && findSquare(line) != null) {
						// System.out.println(j++ + line);

						// System.out.print(j);
						//System.out.print(i+",");

						System.out.print(findSquare(line));
						System.out.print(",no,");
						System.out.print("yes,");
						System.out.print("estate,");
						System.out.print("me_hipoteke,");
						System.out.print("2020");
						System.out.print("," + findAddress(line));

						System.out.print("," + findType(line));
						System.out.print("," + findPrice(line));
						System.out.println();
						set.add(findAddress(line));
					} else {
//						if(findPrice(line) != null && findSquare(line)  !=null)
//						 {j++;
//						 System.out.println( j+ line);
//						 }
					}
				}

			}
			List l = new ArrayList(set);
			Collections.sort(l);
			System.out.println(l);

		}
	}

	public static String furnished(String m) {
		if (m.contains("mobilu")) {
			return "yes";
		} else
			return "no";
	}

	public static String legalized(String m) {
		if (m.contains("hipotek")) {
			return "me_hipoteke";
		} else
			return "me_hipoteke";
	}

	public static String findSquare(String m) {
		String rx = "\\d+(?:,\\d+)?\\s*m\\u00B2";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(m);
		if (matcher.find()) {
			String ret= matcher.group().replace("m²", "").trim();
			if(isNumeric(ret) && Integer.parseInt(ret) <200)
			  return ret;
		}
		return null;
	}
	
	private static Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false; 
	    }
	    return pattern.matcher(strNum).matches();
	}

	public static String findType(String m) {
		String rx = "(2\\+1|1\\+1)";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(m);
		if (matcher.find()) {
			matcher.group();
			if (matcher.group().contains("1+1")) {
				return "one_plus_one";
			} else {
				return "two_plus_one";
			}
		}

		return "two_plus_one";

	}

	public static String findPrice(String m) {
		String rx = "\\d+(?:.\\d+)?\\s*Euro";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(m);
		if (matcher.find()) {
			return matcher.group().trim().replace("Euro", "").replace(".", "").trim();
		}
		return null;
	}

	public static String findAddress(String m) {
 
//		10Qender–Posta_LidhjaShkrimtareve	
//		2Piramida_ETC_TVSH_Stadiumi	
//		2TEG_Lunder_Farke_MjullBathore
//		6PallatimeShigjeta_Delijorgji			
//		7Ekspozita_GjykataRrethit
//		7Parku_Globe_FabrikaBukes
//		8ConcordCenter_5Maji

		
		String rx = "(?<=\\().+?(?=\\))";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(m);
		if (matcher.find()) {
			String text = matcher.group();
			if (text.toLowerCase().contains("fres") || text.toLowerCase().contains("kuka")
					|| text.toLowerCase().contains("sotir caci") || text.toLowerCase().contains("dajt")
					|| text.toLowerCase().contains("thesar") || text.toLowerCase().contains("deliu")
					|| text.toLowerCase().contains("telefer")
					|| text.toLowerCase().contains("fresku")) {
				return "3Fresku_Linze_Dajt";
			} else if (text.toLowerCase().contains("brryl")) {
				return "3Ministriaejashtme_Brryli";

			} else if (text.toLowerCase().contains("ali") || text.toLowerCase().contains("dem")  || 
					  text.toLowerCase().contains("elektr")
				 ) {
				return "1AliDemi_TreguElektrik";

			} else if (text.toLowerCase().contains("laprak") || text.toLowerCase().contains("dritan")) {
				return "11Laprake_Spitaliushtarak_Dogana";

			} else if (text.toLowerCase().contains("komun") || text.toLowerCase().contains("paris")
					|| text.toLowerCase().contains("kristal") || text.toLowerCase().contains("shtylla")) {
				return "5KomunaParisit_KristalCenter";

			}
			else if((text.toLowerCase().contains("liqen")
					&& text.toLowerCase().contains("thate") || text.toLowerCase().contains("botanik")))
			{
				return "5Liqenithate_KopshtiBotanik";
			}
		  else if (text.toLowerCase().contains("liqen") || text.toLowerCase().contains("kosovar")
					|| text.toLowerCase().contains("petro") || text.toLowerCase().contains("nini")
				     ) {
				return "5Liqeni_Rr.Kosovareve_PetroNini";

			}  else if (text.toLowerCase().contains("bosko") || text.toLowerCase().contains("misja")
					|| text.toLowerCase().contains("bosco")) {
				return "9DonBosko_JordanMisja";

			} else if (text.toLowerCase().contains("yzber") || text.toLowerCase().contains("ysber") || text.toLowerCase().contains("misto")) {
				return "6Yzberisht_MistoMame";

			} else if (text.toLowerCase().contains("bardhyl") || text.toLowerCase().contains("siri")
					|| text.toLowerCase().contains("cerova")) {
				return " 8Rr.Bardhyl_SiriKodra_RizaCerova";

			}
			else if(text.toLowerCase().contains("aviacionit")
					|| text.toLowerCase().contains("aviacjonit"))
			{
				return "7FushaAviacionit";
			}
			else if(text.toLowerCase().contains("barrikada")
					|| text.toLowerCase().contains("katshet")
					|| text.toLowerCase().contains("pazar i ri")
					|| text.toLowerCase().contains("avni rustemi"))
			{
				return "2Pazariiri_9katshet_Barrikada";
			}
			
			
			else if (text.toLowerCase().contains("21") || text.toLowerCase().contains("kavaj")
  				 || text.toLowerCase().contains("dhjetor")
				  ) {
				return "7Rr.eKavajes_21Dhjetori";

			} else if (text.toLowerCase().contains("kombina")) {
				return "6Kombinat_Vaqarr_Ndroq";

			} else if (text.toLowerCase().contains("kamez") || text.toLowerCase().contains("kamz")|| text.toLowerCase().contains("paskuqan")) {
				return "11Instituti_Kamez_Paskuqan";

			} else if (text.toLowerCase().contains("selite") || text.toLowerCase().contains("diell")) {
				return "5Selite_KodraeDiellit";

			} 
			else if( text.toLowerCase().contains("myslym") ||  text.toLowerCase().contains("shyri")  ||
					 text.toLowerCase().contains("drejtoria e policise"))
			{
				return "7MyslymShyri_DrejtoriaPolicise";
			}
			else if( text.toLowerCase().contains("fortuzi") ||  text.toLowerCase().contains("mine")  ||
					 text.toLowerCase().contains("peza"))
			{
				return "9Rr.Fortuzi_MinePeza";
			}
			else if( text.toLowerCase().contains("dinamo") ||  text.toLowerCase().contains("wilson")  ||
					 text.toLowerCase().contains("willson"))
			{
				return "5StadiumiDinamo_SheshiWillson";
			}
			
 
			else if (text.toLowerCase().contains("shanto") || text.toLowerCase().contains("tomin")
					|| text.toLowerCase().contains("kesh") || text.toLowerCase().contains("sykja")
					|| text.toLowerCase().contains("tirana e re")

			) {
				return "5VasilShanto_BllokuVilaveSelite";

			} else if (text.toLowerCase().equals("Pasho Hysa") || text.toLowerCase().contains("shkoz")
					|| text.toLowerCase().contains("pasho") || text.toLowerCase().contains("hysa")) {
				return "1Poligrafiku_Autotraktoret_Shkoze";

			} else if (text.toLowerCase().contains("kinostudio") || text.toLowerCase().contains("porcelan")
					|| text.toLowerCase().contains("klan")) {
				return "4Kinostudio_Porcelan";

			} else if (text.toLowerCase().contains("astir") || text.toLowerCase().contains("teodor")
					|| text.toLowerCase().contains("unaze") || text.toLowerCase().contains("unaza")) {
				return "6Astir_Unazaere_TeodorKeko";

			} else if (text.toLowerCase().contains("xhamllik") || text.toLowerCase().contains("xhanfize")
					|| text.toLowerCase().contains("manastir") || text.toLowerCase().contains("varr")
					|| text.toLowerCase().contains("oxhak") || text.toLowerCase().contains("profarm")) {
				return "3Xhamlliku_Oxhaku_Profarma";

			} else if (text.toLowerCase().contains("zog") || text.toLowerCase().contains("zi")
					|| text.toLowerCase().contains("fultz") || text.toLowerCase().contains("hipotek")
					|| text.toLowerCase().contains("trenit")) {
				return "9StacioniTrenit_ZoguiZi";

			} else if (text.toLowerCase().startsWith("elbasan") || text.toLowerCase().contains("balet")
					|| text.toLowerCase().contains("lice") || text.toLowerCase().contains("elbas")) {
				return "2Rr.eElbasanit_ShkollaBaletit";

			} else if (text.toLowerCase().contains("diber") || text.toLowerCase().contains("dibre")
					|| text.toLowerCase().contains("partizan") || text.toLowerCase().contains("kuqe")
					|| text.toLowerCase().contains("selvi") ) {
				return "8Selvia_Rr.eDibres_Gj.Partizani";

			} else if (text.toLowerCase().contains("durres") || text.toLowerCase().contains("durrë")
					|| text.toLowerCase().contains("peza") || text.toLowerCase().contains("bogdaneve")) {
				return "10Rr.Durresit_Prokuroria";

			}
			else if(text.toLowerCase().contains("bllok"))
			{
				return "5Blloku";
			}
			else if (text.toLowerCase().contains("allias") || text.toLowerCase().contains("tufine")) {
				return "4Spitali_Allias_Tufine";

			} else if (text.toLowerCase().contains("budi") || text.toLowerCase().contains("studenti") || text.toLowerCase().contains("sauk")) {
				return "2QytetiStudenti_Sauk";

			} else if (text.toLowerCase().contains("ndertim") || text.toLowerCase().contains("inxhi") || text.toLowerCase().contains("ambasadave")) {
				return "7BllokuAmbasadave_Inxh.Ndertimit";

			} else if (text.toLowerCase().startsWith("medreseja") || text.toLowerCase().contains("industrial")
					|| text.toLowerCase().contains("maji") || text.toLowerCase().contains("medres")
					|| text.toLowerCase().contains("ferit") || text.toLowerCase().contains("farmacia")
					|| text.toLowerCase().contains("4 deshmoret")) {
				return "8Medreseja_TreguIndustrial";

			} else {
				return null;
			}
		}
		return null;
	}

}
