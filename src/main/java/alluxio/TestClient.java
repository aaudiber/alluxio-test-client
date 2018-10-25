package alluxio;

import alluxio.client.file.FileInStream;
import alluxio.client.file.FileSystem;
import alluxio.client.file.URIStatus;

/**
 * Repeatedly read the contents of the specified directory.
 */
public class TestClient {
  public static void main(String[] args) throws Throwable {
    FileSystem fs = FileSystem.Factory.get();
    String dir = args[0];
    while (true) {
      for (URIStatus path : fs.listStatus(new AlluxioURI(dir))) {
        if (path.isFolder()) {
          continue;
        }
        FileInStream in = fs.openFile(new AlluxioURI(path.getPath()));
        byte[] buf = new byte[4096];
        while (in.read(buf) >= 0);
        in.close();
        System.out.printf("Finished reading %s\n", path.getPath());
      }
    }
  }
}