import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static double gaussAtBounds(){
        Random random = new Random();
        double a = 2;
        while (a>= 1 || a<= -1) {
            a = random.nextGaussian();
        }
        return a;
    }
    public static void main(String[] args) throws IOException {

       Sorte sorter = new Sorte();
        Random random = new Random();
        FileWriter fileWriter = new FileWriter("stats.txt");
        fileWriter.write("Первый ряд содежрит границы и название метода : \n");
        fileWriter.write("Следующий ряд содержит время выполнения сортировки (в мс):\n");
        fileWriter.write("TimSort | STL::stable-sort | Pigeonhole Sort | QuickRadixSort | IntroSort | STL::sort");

     for (int i = 0; i < 4; i++) {

           for (int j = 0; j < 4; j++) {

               for (int l = 0; l < 5 ; l++){
                   int size = 0;
                    long upperBound = 0;
                           switch (i){
                        case 0: size = 30000;
                            fileWriter.write("Размер: 30 000 ");
                            break;
                       case 1: size = 100000;
                           fileWriter.write("Размер: 100 000 ");
                            break;
                        case 2: size = 300000;
                           fileWriter.write("Размер: 300 000 ");
                            break;
                               case 3 :
                            fileWriter.write("Размер: 1 000 000 ");
                           break;
                       default: System.err.println("err");
                           break;
                   }
                    switch (j){
                       case 0: upperBound = (long)Math.pow(2,31);
                            fileWriter.write("Границы: 0 - 2^31 ");
                           break;
                        case 1: upperBound = size-1;
                           fileWriter.write("Границы: 0 - " + size + " ");
                           break;
                       case 2: upperBound = (long)Math.pow(2,15);
                           fileWriter.write("Границы: 0 - 2^15 ");
                           break;
                        case 3: upperBound = (long)Math.pow(2,31);
                            fileWriter.write("Границы: 0 - 2^31 (Нормальное распределение ) ");
                            break;
                        default: System.err.println("err");
                            break;
                    }
                   int[] arr = new int[size];
                    for (int g = 0; g < arr.length; g++){
                        if(j == 3){
                           int tmp = (int) gaussAtBounds();
                           if (tmp > 0) {
                                arr[g] = tmp;
                           }else{
                               arr[g] = tmp * -1;
                            }

                       }else{
                            arr[g] = 0 + (int) (Math.random() * upperBound);
                        }

                    }

                    fileWriter.write(Arrays.toString(sorter.sort(arr)));
                    fileWriter.write("\n");
               }
                fileWriter.write("\n");
            }
            fileWriter.write("\n");
        }

        fileWriter.write("end.");
        fileWriter.flush();
//        fileWriter.close();
//
//



        sorts.PigeonholeSort sort = new sorts.PigeonholeSort();
        int[] arr = { 8, 3, 2, 7, 4, 6, 8 };

        System.out.print("Sorted order is : ");

        sort.pigeonhole_sort(arr, arr.length);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");



    }



}
class PigeonholeSort {
        public static void pigeonhole_sort(int arr[],
                                           int n)
        {
            int min = arr[0];
            int max = arr[0];
            int range, i, j, index;

            for (int a = 0; a < n; a++) {
                if (arr[a] > max)
                    max = arr[a];
                if (arr[a] < min)
                    min = arr[a];
            }

            range = max - min + 1;
            int[] phole = new int[range];
            Arrays.fill(phole, 0);

            for (i = 0; i < n; i++)
                phole[arr[i] - min]++;

            index = 0;

            for (j = 0; j < range; j++)
                while (phole[j]-- > 0)
                    arr[index++] = j + min;
        }

}

