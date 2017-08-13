package Server;

/**
 *
 * @author
 */
public interface IRepositorio {
   
    public void asociar(String key, int v);
    public int obtener(String key);
}
