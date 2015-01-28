//Ryan Carley 1/23/15
//import java.util.BitSet;
import java.util.Random;

public class NeuralCircuit {
	Neuron[] neurons;
	int num;
	int fitness;
	boolean[] code;
	int numNeurons;
	Population pop;
	int[] firesLog;
	int maxFires = 4;
	
	NeuralCircuit(boolean[] circuitCode, int numPer){
		
	}
	
	NeuralCircuit(int neuronsPerCircuit, Population p, int n) {
		
		this.numNeurons = neuronsPerCircuit;
		this.pop = p;
		this.num = n;
		int codeLength = numNeurons * numNeurons;
		
		// Generate random circuit
		Random r = new Random();
		//String bits = "";
		boolean[] c = new boolean[codeLength];
		for(int i = 0; i < codeLength; i++){
			//int x = 0;
			//if(r.nextBoolean()) x = 1;
			//bits += x;
			c[i] = r.nextBoolean();
		}
		 //code = fromString(bits);
		neurons = new Neuron[numNeurons];
		firesLog = new int[numNeurons];
		this.code = c;
		this.decode(code);
	}
	
	public void decode(boolean[] circuitCode){
		
		System.out.println("circuit length:" + circuitCode.length);
		// Have Neurons setup all their outputs
		for(int i=0; i< numNeurons; i++){
			//BitSet codeSegment = code.get((i * numNeurons), ((i+1) * numNeurons));// Grab relevant chunk for this neuron
			boolean[] codeSegment = new boolean[numNeurons];
			int h = 0;
			for(int j = (i * numNeurons); j < ((i+1) * numNeurons); j++){
				codeSegment[h] = circuitCode[j];
				h++;
			}
			System.out.println("codeSegment Length:" + codeSegment.length);
			neurons[i] = new Neuron(i,numNeurons,this,codeSegment);
		}
		
		// Manually set output of last Neuron to nothing(actual circuit output)
		for(int i = 0; i < numNeurons; i++){
			neurons[numNeurons - 1].output[i]=0;
		}
		
		// Using the outputs, generate inputs
		for(int i = 0; i < numNeurons; i++){
			neurons[i].sendOutputs();
		}
		
	}
	
	public int fitnessTest(){
		
		boolean doneTesting = false;
		boolean result = false;
		
		// Start Initial Input Fires
		//boolean[] in = pop.getTestInput();
		boolean[] in = new boolean[1];
		in[0]=true;
		for(int j = 0; j < in.length; j++){
			if(in[j]){
				neurons[j].fire();
				//System.out.println("Initial fire on:" + j );
			}
		}
		
		while(!doneTesting){
			
			//System.out.println("Next Step.");
			
			boolean fired = false;
			// Step through the fitness test
			for(int i = 0; i < numNeurons; i++){
				if(neurons[i].checkFire()){
					fired = true;
					firesLog[i]++;
					if(firesLog[i] >= maxFires){
						doneTesting = true;
					}
					
					// Check if output fired
					if(i == (numNeurons - 1)){
						result = true;
					}
				}
			}
			
			// If no neurons fired this step then end the test
			if(!fired){
				doneTesting = true;
			}
			/*try {
			    Thread.sleep(1000);                 // 1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}*/
			
			
		}
		cleanFiresLog();
		if(result){
			return 1;
		}
		
		return 0;
	}
	
	public void cleanFiresLog(){
		for(int i = 0; i < numNeurons; i++){
			firesLog[i] = 0;
		}
	}
	
	/*private static BitSet fromString(final String s) {
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }*/
}
