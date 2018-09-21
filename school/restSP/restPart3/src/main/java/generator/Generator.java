package generator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Generator {

    private final int amount;
    private final String[] firstNames = {"Karsten", "Kim", "Pernille", "Per", "Nikolai"};
    private final String[] lastNames = {"Andersen", "Jensen", "Karsten", "Perlt", "Hansen"};
    private int id;
    

    public Generator(int amount, int id) {
        if(id < 1){
            throw new IllegalArgumentException("id must be bigger then 0");
        }
        this.amount = amount;
        this.id = id;
    }
    
    public String[] genJsonArr(){
        String firstName;
        String lastName;
        int age;
        String[] jsonArr = new String[amount];
        Random ra = new Random();
        for (int i = 0; i < amount; i++) {
            String json = "";
            firstName = firstNames[ra.nextInt(firstNames.length)];
            lastName = lastNames[ra.nextInt(lastNames.length)];
            age = ThreadLocalRandom.current().nextInt(17, 70 + 1);
            json += "{\"firstName\" : \"" + firstName + "\", \"lastName\" : \"" + lastName + "\", \"age\" : " + age + ", \"id\" : " + id + "}";
            if(i < amount -1) json += ",";
            id++;
            jsonArr[i] = json;
        }
        return jsonArr;
    }
    
    public static void main(String[] args) {
        Generator gen = new Generator(100, 1000);
        String[] arr  = gen.genJsonArr();
        System.out.println(arr.toString());
    }
}
