package Project;

import java.io.*;
import java.util.*;

public class Administrator implements Serializable {
	private String AdminName, adminemailid;
	private int adminid, AdminPassword;
	
	static File f = new File("Administrator.dat");
	static ArrayList<Administrator> AdministratorList = new ArrayList<Administrator>();
	static Scanner in = new Scanner(System.in);

	Administrator(String AdminName, String adminemailid, int AdminPassword, int adminid) 
	{
		this.AdminName = AdminName;
		this.adminemailid = adminemailid;
		this.AdminPassword = AdminPassword;
		this.adminid = adminid;
	}

	static void Register() throws IOException {
		System.out.println("Enter Name: ");
		String AdminName = in.next();
		System.out.println("Enter emaild: ");
		String adminemailid = in.next();
		System.out.println("Enter password (numbers only): ");
		int AdminPassword = in.nextInt();
		System.out.println("Enter admin id (numbers only): ");
		int adminid = in.nextInt();

		Administrator New = new Administrator(AdminName, adminemailid, AdminPassword, adminid);
		AdministratorList.add(New);
		writeto();
		System.out.print("Admin Successfully Registered");
		Administrator.afterloginadmin(New);
	}

	static void Login() throws IOException {
		System.out.println("Enter Admin id: ");
		int adminid = in.nextInt();
		System.out.println("Enter password: ");
		int AdminPassword = in.nextInt();

		for (Administrator i : AdministratorList) {
			if (i.adminid == adminid && i.AdminPassword == AdminPassword) {
				Administrator.afterloginadmin(i);
			} else {
				System.out.println("Admin id and password does not match/exist");
				Administrator.main(null);
			}
		}
	}

	static void afterloginadmin(Administrator i) throws IOException {
		System.out.println("\nWelcome " + i.AdminName + "\nChoose option");
		System.out.println("1.Manage Account(Admin)\n2.Item Menu\n3.Back");
		int choosea = in.nextInt();

		switch (choosea) {
		case 1:
			Administrator.Manage_Account(i.adminid);
			break;
		case 2:
			ItemAdmin.main(null);
			break;
		case 3:
			Administrator.main(null);
			break;
		default:
			System.out.println("Invalid choice");
		}
	}

	public static String toString(Administrator i) {
		return "\nName: " + i.AdminName + "\nemailid: " + i.adminemailid + "\nid: " + i.adminid;
	}

	static void Manage_Account(int id) throws IOException {
		for (Administrator i : AdministratorList) {
			if (i.adminid == id) {
				Administrator old = i;
				System.out.println(toString(i));
				System.out.println("MANAGE ACCOUNT\n1.Change password\n2.Change emailid\n3.Back");

				int choicea = in.nextInt();
				switch (choicea) {
				case 1:
					System.out.println("Enter old password: ");
					int oldp = in.nextInt();
					if (i.AdminPassword == oldp) {
						System.out.println("Enter new password: ");
						int AdminPassword = in.nextInt();
						Administrator Newp = new Administrator(i.AdminName, i.adminemailid, AdminPassword, i.adminid);
						AdministratorList.add(Newp);
						System.out.println("Password changed successfully");
					}
					break;
				case 2:
					System.out.println("Enter emaild: ");
					String adminemailid = in.next();
					Administrator Newe = new Administrator(i.AdminName, adminemailid, i.AdminPassword, i.adminid);
					AdministratorList.add(Newe);
					System.out.println("emailid changed successfully");
					break;
				case 3:
					Administrator.afterloginadmin(i);
					break;
				default:
					System.out.println("Invalid Option");
				}
				AdministratorList.remove(old);
				writeto();
				Administrator.afterloginadmin(i);
			}
		}
	}

	static void writeto() {
		File f = new File("Administrator.dat");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			for (Administrator i : AdministratorList)
				out.writeObject(i);
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			while (true) {
				AdministratorList.add((Administrator) in.readObject());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("1.Register \n2.Admin Login \n3.Back ");
		int choosemaina = in.nextInt();
		switch (choosemaina) {
		case 1:
			Administrator.Register();
			break;
		case 2:
			Administrator.Login();
			break;
		case 3:
			MainMenu.main(null);
			break;
		default:
			System.out.print("Invalid Choice\n");
		}
	}
}