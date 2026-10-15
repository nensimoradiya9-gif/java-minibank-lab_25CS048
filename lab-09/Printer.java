class Printer {
    
       synchronized public void printDocuments(int numOfCopies, String docName){
            for(int i=1;i<=numOfCopies;i++){
                System.out.println(">> Printing " + docName + " " + i);
            }
        }
}

class Pc implements Runnable{
    Printer p1=new Printer();
    private int numOfCopies;
    private String docName;

    Pc(Printer p1, int numOfCopies, String docName){
        this.p1=p1;
        this.numOfCopies=numOfCopies;
        this.docName=docName;
    }
    
    public void run(){
        p1.printDocuments(numOfCopies, docName);
    }
}


class PrinterDemo{
    public static void main(String []a){
        Printer p1 = new Printer();
        Pc pc1=new Pc(p1, 5, "Dhruvi.pdf");
        Thread t1 = new Thread(pc1,"Dhruvi");
        t1.start();
        Pc pc2=new Pc(p1, 5, "Diya.pdf");
        Thread t2 = new Thread(pc2,"Diya");
        t2.start();
    //       try{
    //    t1.join();
    //    t2.join();
    //    }
    //    catch(Exception e){
    //     System.out.println(e);
    //    }
    }
}
