import java.util.Scanner;

class prac1{
 
 public static String findsum(String A,String B)
 {
     StringBuilder res= new StringBuilder();
     if(A.length()>B.length())
     {
         String temp=A;
         A=B;
         B=temp;
     }
     
     int carry=0;
     int n1=A.length();
     int n2=B.length();
     
    for(int i =0; i<n1;i++)
    {
        int total=(A.charAt(n1-1-i)-'0')+(B.charAt(n2-1-i)-'0')+carry;
        res.append(total%10);
        carry=total/10;
    }
     
     
     for(int i =n1; i<n2;i++)
    {
        int total=(B.charAt(n2-1-i)-'0')+carry;
        res.append(total%10);
        carry=total/10;
    }
     
     if(carry>0)
     {
         res.append(carry);
     }
     
     return res.reverse().toString();
 }
 
 
 public static String findDiff(String A, String B) {
    // Ensure A is greater than or equal to B
   if (A.length() < B.length()) {
            String temp = A;
            A = B;
            B = temp;
        }

    StringBuilder res = new StringBuilder();
    int carry = 0;
    int n1 = A.length();
    int n2 = B.length();

    for (int i = 0; i < n2; i++) {
        int total = (A.charAt(n1 - 1 - i) - '0') - (B.charAt(n2 - 1 - i) - '0') - carry;
        if (total < 0) {
            total += 10;
            carry = 1;
        } else {
            carry = 0;
        }
        res.append(total);
    }

    for (int i = n2; i < n1; i++) {
        int total = (A.charAt(n1 - 1 - i) - '0') - carry;
        if (total < 0) {
            total += 10;
            carry = 1;
        } else {
            carry = 0;
        }
        res.append(total);
    }

    return removezero(res.reverse().toString());
}

 public static String removezero(String s)
 {
     int i=0;
     while(i<s.length()-1 && s.charAt(i)=='0')// chceck
     {
         i++;
     }
     
     return s.substring(i);
 }
 public static String multiply(String A,String B)
 {
     StringBuilder res= new StringBuilder();
     if(A.length()>B.length())
     {
         String temp=A;
         A=B;
         B=temp;
     }
     int n1=A.length();
     int n2=B.length();
     
     while(n2>n1)
     {
         A="0"+A;
         n1++;
     }
     
     if(n1==1)
     {
         return String.valueOf(Integer.parseInt(A)*Integer.parseInt(B));
     }
     
     if(n1%2==1)
     {
         A="0"+A;
         B="0"+B;
         n1++;
         
     }
     
     int mid=n1/2;
     
     
    String AL=A.substring(0,mid);
    String Ar=A.substring(mid);
    String Bl=B.substring(0,mid);
    String Br=B.substring(mid);
    
   String s1=multiply(AL,Bl);
    String s2=multiply(Ar,Br);
    
    String s3=multiply(findsum(AL,Ar),findsum(Bl,Br));
    
    String s4=findDiff(findDiff(s3,s2),s1);
    
    s1+="0".repeat(2*mid);
    s4+="0".repeat(mid);
   String  rres=findsum(s1,findsum(s4,s2));
    return removezero(rres);
 }
 
public static void main(String [] args)
{
    
    Scanner sc= new Scanner(System.in);
    System.out.println("Enter 1st no:");
    String A= sc.nextLine();
     System.out.println("Enter 2nd no:");
    String B= sc.nextLine();
    
    String result=multiply(A,B);
    System.out.println(result);
    sc.close();
    
    
}

}

/*
 Enter 1st no:
147852
Enter 2nd no:
147852
21860213904
*/