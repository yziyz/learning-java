package yz.learning.io;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author 袁臻
 * 2017/09/07 10:51
 */
public class URL {
    public static void main(String[] args) throws URISyntaxException {
        URI uri = new URI("file:///home/yuanzhen/.vimrc");
        File file = new File(uri);
        System.out.println(file.canWrite());
    }
}
