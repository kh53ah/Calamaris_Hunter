import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;

public class CalamarisHunter {

    public static void main(String[] args) {

        Fetch fetch = new Fetch();
        boolean thereIsCalamari = fetch.getResponse();
        MyWindow window = new MyWindow(thereIsCalamari);
    }
}