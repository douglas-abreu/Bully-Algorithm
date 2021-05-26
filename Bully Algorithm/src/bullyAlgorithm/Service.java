package bullyAlgorithm;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Service {
    private List<UrukHai> urukHaiArmy;
    private boolean electionAvaiable = false;
    private UrukHai captain = null;

    public Service() {
        urukHaiArmy = new CopyOnWriteArrayList<>();
    }
    
    public List<UrukHai> getUrukHaiArmy() {
        return urukHaiArmy;
    }

    public void setUrukHaiArmy(List<UrukHai> urukHaiArmy) {
        this.urukHaiArmy = urukHaiArmy;
    }

    public Boolean isElectionAvaiable() {
        return electionAvaiable;
    }

    public void setActiveElection(Boolean electionAvaiable) {
        this.electionAvaiable = electionAvaiable;
    }

    public UrukHai getCaptain() {
        return captain;
    }

    public void setCaptain(UrukHai captain) {
        this.captain = captain;
    }
    
    // Method to return the amount of Uruk Hais in the amry
    public int getNumAliveUrukHai() {
        int i = 0;
        for(UrukHai urukHai : getUrukHaiArmy()) {
            if(urukHai.isAlive()) {
                i++;
            }
        }
        return i;
    }
    
    // Method to kill the Captain
    public void killCaptain() {
        captain.setAlive(false);
        System.out.println("====================\n"
                + "Captain Uruk Hai was killed by Talion!");
    }
    
    // Method to verify is captain is alive, if true, nothing is executed, 
    // else the election starts
    public void getAliveCaptain() {
        boolean notifyed = false;
        if (getUrukHaiArmy().size() > 1) {
            while(notifyed == false) {
                UrukHai urukHai = getUrukHaiArmy().get(new Random().nextInt(getUrukHaiArmy().size()));
                if (urukHai.isCaptain() == false) {
                    if (urukHai.IsCaptainAlive() == false) {
                        if (isElectionAvaiable() == false) {
                            System.out.println("The Uruk Hais just started a fight for the post of Captain!");
                            setActiveElection(true);
                            urukHai.election();
                        }
                    }
                    notifyed = true;
                }
            }
        }
    }

    public void addUrukHai(UrukHai urukHai) {
        getUrukHaiArmy().add(urukHai);
    }
    
    // Method to kill a random Uruk Hai, Captain not included
    public void killRandomUrukHai() {
        boolean kill = false;
        while(kill == false){
            UrukHai urukHai = getUrukHaiArmy().get(new Random().nextInt(getUrukHaiArmy().size()));
            if(urukHai.isCaptain()) {
                kill = false;
            } else {
                urukHai.setAlive(false);
                kill = true;
            }
        }
        System.out.println("====================\n"
                + "Talion killed a random Uruk Hai!");
        System.out.println(toStringUrukHai());
    }
    
    // Method to print Uruk Hai informations
    public String toStringUrukHai() {
        String result = "";
        for(UrukHai urukHai : getUrukHaiArmy()) {
            if(urukHai.isAlive()) {
                result += urukHai + "\n";
            }
        }
        return result;
    }
}
