package Server;
import java.util.Hashtable;
/**
 *
 * @author
 */
public class Agenda implements IRepositorio
{
    private Hashtable<String,Integer> ht = new Hashtable<String, Integer>();    
    public void asociar(String s, int v){
        ht.put(s, new Integer(v));
    }
    public int obtener(String s){
        return ht.get(s);
    }
}