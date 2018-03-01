
import ioUtils.PathScanWithExcelFile;

public class DoMain {
    public static void main(String[] args) {
        PathScanWithExcelFile ps = new PathScanWithExcelFile();
        try {
            ps.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
