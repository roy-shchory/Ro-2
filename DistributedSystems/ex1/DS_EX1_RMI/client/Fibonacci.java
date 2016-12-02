package client;

import compute.Task;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Fibonacci implements Task<Integer>, Serializable {

    private static final long serialVersionUID = 227L;
    
    private static final Integer Fib0 = Integer.valueOf(0);
    private static final Integer Fib1 = Integer.valueOf(1);
    
    private final int index;
    
    public Fibonacci(int index) {
        this.index = index;
    }
    
    public Integer execute() {
        return computeFibo(index);
    }

    public static Integer computeFibo(int index) {
    	Integer f_prev = Fibonacci.Fib0;
    	Integer f_curr = Fibonacci.Fib1;
    	
    	if (index == 0)
    		return f_prev;
    	else if (index == 1)
    		return f_curr;
    	
    	Integer temp;
    	for (int i = 2; i <= index; i++) {
    		temp = f_curr.intValue();
    		f_curr = f_curr + f_prev;
    		f_prev = temp.intValue();
		}
    	return f_curr;
    }
}