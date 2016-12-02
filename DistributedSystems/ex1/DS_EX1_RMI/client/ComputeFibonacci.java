package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.math.BigDecimal;
import compute.Compute;

public class ComputeFibonacci {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
            Fibonacci task = new Fibonacci(Integer.parseInt(args[1]));
            Integer fibo = comp.executeTask(task);
            System.out.println(fibo);
        } catch (Exception e) {
            System.err.println("ComputeFibonacci exception:");
            e.printStackTrace();
        }
    }    
}