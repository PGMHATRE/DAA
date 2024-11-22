import java.util.*;

class prac2 {
    char id;
    int deadline, profit;

    public prac2() {
    }

    prac2(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

    void jobsch(ArrayList<prac2> arr, int t) {

        int n = arr.size();
        Collections.sort(arr, (a, b) -> b.profit - a.profit);

        boolean result[] = new boolean[t];

        char job[] = new char[t];

        for (int i = 0; i < n; i++) {

            for (int j = Math.min(t - 1, arr.get(i).deadline - 1); j >= 0; j--) {

                if (!result[j]) {
                    result[j] = true;
                    job[j] = arr.get(i).id;
                    break;
                }
            }
        }
        System.out.println("Scheduled jobs:");
        for (char jb : job) {
            if (jb != 0) {
                System.out.print(jb + " ");
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        ArrayList<prac2> arr = new ArrayList<prac2>();

        System.out.println("Enter no of jobs:");
        int n = s.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter the job name :");
            char id = s.next().charAt(0);
            System.out.println("Enter Job profit:");
            int profit = s.nextInt();
            System.out.println("Enter Job deadline:");
            int deadline = s.nextInt();
            arr.add(new prac2(id, deadline, profit));
        }
        System.out.println("Enter the no of time slot available:");
        int t = s.nextInt();

        System.out.println("Following is the maximum profit sequence of jobs:");
        prac2 job = new prac2();

        job.jobsch(arr, t);
        s.close();
    }

}


/*
Enter no of jobs:
4
Enter the job name :
A
Enter Job profit:
10
Enter Job deadline:
2
Enter the job name :
B
Enter Job profit:
20
Enter Job deadline:
1
Enter the job name :
C
Enter Job profit:
30
Enter Job deadline:
1
Enter the job name :
D
Enter Job profit:
40
Enter Job deadline:
2
Enter the no of time slot available:
2
Following is the maximum profit sequence of jobs:
Scheduled jobs:
C D
*/