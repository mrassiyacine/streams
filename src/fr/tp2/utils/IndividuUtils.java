package fr.tp2.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class IndividuUtils {
	private static Random r = new Random();
	private IndividuUtils() {}
	
	public static Individu createIndividu() {
		String nom = "Dupont" + r.nextInt(10);
		String prenom = "Jack" + r.nextInt(100);
		int taille = 150 + r.nextInt(51);
		int poids = 60 + r.nextInt(41);
		Ville lieuDeNaissance  = Ville.values()[r.nextInt(Ville.values().length)];
		int nHobbys = 1 + r.nextInt(Hobby.values().length);
		List<Hobby> hobbys = new ArrayList<>();
		for (int i=0;i< nHobbys;i++) {
			hobbys.add(Hobby.values()[r.nextInt(Hobby.values().length)]);
		}
		hobbys = hobbys.stream().distinct().collect(Collectors.toList());
		int h = r.nextInt(24) + 1;
		int j = r.nextInt(20) + 1;
		int m = r.nextInt(12);
		int y = r.nextInt(106); 
		Calendar calendar = Calendar.getInstance();
		calendar.set(y + 1900, m, j, h, 0); 
		Date dateDeNaissance = calendar.getTime();
		// ...

		return new Individu(nom,prenom,taille,dateDeNaissance,lieuDeNaissance,poids,hobbys);
	}
	
	public static List<Individu> createIndividus(int n){
		var l = new ArrayList<Individu>();
		while(n > 0) {l.add(createIndividu()); n--;}
		return l;
	}
	//Surcharge pour contrôler le type concret de la liste 
	public static List<Individu> createIndividus(int n, Function<Integer, List<Individu>> listCreator) {
	    List<Individu> list = listCreator.apply(n);
	    for (int i = 0; i < n; i++) {
	        list.add(createIndividu());
	    }
	    return list;
	}
	//surcharger pour  contrôler la source de création des individus
	public static List<Individu> createIndividus(int n, Supplier<List<Individu>> listSupplier, Supplier<Individu> individuSupplier) {
	    List<Individu> list = listSupplier.get();
	    for (int i = 0; i < n; i++) {
	        list.add(individuSupplier.get());
	    }
	    return list;
	}
	
	//for etendu pour afficher la liste des individus 
	public static void test(List<Individu> l) {
	    for (Individu individu : l) {
	        System.out.println(individu);
	    }
	}
	//surchage 
	public static void test(List<Individu> l, Consumer<Individu> action) {
	    for (Individu individu : l) {
	        action.accept(individu);
	    }
	}
	
    public static void writeToFile(Individu individu, String filePath) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
            out.println(individu);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Supplier<Individu> supplyIndividuFromFile(String filePath) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null; 
        }
        BufferedReader finalReader = reader;
        return extracted(finalReader);
    }

	private static Supplier<Individu> extracted(BufferedReader finalReader) {
		return () -> {
            try {
                String line = finalReader.readLine();
                if (line != null) {
                    return stringToIndividu(line);
                } else {
                    finalReader.close();
                    return null;
                }
            } catch (IOException | ParseException e) {
                try {
                    finalReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
                return null;
            }
        };
	}

 

	private static Individu stringToIndividu(String line) throws ParseException {
		String[] parts = line.split(" ");
	    
	    String nom = parts[2];
	    String prenom = parts[3];
	    
	    int poids = Integer.parseInt(parts[6].replace("kg,", ""));
	    
	    int taille = Integer.parseInt(parts[9].replace("cm,", ""));
	    
	    String dateStr =  parts[15] + " " + parts[16] + " " + parts[17] + " " + parts[18]+" "+parts[19]+" "+ parts[20] + " "+ parts[21];
	    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
	    Date dateDeNaissance = formatter.parse(dateStr);
		
	    Ville lieuDeNaissance = Ville.valueOf(parts[22]);
	    
	    List<Hobby> hobbys = new ArrayList<>();
	    for (int i = 27; i < parts.length; i++) {
	        hobbys.add(Hobby.valueOf(parts[i].replace(",", "")));
	    }
	    
	    return new Individu(nom, prenom, taille, dateDeNaissance, lieuDeNaissance, poids, hobbys);		
	}




	

	


}
