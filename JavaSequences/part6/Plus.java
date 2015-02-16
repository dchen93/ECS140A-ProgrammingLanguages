

public class Plus{
	public static Seq plus(Constant a, Constant b){
		if(a.num_ <= b.num_){
			return new Constant(a.num_,a.value_+b.value_);
		} else{
			return new Constant(b.num_,a.value_+b.value_);
		}
	}
    public static Seq plus(Delta a, Delta b){
    	if(a.num_ <= b.num_){
    		return new Delta(a.num_, a.initial_ + b.initial_, a.delta_ + b.delta_);
    	} else{
    		return new Delta(b.num_, a.initial_ + b.initial_, a.delta_ + b.delta_);
    	}
    }
    public static Seq plus(Jumble a, Jumble b){
    	int[] array;
    	if(a.values_.length <= b.values_.length){
    		array = new int[a.values_.length];
    	} else{
    		array = new int[b.values_.length];
    	}
    	for(int i = 0; i < array.length; i++){
			array[i] = a.values_[i] + b.values_[i];
		}
    	return new Jumble(array);
    }
}