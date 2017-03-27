package davis.ff.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Player {
	
	//player fields
	private String playerName;
	private String idNumber;
	private String team;
	private String position;
	private int fumblesLost;
	private int fumblesTotal;
	private int passAtts;
	private int passComps;
	private int interceptions;
	private int passTDs;
	private int passTwoPt;
	private int passYDs;
	private int receptions;
	private int recTDs;
	private int recTwoPt;
	private int recYDs;
	private int rushAtts;
	private int rushTDs;
	private int rushTwoPt;
	private int rushYDs;
	private int pointsScored;
	
	private StringProperty playerNameProp;
	private StringProperty idNumberProp;
	private StringProperty teamProp;
	private StringProperty positionProp;
	private IntegerProperty fumblesLostProp;
	private IntegerProperty fumblesTotalProp;
	private IntegerProperty passAttsProp;
	private IntegerProperty passCompsProp;
	private IntegerProperty interceptionsProp;
	private IntegerProperty passTDsProp;
	private IntegerProperty passTwoPtProp;
	private IntegerProperty passYDsProp;
	private IntegerProperty receptionsProp;
	private IntegerProperty recTDsProp;
	private IntegerProperty recTwoPtProp;
	private IntegerProperty recYDsProp;
	private IntegerProperty rushAttsProp;
	private IntegerProperty rushTDsProp;
	private IntegerProperty rushTwoPtProp;
	private IntegerProperty rushYDsProp;
	private IntegerProperty pointsScoredProp;

	


	//default constructor
	public Player() {
		playerName = "A";
		idNumber = "B";
		team = "C";
		position = "D";
		fumblesLost = 0;
		fumblesTotal = 0;
		passAtts = 0;
		passComps = 0;
		interceptions = 0;
		passTDs = 0;
		passTwoPt = 0;
		passYDs = 0;
		receptions = 0;
		recTDs = 0;
		recTwoPt = 0;
		recYDs = 0;
		rushAtts = 0;
		rushTDs = 0;
		rushTwoPt = 0;
		rushYDs = 0;
		pointsScored = 0;
		
		playerNameProp = new SimpleStringProperty(playerName);
		idNumberProp = new SimpleStringProperty(idNumber);
		teamProp = new SimpleStringProperty(team);
		positionProp = new SimpleStringProperty(position);
		fumblesLostProp = new SimpleIntegerProperty(fumblesLost);
		fumblesTotalProp = new SimpleIntegerProperty(fumblesTotal);
		passAttsProp = new SimpleIntegerProperty(passAtts);
		passCompsProp = new SimpleIntegerProperty(passComps);
		interceptionsProp = new SimpleIntegerProperty(interceptions);
		passTDsProp = new SimpleIntegerProperty(passTDs);
		passTwoPtProp = new SimpleIntegerProperty(passTwoPt);
		passYDsProp = new SimpleIntegerProperty(passYDs);
		receptionsProp = new SimpleIntegerProperty(receptions);
		recTDsProp = new SimpleIntegerProperty(recTDs);
		recTwoPtProp = new SimpleIntegerProperty(recTwoPt);
		recYDsProp = new SimpleIntegerProperty(recYDs);
		rushAttsProp = new SimpleIntegerProperty(rushAtts);
		rushTDsProp = new SimpleIntegerProperty(rushTDs);
		rushTwoPtProp = new SimpleIntegerProperty(rushTwoPt);
		rushYDsProp = new SimpleIntegerProperty(rushYDs);
		pointsScoredProp = new SimpleIntegerProperty(pointsScored);
	}

	
	//overloaded constructor
	public Player(String playerName, String idNumber, String team, String position, 
			int fumblesLost, int fumblesTotal, int passAtts, int passComps, 
			int interceptions, int passTDs, int passTwoPt, int passYDs, 
			int receptions, int recTDs, int recTwoPt, int recYDs, int rushAtts, 
			int rushTDs, int rushTwoPt, int rushYDs) {
		
		this.playerNameProp.setValue(this.playerName);
		this.idNumberProp.setValue(this.idNumber);  
		this.teamProp.setValue(this.team); 
		this.positionProp.setValue(this.position);  
		this.fumblesLostProp.set(this.fumblesLost);
		this.fumblesTotalProp.set(this.fumblesTotal);  
		this.passAttsProp.set(this.passAtts); 
		this.passCompsProp.set(this.passComps); 
		this.interceptionsProp.set(this.interceptions);
		this.passTDsProp.set(this.passTDs); 
		this.passTwoPtProp.set(this.passTwoPt); 
		this.passYDsProp.set(this.passYDs); 
		this.receptionsProp.set(this.receptions); 
		this.recTDsProp.set(this.recTDs); 
		this.recTwoPtProp.set(this.recTwoPt); 
		this.recYDsProp.set(this.recYDs); 
		this.rushAttsProp.set(this.rushAtts); 
		this.rushTDsProp.set(this.rushTDs); 
		this.rushTwoPtProp.set(this.rushTwoPt); 
		this.rushYDsProp.set(this.rushYDs); 
		
		
		this.playerNameProp = new SimpleStringProperty(playerName);
		this.idNumberProp = new SimpleStringProperty(idNumber);
		this.teamProp = new SimpleStringProperty(team);
		this.positionProp = new SimpleStringProperty(position);
		this.fumblesLostProp = new SimpleIntegerProperty(fumblesLost);
		this.fumblesTotalProp = new SimpleIntegerProperty(fumblesTotal);
		this.passAttsProp = new SimpleIntegerProperty(passAtts);
		this.passCompsProp = new SimpleIntegerProperty(passComps);
		this.interceptionsProp = new SimpleIntegerProperty(interceptions);
		this.passTDsProp = new SimpleIntegerProperty(passTDs);
		this.passTwoPtProp = new SimpleIntegerProperty(passTwoPt);
		this.passYDsProp = new SimpleIntegerProperty(passYDs);
		this.receptionsProp = new SimpleIntegerProperty(receptions);
		this.recTDsProp = new SimpleIntegerProperty(recTDs);
		this.recTwoPtProp = new SimpleIntegerProperty(recTwoPt);
		this.recYDsProp = new SimpleIntegerProperty(recYDs);
		this.rushAttsProp = new SimpleIntegerProperty(rushAtts);
		this.rushTDsProp = new SimpleIntegerProperty(rushTDs);
		this.rushTwoPtProp = new SimpleIntegerProperty(rushTwoPt);
		this.rushYDsProp = new SimpleIntegerProperty(rushYDs);
	}

	@Override
    public String toString() {
        return playerName+ "{ id Number: "+idNumber+ " Team: "+team+" Total Points Scored: "+pointsScored+" Position: "
        		+position+" Fumbles Lost: "+fumblesLost+" Fumbles Total: "+fumblesTotal+ 
        		" Pass Attempts: "+passAtts+" Pass Completions: "+passComps+" Interceptions: "
        		+interceptions+" Pass TDs: "+passTDs+ " passTwoPt: "+passTwoPt+" passYDs: "
                +passYDs+" receptions: "+receptions+" recTDs: "+recTDs+ 
                		" recTwoPt: "+recTwoPt+" recYDs: "+recYDs+" rushAtts: "+rushAtts+" rushTDs: "+rushTDs+
                		" rushTwoPt: "+rushTwoPt+" RushYDs: "+rushYDs+"}";
    }

	
	
	/**Getter for playerName
	 * @return the playerName
	 */
	public String getPlayerName() {
		return playerName;
	}


	/**Setter for playerName
	 * @param playerName set for the playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	/**Getter for idNumber
	 * @return the idNumber
	 */
	public String getIdNumber() {
		return idNumber;
	}


	/**Setter for idNumber
	 * @param idNumber set for the idNumber
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	/**Getter for team
	 * @return the team
	 */
	public String getTeam() {
		return team;
	}


	/**Setter for team
	 * @param team set for the team
	 */
	public void setTeam(String team) {
		this.team = team;
	}


	/**Getter for position
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}


	/**Setter for position
	 * @param position set for the position
	 */
	public void setPosition(String position) {
		this.position = position;
	}


	/**Getter for fumblesLost
	 * @return the fumblesLost
	 */
	public int getFumblesLost() {
		return fumblesLost;
	}


	/**Setter for fumblesLost
	 * @param fumblesLost set for the fumblesLost
	 */
	public void setFumblesLost(int fumblesLost) {
		this.fumblesLost = fumblesLost;
	}


	/**Getter for fumblesTotal
	 * @return the fumblesTotal
	 */
	public int getFumblesTotal() {
		return fumblesTotal;
	}


	/**Setter for fumblesTotal
	 * @param fumblesTotal set for the fumblesTotal
	 */
	public void setFumblesTotal(int fumblesTotal) {
		this.fumblesTotal = fumblesTotal;
	}


	/**Getter for passAtts
	 * @return the passAtts
	 */
	public int getPassAtts() {
		return passAtts;
	}


	/**Setter for passAtts
	 * @param passAtts set for the passAtts
	 */
	public void setPassAtts(int passAtts) {
		this.passAtts = passAtts;
	}


	/**Getter for passComps
	 * @return the passComps
	 */
	public int getPassComps() {
		return passComps;
	}


	/**Setter for passComps
	 * @param passComps set for the passComps
	 */
	public void setPassComps(int passComps) {
		this.passComps = passComps;
	}


	/**Getter for interceptions
	 * @return the interceptions
	 */
	public int getInterceptions() {
		return interceptions;
	}


	/**Setter for interceptions
	 * @param interceptions set for the interceptions
	 */
	public void setInterceptions(int interceptions) {
		this.interceptions = interceptions;
	}


	/**Getter for passTDs
	 * @return the passTDs
	 */
	public int getPassTDs() {
		return passTDs;
	}


	/**Setter for passTDs
	 * @param passTDs set for the passTDs
	 */
	public void setPassTDs(int passTDs) {
		this.passTDs = passTDs;
	}


	/**Getter for passTwoPt
	 * @return the passTwoPt
	 */
	public int getPassTwoPt() {
		return passTwoPt;
	}


	/**Setter for passTwoPt
	 * @param passTwoPt set for the passTwoPt
	 */
	public void setPassTwoPt(int passTwoPt) {
		this.passTwoPt = passTwoPt;
	}


	/**Getter for passYDs
	 * @return the passYDs
	 */
	public int getPassYDs() {
		return passYDs;
	}


	/**Setter for passYDs
	 * @param passYDs set for the passYDs
	 */
	public void setPassYDs(int passYDs) {
		this.passYDs = passYDs;
	}


	/**Getter for receptions
	 * @return the receptions
	 */
	public int getReceptions() {
		return receptions;
	}


	/**Setter for receptions
	 * @param receptions set for the receptions
	 */
	public void setReceptions(int receptions) {
		this.receptions = receptions;
	}


	/**Getter for recTDs
	 * @return the recTDs
	 */
	public int getRecTDs() {
		return recTDs;
	}


	/**Setter for recTDs
	 * @param recTDs set for the recTDs
	 */
	public void setRecTDs(int recTDs) {
		this.recTDs = recTDs;
	}


	/**Getter for recTwoPt
	 * @return the recTwoPt
	 */
	public int getRecTwoPt() {
		return recTwoPt;
	}


	/**Setter for recTwoPt
	 * @param recTwoPt set for the recTwoPt
	 */
	public void setRecTwoPt(int recTwoPt) {
		this.recTwoPt = recTwoPt;
	}


	/**Getter for recYDs
	 * @return the recYDs
	 */
	public int getRecYDs() {
		return recYDs;
	}


	/**Setter for recYDs
	 * @param recYDs set for the recYDs
	 */
	public void setRecYDs(int recYDs) {
		this.recYDs = recYDs;
	}


	/**Getter for rushAtts
	 * @return the rushAtts
	 */
	public int getRushAtts() {
		return rushAtts;
	}


	/**Setter for rushAtts
	 * @param rushAtts set for the rushAtts
	 */
	public void setRushAtts(int rushAtts) {
		this.rushAtts = rushAtts;
	}


	/**Getter for rushTDs
	 * @return the rushTDs
	 */
	public int getRushTDs() {
		return rushTDs;
	}


	/**Setter for rushTDs
	 * @param rushTDs set for the rushTDs
	 */
	public void setRushTDs(int rushTDs) {
		this.rushTDs = rushTDs;
	}


	/**Getter for rushTwoPt
	 * @return the rushTwoPt
	 */
	public int getRushTwoPt() {
		return rushTwoPt;
	}


	/**Setter for rushTwoPt
	 * @param rushTwoPt set for the rushTwoPt
	 */
	public void setRushTwoPt(int rushTwoPt) {
		this.rushTwoPt = rushTwoPt;
	}


	/**Getter for rushYDs
	 * @return the rushYDs
	 */
	public int getRushYDs() {
		return rushYDs;
	}


	/**Setter for rushYDs
	 * @param rushYDs set for the rushYDs
	 */
	public void setRushYDs(int rushYDs) {
		this.rushYDs = rushYDs;
	}
	
	/**Getter for pointsScored
	 * @return the pointsScored
	 */
	public int getPointsScored() {
		return pointsScored;
	}


	/**Setter for pointsScored
	 * @param pointsScored set for the pointsScored
	 */
	public void setPointsScored(int pointsScored) {
		this.pointsScored = pointsScored;
	}

	
	
	//getters and setter for playerNameProp
	public String getPlayerNameProp() {
        return playerNameProp.get();
    }

    public void setPlayerNameProp(String playerName) {
        this.playerNameProp.set(playerName);
    }

    public StringProperty playerNameProperty() {
        return playerNameProp;
    }
	
    //getters and setter for idNumberProp
    public String getIdNumberProp() {
        return idNumberProp.get();
    }

    public void setIdNumberProp(String idNumber) {
        this.idNumberProp.set(idNumber);
    }

    public StringProperty idNumberProperty() {
        return idNumberProp;
    }
	
  //getters and setter for teamProp
  	public String getTeamProp() {
        return teamProp.get();
    }

    public void setTeamProp(String team) {
        this.teamProp.set(team);
    }

    public StringProperty teamProperty() {
        return teamProp;
    }
      
  //getters and setter for positionProp
  	public String getPositionProp() {
        return positionProp.get();
    }

    public void setPositionProp(String position) {
        this.positionProp.set(position);
    }

    public StringProperty positionProperty() {
        return positionProp;
    }
      
  //getters and setter for fumblesLost
 	public int getFumblesLostProp() {
        return fumblesLostProp.get();
    }

    public void setFumblesLostProp(int fumblesLost) {
        this.fumblesLostProp.set(fumblesLost);
    }

    public IntegerProperty fumblesLostProperty() {
        return fumblesLostProp;
    }
      
  //getters and setter for fumblesTotal
    public int getFumblesTotalProp() {
        return fumblesTotalProp.get();
    }

    public void setFumblesTotalProp(int fumblesTotal) {
        this.fumblesTotalProp.set(fumblesTotal);
    }

    public IntegerProperty fumblesTotalProperty() {
        return fumblesTotalProp;
    }
        
  //getters and setter for passAtts
    public int getPassAttsProp() {
        return passAttsProp.get();
    }

    public void setPassAttsProp(int passAtts) {
        this.passAttsProp.set(passAtts);
    }

    public IntegerProperty passAttsProperty() {
        return passAttsProp;
    }
          
  //getters and setter for passCompsProp
    public int getPassCompsProp() {
        return passCompsProp.get();
    }

    public void setPassCompsProp(int passComps) {
        this.passCompsProp.set(passComps);
    }

    public IntegerProperty passCompsProperty() {
        return passCompsProp;
    }
      
   //getters and setter for interceptionsProp
     public int getInterceptionsProp() {
        return interceptionsProp.get();
    }

     public void setInterceptionsProp(int interceptions) {
        this.interceptionsProp.set(interceptions);
    }

     public IntegerProperty interceptionsProperty() {
        return interceptionsProp;
    }
              
   //getters and setter for passTDsProp
     public int getPassTDsProp() {
        return passTDsProp.get();
    }

     public void setPassTDsProp(int passTDs) {
        this.passTDsProp.set(passTDs);
    }

     public IntegerProperty passTDsProperty() {
        return passTDsProp;
    }
     
   //getters and setter for passTwoPtProp
     public int getPassTwoPtProp() {
        return interceptionsProp.get();
    }

     public void setPassTwoPtProp(int passTwoPt) {
        this.passTwoPtProp.set(passTwoPt);
    }

     public IntegerProperty passTwoPtProperty() {
        return passTwoPtProp;
    }
     
   //getters and setter for passYDsProp
     public int getPassYDsProp() {
        return passYDsProp.get();
    }

     public void setPassYDsProp(int passYDs) {
        this.passYDsProp.set(passYDs);
    }

     public IntegerProperty passYDsProperty() {
        return passYDsProp;
    }
     
   //getters and setter for receptionsProp
     public int getReceptionsProp() {
        return receptionsProp.get();
    }

     public void setReceptionsProp(int receptions) {
        this.receptionsProp.set(receptions);
    }

     public IntegerProperty receptionsProperty() {
        return receptionsProp;
    }
     
   //getters and setter for recTDsProp
     public int getRecTDsProp() {
        return recTDsProp.get();
    }

     public void setRecTDsProp(int recTDs) {
        this.recTDsProp.set(recTDs);
    }

     public IntegerProperty recTDsProperty() {
        return recTDsProp;
    }
     
   //getters and setter for recTwoPtProp
     public int getRecTwoPtProp() {
        return recTwoPtProp.get();
    }

     public void setRecTwoPtProp(int recTwoPt) {
        this.recTwoPtProp.set(recTwoPt);
    }

     public IntegerProperty recTwoPtProperty() {
        return recTwoPtProp;
    }
     
   //getters and setter for recYDsProp
     public int getRecYDsProp() {
        return recYDsProp.get();
    }

     public void setRecYDsProp(int recYDs) {
        this.recYDsProp.set(recYDs);
    }

     public IntegerProperty recYDsProperty() {
        return recYDsProp;
    }
     
   //getters and setter for rushAttsProp
     public int getRushAttsProp() {
        return rushAttsProp.get();
    }

     public void setRushAttsProp(int rushAtts) {
        this.rushAttsProp.set(rushAtts);
    }

     public IntegerProperty rushAttsProperty() {
        return rushAttsProp;
    }
     
   //getters and setter for rushTDsProp
     public int getRushTDsProp() {
        return rushTDsProp.get();
    }

     public void setRushTDsProp(int rushTDs) {
        this.rushTDsProp.set(rushTDs);
    }

     public IntegerProperty rushTDsProperty() {
        return rushTDsProp;
    }
     
   //getters and setter for rushTwoPtProp
     public int getRushTwoPtProp() {
        return rushTwoPtProp.get();
    }

     public void setRushTwoPtProp(int rushTwoPt) {
        this.rushTwoPtProp.set(rushTwoPt);
    }

     public IntegerProperty rushTwoPtProperty() {
        return rushTwoPtProp;
    }
     
   //getters and setter for rushYDsProp
     public int getRushYDsProp() {
        return rushYDsProp.get();
    }

     public void setRushYDsProp(int rushYDs) {
        this.rushYDsProp.set(rushYDs);
    }

     public IntegerProperty rushYDsProperty() {
        return rushYDsProp;
    }
     
     /**Getter for pointsScoredProp
 	 * @return the pointsScoredProp
 	 */
 	public IntegerProperty getPointsScoredProp() {
 		return pointsScoredProp;
 	}


 	/**Setter for pointsScoredProp
 	 * @param pointsScoredProp set for the pointsScoredProp
 	 */
 	public void setPointsScoredProp(int pointsScored) {
 		this.pointsScoredProp.set(pointsScored);
 	}
 	
 	public IntegerProperty pointsScoredProperty() {
 		return pointsScoredProp;
 	}
 	
 	 
      
      
      
      
      
      
      
      
      
      
}
