
/**
 * Write a description of class Energy here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Energy extends Card
{
    private String name;
    
    public Energy(){
        name = "Rainbow energy";
    }
    
    @Override
    public String toString() {
        return "Energy: " + name + "|";
    }
}
