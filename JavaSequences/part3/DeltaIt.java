

public class DeltaIt implements SeqIt{
	private Delta sequence_;
	private int curElem_;
	private int curNum_;
	
	public DeltaIt(Delta s){
		this.sequence_ = s;
		this.curElem_ = 0;
		this.curNum_ = s.initial_ - s.delta_;
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
			System.err.println("DeltaIt called past end");
			System.exit(1);
		}
		curElem_++;
		curNum_ += sequence_.delta_;
		return curNum_;
	}
}