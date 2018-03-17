
package inven;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class myInventory extends Application {

	public static Button list, save, search, btd, btq, btf, back, edit,editBack, editDone;
	public static int i;
	public static File file = new File ("file.txt");
	public static Scanner input = new Scanner(System.in);
	public static Entry entryList[] = new Entry[200];
	public static char command;
	public static int x = 0;
	public static int count = 0;
	public static PrintWriter write;
	public static String NAME;

        public static TextField tf1, tf2, tf3, searchTF, eTF, editQuantityTF,editNotesTF;
        public static Label l1, l2, l3, textLabel, eLabel;
        public static String na, qu, no;
        public static Scene scene, lscene,searchScene, dScene, editScene;
        public static Pane pane, lpane, sPane, dPane, editPane;
        public static Text text;
        public static TableView<Entry> table;
        public static Entry product;
        
        
        
	public static void main(String[] args) throws Exception {
		launch(args);
	}
	
        @Override
      	public void start(Stage mystage) throws Exception{
           		try {
			write = new PrintWriter(
					file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
              
            Image saveImage = new Image(getClass().getResourceAsStream("save.png"));
            ImageView saveView = new ImageView(saveImage);
            saveView.setFitWidth(20);
            saveView.setFitHeight(20);
            Image searchImage = new Image(getClass().getResourceAsStream("search.png"));
            ImageView searchView = new ImageView(searchImage);
            searchView.setFitWidth(20);
            searchView.setFitHeight(20);
            Image quitImage = new Image(getClass().getResourceAsStream("quit.png"));
            ImageView quitView = new ImageView(quitImage);
            quitView.setFitWidth(15);
            quitView.setFitHeight(15);
            Image listImage = new Image(getClass().getResourceAsStream("list.png"));
            ImageView listView = new ImageView(listImage);
            listView.setFitWidth(15);
            listView.setFitHeight(15);
            Image editImage = new Image(getClass().getResourceAsStream("edit.png"));
            ImageView editView = new ImageView(editImage);
            editView.setFitWidth(15);
            editView.setFitHeight(15);
            Image backImage = new Image(getClass().getResourceAsStream("back.png"));
            ImageView backView = new ImageView(backImage);
            backView.setFitWidth(15);
            backView.setFitHeight(15);
            Image deleteImage = new Image(getClass().getResourceAsStream("delete.png"));
            ImageView deleteView = new ImageView(deleteImage);
            deleteView.setFitWidth(15);
            deleteView.setFitHeight(15);
            Image doneImage = new Image(getClass().getResourceAsStream("done.png"));
            ImageView doneView = new ImageView(doneImage);
            doneView.setFitWidth(15);
            doneView.setFitHeight(15);
           
            pane = new Pane();
            lpane = new Pane();
            sPane = new Pane();
            dPane = new Pane(); 
            editPane = new Pane();

            tf1 = new TextField();
            tf2 = new TextField();
            tf3 = new TextField();
            searchTF = new TextField();        

            back = new Button("Back");
            l1 = new Label("Enter Name ");
            l2 = new Label("Enter Quantity ");
            l3 = new Label("Enter Notes ");
            textLabel = new Label("Welcome to Inventory Management");   
            eLabel = new Label("Enter ");
            eTF =  new TextField();
            editQuantityTF = new TextField();
            editNotesTF = new TextField();
            
            save = new Button("  Save", saveView);
            search = new Button("Search", searchView );
            list = new Button(" List", listView);
            text = new Text("Please enter your search below ");
            edit = new Button("Edit", editView);
            editBack = new Button("Back", backView);
            editDone = new Button ("Done", doneView);
                    
                
	    l1.setLayoutX(50);
            l1.setLayoutY(80);
            tf1.setLayoutX(50);
            tf1.setLayoutY(100);
            tf1.setPromptText("Name");
               
            l2.setLayoutX(50);
            l2.setLayoutY(130);
            tf2.setLayoutX(50);
            tf2.setLayoutY(150);
            tf2.setPromptText("Quantity");
              
            l3.setLayoutX(50);
            l3.setLayoutY(180);
            tf3.setLayoutX(50);
            tf3.setLayoutY(200);
            tf3.setPromptText("Notes");
                
            save.setLayoutX(50);
            save.setLayoutY(250);
            
            search.setLayoutX(50);
            search.setLayoutY(300);
                 
            list.setLayoutX(50);
            list.setLayoutY(400);
                 
            btq = new Button("Quit", quitView);
            btq.setLayoutX(50);
            btq.setLayoutY(350);
              
            back.setLayoutX(0);
            back.setLayoutY(400);
                 
            textLabel.setLayoutX(100);
            textLabel.setLayoutY(25);
            textLabel.setStyle("-fx-border-color:red; -fx-background-color:lightgreen;");  
            textLabel.setFont(new Font("Arial", 20));
            
            text.setLayoutX(50);
            text.setLayoutY(40);
            text.setFont(new Font("Arial", 15));
                 
            searchTF.setLayoutX(50);
            searchTF.setLayoutY(50);
            searchTF.setPromptText("Search");
                 
            Button newSearch = new Button("Search");
            newSearch.setLayoutX(50);
            newSearch.setLayoutY(100);
            
            Label textLabel2 = new Label("Quantity and Notes related to your search are: ");
            textLabel2.setLayoutX(50);
            textLabel2.setLayoutY(160);
            textLabel2.setStyle("-fx-border-color:green; -fx-background-color:pink;"); 
            textLabel2.setFont(new Font("Arial", 20));

             Label qSlabel = new Label("Quantity");
            qSlabel.setLayoutX(50);
            qSlabel.setLayoutY(220);

            TextField sQuantityField = new TextField();
            sQuantityField.setLayoutX(50);
            sQuantityField.setLayoutY(240);

             Label nSlabel = new Label("Notes");
             nSlabel.setLayoutX(50);
             nSlabel.setLayoutY(280);

            TextField sNotesField = new TextField();
            sNotesField.setLayoutX(50);
            sNotesField.setLayoutY(300);

            Button clear = new Button("Clear");
            clear.setLayoutX(50);
            clear.setLayoutY(350);

            Button sBack = new Button("Back", backView);
            sBack.setLayoutX(50);
            sBack.setLayoutY(400);


            Button delete = new Button("Delete",deleteView);
            delete.setLayoutX(50);
            delete.setLayoutY(450);
            
            edit.setLayoutX(50);
            edit.setLayoutY(500);

            Label deleteLabel = new Label("Enter the item to be deleted below");
            deleteLabel.setLayoutX(50);
            deleteLabel.setLayoutY(80);


            TextField dTF  = new TextField();
            dTF.setLayoutX(50);
            dTF.setLayoutY(100);

            Button doneDeleting = new Button("Done", doneView);
            doneDeleting.setLayoutX(50);
            doneDeleting.setLayoutY(150);

            Button mainMenu = new Button("Main Menu");
            mainMenu.setLayoutX(50);
            mainMenu.setLayoutY(200);
            
            eLabel.setLayoutX(50);
            eLabel.setLayoutY(50);
            
            eTF.setLayoutX(50);
            eTF.setLayoutY(80);
            eTF.setPromptText("Item to be edited");
            
            editQuantityTF.setLayoutX(50);
            editQuantityTF.setLayoutY(130);
            editQuantityTF.setPromptText("Quanity");
            
            editNotesTF.setLayoutX(50);
            editNotesTF.setLayoutY(180);
            editNotesTF.setPromptText("Notes");
           
            editDone.setLayoutX(50);
            editDone.setLayoutY(250);
            
            editBack.setLayoutX(50);
            editBack.setLayoutY(300);
            
            
            TableColumn<Entry, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setMinWidth(200);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

           //Price column
            TableColumn<Entry, Double> quColumn = new TableColumn<>("Quantity");
            quColumn.setMinWidth(100);
            quColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

           //Quantity column
            TableColumn<Entry, String> noColumn = new TableColumn<>("Notes");
            noColumn.setMinWidth(100);
            noColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

            table = new TableView<>();
            table.getColumns().addAll(nameColumn, quColumn, noColumn);       

            lpane.getChildren().addAll(table, back);  
            sPane.getChildren().addAll(searchTF, text, newSearch, sQuantityField, sNotesField, textLabel2, nSlabel,qSlabel, sBack, clear);
            dPane.getChildren().addAll(deleteLabel, dTF, doneDeleting, mainMenu);     
            pane.getChildren().addAll(l1,l2,l3, tf1, tf2, tf3, save, search, list,btq, textLabel, delete, edit);
            editPane.getChildren().addAll(eLabel, eTF, editBack, editQuantityTF, editNotesTF, editDone);
                           
            pane.setStyle("-fx-background-color:yellow;");
            sPane.setStyle("-fx-background-color:lightgreen;");
            dPane.setStyle("-fx-background-color:pink;");
            editPane.setStyle("-fx-background-color:lightblue;");
                
            scene = new Scene(pane, 500, 550);
            lscene = new Scene (lpane, 400,440);
            searchScene = new Scene(sPane, 500,550);
            dScene = new Scene (dPane, 500,550);
            editScene = new Scene(editPane, 500, 550);
                
            
            mystage.setTitle("Inventory");
	    mystage.setScene(scene);
            mystage.show();
            
            
            readInventory();
                
                                    
            save.setOnAction((ActionEvent event)->{
                 try {
			save();
			} 
                 catch (Exception e) {
			}
			System.out.println("click your Command to proceed \n");
                        add();
            });
            
            list.setOnAction((ActionEvent event)->{
                mystage.setScene(lscene);
            });
            
            btq.setOnAction((ActionEvent event)->{
                 try {
                        storeInventory();
                    } catch (Exception ex) {
                        Logger.getLogger(myInventory.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 System.exit(0);
            });
          
            back.setOnAction((ActionEvent event)->{
                mystage.setScene(scene);
            });
        
            search.setOnAction((ActionEvent event)->{
                mystage.setScene(searchScene);
             });
         
            newSearch.setOnAction((ActionEvent event)->{
                NAME = searchTF.getText();
                          i = find(NAME);
			if (i >= 0) {
                            sQuantityField.setText(entryList[i].quantity);
                            sNotesField.setText(entryList[i].notes);
                         }
			else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("No entry with code: " + NAME);
                alert.showAndWait();
       			 }
             });
         
            sBack.setOnAction((ActionEvent event)->{
                 mystage.setScene(scene);
             });
         
            clear.setOnAction((ActionEvent event)->{
             searchTF.clear();
             sQuantityField.clear();
             sNotesField.clear();
             
            });
         
            delete.setOnAction((ActionEvent event)->{
                 mystage.setScene(dScene);
            });
         
            doneDeleting.setOnAction((ActionEvent event)->{
                  String item = dTF.getText();
                  Alert dAlert = new Alert(Alert.AlertType.ERROR);
                  int index = find(item);
		  for (int c = index; c < entryList.length; c++) {
			table.getItems().set(c, entryList[c+1]);
			dAlert.setContentText("Your item " + item + " is deleted ");
                         dAlert.showAndWait();
			count--;
			break;
                }
            });
                 
                 
            mainMenu.setOnAction((ActionEvent event)->{
                mystage.setScene(scene);
            });
            
            edit.setOnAction((ActionEvent event)->{
            
            mystage.setScene(editScene);
            
            });
            
            editBack.setOnAction((ActionEvent event)->{
                mystage.setScene(scene);
            });
            
            editDone.setOnAction((ActionEvent event)->{
             String editEntry = eTF.getText();
             int item = find(editEntry);
             String editedQuantity = editQuantityTF.getText();
             String editedNotes  = editNotesTF.getText();
             entryList[item].quantity= editedQuantity;
             entryList[item].notes=editedNotes;
             table.getItems().set(item, entryList[item]);
          
             Alert editAlert = new Alert(Alert.AlertType.ERROR);
            editAlert.setContentText("Your entry " + editEntry + " is edited ");
            editAlert.showAndWait();
          
            });
   
	}

        
   // to enter the entries
	public static void save() throws Exception {
                na = tf1.getText();
                qu = tf2.getText();
                no = tf3.getText();
                count++;
                while (x < count) {
                            entryList[x] = new Entry(na, qu, no);
                            x++;
                    }
		// for sorting the entries in alphabetic order.
		Entry temp;
		for (int k = 1; k < count; k++) {
			//entryList[k].name.equalsIgnoreCase(entryList[k].name);
			int result = entryList[k - 1].name
					.compareToIgnoreCase(entryList[k].name);
			if (result > 0) {
				temp = (entryList[k - 1]);
				entryList[k - 1] = entryList[k];
				entryList[k] = temp;
			}
		}
	}
         public void add(){
                product = new Entry();
                product.setName(tf1.getText());
                product.setQuantity(tf2.getText());
                product.setNotes(tf3.getText());
                table.getItems().add(product);
                tf1.clear();
                tf2.clear();
                tf3.clear();
    }
     
	//to search the entries
	public static int find(String key) {
		for (i = 0; i < count; i++) {
			if (key.equalsIgnoreCase(entryList[i].name)) {
			    return i;
			}
		}
		return -1;
	}
	
   // To store entries into file
	public static void storeInventory() throws Exception {
            		for (int k = 0; k < count; k++) {
                            write.println(entryList[k].name);
                            write.println(entryList[k].quantity);
                            write.println(entryList[k].notes);
		}
            Alert quitAlert = new Alert(Alert.AlertType.ERROR);
            quitAlert.setContentText("Your entries are stored to file ");
            quitAlert.showAndWait();   
                        
                        
		write.close();
		//System.out.println("Inventory stored.");
	}
	// to read entries from file
	public static void readInventory() throws Exception {
		Scanner input = new Scanner(file);
		String word[] = new String[200];
		int readCount = 0;
		while (input.hasNext()) {
			String readName = input.next();
                        String  readQuantity = input.next();
                        String readNotes = input.next();
                        
                        //table.getItems().addAll(entryList[readCount]);
                        readCount++;
		}
                input.close();
	}
       
}


	
