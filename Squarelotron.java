import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Squarelotron {
    int[][] squarelotron;
    int size;

    /**
     * It fills the 2-dimensional array with the numbers 1 to n squared, in order. 
     * It also sets the size instance variable to be n.
     * @param n size
     */
    Squarelotron(int n) {
        int s = 1;
        this.squarelotron = new int[n][n];
        for (int i = 0; i < squarelotron.length; i++) {
            for (int j = 0; j < squarelotron.length; j++) {
                squarelotron[i][j] = s;
                s += 1;
            }
        }      
        this.size = n;
    }

    private ArrayList<String> getArr(int initial, int boundary) {
        ArrayList <String> ls = new ArrayList<String>();
        int x = initial;
        int y = initial;
        for (; y <= boundary; y++) {
            ls.add(Integer.toString(x) + Integer.toString(y));
        }
        y -= 1;
        for (; x <= boundary; x++) {
            ls.add(Integer.toString(x) + Integer.toString(y));
        }
        x -= 1;
        for (; y >= initial; y--) {
            ls.add(Integer.toString(x) + Integer.toString(y));
        }
        y += 1;
        for (; x >= initial; x--) {
            ls.add(Integer.toString(x) + Integer.toString(y));
        }
        x += 1;

        Set<String> set = new HashSet<String>(ls);
        ls.clear();
        ls.addAll(set);
        // System.out.println(ls);
        return ls;
    }

    private Hashtable<Integer, ArrayList<String>> Rings(int size) {
        int ringAmount;
        Hashtable <Integer, ArrayList<String>> my_dic = new Hashtable<Integer, ArrayList<String>>();
        if (size % 2 != 0) {
            ringAmount = (size + 1) / 2;
            for (int i = 1; i < ringAmount + 1; i++) {
                my_dic.put(i, getArr(i - 1, size - 1));
                size --;
            }
        }
        else {
            ringAmount = size / 2;
            for (int i = 1; i < ringAmount + 1; i++) {
                my_dic.put(i, getArr(i - 1, size - 1));
                size --;
            }
        }
        // System.out.println(my_dic);
        return my_dic;
    }

    private boolean ifOnRing(int ring, int size, int x, int y, Hashtable<Integer, ArrayList<String>> dic) {
        ArrayList<String> value = new ArrayList<String>();
        value = dic.get(ring);
        String num = Integer.toString(x) + Integer.toString(y);
        for (String str : value) {
            if (str.equals(num)) {
                return true;
            }
        }
        return false;
    }


    /**
     * This method performs the Upside-Down Flip of the squarelotron, 
       as described above, and returns the new squarelotron. 
       The original squarelotron should not be modified (we will check for this).
     * @param ring  
     * @return Squarelotron
     */
    public Squarelotron upsideDownFlip(int ring) {
        Squarelotron upsideDown = new Squarelotron(size);
        Hashtable <Integer, ArrayList<String>> new_dic = new Hashtable<Integer, ArrayList<String>>();
        new_dic = Rings(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (ifOnRing(ring, size, i, j, new_dic)) {
                    upsideDown.squarelotron[i][j] = this.squarelotron[size - 1 - i][j];
                }
            }
        }
        return upsideDown;
    }

    /**
     * This method performs the Main Diagonal Flip of the squarelotron, 
       as described above, and returns the new squarelotron. 
       The original squarelotron should not be modified (we will check for this).
     * @param ring
     * @return
     */
    public Squarelotron mainDiagonalFlip(int ring) {
        Squarelotron mainDiagonal = new Squarelotron(size);
        Hashtable <Integer, ArrayList<String>> new_dic = new Hashtable<Integer, ArrayList<String>>();
        new_dic = Rings(size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (ifOnRing(ring, size, i, j, new_dic) && i != j) {
                    mainDiagonal.squarelotron[i][j] = this.squarelotron[j][i];
                }
            }
        }
        return mainDiagonal;
    }

    /**
     * The argument numberOfTurns indicates the number of times the entire squarelotron should be rotated 90° clockwise. 
       Any integer, including zero and negative integers, is allowable as the argument. 
       A value of -1 indicates a 90° counterclockwise rotation. 
       This method modifies the internal representation of the squarelotron; 
       it does not create a new squarelotron.
     * @param numberOfTurns
     */
    public void rotateRight(int numberOfTurns) {
        if (numberOfTurns > 0) {
            int count = 0;
            while (count < numberOfTurns) {
                Squarelotron rotate = new Squarelotron(size);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        rotate.squarelotron[j][size - 1 - i] = this.squarelotron[i][j];
                    }
                }
                count += 1;
                this.squarelotron = rotate.squarelotron;
            }
        }
        else {
            while (numberOfTurns < 0) {
                Squarelotron rotate = new Squarelotron(size);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        rotate.squarelotron[size - 1 - j][i] = this.squarelotron[i][j];
                    }
                }
                numberOfTurns += 1;
                this.squarelotron = rotate.squarelotron;
            }
        }
    }

    public static void main(String[] args) {
        int size = 10;
        Squarelotron newSquarelotron = new Squarelotron(size);
        newSquarelotron = newSquarelotron.upsideDownFlip(2);
        // newSquarelotron = newSquarelotron.mainDiagonalFlip(1);
        // newSquarelotron.rotateRight(-2);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(newSquarelotron.squarelotron[i][j] + " ");
            }
            System.out.println();
        }
    }
}
