package au.edu.usc.bict_explorer.rules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class ReportGui extends Application {

	private ArrayList<Hierarchy> hierarchy;
	private ArrayList<String[]> content=new ArrayList<>();
	TableView<String[]> table=new TableView<String[]>();
	ObservableList<String[]> data=FXCollections.observableArrayList();
	
	public ReportGui(ArrayList<Hierarchy> hierarchy) {
		this.hierarchy=hierarchy;
		
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Report");
		GridPane gridPane = new GridPane();
	      gridPane.setMinSize(500, 500);
	      gridPane.setPadding(new Insets(10, 10, 10, 10));
	      gridPane.setVgap(5); 
	      gridPane.setHgap(5);
		 final Label label = new Label("Career Book-Selected Courses");
	        label.setFont(new Font("Arial", 20));
	        label.underlineProperty();
	        label.paddingProperty();
		gridPane.add(label, 0, 0);
	     
		 int careerRow=1;
		 int minorRow=1;
		 int courseRow=1;
		 
		 int column=3;
		 int count=1;
		for (Hierarchy root : hierarchy) 
		{
			if(root.getCheckBox().isSelected())
			{
				Label careersLabel=new Label(root.getCheckBox().getText());
				 gridPane.add(careersLabel, 0, careerRow);
				for (Hierarchy child1 : root.getChild()) 
				{
					if(child1.getCheckBox().isSelected())
					{
						Label minorsLabel=new Label(child1.getCheckBox().getText());
						gridPane.add(minorsLabel, 1, minorRow);
						for (Hierarchy child2 : child1.getChild()) {
							if(child2.getCheckBox().isSelected()) {
								content.add(new String[] {root.getCheckBox().getText()+"   ", child1.getCheckBox().getText()+"   ", child2.getCheckBox().getText()+"   "});
								Label coursesLabel=new Label(count+". "+child2.getCheckBox().getText());
								gridPane.add(coursesLabel, column, courseRow);
								column=column+2;
								count++;
						}
						
					}
						courseRow=courseRow+4;
						column=3;
						count=1;
						minorRow=minorRow+4;
				}
				
			}
				careerRow=careerRow+8;
		}
		}
		
		Button buttonReport = new Button("GenerateReport");
		buttonReport.setOnAction(e -> 
		{
			primaryStage.hide();
			File file=new File("src/au/edu/usc/bict_explorer/resources/career.txt");
		try {
			if(file.exists())
			   file.delete();
			else
			   file.createNewFile();
			
			FileWriter fstream = new FileWriter("src/au/edu/usc/bict_explorer/resources/career.txt",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  
			  out.write("Career"+"                              "+"Minor"+"                         "+"Course"+"\n");
			  out.write("____________________________________________________________________________________________________________"+"\n");
			  for(String[] str:content)
			  {
				  
				  for(String str1:str)
				  {
			       out.write(str1);
			       out.write("         ");
			      
				  }
				  out.write("\n");
				  out.write("\n");
			  }
			 out.close();
			} 
	    catch (Exception e1)
			{
				 e1.printStackTrace();
			}
		});
		 gridPane.add(buttonReport, 3, careerRow+5);
		 Scene scene = new Scene(gridPane);
	      primaryStage.setScene(scene);
	      scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	      primaryStage.show();
	}
}
