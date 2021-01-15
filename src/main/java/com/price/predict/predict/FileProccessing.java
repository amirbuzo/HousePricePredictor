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
		String files = "C:\\Users\\abuzo\\git\\HousePricePredictor\\src\\main\\resources\\static\\data.txt";
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

						System.out.print(findSquare(line));
						System.out.print(",no,");
						System.out.print("yes,");
						System.out.print("estate,");
						System.out.print("me_hipoteke,");
						System.out.print("2010");
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
			return matcher.group().replace("m²", "").trim();
		}
		return null;
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
		String rx = "(?<=\\().+?(?=\\))";
		Pattern p = Pattern.compile(rx);
		Matcher matcher = p.matcher(m);
		if (matcher.find()) {
			String text = matcher.group();
			if (text.toLowerCase().contains("fres") || text.toLowerCase().contains("kuka")
					|| text.toLowerCase().contains("sotir caci") || text.toLowerCase().contains("dajt")
					|| text.toLowerCase().contains("fresku")) {
				return "Fresku";
			} else if (text.toLowerCase().contains("brryl")) {
				return "Brryli";

			} else if (text.toLowerCase().contains("ali") || text.toLowerCase().contains("dem")
					|| text.toLowerCase().contains("petro nini") || text.toLowerCase().contains("elektr")
					|| text.toLowerCase().contains("hysa")) {
				return "AliDemi";

			} else if (text.toLowerCase().contains("laprak") || text.toLowerCase().contains("dritan")) {
				return "Laprake";

			} else if (text.toLowerCase().contains("komun") || text.toLowerCase().contains("paris")
					|| text.toLowerCase().contains("kristal") || text.toLowerCase().contains("shtylla")) {
				return "KomuneParisit";

			}

			else if (text.toLowerCase().contains("thesar") || text.toLowerCase().contains("deliu")
					|| text.toLowerCase().contains("telefer")) {
				return "Fresku";

			} else if (text.toLowerCase().contains("liqen") || text.toLowerCase().contains("kosovar")
					|| text.toLowerCase().contains("botanik") || text.toLowerCase().contains("zooogjik")
					|| text.toLowerCase().contains("kopsht") || text.toLowerCase().contains("kopesht")
					|| text.toLowerCase().contains("qesarak")) {
				return "Liqeni";

			} else if (text.toLowerCase().contains("misto") || text.toLowerCase().contains("amerikan 3")) {
				return "MistoMame";

			} else if (text.toLowerCase().contains("bosko") || text.toLowerCase().contains("misja")
					|| text.toLowerCase().contains("bosco")) {
				return "DonBosko";

			} else if (text.toLowerCase().contains("yzber") || text.toLowerCase().contains("ysber")) {
				return "Yzberisht";

			} else if (text.toLowerCase().contains("bardhyl") || text.toLowerCase().contains("siri")
					|| text.toLowerCase().contains("cerova")) {
				return " RrBardhyl_SiriKodra_RizaCerova";

			}

			else if (text.toLowerCase().contains("21") || text.toLowerCase().contains("kavaj")
					|| text.toLowerCase().contains("sherak") || text.toLowerCase().contains("parku")
					|| text.toLowerCase().contains("frosina") || text.toLowerCase().contains("aviacionit")
					|| text.toLowerCase().contains("aviacjonit") || text.toLowerCase().contains("lenja")
					|| text.toLowerCase().contains("shigjeta") || text.toLowerCase().contains("dhjetor")
					|| text.toLowerCase().contains("ndre mjeda") || text.toLowerCase().contains("naim frasheri")
					|| text.toLowerCase().contains("globe")) {
				return "21Dhjetori";

			} else if (text.toLowerCase().contains("kombi")) {
				return "Kombinat";

			} else if (text.toLowerCase().contains("kamez") || text.toLowerCase().contains("kamz")) {
				return "Kamez";

			} else if (text.toLowerCase().contains("selite") || text.toLowerCase().contains("diell")) {
				return "Selite";

			} else if (text.toLowerCase().contains("qender") || text.toLowerCase().contains("9 kat")
					|| text.toLowerCase().contains("bllok") || text.toLowerCase().contains("barrikad")
					|| text.toLowerCase().contains("myslym") || text.toLowerCase().contains("rikadave")
					|| text.toLowerCase().contains("tefta") || text.toLowerCase().contains("zhan")
					|| text.toLowerCase().contains("toptan") || text.toLowerCase().contains("qemal")
					|| text.toLowerCase().contains("bajram curri") || text.toLowerCase().contains("hoxha tahsim")
					|| text.toLowerCase().contains("wilson") || text.toLowerCase().contains("willson")
					|| text.toLowerCase().contains("sami frasheri") || text.toLowerCase().contains("pazar")
					|| text.toLowerCase().contains("rustemi") || text.toLowerCase().contains("fishta")
					|| text.toLowerCase().contains("abdyl frasheri") || text.toLowerCase().contains("dinamo")) {
				return "Qender";

			} else if (text.toLowerCase().contains("shanto") || text.toLowerCase().contains("tomin")
					|| text.toLowerCase().contains("kesh") || text.toLowerCase().contains("sykja")
					|| text.toLowerCase().contains("tirana e re")

			) {
				return "VasilShanto";

			} else if (text.toLowerCase().equals("Pasho Hysa") || text.toLowerCase().contains("shkoz")
					|| text.toLowerCase().contains("pasho") || text.toLowerCase().contains("hysa")) {
				return "Shkoze";

			} else if (text.toLowerCase().contains("kinostudio") || text.toLowerCase().contains("porcelan")
					|| text.toLowerCase().contains("klan")) {
				return "Porcelani";

			} else if (text.toLowerCase().contains("astir") || text.toLowerCase().contains("teodor")
					|| text.toLowerCase().contains("unaze") || text.toLowerCase().contains("unaza")) {
				return "Astir";

			} else if (text.toLowerCase().contains("xhamllik") || text.toLowerCase().contains("xhanfize")
					|| text.toLowerCase().contains("manastir") || text.toLowerCase().contains("varr")
					|| text.toLowerCase().contains("oxhak") || text.toLowerCase().contains("profarm")) {
				return "Xhamlliku";

			} else if (text.toLowerCase().contains("zog") || text.toLowerCase().contains("zi")
					|| text.toLowerCase().contains("fultz") || text.toLowerCase().contains("hipotek")
					|| text.toLowerCase().contains("tren")) {
				return "ZoguZi";

			} else if (text.toLowerCase().startsWith("elbasan") || text.toLowerCase().contains("balet")
					|| text.toLowerCase().contains("lice") || text.toLowerCase().contains("elbas")) {
				return "RrElbasanit";

			} else if (text.toLowerCase().contains("diber") || text.toLowerCase().contains("dibre")
					|| text.toLowerCase().contains("partizan") || text.toLowerCase().contains("kuqe")
					|| text.toLowerCase().contains("selvi") || text.toLowerCase().contains("qsut")) {
				return "RrDibres";

			} else if (text.toLowerCase().contains("durres") || text.toLowerCase().contains("durrë")
					|| text.toLowerCase().contains("peza") || text.toLowerCase().contains("bogdaneve")) {
				return "RrDurresit";

			}

			else if (text.toLowerCase().contains("allias") || text.toLowerCase().contains("tufine")) {
				return "Allias";

			} else if (text.toLowerCase().contains("budi") || text.toLowerCase().contains("studenti")) {
				return "QytetStudenti";

			} else if (text.toLowerCase().contains("ndertim") || text.toLowerCase().contains("inxhi")) {
				return "InxhinieriNdertimit";

			} else if (text.toLowerCase().startsWith("medreseja") || text.toLowerCase().contains("edrese")
					|| text.toLowerCase().contains("maji") || text.toLowerCase().contains("medres")
					|| text.toLowerCase().contains("ferit") || text.toLowerCase().contains("farmacia")
					|| text.toLowerCase().contains("4 deshmoret")) {
				return "Medreseja";

			} else {
				return null;
			}
		}
		return null;
	}

}
