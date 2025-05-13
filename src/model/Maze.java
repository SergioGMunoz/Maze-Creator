package model;
import java.util.ArrayList;

public class Maze {
    private Integer id;
    private String name;
    Integer num_col;
    Integer num_row;
    private Integer numCrocodiles;
    private Integer numMedKit ;
    private Integer dmgCrocodiles ;
    private Integer hpMedKit;
    private Integer timeQuestions;
    private Integer dmgQuestions;
    private Boolean enableHelp;
    
   	// Lista con todos los laberintos
    public static ArrayList<Maze> mazes = new ArrayList<>();

	public Maze(Integer id, String name, Integer num_col, Integer num_row, Integer numCrocodiles, Integer numMedKit,
			Integer dmgCrocodiles, Integer hpMedKit, Integer timeQuestions, Integer dmgQuestions, Boolean enableHelp) {
		this.id = id;
		this.name = name;
		this.num_col = num_col;
		this.num_row = num_row;
		this.numCrocodiles = numCrocodiles;
		this.numMedKit = numMedKit;
		this.dmgCrocodiles = dmgCrocodiles;
		this.hpMedKit = hpMedKit;
		this.timeQuestions = timeQuestions;
		this.dmgQuestions = dmgQuestions;
		this.enableHelp = enableHelp;
	}

    

}
