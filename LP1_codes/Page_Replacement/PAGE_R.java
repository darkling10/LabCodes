package Page_Replacement;

import java.util.*;

public class PAGE_R {
    Scanner sc = new Scanner(System.in);

    public void Menu() {
        System.out.println("1. FIFO");
        System.out.println("2. LRU");
        System.out.println("3. Optimal");
        System.out.println("4. Exit");
    };

    public void accept(int[] ref, int n) {

        System.out.println("\nENTER ELEMENT OF REFERENCE STRING ONE BY ONE::");
        for (int i = 0; i < n; i++) {
            ref[i] = sc.nextInt();
        }
    };

    public void display(int[] ref, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(" " + ref[i]);
        }
    }

    public int search(int[] frame, int x, int fs) {

        for (int i = 0; i < fs; i++) {
            if (frame[i] == x)
                return 1;
        }
        return 0;
    }

    public void fifo(int[] ref, int n) {
        int frame[] = new int[10];
        int k, fs, temp, cnt;
        System.out.print("\n\nENTER FRAME SIZE::");
        temp = sc.nextInt();
        cnt = k = fs = 0;

        for (int i = 0; i < n; i++) {
            if (search(frame, ref[i], fs) == 0) {
                frame[k] = ref[i];
                k = (k + 1) % temp;
                cnt++;
                if (cnt < temp)
                    fs = cnt;
                else
                    fs = temp;
                System.out.format("\n\nFOR PAGE "+ ref[i]);
                display(frame, fs);
            } else {
                System.out.print("\n\nFOR PAGE:: " + ref[i]);
                System.out.print(" NO PAGE FAULT");
            }
        }
        System.out.println("\n\nTOTAL NO. OF PAGE FAULTS = " + cnt);
    }

    public void lru(int[] ref, int n) {
        int frame[] = new int[30];
        int pos[] = new int[30];
        int i, j, k, fs, cnt, min;

        System.out.print("\n\nENTER FRAME SIZE::");
        fs = sc.nextInt();
        cnt = k = 0;

        for (i = 0; i < fs && i < n; i++) {
            if (search(frame, ref[i], i) == 0) {
                frame[k++] = ref[i];
                cnt++;
                System.out.print("\n\nFOR PAGE:: " + ref[i]);
                display(frame, k); // frame size=k
            } else {
                System.out.print("\n\nFOR PAGE:: " + ref[i]);
                System.out.print(" NO PAGE FAULT");
            }
        }
        for (i = 0; i < n; i++) {
            if (search(frame, ref[i], fs) == 0) {
                for (j = 0; j < fs; j++) {
                    for (k = i - 1; k >= 0; k--) {
                        if (frame[j] == ref[k])
                            break;
                    }
                    pos[j] = k;
                }
                k = 0;
                min = pos[0];
                for (j = 1; j < fs; j++) {
                    if (min > pos[j]) {
                        min = pos[j];
                        k = j;
                    }
                }
                frame[k] = ref[i];
                cnt++;
                System.out.print("\n\nFOR PAGE:: " + ref[i]);

                display(frame, fs);
            } else {
                System.out.print("\n\nFOR PAGE  ::\t" + ref[i]);
                System.out.print(" NO PAGE FAULT");
            }
        }
        System.out.print("\n\nTOTAL NO. OF PAGE FAULTS = " + cnt);
    }

    public void optimal(int ref[], int n) {
        int frame[] = new int[30];
        int pos[] = new int[30];
        int i, j, k, fs, cnt, max;

        System.out.print("\n\nENTER FRAME SIZE::");
        fs = sc.nextInt();
        cnt = k = 0;
        for (i = 0; i < fs && i < n; i++) {
            if (search(frame, ref[i], i) == 0) {
                frame[k++] = ref[i];
                cnt++;
                System.out.print("\n\nFOR PAGE:: " + ref[i]);
                display(frame, k); // frame size=k
            } else {
                System.out.print("\n\nFOR PAGE:: " + ref[i]);

                System.out.print(" NO PAGE FAULT");
            }
        }
        for (; i < n; i++) {
            if (search(frame, ref[i], fs) == 0) {
                for (j = 0; j < fs; j++) {
                    for (k = i + 1; k < n; k++) {
                        if (frame[j] == ref[k])
                            break;
                    }
                    pos[j] = k;
                }
                k = 0;
                max = pos[0];
                for (j = 1; j < fs; j++) {
                    if (max < pos[j]) {
                        max = pos[j];
                        k = j;
                    }
                }
                frame[k] = ref[i];
                cnt++;
                System.out.print("\n\nFOR PAGE  ::\t" + ref[i]);
                display(frame, fs);
            } else {
                System.out.print("\n\nFOR PAGE  ::\t" + ref[i]);
                System.out.print(" NO PAGE FAULT");
            }
        }
        System.out.print("\n\nTOTAL NO. OF PAGE FAULTS = " + cnt);
    }

    public static void main(String[] args) {
        PAGE_R obj = new PAGE_R();
        obj.Menu();

        int ref[] = new int[50];
        int n, ch = 0;

        System.out.print("\n\nENTER SIZE OF REFERENCE STRING::");
        n = obj.sc.nextInt();
        obj.accept(ref, n);
        while (ch != 4) {
            obj.Menu();
            System.out.print("\n\nENTER YOUR CHOICE::");
            ch = obj.sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("\nYOUR ENTERED REFERENCE STRING IS::\n\n");
                    obj.display(ref, n);
                    obj.fifo(ref, n);
                    break;
                case 2:
                    System.out.print("\nYOUR ENTERED REFERENCE STRING IS::\n\n");
                    obj.display(ref, n);
                    obj.lru(ref, n);
                    break;
                case 3:
                    System.out.print("\nYOUR ENTERED REFERENCE STRING IS::\n\n");
                    obj.display(ref, n);
                    obj.optimal(ref, n);
                    break;
            }
        }
    }

}