package lan.net;

import org.apache.camel.main.Main;

public class FreepbxAgent {
    public static void main(String[] args) throws Exception {
        new Main(FreepbxAgent.class).run(args);
    }
}
