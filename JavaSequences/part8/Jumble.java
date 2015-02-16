/* Jumble sequence class that inherits from the seq class. 
 * This class is just an array of random numbers with no pattern.
 */

public class Jumble extends Seq{
	protected int[] values_;
	
	public Jumble(int[] values){
		this.values_ = new int[values.length];
		System.arraycopy(values,0,this.values_,0,values.length);
	}
	
	public int min(){
		if(values_.length == 0){
			return 0;
		} else{
			int min = values_[0];
			for(int i = 1; i < values_.length; i++){
				if(values_[i] < min){
					min = values_[i];
				}
			}
			return min;
		}
	}
	public SeqIt createSeqIt(){
		return new JumbleIt(this);
	}
	public String toString(){
		String str = "{ "+values_.length+" : ";	//NOT SURE ABOUT THIS ONE
		for(int i = 0; i < values_.length; i++){
			str += values_[i]+" ";
		}
		str += "}";
		return str;
	}
}