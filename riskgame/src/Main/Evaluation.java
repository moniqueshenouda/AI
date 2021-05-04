package Main;

public class Evaluation {
    int L=0;
    int T=0;
    int f1=1;
    int f2=100;
    int f3=10000;
    int P1=0;
    int P2=0;
    int P3=0;
    
    public Evaluation (int L , int T) {
    	this.L=L;
    	this.T=T;
    }
    
    public void calceval(int l, int t){
        P1=f1*(l+t);
        System.out.println("Evaluation at f=1" + P1);
        
    }
    
      public void calceval2(int l, int t){
        P2=f2*(l+t);
        System.out.println("Evaluation at f=100" + P2);
        
    }
      
        public void calceval3(int l, int t){
        P3=f3*(l+t);
        System.out.println("Evaluation at f=10000" + P3);
    }
}
