package no.aleksnor;

import java.util.Random;

public class Chromosome {

  private char[] genes;
  private float fitness;
  private final String POSSIBLE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ";
  private String target;

  public Chromosome(String targetString) {
    fitness = 0;
    genes = new char[targetString.length()];
    for (int i = 0; i < genes.length ; i++) {
      genes[i] = POSSIBLE_CHARACTERS.charAt(new Random().nextInt(POSSIBLE_CHARACTERS.length()));
    }
    target = targetString;
    calculateFitness(targetString);
  }

  public String toString() {
    return (new String(genes)) + " Fitness score: " + fitness;
  }
  
  public void calculateFitness(String targetString) {
      int correctChars = 0;
      for (int i = 0; i < genes.length ; i++) {
        if (genes[i] == targetString.charAt(i)) correctChars++;
      }
      fitness = (float)correctChars / (float)targetString.length();
  }

  public Chromosome crossover(Chromosome other) {
    Chromosome offspring = new Chromosome(target);

    int crossoverPoint = (new Random().nextInt(genes.length));

    for(int i = 0; i < genes.length; i++) {
      offspring.setGene( i > crossoverPoint ? genes : other.getGenes(), i);
    }
    offspring.calculateFitness(target);
    return offspring;
  }

  public void mutate(double probability) {
    for (int i = 0; i < genes.length; i++) {
      double chance = new Random().nextDouble();
      if(chance < probability) {
        genes[i] = POSSIBLE_CHARACTERS.charAt(new Random().nextInt(POSSIBLE_CHARACTERS.length()));
      }
    }
    calculateFitness(target);
  }

  public void setGene(char[] newGenes, int i) {
    this.genes[i] = newGenes[i];
  }

  public char[] getGenes() {
    return genes;
  }

  public float getFitness() {
    return fitness;
  }
}
