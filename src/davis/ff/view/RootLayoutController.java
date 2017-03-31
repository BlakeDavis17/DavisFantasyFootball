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
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;

public class RootLayoutController {

	// Reference to main
	private Main main;

	private static List<Player> playerList = getPlayerListFromExcel();

	static private ObservableList<Player> playerListObs = FXCollections.observableArrayList(playerList);

	// Filter variables
	private String nameF;
	private String posF = null;
	private String teamF;
	// Boolean values state whether of not the value is matched
	private Boolean matchName = false;
	private Boolean matchPos = false;
	private Boolean matchTeam = false;

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

	// Name filter
	@FXML
	private TextField filterField;

	// Position filters
	@FXML
	private RadioButton allBtn;
	@FXML
	private RadioButton qbBtn;
	@FXML
	private RadioButton rbBtn;
	@FXML
	private RadioButton wrBtn;
	@FXML
	private RadioButton teBtn;

	@FXML
	private ChoiceBox<String> teamPicker;

	@FXML
	private Button filterButton;


	// Constructor
	public RootLayoutController() {
	}


	@FXML
	private void initialize() {
		// Assign each column to the corresponding field
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

		teamPicker.setItems(FXCollections.observableArrayList(
				"ALL", "ARI", "ATL", "BAL", "BUF", "CAR", "CHI", "CIN", "CLE", "DAL", "DEN", "DET", "GB", "HOU", "IND", "JAX", "KC", "LA", "MIA", "MIN", "NE", "NO"
				, "NYG", "NYJ", "OAK", "PHI", "PIT", "SD", "SEA", "SF", "TB", "TEN", "WAS")
				);
		teamPicker.setValue("ALL");


		// Wrap the ObservableList in a FilteredList (initially display all data).
		FilteredList<Player> filteredData = new FilteredList<>(playerListObs, player -> (player.getPosition().equals("QB") || player.getPosition().equals("RB") || 
				player.getPosition().equals("WR") || player.getPosition().equals("TE")));


		// Wrap the FilteredList in a SortedList. 
		SortedList<Player> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

		// Add sorted (and filtered) data to the table.
		playerTable.setItems(sortedData);

	}
	
	
	// Filter method
	/**
	 * Filter method called by the filter button
	 */
	@FXML
	private void filterIt(){
		// Set nameFilterValue to filterField text input
		nameF = filterField.getText().toLowerCase();
		
		// Set teamFilterValue to the value of the teamPicker
		teamF = teamPicker.getValue().toUpperCase();

		// Toggle group of position buttons
		ToggleGroup group = new ToggleGroup();
		allBtn.setToggleGroup(group);
		qbBtn.setToggleGroup(group);
		rbBtn.setToggleGroup(group);
		wrBtn.setToggleGroup(group);
		teBtn.setToggleGroup(group);

		// Buttons set positionFilterValue to coordinating position
		if (allBtn.isSelected()){
			posF = null;
		}
		if (qbBtn.isSelected()){
			posF = "QB";
		}
		if (rbBtn.isSelected()){
			posF = "RB";
		}
		if (wrBtn.isSelected()){
			posF = "WR";
		}
		if (teBtn.isSelected()){
			posF = "TE";
		}

		
		if (posF != null){
			// Wrap the ObservableList in a FilteredList
			FilteredList<Player> filteredData = new FilteredList<>(playerListObs, player -> (player.getPlayerName().toLowerCase().contains(nameF) && player.getPosition().equals(posF) && player.getTeam().equals(teamF)));
			
			// Wrap the FilteredList in a SortedList. 
			SortedList<Player> sortedData = new SortedList<>(filteredData);

			// Bind the SortedList comparator to the TableView comparator.
			sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

			// Add sorted and filtered data to the table.
			playerTable.setItems(sortedData);
		}
		
		if (posF == null){
			// Wrap the ObservableList in a FilteredList
			FilteredList<Player> filteredData = new FilteredList<>(playerListObs, player -> (player.getPlayerName().toLowerCase().contains(nameF) && 
					(player.getPosition().equals("QB") || player.getPosition().equals("RB") || player.getPosition().equals("WR") || player.getPosition().equals("TE")) 
					&& player.getTeam().equals(teamF)));
			
			// Wrap the FilteredList in a SortedList. 
			SortedList<Player> sortedData = new SortedList<>(filteredData);

			// Bind the SortedList comparator to the TableView comparator.
			sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

			// Add sorted and filtered data to the table.
			playerTable.setItems(sortedData);
		}

		if (teamF == "ALL"){
			// Wrap the ObservableList in a FilteredList
			FilteredList<Player> filteredData = new FilteredList<>(playerListObs, player -> (player.getPlayerName().toLowerCase().contains(nameF) && 
					player.getPosition().equals(posF)));
			// Wrap the FilteredList in a SortedList. 
			SortedList<Player> sortedData = new SortedList<>(filteredData);

			// Bind the SortedList comparator to the TableView comparator.
			sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

			// Add sorted and filtered data to the table.
			playerTable.setItems(sortedData);
		}
		
		if (posF == null && teamF == "ALL"){
			// Wrap the ObservableList in a FilteredList
			FilteredList<Player> filteredData = new FilteredList<>(playerListObs, player -> (player.getPlayerName().toLowerCase().contains(nameF) && 
					(player.getPosition().equals("QB") || player.getPosition().equals("RB") || player.getPosition().equals("WR") || player.getPosition().equals("TE"))));
			// Wrap the FilteredList in a SortedList. 
			SortedList<Player> sortedData = new SortedList<>(filteredData);

			// Bind the SortedList comparator to the TableView comparator.
			sortedData.comparatorProperty().bind(playerTable.comparatorProperty());

			// Add sorted and filtered data to the table.
			playerTable.setItems(sortedData);
		}

	}

	// Returns the list as an observable list of persons
	public static ObservableList<Player> getObsPlayerData() {
		return playerListObs;
	}

	// Sets file path to Excel file with player data in it
	private static final String FILE_PATH = "RegularSeason2016.xlsx";

	/**
	 * Uses Apache POI to take Excel data and create player objects and put those into a list of players
	 * @return playerList
	 */
	public static List<Player> getPlayerListFromExcel() {
		List<Player> playerList = new ArrayList<Player>();

		FileInputStream fis = null;

		try {
			fis = new FileInputStream(FILE_PATH);

			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);

			int numberOfSheets = workbook.getNumberOfSheets();

			// Looping over each workbook sheet
			for (int i = 0; i < numberOfSheets; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<?> rowIterator = sheet.iterator();

				// Iterating over each row
				while (rowIterator.hasNext() ) {

					Player player = new Player();
					Row row = (Row) rowIterator.next();
					Iterator<?> cellIterator = row.cellIterator();

					// Iterating over each cell (column wise) in a particular row.
					while (cellIterator.hasNext()) {

						Cell cell = (Cell) cellIterator.next();
						
						// The cell containing string value will contain corresponding player string value
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




						// The cell containing numeric value will contain corresponding player int value
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
							//converting statistics to fantasy points	
							player.setPointsScored((player.getPassYDs()/25)+(player.getPassTDs()*4)+(player.getInterceptions()*-2)+(player.getPassTwoPt()*2)
									+(player.getRushYDs()/10)+(player.getRushTDs()*6)+(player.getRushTwoPt()*2)+(player.getRecYDs()/10)+(player.getRecTDs()*6)+(player.getRecTwoPt()*2));
							player.setPointsScoredProp(player.getPointsScored());
						}
					}

					// End iterating a row, add all the elements of a row in list
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
	 * @param main
	 *
	 */
	public void setMain(Main main) {
		this.main = main;

	}


}
