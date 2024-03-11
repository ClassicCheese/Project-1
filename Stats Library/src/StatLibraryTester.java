import java.util.ArrayList;

/**
 * Write a description of class TestExample here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatLibraryTester
{
    public static void main(String[] args){
        
        StatLibrary test = new StatLibrary();
        
        System.out.println("MN rule test");
        System.out.println("Given m = 3, n = 5");
        System.out.println("Expected: 15");
        System.out.println(test.findMNRule(3, 5));
        
        
    }
}
