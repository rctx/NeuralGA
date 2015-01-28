//Ryan Carley 1/23/15
import java.util.BitSet;
import java.util.Random;

public class NeuralCircuit {
	Neuron[] neurons;
	int fitness;
	boolean[] code;
	int numNeurons;
	
	NeuralCircuit(boolean[] circuitCode, int numPer){
		
	}
	
	NeuralCircuit(int neuronsPerCircuit) {
		
		this.numNeurons = neuronsPerCircuit;
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
		
		return 0;
	}
	
	private static BitSet fromString(final String s) {
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }
}
