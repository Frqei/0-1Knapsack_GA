package code;

public class knapsack {
	private int capacity;
	private boolean[] objects;
	private int value;
	private int weight;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void processValue(){
		value=0;
		for (int i= 0 ; i<Parameters.nbObjects;i++) {
			if(objects[i]){
				value+=Parameters.listObjects[i].getValue();
			}
		}
	}
	
	
	public void processWeight(){
		weight=0;
		for (int i= 0 ; i<Parameters.nbObjects;i++) {
			if(objects[i]){
				weight+=Parameters.listObjects[i].getWeight();
			}
		}
	}
	
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public boolean getoneObject(int index){
		return objects[index];
	}

	public void setoneObject(int index, boolean obj){
		objects[index]=obj;
		processWeight();
		processValue();
	}
	
	public boolean[] getObjects() {
		return objects;
	}

	public void addObjects(int index, boolean object){
		this.objects[index]=object;
		processWeight();
		processValue();
	}
	
	public void setObjects(boolean[] objects) {
		this.objects = objects;
	}
	
	public knapsack(){
		setCapacity(Parameters.CapaciteDuSac);
		setValue(0);
		setWeight(0);
		objects=new boolean[Parameters.nbObjects];
		for (boolean b : objects) {
			b=false;
		}
	}
	
	public knapsack(boolean[] B){
		setCapacity(Parameters.CapaciteDuSac);
		setValue(0);
		setWeight(0);
		objects=B;
		processValue();
		processWeight();
		if(weight>capacity){
			System.err.println("weight>capacity");
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void Display(){
		System.out.println("capacite "+getCapacity());
		System.out.println("valeur "+getValue());
		System.out.println("weight "+getWeight());
		for (int i = 0; i < objects.length; i++) {
			if(objects[i]){
				System.out.print(i+" T | ");
			}else{
				System.out.print(i+" F | ");
			}
		}System.out.println("    ");
	}

	public void generateIndividual(){
		
			for (int i=0 ;i<objects.length;i++) {
				if(Math.random()>0.5 && getWeight()+Parameters.listObjects[i].getWeight()<=capacity){
					objects[i]=true;
					setValue(getValue()+Parameters.listObjects[i].getValue());
					setWeight(getWeight()+Parameters.listObjects[i].getWeight());
				}
			}
		
	}
	
	public int getFitness(){
		return value;
	}
}
