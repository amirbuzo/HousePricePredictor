package scrapping;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenCsvExample {

	private static Set<String> modeli = new HashSet<>();
	private static Set<String> modeli1 = new HashSet<>();
	private static Set<String> modeli2 = new HashSet<>();
	private static Set<String> modeli3 = new HashSet<>();
	private static Set<String> modeli4 = new HashSet<>();
	private static Set<String> modeli5 = new HashSet<>();
	private static Set<String> modeli6 = new HashSet<>();
	private static Set<String> modeli7 = new HashSet<>();
	private static Set<String> modeli8 = new HashSet<>();
	private static Set<String> modeli9 = new HashSet<>();
	private static Set<String> modeli10 = new HashSet<>();

	public static void main(String[] args) throws IOException, CsvException {
 
		String fileName = "c:\\temp\\merged.csv";
		try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
			List<String[]> r = reader.readAll();
			int i = 0;
			for (String[] strings : r) {
				try {
					// System.out.print(i++);
					if (strings[1].contains("EUR") && !strings[1].isEmpty() && !strings[1].isEmpty()
							&& !strings[13].isEmpty() && !strings[14].isEmpty()) {
						String price = strings[1].replaceAll(" ", "").replaceAll(" ", "");
						Matcher matcher = Pattern.compile("\\d+").matcher(price);
						Boolean found = matcher.find();
						int pric = Integer.valueOf(matcher.group());
						
					 
						if (found && pric > 399 &&   pric < 100000  && modeli10.add(strings[0]) ) {
							 
							if (strings[3].equals("Shitet") && strings[4].contains("Person fizik") && Integer.parseInt(strings[5]) >1980) {
								System.out.println("");
								process(strings[4].replaceAll(" ", "") + ",");
								process(strings[5].replaceAll(" ", "") + ",");
								process(strings[6].replaceAll(" ", "") + ",");
								process(strings[7].replaceAll(" ", "") + ",");
								process(strings[8].replaceAll(" ", "") + ",");
								process(strings[9].replaceAll(" ", "") + ",");
								process(strings[10].replaceAll(" ", "") + ",");
								process(strings[11].replaceAll(" ", "") + ",");
								process(strings[12].replaceAll(" ", "") + ",");
								if (strings.length > 13)
									process(strings[13].replaceAll(" ", "") + ",");

								process(strings[14].replaceAll(" ", "") + ",");

								modeli.add(processNoPrint(strings[5]));
								modeli1.add(processNoPrint(strings[6]));
								modeli2.add(processNoPrint(strings[7]));
								modeli3.add(processNoPrint(strings[8]));
								modeli4.add(processNoPrint(strings[9]));
								modeli5.add(processNoPrint(strings[10]));
								modeli6.add(processNoPrint(strings[11]));
								modeli7.add(processNoPrint(strings[12]));
								modeli8.add(processNoPrint(strings[13]));
								modeli9.add(processNoPrint(strings[14]));
							}
							if (strings[5].contains("Kompani") && Integer.parseInt(strings[6]) >1980) {
								System.out.println("");
								process(strings[5].replaceAll(" ", "") + ",");
								process(strings[6].replaceAll(" ", "") + ",");
								process(strings[7].replaceAll(" ", "") + ",");
								process(strings[8].replaceAll(" ", "") + ",");
								process(strings[9].replaceAll(" ", "") + ",");
								process(strings[10].replaceAll(" ", "") + ",");
								process(strings[11].replaceAll(" ", "") + ",");
								process(strings[12].replaceAll(" ", "") + ",");
								process(strings[13].replaceAll(" ", "") + ",");
								process(strings[14].replaceAll(" ", "") + ",");
								process(strings[15].replaceAll(" ", "") + ",");

							}
							System.out.print(
									"" + pric);
						}
					}

				} catch (Exception e) {
				}
			}
			System.out.println(i);


		}
		System.out.println(modeli);
		System.out.println(modeli1);
		System.out.println(modeli2);
		System.out.println(modeli3);
		System.out.println(modeli4);
		System.out.println(modeli5);
		System.out.println(modeli6);
		System.out.println(modeli7);
		System.out.println(modeli8);
		System.out.println(modeli9);
	}

	private static void process(String arg) {
		arg = arg.replaceAll("ë", "e");
		arg = arg.replaceAll(" ", "");
		arg = arg.replaceAll("/", "-");
		arg = arg.replaceAll("\\(", "");
		arg = arg.replaceAll("\\)", "");
		System.out.print(arg);
	}

	private static String processNoPrint(String arg) {
		arg = arg.replaceAll("ë", "e");
		arg = arg.replaceAll(" ", "");
		arg = arg.replaceAll("/", "-");
		arg = arg.replaceAll("\\(", "");
		arg = arg.replaceAll("\\)", "");
		return arg;
	}
}