

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
    public static Seq plus(Constant a, Delta b){
    	if(a.num_ <= b.num_){
    		return new Delta(a.num_,a.value_ + b.initial_,b.delta_);
    	} else{
    		return new Delta(b.num_,a.value_ + b.initial_,b.delta_);
    	}
    }
    public static Seq plus(Delta a, Constant b){
    	if(a.num_ <= b.num_){
    		return new Delta(a.num_,b.value_ + a.initial_,a.delta_);
    	} else{
    		return new Delta(b.num_,b.value_ + a.initial_,a.delta_);
    	}
    }
    public static Seq plus(Constant a, Jumble b){
    	int[] array;
    	if(a.num_ < b.values_.length){
    		array = new int[a.num_];
    	} else{
    		array = new int[b.values_.length];
    	}
    	for(int i = 0; i < array.length; i++){
    		array[i] = a.value_ + b.values_[i];
    	}
    	
    	return new Jumble(array);
    }
	public static Seq plus(Jumble a, Constant b){
		int[] array;
    	if(b.num_ < a.values_.length){
    		array = new int[b.num_];
    	} else{
    		array = new int[a.values_.length];
    	}
    	
    	for(int i = 0; i < array.length; i++){
    		array[i] = b.value_ + a.values_[i];
    	}
    	
    	return new Jumble(array);
    }
	public static Seq plus(Delta a, Jumble b){
		int[] array;
		
		if(a.num_ < b.values_.length){
    		array = new int[a.num_];
    	} else{
    		array = new int[b.values_.length];
    	}
		int deltaVal = a.initial_;
		
		for(int i = 0;i < array.length; i++){
			array[i] = b.values_[i] + (deltaVal);
			deltaVal += a.delta_;
		}
		return new Jumble(array);
	}
	public static Seq plus(Jumble a, Delta b){
		int[] array;
		if(b.num_ < a.values_.length){
    		array = new int[b.num_];
    	} else{
    		array = new int[a.values_.length];
    	}
		int deltaVal = b.initial_;
		
		for(int i = 0;i < array.length; i++){
			array[i] = a.values_[i] + (deltaVal);
			deltaVal += b.delta_;
		}
		return new Jumble(array);
	}
}