import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.jboss.byteman.rule.Rule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;

public class BytemanHelperAdapterTest {

    FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
    BytemanHelperAdapter tested;

    @Before
    public void setUp()
    {
        tested = new BytemanHelperAdapter(Mockito.mock(Rule.class));
        tested.setFs(fs);
    }

    @Test
    public void shouldWriteStreamIntoFile() throws IOException {
        // given
        String originalString = "ZXVCLJASDF";
        InputStream inputStream = new ByteArrayInputStream(originalString.getBytes());

        // when
        tested.writeInptuStreamToFile(inputStream, "file1");

        // then
        String content = new String(Files.readAllBytes(fs.getPath("file1")));
        Assert.assertEquals(originalString, content);
    }
}