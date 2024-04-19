import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import static java.lang.System.exit;
import static java.lang.System.setOut;
public class Main {
    static int row=0,column=0;
    static Scanner sc = new Scanner(System.in);
    public static void line(){
        System.out.println("\t------------------------------------------");
    }
    public static void bookingHall(){
        System.out.println("\t------------START BOOKING PROCESS---------");
        line();
        System.out.println("\tSHOW TIME INFORMATION ");
        System.out.println("\t A. Morning   (10:00 AM - 12:30 PM )");
        System.out.println("\t B. Afternoon ( 3:00 PM -  5:00 PM ) ");
        System.out.println("\t C. Night     ( 7:00 PM -  9:30 PM )");
    }
    public static void hall(String chair [][],String booking [][]){
        char ch ='A';
        for (int i = 0; i < chair.length; i++) {
            for (int j = 0; j < chair[i].length; j++) {
                System.out.print("\t|" + ch + "-" + (j+1) + " |: "+(booking[i][j] != null ?"bo":"av"));
            }
            ch++;
            System.out.println();
        }
    }
    public static void seatSection(String booking[][], String sids[][]){
        System.out.println("\tINSTRUCTION");
        System.out.println("\tSingle   : A-1");
        System.out.println("\tMultiple : A-1,A-2");
        System.out.print("\t=> Please select available to seat : ");
        String  seatInput = sc.next();

        System.out.print("\t=> Please enter student id         : ");
        String _idInput = sc.next();
        System.out.print("\tAre you sure to book (y/n) : ");
        String _Ans = sc.next().toUpperCase();
        String [] seatSelection = seatInput.split(",");

        for (String seatSections : seatSelection){
            String [] parts = seatSections.split("-");

            if (parts.length != 2) {
                System.out.println("Invalid seat selection format. Please use format like A-1");
            }
            int rowInput = parts[0].charAt(0)-'A';
            int colInput = Integer.parseInt(parts[1])-1;

            if(booking[rowInput][colInput] == null ){
                if(_Ans.equalsIgnoreCase("y")){
                    sids [rowInput][colInput] = _idInput;
                    booking[rowInput][colInput] = "bo";
                    System.out.println("\tBooking Success");
                }
                else{
                    System.out.println("\tWrong input");
                }
            }
            else{
                System.out.println("\tSeat already taken ! ");;
            }

        }
    }
    public static void rebootHall(String [][]newChair){
        char letter = 'A';
        for (int i = 0; i < newChair.length; i++) {
            for (int j = 0; j < newChair[i].length; j++) {
                System.out.print("\t|" + letter + "-" + (j+1) + " |: "+ "AV");
            }
            System.out.println();
        }
    }
    public static String timeBooking(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        return now.format(formatter);

    }
    public static void history(String [][] booking, String [][] sids){
        int n=1;
        for (int i = 0; i < booking.length; i++) {
            for (int j=0;j<booking[i].length;j++){
                if(booking[i][j] != null){
                    System.out.println("\n\t#NO : "+n);
                    System.out.println("\tSeats:"+(char)('A'+i)+"-"+(j+1));
                    System.out.println("\tStuId:"+sids[i][j]);
                    System.out.print("\tBooking time :"+timeBooking());
                    n++;
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("\t-----------HALL BOOKING SYSTEM---------");
        System.out.print("\tRow in hall    : ");
        int row = new Scanner(System.in).nextInt();
        System.out.print("\tColumn in hall : ");
        int column = new Scanner(System.in).nextInt();

        String chair [][] = new String [row][column];
        String booking[][] = new String [row][column];
        String booking1[][] = new String [row][column];
        String booking2[][] = new String [row][column];

        String sids [][] = new String[row][column];
        String sids1 [][] = new String[row][column];
        String sids2 [][] = new String[row][column];
        String newChair [][] = new String[row][column];

        do{
            System.out.println("\n\t-----------Application Menu---------");
            System.out.println("\t1.Booking");
            System.out.println("\t2.Hall");
            System.out.println("\t3.Show time");
            System.out.println("\t4.Roobot showtiime");
            System.out.println("\t5.History");
            System.out.println("\t6.Exit");
            System.out.print("\tChoose one option : ");
            Scanner sc = new Scanner(System.in);
            String op = sc.next();

            switch (op){
                case "1": {
                        bookingHall();
                        System.out.print("\n\tPlease select to show time (A,B,C): ");
                        String _char = sc.next().toUpperCase();
                        switch (_char) {
                            case "A": {
                                System.out.println("\tHALL - 1");
                                hall(chair, booking);
                                seatSection(booking, sids);
                            }
                            break;
                            case "B": {
                                System.out.println("\tHALL - 2 ");
                                hall(chair, booking1);
                                seatSection(booking1, sids1);
                            }
                            break;
                            case "C": {
                                System.out.println("\tHALL - 3 ");
                                hall(chair, booking2);
                                seatSection(booking2, sids2);
                            }
                            break;
                        }
                }break;
                case "2": {
                    System.out.println("\tHALL MORNING");
                    hall(chair,booking);
                    System.out.println("\tHALL AFTERNOON");
                    hall(chair,booking1);
                    System.out.println("\tHALL EVENING");
                    hall(chair,booking2);
                }break;
                case "3": {
                    System.out.println("\tDaily show time of CSTAD hall");
                    System.out.println("\t 1. Morning   (10:00 AM - 12:30 PM )");
                    System.out.println("\t 2. Afternoon ( 3:00 PM -  5:00 PM ) ");
                    System.out.println("\t 3. Night     ( 7:00 PM -  9:30 PM )");
                }break;
                case "4": {
                    System.out.println("\tHall Morning");
                    rebootHall(newChair);
                    System.out.println("\tHall Afternoon");
                    rebootHall(newChair);
                    System.out.println("\tHall Evening");
                    rebootHall(newChair);
                }break;
                case "5":{
                    history(booking,sids);
                    history(booking1,sids1);
                    history(booking2,sids2);
                }break;
                default:
                    System.out.println("\tExiting the Hall Booking System. Goodbye!");
                    exit(0);
                    break;
            }
        }while(true);
    }
}
