

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
	
	public int min(){
		if(this.num_ == 0){
			return 0;
		} else{
			int otherNum = initial_+(num_ - 1)*delta_;
			return Math.min(initial_,otherNum);
		}
	}
	
	public SeqIt createSeqIt(){
		return new DeltaIt(this);
	}
	public String toString(){
		return "< "+num_+" : "+initial_+" &"+delta_+" >";
	}
}