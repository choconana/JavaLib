package threadcoreknowledge.stopthreads;

/**
* @Description: run方法无法抛出checked Exception，只能用try/catch
* @Param:
* @return:
* @Author: hezhidong
* @Mail: zdhe9535@163.com
* @Date: 2020/4/9 12:51
*/
public class RunThrowException {
    public void aVoid() throws Exception {
        throw new Exception();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
