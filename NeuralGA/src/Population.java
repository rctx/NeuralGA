//Ryan Carley 1/23/15
import java.util.BitSet;


public class Population {
	NeuralCircuit[] circuits;
	int generation;
	double avgFitness;
	int popSize;
	int neuronsPerCircuit;
	
	Population(int ps, int nc){
		popSize = ps;
		neuronsPerCircuit = nc;
		circuits = new NeuralCircuit[popSize];
	}
	
	public void generateNextPop(){
		
		
		
		for(int i=0; i < popSize; i++){
			boolean[] cirCode = null;
			circuits[i] = new NeuralCircuit(cirCode, neuronsPerCircuit);
		}
	}
	
	public void decodePop(){
		
	}
	
	public void testPopulation(){
		
	}
	
	public void genRandomPop(){
		
		//generate random circuits to fill an initial population
		for(int i = 0; i < popSize; i++){
			circuits[i] = new NeuralCircuit(neuronsPerCircuit);
		}
	}
	
}
