import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Random rnd = new Random();
    public static Scanner input = new Scanner(System.in);
    public static Float user_life;
    public static Float opp_life;
    public static int min = 15;
    public static int max = 40;
    public static int score;
    public static int compScore;

    public static String user;
    public static String opponent;
    public static String[] user_attack;
    public static String[] opp_attack;


    //creating our characters
    public static String[][][] players = {{
            {"SPIDER-MAN", "web slap", "punch", "sling shot"},
            {"ABYSS", "mind control","undead stare","death choke"},
            {"BLACK-WIDOW", "high kick","knuckle punch","slice"},
            {"LOKI", "walk of loki","force of thunder","glance of sneakiness"},
            {"ANDROID-MAN", "samsung slap","window wipe","android attack"},
            {"AMERICA", "nationalism","capitalism","burger brawl" },
            {"ANT-MAN", "ant pinch","ant army","stomp"},
    }, {
            {"BATMAN", "intense stare","those metal bat things","batmobile"},
            {"SUPERMAN", "laser eyes","super strength","fly attack"},
            {"WONDER-WOMAN", "lasso choke","jump scare","metal wrists"},
            {"JOKER", "toxic gas","jokes","birthday party"},
            {"HARLEY-QUINN", "bat slap","puns","ponytail slash"},
            {"CAT-WOMAN", "cat scratch","bite","crazy cat lady"},
            {"CYBORG", "mind hack","utility smash","flame hands"}
    }
    };

    public static float lives[];



    public static void main(String[] args) {

        //print out the available characters


        boolean game = true;
        boolean play = true;

        //initializng outer gameplay
        while(play==true){
            //new lives every
            lives = new float[]{
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min),
                    (int)(Math.random()*(max-min+1)+min)

            };
            //print out all the characters
            call_players();

            //ask user who they want to be
            System.out.print("\n\nWHO WILL YOU BE? (please the character name with proper formatting): ");
            user = input.nextLine();

            //getting an opponent from the opposite team
            opponent =  players[pos(user)][rnd.nextInt(7)][0];

            //get the user's character's attaks + life
            user_attack = get_attacks(user);
            user_life = get_life(user);

            //get the opponents attacks and life
            opp_attack =  get_attacks(opponent);
            opp_life = get_life(opponent);

            game = true;

            //enter main game loop
            while(game==true){
                //check if the user's or opponents life is 0, if so ask to play again
                //print out the winnner

                if(user_life<=0 || opp_life<=0){
                    String winner = (user_life>0)? ""+user+" WINS!!!":""+opponent+" WINS!!!";
                    System.out.println(winner);


                    System.out.println("PLAY AGAIN? (y/n): ");
                    String answ = input.nextLine();

                    if(answ.equals("y")){
                        game = false;

                    }
                    else if (answ.equals("n")){
                        play = false;
                        break;
                    }
                }

                else{
                    battle();
                }

            };

        };
    }


    //for printing out all the characters
    public static void call_players() {
        float life = 0;
        int counter = 0;
        for (int i = 0; i < 2; i++) {
            //enters the 2nd dimension of the arr
            for (int j = 0; j < players[i].length; j++) {
                String name = players[i][j][0];
                String atk_1 = players[i][j][1];
                String atk_2 = players[i][j][2];
                String atk_3 = players[i][j][3];
                if(i==1){
                    life = lives[(j*2)+1];
                }
                else{
                    life = lives[j];
                }

                System.out.println("" + name + " | ATTACK " + atk_1 + ", " + atk_2 + ", " + atk_3 + " | LIFE: " + life + "  ");
            }
        }
    }



    //for getting the attacks of a player
    public static String[] get_attacks(String player) {

        //initiate a new arr for attacks
        String[] attack = new String[3];

        for(int i=0; i<2; i++){
            for(int j=0; j<players[i].length; j++) {
                if(players[i][j][0].equals(player)){
                    attack[0] = players[i][j][1];
                    attack[1] = players[i][j][2];
                    attack[2] = players[i][j][3];
                }
            }
        }
        return attack;
    }

    //getting the player's life
    public static float get_life(String player) {
        float life = 0;
        for(int i=0; i<2; i++){
            for(int j=0; j<players[i].length; j++) {
                if(players[i][j][0].equals(player)){
                    //check if player in the dc array, if so double the index
                    if(i==1){
                        life = lives[(j*2)+1];
                    }
                    //otherwise use natural index
                    else{
                        life = lives[j];
                    }

                }
            }
        }

        return life;
    }

    //getting the position of charac for opponent
    public static int pos(String character){
        int pos = 0;
        for (int i = 0; i<2; i++) {
            for (int j = 0; j < players[i].length; j++) {
                if(players[i][j][0].equals(character)){
                    pos = Math.abs(i-1);
                }
            }
        }

        return pos;
    }




    //main battle method
    public static void battle(){
        //random hit between 1 and 5
        int user_hit = rnd.nextInt(1,6);
        int opp_hit = rnd.nextInt(1,6);

        //print out the lives and character
        System.out.println("\n"+user+": "+user_life+" vs. "+opponent+": "+opp_life+"");

        //ask user to choose an attack and subtract teh hit from life
        System.out.print("\nChoose an attack between 1 and 3: ");
        int atk_choice_user = input.nextInt();
        opp_life -= user_hit;

        System.out.println("\n"+user+" attacks "+opponent+" with their "+user_attack[atk_choice_user-1]+"\n"+user_hit+" points of damage! ");

        //opponents turn
        int atk_choice = rnd.nextInt(2);
        user_life -= opp_hit;

        System.out.println("\n"+opponent+" attacks "+user+" with their "+opp_attack[atk_choice]+"\n"+opp_hit+" points of damage! ");

    }

}
