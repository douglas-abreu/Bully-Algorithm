package bullyAlgorithm;

import java.util.logging.Level;
import java.util.logging.Logger;

/*  
    Welcome to Mordor!

    In this code we are going to see the "Bully Algorithm" working in the
    Sauron's Uruk Hai army. They have an enemy called Talion, who can kill the 
    army's Captain or random Uruk Hais. But an army can't stay without a Captain,
    an election start when the army notice their Captain is dead! As we know,
    Uruk Hais are not civilized, so they fight with each other to get the free post
    of Captain, the strongest will prevail!

    What Uruk Hai looks like -> https://www.zbrushcentral.com/t/uruk-hai/362994

    This code was based from: https://github.com/vinibiavatti1/Bully
    
*/

public class MainInit {
    public static void main(String[] args) {
        Service service = new Service();
        UrukHai newUrukHai = new UrukHai(service, true);
        service.setCaptain(newUrukHai);
        System.out.println("====================\n" +
                           "A new Uruk Hai was born...with strength: " + newUrukHai.getStrength() + "\n" +
                           service.toStringUrukHai() +
                           "He is the new Captain!\n");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(15 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainInit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    UrukHai newUrukHai = new UrukHai(service, true);
                    System.out.println("====================\n" +
                                       "A new Uruk Hai was born...with strength: " + newUrukHai.getStrength() + "\n" +
                                       service.toStringUrukHai());
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(40 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainInit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    service.killRandomUrukHai();
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(20 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainInit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    service.getAliveCaptain();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(50 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainInit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    service.killCaptain();
                }
            }
        }).start();
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    try {
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainInit.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("====================\n"
                            + "The Sauron's army size now is: " + service.getNumAliveUrukHai());
                }
            }
        }).start();
    }
}
