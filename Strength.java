/*
inputs needed:
- gender
- weight
- lifts (squat bench deadlift)

methods needed:
- calculate lift based on female
- calculate lift based on male

*/

import java.util.Scanner;
public class Strength{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String gender = "";
        int weight;
        int squatPR;
        int benchPR;
        int deadliftPR;

        System.out.println("--- Strength Level Calculator! ---");
        boolean validWeight = false;
        //get user inputs
        gender = getGender();
        weight = getPositionInput("Enter your weight in lbs: ");        
        squatPR = getPositionInput("Enter your Squat PR: ");
        benchPR = getPositionInput("Enter your Bench PR: ");
        deadliftPR = getPositionInput("Enter your Deadlift PR: ");
        String[] values = getStatus(gender, weight, squatPR, benchPR, deadliftPR);
        for (int i=0; i<3; i++){
            System.out.println(values[i]);
        }
    }

    //make sure user input is positive
    public static int getPositionInput(String prompt){
        int input = -1;
        while (input <=0) {
            System.out.print(prompt);
            if (scanner.hasNextInt()){
                input = scanner.nextInt();
                if (input <= 0){
                    System.out.println("Please enter a positive number");
                }
            } else {
                System.out.println("Invalid input. try again");
                scanner.next();
            }
        }
        return input;
    }

    //make sure user input is female or male
    public static String getGender(){
        String input = "";
        while(input == ""){
            System.out.println("Enter your gender (male/female)");
            if (scanner.hasNext()){
                input = scanner.next();
                if (!input.equals("female") && !input.equals("male")){
                    input = "";
                    System.out.println("Please enter either female or male");
                }
            }
        }
        return input;
    }

    //using the gender, weight and lifts 
    //condition ? valueIfTrue : valueIfFalse
    public static String[] getStatus(String gender, int weight, int squat, int bench, int deadlift){
        double[] squatThres = new double[5];
        double[] benchThres = new double[5];
        double[] dlThres = new double[5];
        String[] levels = {"Beginner", "Novice", "Intermediate", "Advanced", "Elite"};
        if (gender.equals("male")){
            squatThres = new double[]{0.8, 1.2, 1.5, 2.0, 2.5};
            benchThres = new double[]{0.6, 1.0, 1.3, 1.6, 2.0};
            dlThres = new double[]{1.0, 1.5, 2.0, 2.5, 3.0};
        } else {
            squatThres = new double[]{0.5, 0.8, 1.0, 1.3, 1.75};
            benchThres = new double[]{0.3, 0.6, 0.8, 1.0, 1.5};
            dlThres = new double[]{0.8, 1.2, 1.5, 1.8, 2.2};
        }

        //we now have the thresholds that for each class: 
        //beginner, novice, intermediate, advanced, elite
        //since we calculate it based on body weight, we need to find what percent of their body weight the person can lift
        /*
        ex: i bench 135 and weigh 120, i bench 1.125 of my bodyweight, so {0.3, 0.6, 0.8, 1.0, 1.5}, I would be index 3, advanced
        1. find index of the minimum
        */
       int index = 0;
       String squatclass = "Beginner";
       String benchClass = "Beginner";
       String deadClass = "Beginner";
       double userSquat = squat/weight;
       double userBench = bench/weight;
       double userDL = deadlift/weight;
       for (int i=0; i<5; i++){
        //divide user's lift by their weight -> lift/weight
        if(userSquat>squatThres[i]){
            squatclass = levels[i];
        }
        if(userBench>benchThres[i]){
            benchClass = levels[i];
        }
        if(userDL>dlThres[i]){
            deadClass = levels[i];
        }
       }
       return new String[]{squatclass, benchClass, deadClass};

    }

}



