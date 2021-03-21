package apr.ss.utopia.cli;

public interface Menu<T> {

    void display();
    default void display(String header){
        System.out.println(header);
        display();
    }
    T getMenuSelection();

}
