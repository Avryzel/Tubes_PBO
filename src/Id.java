// class ID
public class Id {
    private String Id;
    private String Password;
    private String NamaAPK;

    public Id(String Id, String Password, String NamaAPK){
        this.Id = Id;
        this.Password = Password;
        this.NamaAPK = NamaAPK;
    }

    public String getId(){
        return Id;
    }

    public void setId(String Id){
        this.Id = Id;
    }

    public String getPassword(){
        return Password;
    }

    public void setPassword(String Password){
        this.Password = Password;
    }

    public String getNamaApk(){
        return NamaAPK;
    }

    public void setNamaApk(String NamaAPK){
        this.NamaAPK = NamaAPK;
    }

    @Override
    public String toString(){
        return "(Nama Aplikasi : " + NamaAPK + ") \n(Id : " + Id + ") \n(Password : " + Password + ")";
    }
}
