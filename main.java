public class main {


    public static void main(String[] args) {
        state systemstate = state.locked; //we start in the locked state.
        turnstile turnstile = new turnstile();
        while (true) { // the state machine shouldn't ever stop
            switch (systemstate) { // you can almost consider this switch statement the overall state machine.
                case locked:
                    if(turnstile.hasChange()){
                        systemstate = state.unlocked; // if there is change it should not be locked
                    }
                    else{
                        turnstile.displayMessage();
                        systemstate = state.locked; // we stay in the current state. this isn't neccecary but if you actually are coding this you'd want to log all state transitions even the ones that don't seem to do anything;
                        // you definitely would want a function that anytime you change  the states it automatically logs what happened. it makes debugging much easier
                    }
                    break;
                case unlocked:
                    if(turnstile.personGoneThrough()){
                        systemstate = state.locked; // when a person uses the coin we need to relock
                    }
                    else{
                        systemstate = state.unlocked; // once again make it explicit we are staying unlocked. I cannot emphasize enough how important it is to log the states and transitions. the #1 error in FSMs is being in the wrong state at the wrong time or for the wrong reason.
                    }
            }
        }
    }
}


enum state {
    locked,
    unlocked
}

class turnstile {
    turnstile(){}

    /**
     * @return is there change in the machine or not
     */
    boolean hasChange(){
        return true;
    }

    /**
     *
     * @return has a person passed through and used up the coin
     */
    boolean personGoneThrough(){
        return true;
    }

    /**
     * tell the person that they need to pay
     */
    void displayMessage(){
        System.out.println("please insert money to unlock");
    }
}