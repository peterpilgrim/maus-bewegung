package org.example.maus;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("Available Processors: "+availableProcessors);
    }
}
