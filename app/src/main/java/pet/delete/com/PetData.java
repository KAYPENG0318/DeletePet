package pet.delete.com;

/**
 * Created by user on 2018/3/7.
 */

public class PetData {

    private   String petName;
    private String date;
    public PetData(){

    }

    public PetData(String petName, String date){
        this.petName = petName;
        this.date = date;
    }
    public String getPetName(){
        return petName;
    }
    public void setPetName(String petName){
        this.petName = petName;
    }
    public String getdate(){
        return date;
    }
    public void setdate(String date){
        this.date = date;
    }


}
