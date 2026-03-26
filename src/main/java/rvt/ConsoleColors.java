package rvt;

public class ConsoleColors {
    public static void SetColor(GroupColors code){
        if (code == GroupColors.RED){
            System.out.println(GroupColors.RED.code + "This text is red!" + GroupColors.RESET.code);
        } else if(code == GroupColors.GREEN){
            System.out.println(GroupColors.GREEN.code + "This text is green!" + GroupColors.RESET.code);
        } else if (code == GroupColors.YELLOW){
            System.out.println(GroupColors.YELLOW.code + "This text is yellow!" + GroupColors.RESET.code);
        } else if (code == GroupColors.BLUE){
            System.out.println(GroupColors.BLUE.code + "This text is blue!" + GroupColors.RESET.code);
        } else if (code == GroupColors.PURPLE){
            System.out.println(GroupColors.PURPLE.code + "This text is purple!" + GroupColors.RESET.code);
        } else {
            System.out.println("Invalid group color!");
        }
    }
    public static void main(String[] args) {
        SetColor(GroupColors.YELLOW);    
    }
}