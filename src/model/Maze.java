package model;
import java.util.ArrayList;

public class Maze {
    private Integer id;
    private String name;
    private Integer height;
    private Integer width;
    private Integer numCrocodiles;
    private Integer numMedKit ;
    private Integer dmgCrocodiles ;
    private Integer hpMedKit;
    private Integer timeQuestions;
    private Integer dmgQuestions;
    private Boolean enableHelp;
    
   	// Lista con todos los laberintos
    public static ArrayList<Maze> mazes = new ArrayList<>();

	public Maze(Integer id, String name, Integer height, Integer width, Integer numCrocodiles, Integer numMedKit,
			Integer dmgCrocodiles, Integer hpMedKit, Integer timeQuestions, Integer dmgQuestions, Boolean enableHelp) {
		super();
		this.id = id;
		this.name = name;
		this.height = height;
		this.width = width;
		this.numCrocodiles = numCrocodiles;
		this.numMedKit = numMedKit;
		this.dmgCrocodiles = dmgCrocodiles;
		this.hpMedKit = hpMedKit;
		this.timeQuestions = timeQuestions;
		this.dmgQuestions = dmgQuestions;
		this.enableHelp = enableHelp;
	}

    

    // Getters y setters si los necesitas
}
