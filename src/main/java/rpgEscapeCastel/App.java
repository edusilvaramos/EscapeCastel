package rpgEscapeCastel;

import java.util.Scanner;

import rpgEscapeCastel.ascii.PrintAscii;
import rpgEscapeCastel.player.Player;
import rpgEscapeCastel.player.Team;
import rpgEscapeCastel.weapon.Weapon;
import rpgEscapeCastel.weapon.WeaponStore;


public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintAscii.printAsciiart("logo/logo1.txt");
        System.out.println("----------------------");
        System.out.println("Welcome to Escape Castel !!!");
        System.out.println("----------------------");

        System.out.println("Create the name player:");
        String playerName = scanner.nextLine();
        System.out.println("----------------------");

        Team.printTeams();
        int teamId = scanner.nextInt();
        Team team = Team.fromId(teamId);
        System.out.println("----------------------");
        String playerTeam = scanner.nextLine();
        System.out.println("----------------------");

        Player p = new Player(playerName, team);
        p.printAvatar();

        System.out.println("open weapon store: S/N");
        String r = new Scanner(System.in).nextLine();
        WeaponStore weaponStore = new WeaponStore();
        if (r.equals("S")) {
            weaponStore.openStock();
            System.out.println("Buy a weapon:");
            int idWeapon = new Scanner(System.in).nextInt();
            p.getInventory().addWeapon(Weapon.fromId(idWeapon));
            weaponStore.buyWeapon(idWeapon);
        } else if (r.equals("N")) {
            System.out.println("Sell a weapon: S/N");
            r = new Scanner(System.in).nextLine();
            if (r.equals("N")) {
                p.printInventory();
                return;
            }
            int idWeapon = new Scanner(System.in).nextInt();
            p.getInventory().removeWeapon(Weapon.fromId(idWeapon));
            weaponStore.sellWeapon(idWeapon);
        }

        p.printAvatar();
        
    
        // inplements start the game ? 
        PrintAscii.printAsciiart("logo/logo2.txt");
        
    }

}
