package code;

public class Objects {
	private int value;
	private int weight;
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public void Display(){
		System.out.println("poids :"+getWeight()+" ; valeur"+getValue());
	}
	
}
