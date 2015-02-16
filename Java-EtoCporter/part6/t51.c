#include <stdio.h>
#include <math.h>
double c_sqrtFunc(double num);
main(){
int x_k = -12345;
int x_g = -12345;
int x_h = -12345;
x_g = 1 ;
x_h = 1 ;
for(x_k = 1 ; x_k <= 20 ; x_k++){
int x_newg = -12345;
printf("%d\n",x_h );
x_newg = x_h ;
x_h = x_g + x_h ;
x_g = x_newg ;
}
}
double c_sqrtFunc(double num){
    if(num < 0){
        return 0;
    } else{
        return sqrt(num);
    }
}
