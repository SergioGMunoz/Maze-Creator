package model;
import java.util.ArrayList;

public class Maze {
    Integer id;
    String name;
    Integer numCol;
    Integer numRow;
    Integer numCrocodiles;
    Integer numMedKit ;
    Integer dmgCrocodiles ;
    Integer hpMedKit;
    Integer timeQuestions;
    Integer dmgQuestions;
    Boolean enableHelp;
    
   	// Lista con todos los laberintos
    public static ArrayList<Maze> mazes = new ArrayList<>();

	public Maze(Integer id, String name, Integer numCol, Integer numRow, Integer numCrocodiles, Integer numMedKit,
			Integer dmgCrocodiles, Integer hpMedKit, Integer timeQuestions, Integer dmgQuestions, Boolean enableHelp) {
		this.id = id;
		this.name = name;
		this.numCol = numCol;
		this.numRow = numRow;
		this.numCrocodiles = numCrocodiles;
		this.numMedKit = numMedKit;
		this.dmgCrocodiles = dmgCrocodiles;
		this.hpMedKit = hpMedKit;
		this.timeQuestions = timeQuestions;
		this.dmgQuestions = dmgQuestions;
		this.enableHelp = enableHelp;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumCol() {
		return numCol;
	}

	public void setNumCol(Integer numCol) {
		this.numCol = numCol;
	}

	public Integer getNumRow() {
		return numRow;
	}

	public void setNumRow(Integer numRow) {
		this.numRow = numRow;
	}

	public Integer getNumCrocodiles() {
		return numCrocodiles;
	}

	public void setNumCrocodiles(Integer numCrocodiles) {
		this.numCrocodiles = numCrocodiles;
	}

	public Integer getNumMedKit() {
		return numMedKit;
	}

	public void setNumMedKit(Integer numMedKit) {
		this.numMedKit = numMedKit;
	}

	public Integer getDmgCrocodiles() {
		return dmgCrocodiles;
	}

	public void setDmgCrocodiles(Integer dmgCrocodiles) {
		this.dmgCrocodiles = dmgCrocodiles;
	}

	public Integer getHpMedKit() {
		return hpMedKit;
	}

	public void setHpMedKit(Integer hpMedKit) {
		this.hpMedKit = hpMedKit;
	}

	public Integer getTimeQuestions() {
		return timeQuestions;
	}

	public void setTimeQuestions(Integer timeQuestions) {
		this.timeQuestions = timeQuestions;
	}

	public Integer getDmgQuestions() {
		return dmgQuestions;
	}

	public void setDmgQuestions(Integer dmgQuestions) {
		this.dmgQuestions = dmgQuestions;
	}

	public Boolean getEnableHelp() {
		return enableHelp;
	}

	public void setEnableHelp(Boolean enableHelp) {
		this.enableHelp = enableHelp;
	}
	
	

    

}
