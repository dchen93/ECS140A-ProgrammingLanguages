

public class Jumble extends Seq{
	protected int[] values_;
	
	public Jumble(int[] values){
		this.values_ = new int[values.length];
		System.arraycopy(values,0,this.values_,0,values.length);
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