package no.aleksnor;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {

    static int popSize;
    static double crossoverProbability;
    static double mutationProbability;
    static String targetString;
    static Population pop;
    static Scanner sc = new Scanner(System.in);

    public static void main( String[] args ) throws InterruptedException {
        System.out.println("Enter population size:");
        popSize = sc.nextInt();
        System.out.println();
        System.out.println("Enter crossover probability:");
        sc.nextLine();
        crossoverProbability = sc.nextDouble();
        System.out.println();
        System.out.println("Enter mutation probability:");
        sc.nextLine();
        mutationProbability = sc.nextDouble();
        System.out.println();
        sc.nextLine();
        System.out.println("String to replicate:");
        targetString = sc.nextLine();
        System.out.println();

        System.out.println("Target: " + targetString);
        System.out.println("Mutation: " + mutationProbability);
        System.out.println("Crossover: " + crossoverProbability);
        System.out.println("Population Size: " + popSize);
        Thread.sleep(2000);
        pop = new Population(targetString, mutationProbability, crossoverProbability, popSize);

        while(!pop.getDone()) {
            System.out.println(pop.getBestString());
            pop.matingSelection();
            pop.cycle();
        }

        System.out.println();
        System.out.println("Best string: " + pop.getBestString());
        System.out.println("Number of generations: " + pop.getGenerations());
    }
}
