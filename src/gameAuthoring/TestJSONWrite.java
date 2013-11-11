package gameAuthoring;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class TestJSONWrite {

    Writer writer;
    
    public TestJSONWrite () {
//        writer = new PrintWriter(System.out);
//        EnemyJSON testEnemy = new EnemyJSON("test", 1, "imgsrc", 2, 3);
//        
//        testEnemy.write(writer);
//        FileWriter file = new FileWriter("c:\\test.json");
//        file.write(testEnemy.);
//        file.flush();
//        file.close();
    }
    
    public void testWrite(){
        writer = new PrintWriter(System.out);
        EnemyJSON testEnemy = new EnemyJSON("test", 1, "imgsrc", 2, 3);
        try {
            writer.write("what", 0, 4);
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        try {
            writer.flush();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        testEnemy.write(writer);
    }
    
    public static void main(String[] args){
        TestJSONWrite x = new TestJSONWrite();
        x.testWrite();
    }

}
