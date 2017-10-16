//Name: Chand Babu Haryani
//Inventory with buttons of GUI.

import java.util.*;
import java.io.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Inventory extends Application implements EventHandler<ActionEvent> {

	Button bte, btf, btl, btq, btd;
	public static int i;
	public static File file = new File(
			"C:/Users/Chand/Desktop/eclipse/readme/textfile.txt");
	public static Scanner input = new Scanner(System.in);
	public static Entry entryList[] = new Entry[200];
	public static char command;
	public static int x = 0;
	public static int count = 0;
	public static PrintWriter write;
	public static String NAME;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage mystage) {

		bte = new Button("Enter");
		btf = new Button("Find");
		btl = new Button("List");
		btd = new Button("Delete");
		btq = new Button("Quit");
		Pane pane = new Pane();

		bte.setOnAction(this);
		btf.setOnAction(this);
		btl.setOnAction(this);
		btd.setOnAction(this);
		btq.setOnAction(this);

		Text text = new Text("Click one of the command icons to proceed");
		text.setLayoutX(75);
		text.setLayoutY(200);
		text.setFont(Font.font("Calibri", FontWeight.BOLD, FontPosture.REGULAR,
				20));
		pane.getChildren().add(text);

		bte.setLayoutX(75);
		bte.setLayoutY(250);
		pane.getChildren().add(bte);

		btf.setLayoutX(150);
		btf.setLayoutY(250);
		pane.getChildren().add(btf);

		btl.setLayoutX(225);
		btl.setLayoutY(250);
		pane.getChildren().add(btl);

		btd.setLayoutX(300);
		btd.setLayoutY(250);
		pane.getChildren().add(btd);

		btq.setLayoutX(375);
		btq.setLayoutY(250);
		pane.getChildren().add(btq);

		pane.setStyle("-fx-background-color:lightblue;");
		Scene scene = new Scene(pane, 500, 550);

		mystage.setTitle("Inventory");
		mystage.setScene(scene);
		mystage.show();
	}

	@Override
	public void handle(ActionEvent event) {

		try {
			readInventory();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			write = new PrintWriter(
					"C:/Users/Chand/Desktop/eclipse/readme/textfile.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (event.getSource() == bte) {
			// if you wanna use method inside a method use try and catch to
			// avoid exception at times
			try {
				enter("C:/Users/Chand/Desktop/eclipse/readme/textfile.txt");

			} catch (Exception e) {

			}
			System.out.println("click your Command to proceed \n");
		}

		else if (event.getSource() == btf) {
			System.out.print("\nEnter the name search: ");
			NAME = input.next();
			i = find(NAME);
			if (i >= 0) {

				System.out.println("quantity:  " + entryList[i].quantity);

				System.out.println("notes: " + entryList[i].notes);
				System.out.println("click your Command to proceed");
			}

			else {
				System.out.println("No entry with code: " + NAME);
				System.out.println("click your Command to proceed");
			}

		}

		else if (event.getSource() == btl) {

			list();
			System.out.println("click your Command to proceed");

		}

		else if (event.getSource() == btd) {
			delete();
			System.out.println("click your Command to proceed");

		} else if (event.getSource() == btq) {

			try {
				storeInventory();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
   // to enter the entries
	public static void enter(String FileName) throws Exception {

		String Name = "";
		String Notes = "";
		int q = 0;

		System.out.print("Enter name: ");
		Name = input.next();
		System.out.print("Enter quantity: ");
		q = input.nextInt();
		System.out.print("Enter notes: ");
		Notes = input.next();

		count++;

		while (x < count) {
			entryList[x] = new Entry(Name, q, Notes);
			x++;
		}
		
		// for sorting the entries in alphabetic order.
		Entry temp;
		int i;
		for (int k = 1; k < count; k++) {
			entryList[k].name.equalsIgnoreCase(entryList[k].name);

			int result = entryList[k - 1].name
					.compareToIgnoreCase(entryList[k].name);
			if (result > 0) {
				temp = (entryList[k - 1]);
				entryList[k - 1] = entryList[k];
				entryList[k] = temp;

			}

		}

	}
	
	//to search the entries
	public static int find(String key) {

		for (i = 0; i < count; i++) {
			if (key.equalsIgnoreCase(entryList[i].name)) {
				// IgnoreCase is a java method for ignoring upper and lowercase.

				return i;
			}
		}

		return -1;
	}
	// To delete entries
	public static void delete() {
		System.out.print("Enter the name to be delted: ");
		String item = input.next();
		int index = find(item);
		for (int c = index; c < entryList.length; c++) {
			entryList[c] = entryList[c + 1];
			System.out.println("Your item " + item + " is deleted ");
			count--;
			break;
		}

	}
   // To store entries into file
	public static void storeInventory() throws Exception {

		for (int k = 0; k < count; k++) {
			write.println(entryList[k].name);
			write.println(entryList[k].quantity);
			write.println(entryList[k].notes);

		}
		write.close();
		System.out.println("Inventory stored.");

	}

	//to list the entries
	public static void list() {

		for (int k = 0; k < count; k++) {
			System.out.println("name: " + entryList[k].name);
			System.out.println("quantity: " + entryList[k].quantity);
			System.out.println("notes: " + entryList[k].notes + "\n");
		}
	}
	
	// to read entries from file
	public static void readInventory() throws Exception {
		Scanner input = new Scanner(file);
		String word[] = new String[200];
		int t = 0;
		while (input.hasNext()) {

			word[t] = input.next();
			System.out.print(word[t] + "\n");
		}
		input.close();
	}
}

class Entry {

	String name;
	int quantity;
	String notes;

	Entry() {
		this.name = name;
		this.quantity = 0;
		this.notes = notes;
	}

	Entry(String newname, int nq, String nn) {
		name = newname;
		quantity = nq;
		notes = nn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
	








