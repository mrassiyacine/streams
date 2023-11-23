package fr.tp2.main.individu.main;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import fr.tp2.utils.Individu;
import fr.tp2.utils.IndividuUtils;
import fr.tp2.utils.Ville;
import fr.tp2.utils.Hobby;

public class Test1 {

	public static void main(String[] args) {
        List<Individu> individus = IndividuUtils.createIndividus(1000);
        
        
        for (int i =0;i < 1000; i++) { 
        	System.out.println(individus.get(i));
   
        }
        System.out.println("Tous les individus nés en Mars 2000: ");
        individus.stream()
        		.filter(individu -> {
        			Date d = individu.getDateDeNaissance();
                    int year = d.getYear();
                    int month = d.getMonth();
                    return year == 2000 && month == 2;
        		})
        		.forEach(System.out::println);
        
        System.out.println("Les 5 premiers individus nés en Juillet 1993: ");
        individus.stream()
		.filter(individu -> {
			Date d = individu.getDateDeNaissance();
            int year = d.getYear();
            int month = d.getMonth();
            return year == 1993 && month == 6;	
		})
		.limit(5)
		.forEach(System.out::println);
        
       
        long nb_ne_1994 = individus.stream()
		.filter(individu -> {
			Date d = individu.getDateDeNaissance();
            int year = d.getYear();
            return year == 1994;	
		})
		.count();
        System.out.println("Le nombre total d’individus nés en 1994: "+ nb_ne_1994);
        
        List<Individu> plus_grand_5_né_decembre_2001 = individus.stream()
                .filter(individu -> {
                    Date d = individu.getDateDeNaissance();
                    int year = d.getYear();
                    int month = d.getMonth();
                    return year == 2001 && month == Calendar.DECEMBER;
                })
                .sorted(Comparator.comparingInt(Individu::getTaille).reversed())
                .limit(5)
                .collect(Collectors.toList());
        System.out.println("Les 5 plus grand individus nés en décembre 2001: ");
        plus_grand_5_né_decembre_2001.forEach(System.out::println);
        
        System.out.println("les 5 plus petits individus de taille ≥180cm nés en 2002: ");
        List<Individu> l = individus.stream()
                .filter(individu -> {
                    Date d = individu.getDateDeNaissance();
                    int year = d.getYear();
                    int taille = individu.getTaille();
                    return year == 2002 && taille>=180;
                })
                .sorted(Comparator.comparingInt(Individu::getTaille))
                .limit(5)
                .collect(Collectors.toList());
        l.forEach(System.out::println);
        System.out.println("les 5 plus petits individus de taille ≥180cm nés en 2002 par Ordre décroissant: ");
        Collections.reverse(l);
        l.forEach(System.out::println);
        System.out.println("Hobbys des 5 plus lourds parmi les 20 plus grands individus nés en 1999: ");
        List<Individu> plus_grand_né_1999 = individus.stream()
                .filter(individu -> {
                    return individu.getDateDeNaissance().getYear() == 1999;
                })
                .sorted(Comparator.comparingInt(Individu::getTaille).reversed())
                .limit(20)
                .collect(Collectors.toList());
        List<Individu> plus_lourd_de_plus_grand_1999 = plus_grand_né_1999.stream()
                .sorted(Comparator.comparingInt(Individu::getPoids).reversed())
                .limit(5)
                .collect(Collectors.toList());
        Set<Hobby> uniqueHobbies = plus_lourd_de_plus_grand_1999.stream()
                .flatMap(individu -> individu.getHobbys().stream())
                .distinct()
                .collect(Collectors.toSet());
        uniqueHobbies.forEach(hobby -> System.out.println(hobby));
        System.out.println("des mois de naissance des 20 plus grands individus nés en 1997: ");
        Set<Integer> uniqueMonthsOfBirth = individus.stream()
                .filter(individu -> {
                    return individu.getDateDeNaissance().getYear() == 1997;
                })
                .sorted(Comparator.comparingInt(Individu::getTaille).reversed())
                .limit(20)
                .map(individu -> {
                    return individu.getDateDeNaissance().getMonth() + 1;
                })
                .collect(Collectors.toSet());
        uniqueMonthsOfBirth.forEach(System.out::println);
	}

}

