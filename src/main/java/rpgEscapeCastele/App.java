package rpgEscapeCastele;

import java.util.Scanner;

import rpgEscapeCastele.ascii.PrintAscii;
import rpgEscapeCastele.gameMap.GameMap;
import rpgEscapeCastele.player.Player;
import rpgEscapeCastele.player.Team;
import rpgEscapeCastele.weapon.Weapon;
import rpgEscapeCastele.weapon.WeaponStore;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintAscii.printAsciiart("logo/logo1.txt");
        System.out.println("----------------------");
        System.out.println("Welcome to Escape Castele !!!");
        System.out.println("----------------------");

        System.out.println("Create the name player:");
        String playerName = scanner.nextLine();

        System.out.println("----------------------");
        int teamId;
        while (true) {
            Team.printTeams();
            String teamIdInput = scanner.nextLine().trim();

            try {
                teamId = Integer.parseInt(teamIdInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option !!");
                continue;
            }

            if (teamId < 1 || teamId > 12) {
                System.out.println("Invalid Team: " + teamId);
            } else {
                break;
            }
        }
        Team team = Team.fromId(teamId);
        System.out.println("----------------------");

        Player p = new Player(playerName, team);
        p.printAvatar();

        System.out.println("open weapon store: S/N");
        String r;
        while (true) {
            String response = scanner.nextLine().toUpperCase();
            if (response.equals("S") || response.equals("N")) {
                r = response;
                break;
            } else {
                System.out.println("Invalid option !!");
            }
        }

        WeaponStore weaponStore = new WeaponStore();
        if (r.equals("S")) {
            int weaponId;
            while (true) {
                weaponStore.openStock();
                System.out.println("Buy a weapon: ");
                String weaponIdinput = scanner.nextLine().trim();
                try {
                    weaponId = Integer.parseInt(weaponIdinput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid option !!");
                    continue;
                }

                if (weaponId < 1 || weaponId > 8) {
                    System.out.println("Invalid : " + weaponId);
                } else {
                    break;
                }
            }
            if (weaponStore.buyWeapon(weaponId, p)) {
                System.out.println("You can kill the monsters !! good louck !!");
            } else {
                System.out.println("You don't have money !!!");
            }

        } else if (r.equals("N")) {
            if (!p.getInventory().viewWeapons().isEmpty()) {
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

        }

        p.printAvatar();
        PrintAscii.printAsciiart("logo/logo2.txt");
        GameMap map = new GameMap(p);
        map.playerMovement();
        // inplements start the game ? 

    }

}
