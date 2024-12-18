// Class Catatan
public class Catatan {
    private String Catatan;

    public Catatan(String Catatan){
        this.Catatan = Catatan;
    }

    public String getCatatan(){
        return Catatan;
    }

    public void setCatatan(String Catatan){
        this.Catatan = Catatan;
    }

    @Override
    public String toString(){
        return "Catatan: " + Catatan;
    }
}
