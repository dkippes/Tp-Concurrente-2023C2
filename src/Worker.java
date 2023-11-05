import task.PoisonPill;
import task.Task;

public class Worker extends Thread {
    private Buffer buffer;
    private WorkerCounter workerCounter;

    public Worker(Buffer buffer, WorkerCounter workerCounter) {
        this.buffer = buffer;
        this.workerCounter = workerCounter;
    }

    @Override
    public void run() {
        while (true) {
            Task task = buffer.get();
            if (task instanceof PoisonPill) {
                workerCounter.workerFinished();
                break;
            }
            task.run();
        }
    }
}
