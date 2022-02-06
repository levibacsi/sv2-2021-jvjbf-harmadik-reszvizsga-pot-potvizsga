package skirental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class SkiRental {
    private Map<String, Equipment> rentals = new TreeMap<>();

    public Map<String, Equipment> getRentals() {
        return rentals;
    }

    public void loadFromFile(Path path){
        try {
            List<String> input = new ArrayList<>(Files.readAllLines(path));
            for (int i = 1; i < input.size(); i++){
                String[] temp = input.get(i).split(";");
                String name = temp[0];
                String[] sizes = temp[1].split(" ");
                int skis = Integer.parseInt(sizes[0]);
                int boots = Integer.parseInt(sizes[1]);

                rentals.put(name, new Equipment(skis, boots));
            }

        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file: " + path,ioe);
        }
    }


    public List<String> listChildren(){
        return rentals.entrySet().stream()
                .filter(e->e.getValue().getSizeOfBoot() <= 37 &&
                        e.getValue().getSizeOfBoot() >0 &&
                        e.getValue().getSizeOfSkis() <= 120)
                .map(Map.Entry::getKey)
                .toList();
    }

    public String getNameOfPeopleWithBiggestFoot(){
        return rentals.entrySet().stream()
                .filter(e->e.getValue().getSizeOfSkis() > 0 &&
                        e.getValue().getSizeOfBoot() > 0)
                .max(Comparator.comparingInt(e->e.getValue().getSizeOfBoot()))
                .orElseThrow(()-> new IllegalStateException("Biggest foot not found"))
                .getKey();
    }


}
