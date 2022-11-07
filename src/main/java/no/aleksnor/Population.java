package no.aleksnor;

import java.util.Random;

public class Population {
  private Chromosome[] pop;
  private String targetString;
  private double mutationProbability;
  private double crossoverProbability;
  private Chromosome[] matingPool;
  private int currentGeneration = 0;
  private boolean done = false;
  private String bestFitString;
  private final int TOURNAMENT_SIZE = 4;

  public Population(String targetString, double mutationProbability, double crossoverProbability, int size) {
    this.targetString = targetString;
    this.mutationProbability = mutationProbability;
    this.crossoverProbability = crossoverProbability;
    this.pop = new Chromosome[size];

    for (int i = 0; i < size ; i ++) pop[i] = new Chromosome(targetString);
  }

  public void matingSelection() {
    matingPool = new Chromosome[pop.length / TOURNAMENT_SIZE];
    
    for(int i = 0; i < matingPool.length; i++) {
      Chromosome bestFitChromosome = null;
      for (int j = 0; j < TOURNAMENT_SIZE; j++) {
        Chromosome contestant = pop[new Random().nextInt(pop.length)];
        if(bestFitChromosome == null || 
        contestant.getFitness() > bestFitChromosome.getFitness()) {
          bestFitChromosome = contestant;
        }
      }
      matingPool[i] = bestFitChromosome;
    }
  }

  public void cycle() {
    bestFitString = getBest();
    for (int i = 0; i < matingPool.length; i++) {
      if(crossoverProbability > new Random().nextDouble()) {
      Chromosome parent1 = matingPool[new Random().nextInt(matingPool.length)];
      Chromosome parent2 = matingPool[new Random().nextInt(matingPool.length)];

      Chromosome offspring = parent1.crossover(parent2);
      offspring.mutate(mutationProbability);
      pop[getWorstIndex()] = offspring;
      }
    }
    currentGeneration++;
  }

  public String getBest() {
    Chromosome bestFit = null;
    int i = 0;
    while(!done && i < pop.length) {
      if (bestFit == null || pop[i].getFitness() > bestFit.getFitness()) {
        bestFit = pop[i];
      }
      if (bestFit.getFitness() == 1.0) {
        done = true;
      }
      i++;
    }
    System.out.println(bestFit);
    return bestFit.toString();
  }

  private int getWorstIndex() {
    int worstIndex = 0;
    float worstFit = 1;
    for (int i = 0; i < pop.length; i++) {
      float contestant = pop[i].getFitness();
      if(contestant < worstFit) {
        worstIndex = i;
        worstFit = contestant;
      }
    }
    return worstIndex;
  }

  public boolean getDone() {
    return done;
  }

  public int getGenerations() {
    return currentGeneration;
  }

  public String getBestString() {
    return bestFitString;
  }
}
