// author : Gabriel Vieira Equitz ,id: 915254839
package statisticsproject;
import java.io.*;
import java.util.*;
public class StatisticsProject {
    public static void main(String[] args) {
        ///////////////////////Part 1 ////////////////////////////
        int N = 100; int M = 225; // number of samples and elements in the sample
        double [] xMean = new double[400];   double randomDouble;double tempSum;  int nCount = 0; 
        double xMin = -3.0;double xMax = 6.0; double sumOfMean = 0.;double sumOfMean2 = 0.;
        for (int j = 0; j< N; j++){
            tempSum = 0.0;
            for (int i = 0; i < M; i++){
                double randNumber = Math.random(); // random number between 0 and 1
                randomDouble = xMin + (randNumber*(xMax- xMin)) ;  // rescalling random number to be between -3 and 6               
                tempSum += randomDouble; }//finding the sum  
            xMean[j]= tempSum/(double)M; // finding the mean for each sample  
            if (xMean[j] >= 1 && xMean[j] <= 2){// calculating the simulated probabitily of the mean
                nCount += 1;}
            sumOfMean += xMean[j]; // sum to find the mean of the means
            sumOfMean2 += xMean[j]*xMean[j]; }//sum to calculate the standard deviation
        double xProb = nCount/(double)N;// a) find the simulated probability that the mean is between 1 and 2:
        System.out.println("1.a) Probability the mean is between 1 and 2 (uniform distribution): " + xProb);
        double meanOfMean = sumOfMean/(double)N; //b) find the mean of the means
        double variance = (N*sumOfMean2 - sumOfMean*sumOfMean)/(double)(N*(N-1));
        double standardDeviation = Math.sqrt(variance); //c) find the standard deviation of the means
        System.out.println("1.b) mean of the means (uniform distribution): " + meanOfMean );
        System.out.println("1.c) standard deviation of the means (uniform distribution): "  + standardDeviation); 
        int nBin = 30; // d) calculating the histogram 
        int [] nNumber = new int[nBin];double [] xValue = new double[nBin];
        xMin = 0.;xMax = 3.;
        double delta = (xMax - xMin)/(double)nBin;
        for (int i=0; i<nBin; i++){
          xValue[i] = (xMin + i*delta + delta/2.0); //plots points in the middle of the interval
          for (int j=0; j<N; j++)
            if (xMean[j] >= (xMin + i*delta) && xMean[j] < (xMin + (i+1)*delta) ){                
                nNumber[i] += 1;  } }  
        try {  // writing histogram data to a file
          File file = new File("hist_uniform.txt");
          PrintWriter output = new PrintWriter(new FileWriter(file));
          for (int i = 0; i < nBin; i ++){
              output.write(String.valueOf(xValue[i])+ "," + nNumber[i] + '\n'); }  
          output.close();
        } catch (IOException e) {           
            System.err.println("Problem writing to the file. " + e.getMessage());}
        //////////////////Part 2 /////////////////////////////////////
         N = 400; // number of samples
         M = 625; // number of elements in the samples 
        for (int i =0; i<400; i++) {
         xMean[i]=0.;  }
        sumOfMean = 0.;sumOfMean2 = 0.;double lambda = 0.2;nCount = 0;
        for (int j = 0; j< N; j++){
            tempSum = 0.0;
            for (int i = 0; i < M; i++){
                double randNumber = Math.random(); // random number between 0 and 1
                double newRandNumber = (-1.0/lambda)*Math.log(1.0-randNumber); 
                tempSum += newRandNumber;}// finding the sum
            xMean[j]= tempSum/(double)M;//mean for each sample
            if (xMean[j] >= 3 && xMean[j] <= 5){ // calculating the simulated probabitily of the mean
                nCount += 1;}
            sumOfMean += xMean[j]; 
            sumOfMean2 += xMean[j]*xMean[j]; }// sum to calculate the standard deviation
        xProb = nCount/(double)N; // a) find the simulated probability that the mean is between 3 and 5:
        System.out.println("2.a) Probability the mean is between 3 and 5 (exponential distribution): " + xProb);
        meanOfMean = sumOfMean/(double)N;//b) find the mean of the means
        variance = (N*sumOfMean2 - sumOfMean*sumOfMean)/(double)(N*(N-1));
        standardDeviation = Math.sqrt(variance);//c) find the standard deviation of the means
        System.out.println("2.b) mean of the means (exponential distribution): " + meanOfMean );
        System.out.println("2.c) standard deviation of the means (exponential distribution): "  + standardDeviation);
        nBin = 25; // d) calculating the histogram
        for (int i=0;i<nBin; i++){
         nNumber[i] = 0;
         xValue[i] = 0.; }
        xMin = 2.;xMax = 7.;
        delta = (xMax - xMin)/(double)nBin;
        for (int i=0; i<nBin; i++){
          xValue[i] = (xMin + i*delta + delta/2.0); //plots points in the middle of the interval
          for (int j=0; j<N; j++)
            if (xMean[j] >= (xMin + i*delta) && xMean[j] < (xMin + (i+1)*delta) ){                
                nNumber[i] += 1;   } }  
        try {// writing the output to a file
          File file = new File("hist_exponential.txt");
          PrintWriter output = new PrintWriter(new FileWriter(file));
          for (int i = 0; i < nBin; i ++){
              output.write(String.valueOf(xValue[i])+ "," + nNumber[i] + '\n'); }  
          output.close();
        } catch (IOException e) {           
            System.err.println("Problem writing to the file. " + e.getMessage());
        } 
    }    
}
