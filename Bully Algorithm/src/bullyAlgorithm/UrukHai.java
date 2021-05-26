package bullyAlgorithm;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public final class UrukHai {
    private int strength;
    private Service service = null;
    private boolean alive = true;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public boolean isCaptain() {
        return getService().getCaptain() == this;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public UrukHai(Service service) {
        setService(service);
        service.addUrukHai(this);
        genStrength();
    }

    public UrukHai(Service service, boolean initExecution) {
        this(service);
    }

    public boolean isAliveRequest(UrukHai urukhai) {
        return urukhai.isAliveResponse();
    }

    public boolean isAliveResponse() {
        return isAlive();
    }
    
    // Method to update the new Captain after an election
    public synchronized void updateCaptain(UrukHai newCaptain) {
        System.out.println("====================\n" +
                "An Uruk Hai prevailed the fight!\nNew Captain Uruk Hai with strength:" + newCaptain.getStrength());
        getService().setCaptain(newCaptain);
    }
    
    // Method to verify if Captain is alive
    public boolean IsCaptainAlive() {
        System.out.println("====================\n" + 
                "Verifying if Captain Uruk Hai still alive: " + getService().getCaptain().isAlive());
        return getService().getCaptain().isAlive();
    }
    
    // Method elections is executed if Captain is dead
    public synchronized void election() {
        List<UrukHai> aliveUrukHais = new ArrayList<>();
        for (UrukHai urukHai : getService().getUrukHaiArmy()) {
            if (urukHai.getStrength() > getStrength()) {
                boolean response = isAliveRequest(urukHai);
                if (response == true) {
                    aliveUrukHais.add(urukHai);
                }
            }
        }
        if (aliveUrukHais.isEmpty()) {
            getService().setCaptain(this);
            updateCaptain(this);
            getService().setActiveElection(false);
            return;
        }
        aliveUrukHais.forEach((urukHai) -> urukHai.election());
    }
    
    // Method used to generate an random value to new Uruk Hai's strength
    public void genStrength() {
        boolean validStrength = false;
        int strength = 0;
        while (validStrength == false) {
            validStrength = true;
            Random random = new Random();
            strength = random.nextInt(1000);
            for (UrukHai urukHai : getService().getUrukHaiArmy()) {
                if (urukHai.getStrength() == strength) {
                    validStrength = false;
                }
            }
        }
        setStrength(strength);
    }
    
    // Method used to print Uruk Hai information, if he is Captain or not
    @Override
    public String toString() {
        return "Strength " + getStrength() + (isCaptain() ? " -> Captain Uruk Hai!" : "");
    }
}
