//Ryan Carley 1/23/15
//import java.util.BitSet;


public class Neuron {
	int num;
	int totalNum;
	int inputNum = 0;
	int[] inputConnections;
	int[] inputWeights;
	int[] input;
	int[] output;
	//BitSet code;
	boolean[] code;
	NeuralCircuit circuit;
	
	Neuron(int index, int totalN, NeuralCircuit cir, boolean[] c){
		num = index;
		totalNum = totalN; 
		circuit = cir;
		
		inputConnections = new int[totalNum];
		inputWeights = new int[totalNum];
		input = new int[totalNum];
		output = new int[totalNum];
		decode(c);
		
	}
	
	// Turn connections, weights, and action potential formula etc into a binary string(or array?)
	public boolean[] encode(){
		String codeStr = "";
		
		if(code != null){
			return code;
		}
		
		for(int i = 0; i < totalNum; i++){
			if(output[i] == 1){
				codeStr += "1";
			}
			else
			{
				codeStr += "0";
			}
		}
		
		// Temp, will need more variables later
		//boolean[] c = fromString(codeStr);
		//code = c;
		return code;
	}
	
	//Turn binary back into the neuron
	public void decode(boolean[] c){
		for(int i = 0; i < totalNum; i++){
			if(c[i]){
				output[i]=1;
			}else{
				output[i]=0;
			}
		}
		//System.out.println("want:" + totalNum + " from boolean array of size:" + c.length);
	}
	
	public void recieveInput(int in){
		/*int inNum = 0;
		for(int i  = 0; i < inputConnections.length;i++)
		{
			if (in == inputConnections[i])
			{
				inNum = i;
			}
		}
		
		input[inNum] = 1;*/
		input[in] = 1;
		inputNum++;
		//checkFire();
	}
	
	public boolean checkFire(){
		
		//System.out.println(" checkfire neuron:" + num + " for circuit:" + circuit.num);
		// Gather total input value (weights?)
		double runningTotal = 0;
		for(int i = 0 ; i < totalNum; i++){
			if(input[i] == 1){
				// Later change this to account for weight
				runningTotal += 1;
			}
		}
		
		// Values in this check may change
		if(runningTotal >= (inputNum / 2) && runningTotal > 0){
			this.fire();
			return true;
		}
		return false;
	}
	
	// Send activation signal to all output neurons
	public void fire(){
		
		//System.out.print("Firing node:" + num + " with inputs:");
		
		// Wipe current inputs (action potential)
		for(int i = 0; i < totalNum; i++){
			if(input[i] != 0){
				//System.out.print(i + ",");
			}
			input[i] = 0;
		}
		inputNum = 0;
		//System.out.println();
		
		// Send output
		//System.out.print("Sending output to nodes:");
		for(int i = 0; i< output.length; i++){
			if(output[i] == 1){
				if(num != i){
					circuit.neurons[i].recieveInput(num);
					//System.out.print(i + ",");
				}
			}
		}
		//System.out.println();
		
		// Final output if last Neuron in circuit
		if(num == (totalNum - 1)){
			//System.out.println("finished. Last neuron activated!");
		}
		//System.out.println("Done firing node:" + num + "  for circuit:" + circuit.num + "!");
	}
	
	
	/*private static BitSet fromString(final String s) {
        return BitSet.valueOf(new long[] { Long.parseLong(s, 2) });
    }*/

	public void sendOutputs() {
		for(int i= 0; i < totalNum; i++){
			if(output[i] == 1){
				circuit.neurons[i].setupInput(num);
			}
		}
		
	}

	private void setupInput(int iN) {
		inputConnections[iN]=1;
		inputNum++;
	}
}
