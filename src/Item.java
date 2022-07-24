package Project;

import java.io.*;
import java.util.*;

public class Item implements Serializable {
	private String ItemName, ItemGroup;
	private int Itemid;
	private float ItemPrice;

	public static ArrayList<Item> ItemList = new ArrayList<Item>();
	static Scanner in = new Scanner(System.in);
	static File f = new File("Item.dat");

	Item(String ItemName, String ItemGroup, int Itemid, float ItemPrice) {
		this.ItemName = ItemName;
		this.ItemGroup = ItemGroup;
		this.Itemid = Itemid;
		this.ItemPrice = ItemPrice;
	}
	
	static void ViewOnly() throws IOException {
		for (Item i : ItemList) {
			System.out.println("\nid: " + i.getItemid() + "\nName: " + i.getItemName() + "\nGroup: " + i.getItemGroup()
					+ "\nPrice: " + i.ItemPrice);
		}
		MainMenu.main(null);
	}
	
	static void ViewAll(int in) throws IOException {
		for (Item i : ItemList) {
			System.out.println("\nid: " + i.getItemid() + "\nName: " + i.getItemName() + "\nGroup: " + i.getItemGroup()
					+ "\nPrice: " + i.ItemPrice);
		}
		Item.main(null);
	}

	static void ViewAll() throws IOException {
		for (Item i : ItemList) {
			System.out.println("\nid: " + i.getItemid() + "\nName: " + i.getItemName() + "\nGroup: " + i.getItemGroup()
					+ "\nPrice: " + i.getItemPrice());
		}
		ItemAdmin.main(null);
	}

	static void ViewItem(int Itemid) throws IOException {
		for (Item i : ItemList) {
			if (i.getItemid() == Itemid) {
				System.out.println("\nid: " + i.getItemid() + "\nName: " + i.getItemName() + "\nGroup: "
						+ i.getItemGroup() + "\nPrice: " + i.ItemPrice);
				System.out.println("Do you want to add this item to cart?");
				System.out.println("1.Yes\n2.No");
				int cartchoice = in.nextInt();
				switch (cartchoice) {
				case 1:
					ShoppingCart.AddToCart(i);
					break;
				case 2:
					Item.main(null);
					break;
				}
			}
		}
	}

	public static String toString(Item i) {
		return "\nid: " + i.getItemid() + "\nName: " + i.getItemName() + "\nGroup: " + i.getItemGroup() + "\nPrice: "
				+ i.ItemPrice;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getItemGroup() {
		return ItemGroup;
	}

	public void setItemGroup(String itemGroup) {
		ItemGroup = itemGroup;
	}

	public int getItemid() {
		return Itemid;
	}

	public void setItemid(int itemid) {
		Itemid = itemid;
	}

	public float getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(float itemprice) {
		ItemPrice = itemprice;
	}

	static void writeto() {
		File f = new File("Item.dat");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
			for (Item i : ItemList)
				out.writeObject(i);
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		File f = new File("Item.dat");
		//writeto();
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
			while (true) {
				ItemList.add((Item) in.readObject());
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("1.View All Items\n2.View a selected Item");
		int choose = in.nextInt();
		switch (choose) {
		case 1:
			ViewAll(choose);
			break;
		case 2:
			System.out.println("Enter id of item to be viewed");
			int itemid = in.nextInt();
			ViewItem(itemid);
			break;
		default:
			System.out.print("Invalid Choice\n");
		}
	}
}
