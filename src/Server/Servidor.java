package Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
/**
 *
 * @author 
 */
public class Servidor extends Thread{
    ServerSocket serverSocket=null;
    Agenda obj=new Agenda();
    
    public Servidor()
    {
    	try
    	{
    		serverSocket= new ServerSocket(6066);
            serverSocket.setSoTimeout(0);
    	}catch(IOException e)
    	{
    	}
    }
    public void run()
    {
    	while(true)
		{
    		try{
                System.out.println("Esperando cliente..."+serverSocket.getLocalPort());
                Socket server=serverSocket.accept();
                System.out.println("-------------------------------------");
                System.out.println("Conectado a: "
                		+"\nDirección: "+server.getRemoteSocketAddress()
                		+"\nPuerto: "+server.getPort()+
                		"\n-------------------------------------");
                //ENTRADA
                DataInputStream in=new DataInputStream(server.getInputStream());
                BufferedReader inB=new BufferedReader(new InputStreamReader(server.getInputStream()));
                //SALIDA
                DataOutputStream out=new DataOutputStream(server.getOutputStream());
    			PrintWriter outB = new PrintWriter(new BufferedWriter(new OutputStreamWriter(server.getOutputStream())),true);
                
                int op=inB.read();
                String nombre;
                int num,numServer;
                boolean resp;
                switch(op)
                {
                    case 1: 
                    		nombre=inB.readLine();
                    		num=Integer.parseInt(inB.readLine());
                    		System.out.println("Agenda Creada Exitosamente...");
                    		
                    		try{
                    			System.out.println("El contacto se asoció correctamente...");
                    			System.out.println("Contacto Ingresado por el Cliente");
                        		System.out.println("Nombre: "+nombre);
                        		System.out.println("Numero: "+num
                        		+"\n-------------------------------------");
                                obj.asociar(nombre, num);
                                resp=true;
                            }catch(Exception e)
                            {
                                resp=false;
                                System.out.println("ERROR: No se pudo guardar...");
                            }
                    		out.writeBoolean(resp);
                    break;
                    case 2:
    	            {
    	                nombre= inB.readLine();
    	                try{
    	                    numServer=obj.obtener(nombre);
    	                    System.out.println("-------------------------------------\n"
    	                    		+ "Datos Solicitados por el Cliente: \n"
    	                    		+ "Nombre: "+nombre+"\nNúmero: "+numServer
    	                    		+"\n-------------------------------------");
    	                    outB.println(numServer);
    	                }catch(Exception e)
    	                {
    	                    outB.println("No existe esa Referencia...");
    	                }
    	                break;
    	            }
                }
            server.close();          
            }catch(Exception e)
            {
                System.out.println("Conexión Finalizada...");
                e.printStackTrace();
            }
		}
        
                   
    }
    public static void main(String[] args) throws IOException
    { 
        Thread t=new Servidor();
        t.start();
    }
    
    
}
