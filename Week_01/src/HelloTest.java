import java.io.*;
import java.lang.reflect.Method;

public class HelloTest extends ClassLoader{


    public static void main(String[] args) throws Exception{
        Object hello = new HelloTest().findClass("Hello").newInstance();
        Class<?> clazz = hello.getClass();
        Method method = clazz.getDeclaredMethod("hello");
        method.invoke(hello);
    }



    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = new byte[0];
        try {
            bytes = inputStream2Byte();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] inputStream2Byte() throws IOException {

        ClassLoader classLoader = HelloTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("Hello.xlass");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int byteValue;
        while ((byteValue = inputStream.read()) != -1) {
            outputStream.write(255 - byteValue);
        }
        return outputStream.toByteArray();
    }
}
