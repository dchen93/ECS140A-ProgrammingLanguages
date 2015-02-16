

public class ConstantIt implements SeqIt{
	private Constant sequence_;
	private int curElem_;
	
	public ConstantIt(Constant s){
		this.sequence_ = s;
		this.curElem_ = 0;
	}
	
	public boolean hasNext(){
		if(curElem_ < sequence_.num_){
			return true;
		} else{
			return false;
		}
	}
	public int next(){
		if(curElem_ >= sequence_.num_){
			System.err.println("ConstantIt called past end");
			System.exit(1);
		} 
		curElem_++;
		return sequence_.value_;
	}
}