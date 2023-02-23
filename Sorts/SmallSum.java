package Sorts;

public class SmallSum {
    // 小和问题
    // 分而治之的思想，找出右边有几个数比自己大
    private static int sum = 0;
    public static void smallSum(int[] nums) {
        partition(nums, 0, nums.length - 1);
    }

    public static void partition(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        partition(arr, L, M);
        partition(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L, p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            // merge的过程中，等于的情况下一定要先拷贝右侧数据
            if (arr[p1] < arr[p2]) {
                sum += (R - p2 + 1) * arr[p1];
                help[i++] = arr[p1++];
            } else {
                help[i++] = arr[p2++];
            }
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (int k : help) {
            arr[L++] = k;
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{3, 2, 5, 6, 9, 5, 3};
        int[] arr = new int[]{1, 3, 4, 2, 5};
        smallSum(arr);
        System.out.println(SmallSum.sum);
    }
}
