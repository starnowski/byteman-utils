import org.jboss.byteman.rule.Rule;
import org.jboss.byteman.rule.helper.Helper;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class BytemanHelperAdapter extends Helper {

    private FileSystem fs = FileSystems.getDefault();

    protected BytemanHelperAdapter(Rule rule) {
        super(rule);
    }

    public void setFs(FileSystem fs) {
        this.fs = fs;
    }

    public void writeInptuStreamToFile(InputStream inputStream, String filePath) throws IOException {
        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        Files.write(fs.getPath(filePath).toAbsolutePath(), buffer);
    }
}
