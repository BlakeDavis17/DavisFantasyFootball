package davis.ff.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import davis.ff.Main;
import davis.ff.model.Player;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

public class RootLayoutController {
	
	//reference to main
	private Main main;
	
	private static List<Player> playerList = getPlayerListFromExcel();
	
	static private ObservableList<Player> playerListObs = FXCollections.observableArrayList(playerList);
	
	//filter variables
	private String nameF;
	private String posF;
	private String teamF;

	@FXML
	private TableView<Player> playerTable;
	@FXML
	private TableColumn<Player, String> playerNameColumn;
	@FXML
	private TableColumn<Player, String> teamColumn;
	@FXML
	private TableColumn<Player, String> positionColumn;
	@FXML
	private TableColumn<Player, Number> pointsScoredColumn;
//	@FXML
//	private TableColumn<Player, Number> rankColumn;
	@FXML
	private TableColumn<Player, Number> rushAttsColumn;
	@FXML
	private TableColumn<Player, Number> rushYDsColumn;
	@FXML
	private TableColumn<Player, Number> rushTDsColumn;
	@FXML
	private TableColumn<Player, Number> receptionsColumn;
	@FXML
	private TableColumn<Player, Number> recYDsColumn;
	@FXML
	private TableColumn<Player, Number> recTDsColumn;
	@FXML
	private TableColumn<Player, Number> fumblesColumn;
	@FXML
	private TableColumn<Player, Number> passCompsColumn;
	@FXML
	private TableColumn<Player, Number> passAttsColumn;
	@FXML
	private TableColumn<Player, Number> passYDsColumn;
	@FXML
	private TableColumn<Player, Number> passTDsColumn;
	@FXML
	private TableColumn<Player, Number> interceptionsColumn;
	
	//name filter
	@FXML
	private TextField filterField;
	
	//position filters
	@FXML
	private RadioButton qbBtn;
	@FXML
	private RadioButton rbBtn;
	@FXML
	private RadioButton wrBtn;
	@FXML
	private RadioButton teBtn;
	@FXML
	private RadioButton flexBtn;

	
	@FXML
	private ChoiceBox<String> teamPicker;
	
	
	//constructor
	public RootLayoutController() {
	}
	
	
	@FXML
	private void initialize() {
		//assign each column to the corresponding field
		playerNameColumn.setCellValueFactory(
				cellData -> cellData.getValue().playerNameProperty());
		teamColumn.setCellValueFactory(
				cellData -> cellData.getValue().teamProperty());
		positionColumn.setCellValueFactory(
				cellData -> cellData.getValue().positionProperty());
		pointsScoredColumn.setCellValueFactory(
			    cellData -> cellData.getValue().pointsScoredProperty());
		rushAttsColumn.setCellValueFactory(
				cellData -> cellData.getValue().rushAttsProperty());
		rushYDsColumn.setCellValueFactory(
				cellData -> cellData.getValue().rushYDsProperty());
		rushTDsColumn.setCellValueFactory(
				cellData -> cellData.getValue().rushTDsProperty());
		receptionsColumn.setCellValueFactory(
				cellData -> cellData.getValue().receptionsProperty());
		recYDsColumn.setCellValueFactory(
				cellData -> cellData.getValue().recYDsProperty());
		recTDsColumn.setCellValueFactory(
				cellData -> cellData.getValue().recTDsProperty());
		fumblesColumn.setCellValueFactory(
				cellData -> cellData.getValue().fumblesLostProperty());
		passCompsColumn.setCellValueFactory(
				cellData -> cellData.getValue().passCompsProperty());
		passAttsColumn.setCellValueFactory(
				cellData -> cellData.getValue().passAttsProperty());
		passYDsColumn.setCellValueFactory(
				cellData -> cellData.getValue().passYDsProperty());
		passTDsColumn.setCellValueFactory(
				cellData -> cellData.getValue().passTDsProperty());
		interceptionsColumn.setCellValueFactory(
				cellData -> cellData.getValue().interceptionsProperty());
			
		
		
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(player -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if ((player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE")) && player.getPlayerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});

		
		filteredData.setPredicate(player -> {

			if (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
					player.getPositionProp().equals("TE")) {
				return true; // Filter matches position.
			} 
			return false; // Does not match.
		});


		teamPicker.setItems(FXCollections.observableArrayList(
			    "All", "Ari", "Atl", "Bal", "Buf", "Car", "Chi", "Cin", "Cle", "Dal", "Den", "Det", "GB", "Hou", "Ind", "Jax", "KC", "LA", "Mia", "Min", "NE", "NO"
			    , "NYG", "NYJ", "Oak", "Phi", "Pit", "SD", "Sea", "SF", "TB", "Ten", "Was")
			);
		
		
		teamPicker.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

				teamFilter();
//				switch (teamPicker.getItems().get((Integer) number2)) {
//
//				case "All" :  {
//					playerTable.setItems(filteredData);
//					break;
//				}
//				case "Ari" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("ARI") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Atl" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("ATL") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Bal" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("BAL") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Buf" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("BUF") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Car" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("CAR") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Chi" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("CHI") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Cin" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("CIN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Cle" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("CLE") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Dal" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("DAL") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Den" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("DEN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Det" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("DET") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "GB" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("GB") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Hou" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("HOU") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Ind" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("IND") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Jax" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("JAX") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "KC" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("KC") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "LA" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("LA") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Mia" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("MIA") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Min" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("MIN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "NE" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("NE") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "NO" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("NO") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "NYG" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("NYG") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "NYJ" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("NYJ") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Oak" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("OAK") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Phi" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("PHI") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "Pit" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("PIT") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "SD" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("SD") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Sea" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("SEA") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "SF" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("SF") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});
//					break;
//				}
//				case "TB" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("TB") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Ten" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("TEN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				case "Was" :  {
//					filteredData.setPredicate(player -> {
//
//						if (player.getTeamProp().equals("WAS") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
//								player.getPositionProp().equals("TE"))) {
//							return true; // Filter matches position.
//						} 
//						return false; // Does not match.
//					});    	  				
//					break;
//				}
//				}
			}
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// 5. Add sorted (and filtered) data to the table.
		playerTable.setItems(sortedData);

	}
	
	//returns the list as an observable list of persons
		public static ObservableList<Player> getObsPlayerData() {
			return playerListObs;
		}
	
	
	
	@FXML
	private void showQBs(){
		// Wrap the ObservableList in a FilteredList
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);
		
		filteredData.setPredicate(player -> {

			if (player.getPositionProp().equals("QB")) {
				return true; // Filter matches position.
			} 
			return false; // Does not match.
		});

		// 2. Set the filter Predicate whenever the filter changes.
				filterField.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(player -> {
						// If filter text is empty, display all persons.
						if (newValue == null || newValue.isEmpty() && player.getPositionProp().equals("QB")) {
							return true;
						}

						// Compare first name and last name of every person with filter text.
						String lowerCaseFilter = newValue.toLowerCase();

						if (player.getPositionProp().equals("QB") && player.getPlayerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches first name.
						} 
						return false; // Does not match.
					});
				});


		// Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);
		
		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Add sorted and filtered data to the table.
		playerTable.setItems(sortedData);

	}
	
	@FXML
	private void showRBs(){
		// Wrap the ObservableList in a FilteredList
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);

		filteredData.setPredicate(player -> {

			if (player.getPositionProp().equals("RB")) {
				return true; // Filter matches position.
			} 
			return false; // Does not match.
		});
		
		// 2. Set the filter Predicate whenever the filter changes.
				filterField.textProperty().addListener((observable, oldValue, newValue) -> {
					filteredData.setPredicate(player -> {
						// If filter text is empty, display all persons.
						if (newValue == null || newValue.isEmpty() && player.getPositionProp().equals("RB")) {
							return true;
						}

						// Compare first name and last name of every person with filter text.
						String lowerCaseFilter = newValue.toLowerCase();

						if (player.getPositionProp().equals("RB") && player.getPlayerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
							return true; // Filter matches first name.
						} 
						return false; // Does not match.
					});
				});

		// Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Add sorted and filtered data to the table.
		playerTable.setItems(sortedData);
		
	}
	
	@FXML
	private void showWRs(){
		// Wrap the ObservableList in a FilteredList
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);

		filteredData.setPredicate(player -> {

			if (player.getPositionProp().equals("WR")) {
				return true; // Filter matches position.
			} 
			return false; // Does not match.
		});

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(player -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty() && player.getPositionProp().equals("WR")) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (player.getPositionProp().equals("WR") && player.getPlayerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Add sorted and filtered data to the table.
		playerTable.setItems(sortedData);
		
	}
	
	@FXML
	private void showTEs(){
		// Wrap the ObservableList in a FilteredList
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);

		filteredData.setPredicate(player -> {

			if (player.getPositionProp().equals("TE")) {
				return true; // Filter matches position.
			} 
			return false; // Does not match.
		});

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(player -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty() && player.getPositionProp().equals("TE")) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if (player.getPositionProp().equals("TE") && player.getPlayerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Add sorted and filtered data to the table.
		playerTable.setItems(sortedData);
		
	}
	
	@FXML
	private void showFlex(){
		// Wrap the ObservableList in a FilteredList
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);

		filteredData.setPredicate(player -> {

			if (player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
					player.getPositionProp().equals("TE")) {
				return true; // Filter matches position.
			} 
			return false; // Does not match.
		});

		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(player -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty() && (player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
					player.getPositionProp().equals("TE"))) {
					return true;
				}

				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();

				if ((player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE")) && player.getPlayerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches first name.
				} 
				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Add sorted and filtered data to the table.
		playerTable.setItems(sortedData);
		
	}
	
	@FXML
	private void teamFilter(){
		// Wrap the ObservableList in a FilteredList
				FilteredList<Player> filteredData = new FilteredList<>(playerListObs, p -> true);
				
				
		switch (teamPicker.getValue()) {

		case "All" :  {
			playerTable.setItems(filteredData);
			break;
		}
		case "Ari" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("ARI") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Atl" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("ATL") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Bal" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("BAL") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Buf" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("BUF") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Car" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("CAR") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Chi" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("CHI") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Cin" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("CIN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Cle" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("CLE") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Dal" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("DAL") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Den" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("DEN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Det" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("DET") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "GB" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("GB") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Hou" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("HOU") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Ind" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("IND") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Jax" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("JAX") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "KC" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("KC") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "LA" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("LA") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Mia" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("MIA") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Min" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("MIN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "NE" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("NE") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "NO" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("NO") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "NYG" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("NYG") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "NYJ" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("NYJ") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Oak" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("OAK") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Phi" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("PHI") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "Pit" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("PIT") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "SD" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("SD") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Sea" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("SEA") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "SF" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("SF") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});
			break;
		}
		case "TB" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("TB") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Ten" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("TEN") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		case "Was" :  {
			filteredData.setPredicate(player -> {

				if (player.getTeamProp().equals("WAS") && (player.getPositionProp().equals("QB") || player.getPositionProp().equals("RB") || player.getPositionProp().equals("WR") || 
						player.getPositionProp().equals("TE"))) {
					return true; // Filter matches position.
				} 
				return false; // Does not match.
			});    	  				
			break;
		}
		}
	
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);
		
		
// Bind the SortedList comparator to the TableView comparator.
sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

// 5. Add sorted (and filtered) data to the table.
playerTable.setItems(sortedData);

		
	}
	
	private static final String FILE_PATH = "RegularSeason2016.xlsx";
	
	public static List<Player> getPlayerListFromExcel() {
		List<Player> playerList = new ArrayList<Player>();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			//looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<?> rowIterator = sheet.iterator();

				//iterating over each row
				while (rowIterator.hasNext() ) {

					Player player = new Player();
					Row row = (Row) rowIterator.next();
					Iterator<?> cellIterator = row.cellIterator();

					//Iterating over each cell (column wise) in a particular row.
					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();
						
						if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
							switch (cell.getColumnIndex()) {
							
							case 0: 
								player.setPlayerName(String.valueOf(cell.getStringCellValue()));
								player.setPlayerNameProp(player.getPlayerName());
							break;
							
							case 1:
								player.setIdNumber(String.valueOf(cell.getStringCellValue()));
								player.setIdNumberProp(player.getIdNumber());
							break;
							
							case 3:
								player.setTeam(String.valueOf(cell.getStringCellValue()));
								player.setTeamProp(player.getTeam());
							break;

							case 4:
								player.setPosition(String.valueOf(cell.getStringCellValue()));
								player.setPositionProp(player.getPosition());
							break;
							}




							//The Cell Containing numeric value will contain marks
						} else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
							
							switch (cell.getColumnIndex()) {

							case 10:
								player.setFumblesLost(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setFumblesLostProp(player.getFumblesLost());
							break;

							case 12:
								player.setFumblesTotal(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setFumblesTotalProp(player.getFumblesTotal());
							break;
							

							case 29:
								player.setPassAtts(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setPassAttsProp(player.getPassAtts());
							break;	
							

							case 30:
								player.setPassComps(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setPassCompsProp(player.getPassComps());
							break;

							case 31:
								player.setInterceptions(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setInterceptionsProp(player.getInterceptions());
							break;

							case 32:
								player.setPassTDs(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setPassTDsProp(player.getPassTDs());
							break;

							case 34:
								player.setPassTwoPt(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setPassTwoPtProp(player.getPassTwoPt());
							break;

							case 35:
								player.setPassYDs(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setPassYDsProp(player.getPassYDs());
							break;

							case 46:
								player.setReceptions(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setReceptionsProp(player.getReceptions());
							break;

							case 49:
								player.setRecTDs(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRecTDsProp(player.getRecTDs());
							break;

							case 51:
								player.setRecTwoPt(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRecTwoPtProp(player.getRecTwoPt());
							break;

							case 52:
								player.setRecYDs(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRecYDsProp(player.getRecYDs());
							break;

							case 53:
								player.setRushAtts(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRushAttsProp(player.getRushAtts());
							break;

							case 56:
								player.setRushTDs(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRushTDsProp(player.getRushTDs());
							break;

							case 58:
								player.setRushTwoPt(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRushTwoPtProp(player.getRushTwoPt());
							break;

							case 59:
								player.setRushYDs(Integer.valueOf((int) cell.getNumericCellValue()));
								player.setRushYDsProp(player.getRushYDs());
							break;

						} 
						//make into final values\/	
						player.setPointsScored((player.getPassYDs()/25)+(player.getPassTDs()*4)+(player.getInterceptions()*-2)+(player.getPassTwoPt()*2)
								+(player.getRushYDs()/10)+(player.getRushTDs()*6)+(player.getRushTwoPt()*2)+(player.getRecYDs()/10)+(player.getRecTDs()*6)+(player.getRecTwoPt()*2));
						player.setPointsScoredProp(player.getPointsScored());
						}
					}

					//end iterating a row, add all the elements of a row in list
					playerList.add(player);
				}
			}

			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playerList;
	}
	
	
	
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMain(Main main) {
        this.main = main;

        // Add observable list data to the table
        //playerTable.setItems(main.getObsPlayerData());
        
        //teamPicker.setItems(main.get);
    }
	
	private void filterIt(){
		nameF = filterField.getText();
		
		ToggleGroup group = new ToggleGroup();
		qbBtn.setToggleGroup(group);
		rbBtn.setToggleGroup(group);
		wrBtn.setToggleGroup(group);
		teBtn.setToggleGroup(group);
		flexBtn.setToggleGroup(group);

		
		if (qbBtn.isPressed()){
			posF = "QB";
		}
		if (rbBtn.isPressed()){
			posF = "RB";
		}
		if (wrBtn.isPressed()){
			posF = "WR";
		}
		if (teBtn.isPressed()){
			posF = "TE";
		}
		if (flexBtn.isPressed()){
			posF = "FLEX";
		}


		
	}
}
