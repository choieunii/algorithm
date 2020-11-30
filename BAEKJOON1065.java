import java.util.*;
import java.lang.*;

public class BAEKJOON1065 {
    public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    int input = s.nextInt();
    int answer =99;
    int flag = 0;
    if(input<100) {
    	System.out.println(input);
    	return;
    }
    while(input>=100) {
        String str_input = Integer.toString(input);
        int arr[] = new int[str_input.length()+1];
        int value=0;
        for(int i=0;i<str_input.length();i++) {
        	arr[i] = Integer.parseInt(str_input.substring(i, i+1));
        	if(i!=0)
        	{
        		if(flag==0) {
        		value=arr[i]-arr[i-1];
        		flag=1;
        		}
        		else {
        			if(value!=arr[i]-arr[i-1]) {
        				flag=0;
        				break;
        			}else {
        				value=arr[i]-arr[i-1];
        			}
        		}
        	}
        }
        if(flag==1) {
        	answer++;	
        }
        input--;
    }
    System.out.println(answer);
    }
}
    
