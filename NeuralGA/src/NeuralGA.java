
public class NeuralGA {

	int numNeurons;
	int populationSize;
	int numInputs;
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NeuralGA nga = new NeuralGA();

	}
	
	NeuralGA(){
		
		// Hardcode test values for now
		populationSize = 40;
		numNeurons = 10;
		numInputs = 1;
		
		boolean[] testIn = new boolean[1];
		boolean[] testOut = new boolean[1];
		testIn[0]=true;
		testOut[0]=true;
		
		Population initialPop = new Population(populationSize, numNeurons);
		initialPop.setTestInput(testIn);
		initialPop.setTestOutput(testOut);
		initialPop.genRandomPop();
		initialPop.testPopulation();
		
		for(int i = 0; i< populationSize;i++){
			//System.out.println(initialPop.circuits[i].code.toString());
			//System.out.println("Circuit Number:" + i);
			/*for(int j = 0; j < (numNeurons * numNeurons); j++){
				//System.out.print(initialPop.circuits[i].code.get(j) + ",");
				if(initialPop.circuits[i].code[j] == true){
					System.out.print("1");
				}
				else{
					System.out.print("0");
				}
			}
			System.out.print("\n");*/
		}
		
	}

}
