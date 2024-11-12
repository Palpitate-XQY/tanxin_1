import java.util.Arrays;
import java.util.PriorityQueue;

public class MultiMachineScheduling {

    /**
     * 贪心算法：最小化所有作业完成的最大时间
     *
     * @param n 作业数量
     * @param m 机器数量
     * @param times 每个作业的处理时间
     * @return 最短的完成时间
     */
    public static int scheduleJobs(int n, int m, int[] times) {
        // Step 1: 对作业时间进行降序排列
        Arrays.sort(times);
        int[] sortedTimes = new int[n];
        for (int i = 0; i < n; i++) {
            sortedTimes[i] = times[n - 1 - i];
        }

        // Step 2: 使用优先队列存储每台机器的当前总负载
        PriorityQueue<Integer> machineLoads = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            machineLoads.add(0);
        }

        // Step 3: 分配作业
        for (int time : sortedTimes) {
            // 取出当前负载最小的机器
            int minLoad = machineLoads.poll();
            // 更新该机器的负载并重新放入队列
            machineLoads.add(minLoad + time);
        }

        // Step 4: 获取最大负载值
        int maxLoad = 0;
        for (int load : machineLoads) {
            maxLoad = Math.max(maxLoad, load);
        }

        return maxLoad;
    }

    public static void main(String[] args) {
        int n = 5; // 作业数量
        int m = 3; // 机器数量
        int[] times = {4, 8, 3, 5, 2}; // 每个作业的处理时间

        int result = scheduleJobs(n, m, times);
        System.out.println("所有作业完成的最短时间为: " + result);

    }
}
