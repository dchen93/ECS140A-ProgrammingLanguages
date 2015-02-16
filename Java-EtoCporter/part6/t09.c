#include <stdio.h>
#include <math.h>
double c_sqrtFunc(double num);

main(){
	int x_i = -12345;
	int x_k = -12345;
	int x_long = -12345;
	int x_n = -12345;
	
	x_i = 25 ;
	while(1){   //1st While
		if(10 != x_i ){
			if(20 == x_i ){
				x_k = 20 - x_i ;
				while(1){   //2nd While
					if(x_k ){
						printf("%d\n",x_k );
						x_k = x_k + 1 ;
					}
					else{
						break;
					}
				}
			}
			else if(15 == x_i ){
				x_k = 15 - x_i ;
				x_n = 5 ;
				while(1){ //3rd While
					if(x_k ){
						x_long = 10 * x_k ;
						printf("%d\n",x_long );
						x_k = x_k + 1 ;
					}
					else if(x_n ){
						printf("%d\n",x_n );
						x_n = 0 ;
					}
					else{
						break;
					}
				}
				printf("%d\n",x_long );
			}
			else{
				x_k = 10 - x_i ;
				while(1){
					if(x_k ){
						printf("%d\n",x_k );
						x_k = x_k + 1 ;
					}
					else{
						break;
					}
				}
			}
			x_i = x_i - 1 ;
		}
		else{ //Else for the outer 1st While
			break;
		}
	}
	printf("%d\n",x_i );
}

double c_sqrtFunc(double num){
    if(num < 0){
        return 0;
    } else{
        return sqrt(num);
    }
}
