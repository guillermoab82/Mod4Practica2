package guillermoab.posgrado.unam.mx.practica2.models;

/**
 * Created by GuillermoAB on 29/06/2016.
 */
public class ModelAPP {
    public int id;
    public String cName_App;
    public String cName_Dev;
    public String cApp_Details;
    public int bInstall;
    public int nImageID;

    public ModelAPP(int id, String cName_App,String cName_Dev,String cApp_Details, int bInstall, int nImageID){
        this.id = id;
        this.cName_App = cName_App;
        this.cName_Dev = cName_Dev;
        this.cApp_Details = cApp_Details;
        this.bInstall = bInstall;
        this.nImageID = nImageID;
    }
}
