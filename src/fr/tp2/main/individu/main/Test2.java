package fr.tp2.main.individu.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import fr.tp2.utils.Individu;
import fr.tp2.utils.IndividuUtils;

public class Test2 {

	public static void main(String[] args) {
		//Question 1
		List<Individu> array_individus = IndividuUtils.createIndividus(10, n -> new ArrayList<>(n));
		List<Individu> linkedList_individus = IndividuUtils.createIndividus(10, n -> new LinkedList<>());

		//Question 2
        System.out.println("===================================================== Question2:");

		List<Individu> unique_individus = IndividuUtils.createIndividus(5, ArrayList::new, IndividuUtils::createIndividu);
		
		Individu fixe_individu = IndividuUtils.createIndividu();
		Supplier<Individu> fixe_individu_supplier = () -> fixe_individu;
		List<Individu> individusIdentiques = IndividuUtils.createIndividus(5, ArrayList::new, fixe_individu_supplier);
		
		//Question 3
        System.out.println("===================================================== Question3:");

        List<Individu> individus = IndividuUtils.createIndividus(1000);
        //IndividuUtils.test(individus);
        
        // Question 4 
        System.out.println("===================================================== Question4:");

    	Consumer<Individu> affichage = individu -> {
    	    System.out.println(individu); 
    	    System.out.println("Hobbys: " + individu.getHobbys().stream().map(Enum::name).collect(Collectors.joining(", ")));
    	 };

        List<Individu> test_individus = IndividuUtils.createIndividus(5, ArrayList::new, IndividuUtils::createIndividu);
        IndividuUtils.test(test_individus, affichage);
        
        //Question 5
        System.out.println("===================================================== Question5:");

        String filePath = "data/file.txt";
        Consumer<Individu> saveToFile = individu -> IndividuUtils.writeToFile(individu, filePath);
        IndividuUtils.test(test_individus, saveToFile);

        
        //Question 6
        System.out.println("===================================================== Question6:");
        Supplier<Individu> supplier  = IndividuUtils.supplyIndividuFromFile("data/individusFile.txt");
        List<Individu> individuFromFile = new ArrayList<>();
        Individu individu;
        while (( individu = supplier.get()) != null) {
        	
            System.out.println(individu);        
        }
        IndividuUtils.test(individuFromFile);

	}

}
