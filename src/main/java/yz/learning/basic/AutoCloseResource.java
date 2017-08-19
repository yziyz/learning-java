package yz.learning.basic;

import java.time.LocalDateTime;

/**
 * 自动关闭资源
 *
 * @author 袁臻
 * 2017/08/19 15:40
 */
public class AutoCloseResource implements AutoCloseable {
    /**
     * 构造方法
     */
    private AutoCloseResource() {
        System.out.println(LocalDateTime.now() + " Constructed");
    }

    /**
     * 操作
     *
     * @throws InterruptedException 当线程被终止时抛出异常
     */
    private void work() throws InterruptedException {
        System.out.println(LocalDateTime.now() + " Doing something");
        Thread.sleep(2000);
    }

    @Override
    public void close() throws Exception {
        System.out.println(LocalDateTime.now() + " Closed");
    }

    /**
     * 测试方法
     * 输出如下：
     2017-08-19T15:50:31.219 Constructed
     2017-08-19T15:50:31.220 Doing something
     2017-08-19T15:50:33.220 Closed
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        try (AutoCloseResource resource = new AutoCloseResource()) {
            //使用资源
            resource.work();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
