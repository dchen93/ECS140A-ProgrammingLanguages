

public class Delta extends Seq{
	protected int num_;
	protected int initial_;
	protected int delta_;
	
	public Delta(int num, int initial, int delta){
		if(num == 0){
			this.num_ = 0;
			this.initial_ = 0;
			this.delta_ = 0;
		} else{
			this.num_ = num;
			this.initial_ = initial;
			this.delta_ = delta;
		}
	}
	
	public String toString(){
		return "< "+num_+" : "+initial_+" &"+delta_+" >";
	}
	
	
}