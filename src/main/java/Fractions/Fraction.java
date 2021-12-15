package Fractions;

import java.util.*;

public class Fraction {
    int numerator;
    int denominator;

    Fraction(int num, int denom){
        numerator=num;
        denominator=denom;
    }

    private Fraction reducedFrac(int num, int denom){
        int smallestFactor=1;
        int maxCommon;
        if(num<denom){
            maxCommon=num;
        } else{
            maxCommon=denom;
        }
        for (int i=maxCommon;i>1;i--){
            if(num%i==0 && denom%i==0){
                smallestFactor=i;
                break;
            }
        }
        num=num/smallestFactor;
        denom=denom/smallestFactor;

        return new Fraction(num,denom);
    }

    public Double toDecimalNotation(){
        return (double) numerator/denominator;
    }

    Fraction add(int number){
        return  reducedFrac(numerator+ (number * denominator), denominator);
    }


    Fraction add(Fraction fraction){
        int denom =this.denominator * fraction.denominator;
        int num = this.numerator * fraction.denominator + fraction.numerator*this.denominator;

        return reducedFrac(num, denom);

    }

    Fraction substract(int number){
        return  reducedFrac(numerator - (number * denominator), denominator);
    }

    Fraction substract(Fraction fraction){
        int denom =this.denominator * fraction.denominator;
        int num = this.numerator * fraction.denominator - fraction.numerator*this.denominator;

        return reducedFrac(num, denom);
    }

    Fraction multiply(int number){
        return  reducedFrac(numerator*number, denominator);
    }

    Fraction multiply(Fraction fraction){
        int denom =this.denominator * fraction.denominator;
        int num = this.numerator *  fraction.numerator;
        return reducedFrac(num, denom);
    }

    Fraction divide(int number){
        return  reducedFrac(numerator, denominator*number);
    }

    Fraction divide(Fraction fraction){
        Fraction inverse = new Fraction(fraction.denominator,fraction.numerator);
        return this.multiply(inverse);
    }

    public String toString(){
        return  numerator + "/" + denominator;
    }

    public static void main(String[] args) {
        Fraction example1 = new Fraction(1,3);
        Fraction example2 = new Fraction(2, 5);
        Double example1Double = example1.toDecimalNotation();

        Fraction r1=example1.add(example2);
        System.out.println(r1.toString());

        Fraction r2=example1.substract(example2);
        System.out.println(r2.toString());


        Fraction r3=example1.multiply(example2);
        System.out.println(r3.toString());

        Fraction r4=example1.divide(example2);
        System.out.println(r4.toString());

        Fraction r5=example1.add(1);
        System.out.println(r5.toString());

        Fraction r6=example1.substract(1);
        System.out.println(r6.toString());

        Fraction r7=example1.multiply(1);
        System.out.println(r7.toString());

        Fraction r8=example1.divide(1);
        System.out.println(r8.toString());
    }

}