#include <stdio.h>
#include <math.h>
double c_sqrtFunc(double num);

main(){
	int x_i = -12345;
	int x_j = -12345;
	
	for(x_i = 1 ; x_i <= 4 ; x_i++){
		printf("%d\n",x_i );
	}
	for(x_i = 2 ; x_i <= 5 ; x_i++){
		for(x_j = x_i ; x_j <= 5 ; x_j++){
			printf("%d\n",x_i * 100 + x_j );
		}
	}
	for(x_i = 2 ; x_i <= 9 ; x_i++){
		for(x_j = 1 ; x_j <= 5 ; x_j+= (x_i != x_j +1 ? 1 : 2)){
			printf("%d\n",x_i * 100 + x_j );
		}
	}
	for(x_j = 10 ; x_j <= 3 ; x_j++){
		printf("%d\n",2000 + x_j );
	}
}

double c_sqrtFunc(double num){
    if(num < 0){
        return 0;
    } else{
        return sqrt(num);
    }
}
